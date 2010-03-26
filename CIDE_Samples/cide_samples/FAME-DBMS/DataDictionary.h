#ifndef DATADICTIONARY_H_
#define DATADICTIONARY_H_

/*
*  14.02.2008 Syed Saif Ur Rahman <srahman@iti.cs.uni-magdeburg.de>; Christian Hübner <ch@ch82.info>: Initial version 
*/

#include  "include.h"
#include  "Page.h"
#include  "PageBuffer.h"
#include "RECORD.h"

/*
 * DataDictionary hold the meta information for database, table etc information
 */

class DataDictionary
{

private:
	PageBuffer& pb;
    
    PID firstpg;
    static const size_t FIRSTPAGE_KEY = 1;

	PID freeListHead;
    static const size_t FREELIST_KEY = 2;
    
    uint16_t allocatedPageCount;
    static const size_t ALLOC_PAGECOUNT_KEY = 3;

	uint16_t usedPageCount;
    static const size_t USED_PAGECOUNT_KEY = 4;  
    
public:

	static void OnUsedPageCountChanged(void* eventSink, size_t newCount)
	{
		DataDictionary* _this = static_cast<DataDictionary*>(eventSink);
		_this->OnUsedPageCountChanged_Handler(newCount);
	}
	
	void OnUsedPageCountChanged_Handler(size_t count)
	{
		if(count == 1)
		{
			firstpg = EMPTY_PAGE;	
		}
	}

	DataDictionary(PageBuffer& _pb) : pb(_pb)
	{  
		firstpg = EMPTY_PAGE; //Temporary to be delete
        freeListHead = EMPTY_PAGE;
        allocatedPageCount = 0;
		usedPageCount = 0;
	}
	
	bool Create() 
	{
		typedef PageBuffer dummy;
		pb.SetConfigData(0, 0, EMPTY_PAGE, OnUsedPageCountChanged, this);

		return (NULL != pb.GetNewPage());
	}
	
	bool Open() 
	{
		pb.SetConfigData(0, 0, EMPTY_PAGE, OnUsedPageCountChanged, this);
		
		if(!Read())
			return false;
		
		pb.SetConfigData(GetPageCount(), GetUsedPageCount(), GetFreeListHead(), OnUsedPageCountChanged, this);
        
        return true;
	}

    bool Close()
    {
    	//debug_print("\n DD::Close?!?! \n");
    	
    	uint16_t pageCount;
		uint16_t usedPageCount;
        PID freeList;       
        
        pb.GetConfigData(pageCount, usedPageCount, freeList);
                
        SetPageCount(pageCount);
		SetUsedPageCount(usedPageCount);
        SetFreeListHead(freeList);  
        
        return Write(); return true;
    }
    
    
	PID GetStartPage()
	{
		return firstpg;
	}
	
    void SetStartPage(PID firstpg)
	{
		this->firstpg = firstpg;
	}
    
    
    uint16_t GetPageCount()
    {
        return allocatedPageCount;
    }
    
    void SetPageCount(uint16_t count)
    {
        this->allocatedPageCount = count;
    }


	uint16_t GetUsedPageCount()
    {
        return usedPageCount;
    }
    
    void SetUsedPageCount(uint16_t count)
    {
        this->usedPageCount = count;
    }
       
    
    PID GetFreeListHead()
    {
        return freeListHead;
    }
    
    void SetFreeListHead(PID listHead)
    {
        this->freeListHead = listHead;
    }
    
private:
    
    bool Read()
    {
        Page* ddPage = pb.GetPage(1);
        if(ddPage == NULL)
        {
        	debug_print("\n Unable to read page 1");
        	return false;
        }
        
        //ddPage->PrintAllTuples();
        
        RECORD rec;
        rec.key = FIRSTPAGE_KEY;
        if(!ddPage->GetTuple(rec))
            return false;            
        firstpg = ReadPidFromMem(rec.value);
        

		rec.key = FREELIST_KEY;
        if(!ddPage->GetTuple(rec))
            return false;
        freeListHead = ReadPidFromMem(rec.value);
        

        rec.key = ALLOC_PAGECOUNT_KEY;
        if(!ddPage->GetTuple(rec))
            return false;
        allocatedPageCount = ReadBigEndian16FromMem(rec.value);


		rec.key = USED_PAGECOUNT_KEY;
         if(!ddPage->GetTuple(rec))
            return false;
        usedPageCount = ReadBigEndian16FromMem(rec.value);
		
		return true;
    }
    
    bool Write()
    {   
	    Page* ddPage = pb.GetPage(1, true, true);
	    if(ddPage == NULL)
        {
        	debug_print("\n Unable to read page 1");
        	return false;
        }
	    
	    //debug_print("\nDD 0 \n");
	    
		if(ddPage == NULL) return false;
		
		//debug_print("\nDD 1 \n");

		ddPage->PageC();
		ddPage->SetID(1);
		
		//debug_print("\nDD 2 \n");

        byte pid_buffer[sizeof(PID)];
        
        RECORD rec;
        rec.key = FIRSTPAGE_KEY;
        rec.size = sizeof(PID);
        rec.value = pid_buffer;
        WritePidToMem(pid_buffer, firstpg);
        ddPage->RemoveTuple(rec);
        if(!ddPage->PutTuple(rec))
            return false;         
        //ddPage->PrintAllTuples();
        
        //debug_print("\nDD 3 \n");

		rec.key = FREELIST_KEY;
        rec.size = sizeof(PID);
        rec.value = pid_buffer;
        WritePidToMem(pid_buffer, freeListHead);
        ddPage->RemoveTuple(rec);
        if(!ddPage->PutTuple(rec))
            return false;      
        //ddPage->PrintAllTuples();

		//debug_print("\nDD 4 \n");
		
		byte alloc_buffer[sizeof(allocatedPageCount)];
		rec.key = ALLOC_PAGECOUNT_KEY;
        rec.size = sizeof(allocatedPageCount);
        rec.value = alloc_buffer;
        WriteBigEndian16ToMem(alloc_buffer, allocatedPageCount);
		ddPage->RemoveTuple(rec);
        if(!ddPage->PutTuple(rec))
            return false; 

		//debug_print("\nDD 5 \n");
        
        byte count_buffer[sizeof(usedPageCount)];
        rec.key = USED_PAGECOUNT_KEY;
        rec.size = sizeof(usedPageCount);
        rec.value = count_buffer;
        WriteBigEndian16ToMem(count_buffer, usedPageCount);
      
      	//debug_print("\nDD 6 \n");
      
        ddPage->RemoveTuple(rec);
        if(!ddPage->PutTuple(rec))
            return false; 
        //ddPage->PrintAllTuples();
        
        //debug_print("\nDD 7 \n");
        
        ddPage->SetModified(true);
          
        return true;
    }
};
#endif