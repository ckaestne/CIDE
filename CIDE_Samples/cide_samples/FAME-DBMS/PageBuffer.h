#ifndef PAGEBUFFER_H_
#define PAGEBUFFER_H_

/*
*  14.02.2008 Christian Hübner <ch@ch82.info>: Initial version 
*/

#include "include.h"
#include "Page.h"
#include "FrameAllocator.h"
#include "PageLookup.h"
#include "StorageDevice.h"

//InMemory
//Max 4 pages for btnode
#define MAX_PAGES (4096 / PAGE_SIZE)

Page pg[MAX_PAGES];
//InMemory End

class PageBuffer {

public:
	typedef void (*F_OnCountChanged)(void* evenSink, size_t count);

private:
	FrameAllocator allocator;
    PageLookup lookup;
    StorageDevice device;
    
    //Temporary code
    //PID tmpPID; //temporary can be deleted    
    //Page pg[2];
    //Temporary code end
    
    uint16_t pageCount;
	uint16_t usedPageCount;
    PID freeListHead;
	F_OnCountChanged fOnUsedPageCountChanged;
	void* evenSink;
    
	//InMemory
    PID tmpPID;
	//InMemory End
    
    //LRU
	private:

    POSITION headLRUList;
    POSITION tailLRUList;
    //LRU
    
public:


	//To be updated manually in generated configuration
	//_forceinline void PageBuffer_Sub()
	void PageBuffer_Sub()
	{
		//printf("\nPage Buffer");
		
		//fflush(stdout);
		
		//Temporary code
		//tmpPID = 0;
		//Temporary code end
        
		pageCount = 0;
		usedPageCount = 0;
        freeListHead = EMPTY_PAGE;
		fOnUsedPageCountChanged = NULL;

		//InMemory
		tmpPID = 1;
		//InMemory End

	}
    
    void SetConfigData(uint16_t pageCount, uint16_t usedPageCount, PID freeListHead, F_OnCountChanged fOnUsedPageCountChanged, void* evenSink)
    {
		this->usedPageCount = usedPageCount;
        this->pageCount = pageCount;
        this->freeListHead = freeListHead;
		this->fOnUsedPageCountChanged = fOnUsedPageCountChanged;
		this->evenSink = evenSink;
    }
    
    void GetConfigData(uint16_t& pageCount, uint16_t& usedPageCount, PID& freeListHead)
    {
		usedPageCount = this->usedPageCount;
        pageCount = this->pageCount;
        freeListHead = this->freeListHead;
    }
    
    bool CreateDatabase(const char* filename)
    {
        if(!device.Create(filename))
            return false;
                
        return true;
    }
    
    bool OpenDatabase(const char* filename)
    {
        return device.Open(filename);
    }
    
    void CloseDatabase_Super()
    {
        SaveModifiedPages();
        
        lookup.Clear();
        allocator.Clear();
        
        device.Close();
    }

     Page* GetPage(PID pid, bool readFromDev = true, bool delay = false) //Commented temporary 
    {
    	 //InMemory
     	if(pid > MAX_PAGES )
     	{
     		debug_print1("\nNo page with the given ID: %d", pid);
     		return NULL;
     	}
     	return &pg[pid - 1];
     	//InMemory

     	//temporary code for testign above layers
    	//return &pg[pid - 1];
    	//temporary code end
        
        Page* resPage = NULL;
        POSITION resPos = lookup.Get(pid);
        
        if(resPos != POSITION::INVALID)
        {
            BufferFrame* frame = allocator.GetFrame(resPos);
            if(frame != NULL)
                resPage = &frame->GetPage();
            
#ifdef btnode3
             if(delay) NutSleep(10); // yes we need this!!! don't ask! actually, 4 is enough.
#endif
        }
        else
        {
            // requested page is not in the buffer, fetch it from StorageDevice:
            
			BufferFrame* frame = NULL;
			
            if(!allocator.IsExhausted())
            {
                resPos = allocator.AllocFrame();
                frame = allocator.GetFrame(resPos);
                if(frame!=NULL) frame->GetPage().PageC();
            }
            else 
            {
                resPos = GetSwapFrame();
                frame = allocator.GetFrame(resPos); 
                if(!OnSwapFrame(frame))
                    return NULL;
            }
            if(!frame) return NULL;

			resPage = &frame->GetPage();  
			
			if(readFromDev)
			{
	            byte buffer[PAGE_SIZE];        
	            if(!ReadPage(pid, buffer))
	                return NULL;
	                              
	            resPage->ReadFromByteStream(buffer); 
			}
            resPage->SetID(pid);
                       
            lookup.Put(pid, resPos);
            
        }
        
        OnFrameTouched(resPos);
        
        //debug_print("\nReturn of GetPage %X !\n", resPage);
        
        return resPage;    
    }
    
    

