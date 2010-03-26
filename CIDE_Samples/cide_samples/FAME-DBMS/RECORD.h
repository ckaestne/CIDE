#ifndef RECORD_H_
#define RECORD_H_
/*
*  14.02.2008 Syed Saif Ur Rahman <srahman@iti.cs.uni-magdeburg.de>: Initial version 
*/

#include "include.h"

/*
 * Basic record structure
 */

class RECORD
{
public:
 int16_t key;
 uint16_t size;
 byte* value;
};
#endif