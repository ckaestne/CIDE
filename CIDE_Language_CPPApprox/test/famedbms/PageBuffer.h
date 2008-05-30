#ifndef PageBuffer_h__included
#define PageBuffer_h__included

//Layer BufferManager

#line 1 "C:/workspace/FAME/FAMEDB/src/BufferManager/PageBuffer.h"
/*
*  14.02.2008 Christian Hübner <ch@ch82.info>: Initial version 
*/

#include "include.h"


//Layer BufferManager.PageReplace.LRU

#line 1 "C:/workspace/FAME/FAMEDB/src/BufferManager/PageReplace/LRU/PageBuffer.h"
/*
*  14.02.2008 Christian Hübner <ch@ch82.info>: Initial version 
*/


#include "PageLookup.h"
#include "FrameAllocator.h"
#include "POSITION.h"
#include "StorageDevice.h"
class BufferFrame;
class Page;

#line 7 "C:/workspace/FAME/FAMEDB/src/BufferManager/PageBuffer.h"
class PageBuffer {
//**** Layer BufferManager ****
private:
#line 7 "C:/workspace/FAME/FAMEDB/src/BufferManager/PageBuffer.h"


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
    
public:

	__forceinline void 
#line 30 "C:/workspace/FAME/FAMEDB/src/BufferManager/PageBuffer.h"
BufferManager_PageBuffer() {
		
#line 32 "C:/workspace/FAME/FAMEDB/src/BufferManager/PageBuffer.h"
//printf("\nPage Buffer");
		
		//fflush(stdout);
		
		//Temporary code
		//tmpPID = 0;
		//Temporary code end
        
		pageCount = 0;
		usedPageCount = 0;
        freeListHead = EMPTY_PAGE;
		fOnUsedPageCountChanged = NULL;
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
    
    __forceinline 
#line 75 "C:/workspace/FAME/FAMEDB/src/BufferManager/PageBuffer.h"
void BufferManager_CloseDatabase()
    {
        SaveModifiedPages();
        
        lookup.Clear();
        allocator.Clear();
        
        device.Close();
    }

     Page* GetPage(PID pid, bool readFromDev = true, bool delay = false) //Commented temporary 
    {
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

        Page* result = NULL;
        
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

    bool OnSwapFrame(BufferFrame* frame)
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
    
    __forceinline 
#line 360 "C:/workspace/FAME/FAMEDB/src/BufferManager/PageBuffer.h"
void BufferManager_OnFrameTouched(POSITION pos)
    {
      // real implementation must be provided via a Page Replace Feature
    }
        
    __forceinline 
#line 365 "C:/workspace/FAME/FAMEDB/src/BufferManager/PageBuffer.h"
POSITION BufferManager_GetSwapFrame() const
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
    

//**** Layer BufferManager.PageReplace.LRU ****
private:
#line 5 "C:/workspace/FAME/FAMEDB/src/BufferManager/PageReplace/LRU/PageBuffer.h"


private:

    POSITION headLRUList;
    POSITION tailLRUList;
    
public:

    PageBuffer() 
    
#line 30 "C:/workspace/FAME/FAMEDB/src/BufferManager/PageBuffer.h"
: lookup(allocator)
	
#line 15 "C:/workspace/FAME/FAMEDB/src/BufferManager/PageReplace/LRU/PageBuffer.h"
{
	BufferManager_PageBuffer();
#line 15 "C:/workspace/FAME/FAMEDB/src/BufferManager/PageReplace/LRU/PageBuffer.h"

        headLRUList = POSITION::INVALID;
        tailLRUList = POSITION::INVALID;
    }
    
    void CloseDatabase()
    {
        BufferManager_CloseDatabase();
        
        headLRUList = POSITION::INVALID;
        tailLRUList = POSITION::INVALID;
    }
       
    POSITION GetSwapFrame()
    {
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

#line 388 "C:/workspace/FAME/FAMEDB/src/BufferManager/PageBuffer.h"
};
#endif //PageBuffer_h__included

