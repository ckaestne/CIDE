#ifndef POSITION_h__included
#define POSITION_h__included

//Layer BufferManager.MemoryAlloc.Static

#line 1 "C:/workspace/FAME/FAMEDB/src/BufferManager/MemoryAlloc/Static/POSITION.h"
/*
*  14.02.2008 Christian Hübner <ch@ch82.info>: Initial version 
*/

#include "include.h"

/*
This class is not much different than a "typedef size_t POSITION;"
But we need a class for the FOP transform.
*/


#line 11 "C:/workspace/FAME/FAMEDB/src/BufferManager/MemoryAlloc/Static/POSITION.h"
class POSITION {
//**** Layer BufferManager.MemoryAlloc.Static ****
private:
#line 11 "C:/workspace/FAME/FAMEDB/src/BufferManager/MemoryAlloc/Static/POSITION.h"


private:
     size_t Value;
     
public:

    static const size_t INVALID = 0;

    POSITION() : Value(INVALID) {}
    POSITION(size_t v) : Value(v) {}
    
/*    POSITION& operator=(const POSITION& v)
    {
        Value = v.Value;
        return *this;
    }*/
    
    operator size_t() const
    {
        return Value;
    }   

};
#endif //POSITION_h__included