    void PinPage(Page* page) 
    {
        BufferFrame* frame = GetFrameFromPage(page);
        if(frame != NULL)
            frame->SetPinned(true);
    }
    
    void UnpinPage(Page* page) 
    {
        BufferFrame* frame = GetFrameFromPage(page);
        if(frame != NULL)
            frame->SetPinned(false);
    }

    Page* GetNewPage() 
    {
    	
    	//InMemory
        Page* result = NULL;
        debug_print("\nIn GetNewPage");
        if(freeListHead != EMPTY_PAGE)
        {
            debug_print("\nIn freeListHead check");
            PID id = freeListHead;
            debug_print1("\nReturned PID : %d", id);
            result = GetPage(id);
			if(result == NULL)
				return NULL;
            
			PID next = result->GetNextPageID();
            debug_print("\n next set");
            freeListHead = next;
            result->PageC();
            result->SetID(id);
            debug_print("\n constructor again called");
            return result;
        }
        else
        {
        
	    	tmpPID++;
	    	if(tmpPID > MAX_PAGES )
	    	{
	    		debug_print("\nNo more space available");
	    		return NULL;
	    	}
	    	pg[tmpPID - 1].PageC();
	    	pg[tmpPID - 1].SetID(tmpPID);
	        return &pg[tmpPID - 1];
        }
    	//InMemory End
    	
    	//debug_print("\nGetNewPage()\n");
        //temporary implementation to check the above layer code, can be updated/deleted
    	/*
    	tmpPID++;
    	if(tmpPID > 3 )
    	{
    		return NULL;
    	}
		//debug_print("\nPID to be set = %d", tmpPID);
    	pg[tmpPID - 1].PageC();
    	pg[tmpPID - 1].SetID(tmpPID);
        return &pg[tmpPID - 1];
        */    
        //temporary code end

        //Page* result = NULL;
        
        if(freeListHead != EMPTY_PAGE)
        {
        	debug_print("\nfreeListHead != EMPTY_PAGE\n");
        				
            PID id = freeListHead;
            result = GetPage(id);
			if(result == NULL)
				return NULL;
				            
			PID next = result->GetNextPageID();
            freeListHead = next;
            result->PageC();
        }
        else
        {
        	debug_print("\nresult->PageC()\n");
        				
            if(!device.AllocNewBlock())
                return NULL;
            
			PID newPID = pageCount+1;
            result = GetPage(newPID, false);
            if(result == NULL)
				return NULL;

            result->PageC();
            result->SetID(newPID);
            ++pageCount;
        }

		++usedPageCount;
		
		fOnUsedPageCountChanged(evenSink, usedPageCount);
		debug_print("\nGetNewPage OK!\n");
		return result;
    }

    
    //this should be public to be used by storage manager class
    void ReleasePage(Page* page) 
    {
        if(!page) return;
        
        page->SetPrevPageID(EMPTY_PAGE);
        page->SetNextPageID(freeListHead);
        page->SetModified(true);
        freeListHead = page->GetID();

		--usedPageCount;
		fOnUsedPageCountChanged(evenSink, usedPageCount);
    }

private:
	
    BufferFrame* GetFrameFromPage(Page* page)
    {
        if(page == NULL) return NULL;
        POSITION pos = lookup.Get(page->GetID());
		return allocator.GetFrame(pos);
    }

    bool OnSwapFrame_Super(BufferFrame* frame)
    {
        if(frame == NULL) return false;
      
        Page& page = frame->GetPage();

		debug_print1("\nOnSwapFrame id: %u ", page.GetID());

        if(!SavePage(page))
            return false;
        
        lookup.Remove(page.GetID());
        return true;
    }

