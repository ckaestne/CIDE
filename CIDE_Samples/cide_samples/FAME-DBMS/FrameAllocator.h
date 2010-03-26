#ifndef FRAMEALLOCATOR_H_
#define FRAMEALLOCATOR_H_

/*
*  14.02.2008 Christian Hübner <ch@ch82.info>: Initial version 
*/
#include "BufferFrame.h"

class FrameAllocator {
        
private:

    static const size_t USED_MARKER = (size_t)-1; // use the maximum value of size_t as a special constant

    struct SLOT 
    {
        BufferFrame frame;
        POSITION nextFreeSlot;
    };
    
    static const size_t SlotCount = PAGE_BUFFER_MAX_SIZE / sizeof(SLOT);
    
    SLOT slots[SlotCount];
    POSITION headFreeSlots;
    
    size_t allocatedFrames;

public:

    FrameAllocator()
    {
        Clear();
    }
    
    POSITION GetFirstPos() const
    {
        for(size_t i = 0; i < SlotCount; ++i)
        {
            if(slots[i].nextFreeSlot == USED_MARKER)
                return i+1;
        }
        return POSITION::INVALID;
    }
    
    POSITION GetNextPos(POSITION pos) const
    {
        if(pos == POSITION::INVALID) return POSITION::INVALID;
        
        for(size_t i = pos; i < SlotCount; ++i)
        {
            if(slots[i].nextFreeSlot == USED_MARKER)
                return i+1;
        }
        return POSITION::INVALID;
    }
    
    BufferFrame* GetFrame(POSITION pos)
    {
        if(pos == POSITION::INVALID || pos > SlotCount) return NULL;
        return &slots[pos-1].frame;
    }
    
    const BufferFrame* GetFrame(POSITION pos) const
    {
        if(pos == POSITION::INVALID || pos > SlotCount) return NULL;
        return &slots[pos-1].frame;
    }
    
    size_t CountAllocatedFrames() 
    {
        return allocatedFrames;
    }
     
    bool IsExhausted() const 
    {
        return headFreeSlots == POSITION::INVALID;
    }
    
    POSITION AllocFrame()
    {    
        POSITION res = headFreeSlots;
        
        if(res != POSITION::INVALID)
        {
            SLOT& slot = slots[res-1];
            headFreeSlots = slot.nextFreeSlot;
            slot.nextFreeSlot = USED_MARKER;
            slot.frame.Init();
            ++allocatedFrames;
        }
        
        return res;
    }
    
    void FreeFrame(POSITION pos)
    {
        if(pos == POSITION::INVALID || pos > SlotCount) return;
    
        slots[pos-1].nextFreeSlot = headFreeSlots;
        headFreeSlots = pos;
        
        if(allocatedFrames>0) --allocatedFrames;
    }    

    void Clear()
    {
        headFreeSlots = 1;
        for(size_t i = 0; i < SlotCount; ++i)
            slots[i].nextFreeSlot = i+2;
            
        slots[SlotCount-1].nextFreeSlot = POSITION::INVALID;
        
        allocatedFrames = 0;
    }
};
#endif