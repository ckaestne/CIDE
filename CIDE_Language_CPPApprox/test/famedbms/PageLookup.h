#ifndef PageLookup_h__included
#define PageLookup_h__included

//Layer BufferManager.PageFind.Hash

#line 1 "C:/workspace/FAME/FAMEDB/src/BufferManager/PageFind/Hash/PageLookup.h"
/*
*  14.02.2008 Christian Hübner <ch@ch82.info>: Initial version 
*/

#include "include.h"



#include "FrameAllocator.h"
#include "POSITION.h"
#include "BufferFrame.h"

#line 8 "C:/workspace/FAME/FAMEDB/src/BufferManager/PageFind/Hash/PageLookup.h"
class PageLookup {
//**** Layer BufferManager.PageFind.Hash ****
private:
#line 8 "C:/workspace/FAME/FAMEDB/src/BufferManager/PageFind/Hash/PageLookup.h"


private:

    static const size_t TableSize = 3 * (PAGE_BUFFER_MAX_SIZE / sizeof(BufferFrame));
    POSITION table[TableSize];
    FrameAllocator& allocator;

public:

    PageLookup(FrameAllocator& alloc) : allocator(alloc)
    {
        Clear();
    }

    void Put(PID id, POSITION assocPos)
    {
		typedef FrameAllocator dummy;
        BufferFrame* frame = allocator.GetFrame(assocPos);
        if(frame == NULL) return;
        
        POSITION* pos = &table[id % TableSize];
        frame->NextHashLookupEntry = *pos; 
        *pos = assocPos;      
    }
    
    POSITION Get(PID id) const
    {
        POSITION pos = table[id % TableSize];
        
        while(pos != POSITION::INVALID)
        {
            BufferFrame* frame = allocator.GetFrame(pos);
            
            if(frame->GetPage().GetID() == id)
                return pos;
                
            pos = frame->NextHashLookupEntry;        
        }
        
        return POSITION::INVALID;
    }
    
    void Remove(PID id)
    {
        POSITION* pos = &table[id % TableSize];
        
        while(*pos != POSITION::INVALID)
        {
            BufferFrame* frame = allocator.GetFrame(*pos);
            
            if(frame->GetPage().GetID() == id)
            {
                *pos = frame->NextHashLookupEntry;
                return;
            }
                
            pos = &frame->NextHashLookupEntry;
        }
    }
    
    void Clear() 
    {
        for(size_t i = 0; i < TableSize; ++i)
            table[i] = POSITION::INVALID;
    }

#line 74 "C:/workspace/FAME/FAMEDB/src/BufferManager/PageFind/Hash/PageLookup.h"
};
#endif //PageLookup_h__included