    bool SavePage(Page& page)
    {
        if(!page.IsModified()) return true; // no need to save
           
        byte buffer[PAGE_SIZE];
        page.WriteToByteStream(buffer);
        
        if(!WritePage(page.GetID(), buffer))
            return false;
        
        page.SetModified(false);        

        return true;
    }
           
    bool ReadPage(PID pid, byte* buffer) 
    {
        if(StorageDevice::BLOCK_SIZE == PAGE_SIZE)
        {
            const BID bid = (BID)pid;         
            return device.ReadBlock(bid, buffer);
        }
        else if(StorageDevice::BLOCK_SIZE < PAGE_SIZE)
        {
            const size_t blocksPerPage = PAGE_SIZE / StorageDevice::BLOCK_SIZE;
            if(blocksPerPage*StorageDevice::BLOCK_SIZE != PAGE_SIZE)
                return false; // Error: PAGE_SIZE is not a multiple of BLOCK_SIZE!
            const BID bidStart = pid * blocksPerPage;
            for(size_t i = 0; i < blocksPerPage; ++i)
            {
                if(!device.ReadBlock(bidStart+i, buffer+(i*StorageDevice::BLOCK_SIZE)))
                    return false;
            }
            return true;
        }
        else 
        {   // a block contains several pages
            
            const size_t pagesPerBlock = StorageDevice::BLOCK_SIZE / PAGE_SIZE;
            if(pagesPerBlock*PAGE_SIZE != StorageDevice::BLOCK_SIZE)
                return false; // Error: BLOCK_SIZE is not a multiple of PAGE_SIZE!
            const BID bid = pid / pagesPerBlock;
            
            byte block_buffer[StorageDevice::BLOCK_SIZE];
            if(!device.ReadBlock(bid, block_buffer))
                return false;
                
            const size_t offset = (pid % pagesPerBlock) * PAGE_SIZE;
            for(size_t i = 0; i < PAGE_SIZE; ++i)
                buffer[i] = block_buffer[offset+i];
                
            return true;        
        }    
    }
    
    bool WritePage(PID pid, const byte* buffer) 
    {
        if(StorageDevice::BLOCK_SIZE == PAGE_SIZE)
        {
            const BID bid = (BID)pid;         
            return device.WriteBlock(bid, buffer);
        }
        else if(StorageDevice::BLOCK_SIZE < PAGE_SIZE)
        {
            const size_t blocksPerPage = PAGE_SIZE / StorageDevice::BLOCK_SIZE;
            if(blocksPerPage*StorageDevice::BLOCK_SIZE != PAGE_SIZE)
                return false; // Error: PAGE_SIZE is not a multiple of BLOCK_SIZE!
            const BID bidStart = pid * blocksPerPage;
            for(size_t i = 0; i < blocksPerPage; ++i)
            {
                if(!device.WriteBlock(bidStart+i, buffer+(i*StorageDevice::BLOCK_SIZE)))
                    return false;
            }
            return true;
        }
        else 
        {   // a block contains several pages
            
            const size_t pagesPerBlock = StorageDevice::BLOCK_SIZE / PAGE_SIZE;
            if(pagesPerBlock*PAGE_SIZE != StorageDevice::BLOCK_SIZE)
                return false; // Error: BLOCK_SIZE is not a multiple of PAGE_SIZE!
            const BID bid = pid / pagesPerBlock;
            
            byte block_buffer[StorageDevice::BLOCK_SIZE];
            if(!device.ReadBlock(bid, block_buffer))
                return false;
                
            const size_t offset = (pid % pagesPerBlock) * PAGE_SIZE;
            for(size_t i = 0; i < PAGE_SIZE; ++i)
                block_buffer[offset+i] = buffer[i];
                
            if(!device.WriteBlock(bid, block_buffer))
                return false;
                
            return true;        
        }    
    }
    
    /////////////////////////////////////////////////////////////////////////////////////    
        
    POSITION GetSwapFrame_Sub() const
    {
        return POSITION::INVALID; // real implementation must be provided via a Page Replace Feature
    }
    
    /////////////////////////////////////////////////////////////////////////////////////    
        
