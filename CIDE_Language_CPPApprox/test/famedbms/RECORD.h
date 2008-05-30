#ifndef RECORD_h__included
#define RECORD_h__included

//Layer StorageManager.Page.PageO

#line 1 "C:/workspace/FAME/FAMEDB/src/StorageManager/Page/PageO/RECORD.h"
/*
*  14.02.2008 Syed Saif Ur Rahman <srahman@iti.cs.uni-magdeburg.de>: Initial version 
*/

#include "include.h"

/*
 * Basic record structure
 */



#line 11 "C:/workspace/FAME/FAMEDB/src/StorageManager/Page/PageO/RECORD.h"
class RECORD
{
//**** Layer StorageManager.Page.PageO ****
private:
#line 12 "C:/workspace/FAME/FAMEDB/src/StorageManager/Page/PageO/RECORD.h"

public:
 int16_t key;
 uint16_t size;
 byte* value;

#line 17 "C:/workspace/FAME/FAMEDB/src/StorageManager/Page/PageO/RECORD.h"
};
#endif //RECORD_h__included

