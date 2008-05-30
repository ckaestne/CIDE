#ifndef DataAndAccessManager_h__included
#define DataAndAccessManager_h__included

//Layer DataAndAccessManager

#line 1 "C:/workspace/FAME/FAMEDB/src/DataAndAccessManager/DataAndAccessManager.h"
/*
*  14.02.2008 Syed Saif Ur Rahman <srahman@iti.cs.uni-magdeburg.de>: Initial version 
*/

#include "include.h"

/*
 * This class exposes the access api for the client
 * */


//Layer DataAndAccessManager.WriteSupport

#line 1 "C:/workspace/FAME/FAMEDB/src/DataAndAccessManager/WriteSupport/DataAndAccessManager.h"
/*
*  14.02.2008 Syed Saif Ur Rahman <srahman@iti.cs.uni-magdeburg.de>: Initial version 
*/

#include "include.h"

/*
 * This refinement for DataAndAccessManager class adds the write support functionality
 */


#include "StorageManager.h"
class RECORD;

#line 11 "C:/workspace/FAME/FAMEDB/src/DataAndAccessManager/DataAndAccessManager.h"
class DataAndAccessManager
{
//**** Layer DataAndAccessManager ****
private:
#line 12 "C:/workspace/FAME/FAMEDB/src/DataAndAccessManager/DataAndAccessManager.h"

private:
	StorageManager sm; //Storage manager is responsible for all storage related functionalities
public:
	__forceinline void 
#line 16 "C:/workspace/FAME/FAMEDB/src/DataAndAccessManager/DataAndAccessManager.h"
DataAndAccessManager_DataAndAccessManager()
	{
	}
    
    bool CreateDatabase(const char * filename)
	{
		return sm.CreateDatabase(filename);
	}

	bool OpenDatabase(const char * filename)
	{
		return sm.OpenDatabase(filename);
	}

	void CloseDatabase()
	{
		sm.CloseDatabase();
	}

	bool GetData(RECORD& r)
	{
		return sm.GetData(r);
	}
	bool PrintStatus()
	{
		return sm.PrintStatus();
	}	

//**** Layer DataAndAccessManager.WriteSupport ****
private:
#line 12 "C:/workspace/FAME/FAMEDB/src/DataAndAccessManager/WriteSupport/DataAndAccessManager.h"

private:

public:
	DataAndAccessManager()
	{
	DataAndAccessManager_DataAndAccessManager();
#line 17 "C:/workspace/FAME/FAMEDB/src/DataAndAccessManager/WriteSupport/DataAndAccessManager.h"

		//debug_print("\nDataAndAccessManager Write Support");
	}

	bool PutData(RECORD& r)
	{
		return sm.PutData(r);
	}

	bool Delete(RECORD& r)
	{
		return sm.Delete(r);
	}
	

#line 43 "C:/workspace/FAME/FAMEDB/src/DataAndAccessManager/DataAndAccessManager.h"
};
#endif //DataAndAccessManager_h__included