    bool SaveModifiedPages()
    {
        POSITION pos = allocator.GetFirstPos();
        while(pos != POSITION::INVALID)
        {
            BufferFrame* frame = allocator.GetFrame(pos);
            if(frame == NULL || !SavePage(frame->GetPage()))
                return false;
            pos = allocator.GetNextPos(pos);
        }

		return true;
    }
    
/////////////////////////////////////////////////////////////////////////////////////
    
    //LFU
public: 
       
    bool OnSwapFrame(BufferFrame* frame)
    {
        if(!OnSwapFrame_Super(frame))
            return false;
        
        frame->LFUReplaceCounter = 0;
        return true;
    }
    
    POSITION GetSwapFrame() const
    { GetSwapFrame_Sub();
        size_t minValue = (size_t)-1; // init with maximum value of size_t
        POSITION minPos = POSITION::INVALID;
        
        POSITION pos = allocator.GetFirstPos();
        while(pos != POSITION::INVALID)
        {
            const BufferFrame* frame = allocator.GetFrame(pos);
            if(frame != NULL && frame->LFUReplaceCounter < minValue && !frame->IsPinned())
            {
                minValue = frame->LFUReplaceCounter;
                minPos = pos;
            }
            
            pos = allocator.GetNextPos(pos);
        }
        return minPos;
    }
    
    void CloseDatabase()
    {
        CloseDatabase_Super();
        
        POSITION pos = allocator.GetFirstPos();
        while(pos != POSITION::INVALID)
        {
            BufferFrame* frame = allocator.GetFrame(pos);
            frame->LFUReplaceCounter = 0;
            pos = allocator.GetNextPos(pos);
        }
    }
    
    void OnFrameTouched(POSITION pos)
    {
        BufferFrame* frame = allocator.GetFrame(pos);
        if(frame != NULL) 
        {
            frame->LFUReplaceCounter++;
        }
    }
    //LFU End

    //LRU
public:

    PageBuffer()  : lookup(allocator)
    {
    	PageBuffer_Sub();
        headLRUList = POSITION::INVALID;
        tailLRUList = POSITION::INVALID;
    }
    
    void CloseDatabase()
    {
    	CloseDatabase_Super();
        
        headLRUList = POSITION::INVALID;
        tailLRUList = POSITION::INVALID;
    }
       
    POSITION GetSwapFrame()
    { GetSwapFrame_Sub();
        const size_t maxRetries = allocator.CountAllocatedFrames();
        size_t retries = 0;
        
        while(retries < maxRetries)
        {
            POSITION res = tailLRUList;    
            
            const BufferFrame* frame = allocator.GetFrame(res);
            if(frame==NULL) return POSITION::INVALID;
                        
            if(frame->IsPinned()) 
                OnFrameTouched(res);
            else
                return res;
                
            ++retries;
        }
        return POSITION::INVALID;
    }
    
    void OnFrameTouched(POSITION pos)
    {
        BufferFrame* frame = allocator.GetFrame(pos);
        if(frame != NULL && pos != headLRUList) 
        {
            if(tailLRUList == POSITION::INVALID)
            {
                tailLRUList = pos;
            }
            else if(tailLRUList == pos)
            {
                tailLRUList = frame->PrevLRUReplaceEntry;
            }
            
            BufferFrame* prevFrame = allocator.GetFrame(frame->PrevLRUReplaceEntry);
            if(prevFrame != NULL) prevFrame->NextLRUReplaceEntry = frame->NextLRUReplaceEntry;
            
            BufferFrame* nextFrame = allocator.GetFrame(frame->NextLRUReplaceEntry);
            if(nextFrame != NULL) nextFrame->PrevLRUReplaceEntry = frame->PrevLRUReplaceEntry;
            
            BufferFrame* newSecond = allocator.GetFrame(headLRUList);
            if(newSecond != NULL) newSecond->PrevLRUReplaceEntry = pos;
            
            frame->NextLRUReplaceEntry = headLRUList;
            headLRUList = pos;
            frame->PrevLRUReplaceEntry = POSITION::INVALID;           
        }
    }
    //LRU End

//LFU
public:

    PageBuffer()  : lookup(allocator)
    {
    	PageBuffer_Sub();
    }
//LFU End
    
    //lRU
    public: 
       
    bool OnSwapFrame(BufferFrame* frame)
    {
        if(!OnSwapFrame_Super(frame))
            return false;
       
        return true;
    }
    
    //LRU End
};

#endif