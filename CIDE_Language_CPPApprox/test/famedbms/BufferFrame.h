#ifndef BufferFrame_h__included
#define BufferFrame_h__included

//Layer BufferManager

#line 1 "C:/workspace/FAME/FAMEDB/src/BufferManager/BufferFrame.h"
/*
*  14.02.2008 Christian Hübner <ch@ch82.info>: Initial version 
*/


//Layer BufferManager.PageFind.Hash

#line 1 "C:/workspace/FAME/FAMEDB/src/BufferManager/PageFind/Hash/BufferFrame.h"
/*
*  14.02.2008 Christian Hübner <ch@ch82.info>: Initial version 
*/


//Layer BufferManager.PageReplace.LRU

#line 1 "C:/workspace/FAME/FAMEDB/src/BufferManager/PageReplace/LRU/BufferFrame.h"
/*
*  14.02.2008 Christian Hübner <ch@ch82.info>: Initial version 
*/


#include "POSITION.h"
#include "Page.h"

#line 5 "C:/workspace/FAME/FAMEDB/src/BufferManager/BufferFrame.h"
class BufferFrame {
//**** Layer BufferManager ****
private:
#line 5 "C:/workspace/FAME/FAMEDB/src/BufferManager/BufferFrame.h"


private:
    bool pinned;
    Page page;
    
public:

    __forceinline void 
#line 13 "C:/workspace/FAME/FAMEDB/src/BufferManager/BufferFrame.h"
BufferManager_BufferFrame() 
    { 
        Init();
    }
    
    void Init() 
    {
        pinned = false;
    }
    
    bool IsPinned() const
    {
        return pinned;
    }
    
    void SetPinned(bool pin)
    {
        pinned = pin;
    }

    Page& GetPage()
    {
        return page;
    }

//**** Layer BufferManager.PageFind.Hash ****
private:
#line 5 "C:/workspace/FAME/FAMEDB/src/BufferManager/PageFind/Hash/BufferFrame.h"


public:

    POSITION NextHashLookupEntry;


//**** Layer BufferManager.PageReplace.LRU ****
private:
#line 5 "C:/workspace/FAME/FAMEDB/src/BufferManager/PageReplace/LRU/BufferFrame.h"


public:

    BufferFrame()
    {
	BufferManager_BufferFrame();
#line 10 "C:/workspace/FAME/FAMEDB/src/BufferManager/PageReplace/LRU/BufferFrame.h"

        PrevLRUReplaceEntry = POSITION::INVALID;
        NextLRUReplaceEntry = POSITION::INVALID;
    }

    POSITION PrevLRUReplaceEntry;
    POSITION NextLRUReplaceEntry;


#line 37 "C:/workspace/FAME/FAMEDB/src/BufferManager/BufferFrame.h"
};
#endif //BufferFrame_h__included

