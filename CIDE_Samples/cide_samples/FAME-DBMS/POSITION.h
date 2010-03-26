#ifndef POSITION_H_
#define POSITION_H_
/*
*  14.02.2008 Christian Hübner <ch@ch82.info>: Initial version 
*/

#include "include.h"

/*
This class is not much different than a "typedef size_t POSITION;"
But we need a class for the FOP transform.
*/
class POSITION {

private:
     size_t Value;
     
public:

    static const size_t INVALID = 0;

    POSITION() : Value(INVALID) {}
    POSITION(size_t v) : Value(v) {}
    
    POSITION& operator=(const POSITION& v)
    {
        Value = v.Value;
        return *this;
    }
    
    operator size_t() const
    {
        return Value;
    }   
};

// 
#endif