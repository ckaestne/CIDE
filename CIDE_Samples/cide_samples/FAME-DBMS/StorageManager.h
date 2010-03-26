#ifndef STORAGEMANAGER_H_
#define STORAGEMANAGER_H_

/*
*  14.02.2008 Syed Saif Ur Rahman <srahman@iti.cs.uni-magdeburg.de>: Initial version 
*/

#include  "include.h"
#include  "btree.h"
#include  "DataDictionary.h"
#include "Page.h"
#include "PageBuffer.h"

/*
 * Storage manager manage the storage related functionalities
 */

class StorageManager
{
private:
	PageBuffer pb;
	DataDictionary dd;
    
public:
	StorageManager() : dd(pb)
	{ StorageManager_sub();
		//debug_print("\nStorageManager"); 
	}
bool CreateDatabase(const char* filename)
    {   
    	debug_print("\nStorman::CreateDatabase 1\n");
    	
		if(!pb.CreateDatabase(filename))
            return false;
            
		debug_print("\nStorman::CreateDatabase 2\n");
        
        if(!dd.Create())
        	return false;
        	
        debug_print("\nStorman::CreateDatabase 3\n");
                
        return true;
    }
    
    bool OpenDatabase(const char * filename)
	{
		if(!pb.OpenDatabase(filename))
            return false;
        
        if(!dd.Open())
            return false;
                
		return true;
	}

	void CloseDatabase()
	{
		dd.Close();
        
        debug_print("\n StorareMan::CloseDatabase 4 ?!?! \n");
        
		pb.CloseDatabase();
	}

	//Read Index
private:
	btree bpt;
public:

	void StorageManager_sub()
	{
		//debug_print("\nStorageManager");
		bpt.btreeSetRef(&pb, &dd);
		//fflush(stdout);
	}

	bool GetData(RECORD& r)
	{
		if ( bpt.GetData(r) == false)
		{
			debug_print("\nRecord not found");
			return false;
		}
		else
		{
			debug_print("\nRecord found");
		}
		return true;
	}

	bool PrintStatus()
	{
		return bpt.PrintStatus() ;
	}
	//Read Index End
	
	//Read UnIndex
private:
	Page *pg;

public:

/*
	bool OpenDatabase(const char * filename)
	{
		return super::OpenDatabase(filename);
	}

	bool CloseDatabase()
	{
		//Uncomment it as the defination for this method will be provided in page buffer
		//return pb.CloseDatabase(r);
		return true;
	}
*/
	bool GetData(RECORD& r)
	{
		//This check is like optimistic search
		//Might be user need data from currently loaded page :-)
		//This will give efficiency if user queries consecutive in separate queries
		/*
		//This didn't worked for swaped back page :-)
		debug_print("\nFirst checking in currently loaded page");
		if (pg != NULL)
		{
			debug_print1("\nPage id: %d", pg->GetID());
			debug_print1("\nPage tuple count: %d", pg->CountTuples());
			debug_print1("\nPage max tuple: %d", pg->MaxTuples());
			if(pg->GetTuple(r))
			{
				debug_print("\nRecord found in already loaded page");
				return true;
			}
			else
			{
				debug_print("\nRecord not found in already loaded page, now traverse all data from start");
			}			
		}
		*/
		//debug_print("\nGetting start page to traverse from start");
		PID startpg = dd.GetStartPage();
		//If not data exists the PID could be 0
		if (startpg == EMPTY_PAGE )
		{
			debug_print("\nNo data exists");
			 
			return false;
		}
		
		//If data exists then load the
		Page *tmppg = pb.GetPage(startpg);
		if(tmppg == NULL)
		{
			return false;
		}
		//This check was unlogical :-)
		/*
		debug_print("\nLoading start page and check if current page is also the start page");
		if (tmppg->GetID() == pg->GetID())
		{
			//This means the currently loaded page was the first page
			//we have already search the currently loaded page optimistically and we are aware that it does not contain data
			return false;
		}
		*/
		
		//Make the newly get page as current page
		pg = tmppg;
		
		while(1) 
		{
			//debug_print1("\nSearching Page : %d", pg->GetID());
			if(pg->GetTuple(r))
			{
				debug_print("\nRecord found");
				 
				return true;
			}
			if(pg->GetNextPageID() == EMPTY_PAGE)
			{
				break;
			}
			pg =  pb.GetPage(pg->GetNextPageID());
			if(pg == NULL)
			{
				return false;
			}
		}
		
		debug_print("\nRecord not found");
		 
		return false;
	}

	bool PrintStatus()
	{
		PID startpg = dd.GetStartPage();
		//If not data exists the PID could be 0
		if (startpg == 0 )
		{
			debug_print("\n Empty database");
			 
			return false;
		}
		
		//If data exists then load the
		Page *pg = pb.GetPage(startpg);
		if(pg == NULL)
		{
			return false;
		}
		
		printf("\nStatus info start");
		 
		while(1) 
		{
			printf("\n Page ID : %d", pg->GetID());
			printf(":: Page SIZE : %d", pg->GetPageSize());
			printf(":: Tuple Count : %d", pg->CountTuples());
			printf(":: Is page Full : %d", pg->IsFull());
			RECORD r;
			pg->GetFirstTuple(r);
			printf(":: First record : %d", r.key);
			pg->GetLastTuple(r);
			printf(":: Last record : %d", r.key);
			pg->PrintAllTuples();
			printf("\n");			
			 
			if(pg->GetNextPageID() == EMPTY_PAGE)
			{
				break;
			}
			pg =  pb.GetPage(pg->GetNextPageID());
			if(pg == NULL)
			{
				return false;
			}
		}
		
		printf("\nStatus info end");
		 
		return true;
	}
	//Read UnIndex End
	
		//Write UnIndex
public:
	void StorageManager_dummy()
	{
		//debug_print("\nStorageManager UnIndexWriteSupport\n"); 
	}

	bool PutData(RECORD& r)
	{
		debug_print("\nEntering storage manager put data");
		PID startpg = dd.GetStartPage();
		//If not data exists the PID could be EMPTY_PAGE
		debug_print1("Start Page PID = %d", startpg);
		if (startpg == EMPTY_PAGE )
		{
			debug_print("\nCase for the first page");
			pg = pb.GetNewPage();
			if(pg == NULL)
			{
				return false;
			}
			debug_print("\nGetNewPage ok");
			pg->PutTuple(r);
			debug_print("\nPutTuple ok");
			//pg->PrintAllTuples();
			//For the very first page previous page id should be EMPTY_PAGE
			pg->SetPrevPageID(EMPTY_PAGE);
			debug_print1("\nSet the start page id:%d", pg->GetID());
			dd.SetStartPage(pg->GetID());
			pg->SetModified(true);
			debug_print("\nRecord inserted");
			return true;
		}
		
		//If data exists then load the first page
		if (pg->GetID() == startpg)
		{
			debug_print("\nStart page already loaded");
		}else
		{
			debug_print("\nGetting start page from page buffer");
			pg = pb.GetPage(startpg);
			if(pg == NULL)
			{
				return false;
			}

		}

		while(1) 
		{
			//Very first record for the page, page is empty
			debug_print1("\nTuple count: %d", pg->CountTuples());
			if (pg->CountTuples() == 0 )
			{
				pg->PutTuple(r);
				pg->SetModified(true);
				debug_print("\nRecord inserted");
				return true;
			}
			RECORD tmp;
			pg->GetLastTuple(tmp);
			debug_print1("\nLast tuple key: %d", tmp.key);
			debug_print1("\nCurrent tuple key: %d", r.key);
			//if(r.key <= tmp.key )
			//{
				debug_print1("\nCurrent key : %d", r.key);
				debug_print("\nThis key should be inserted into same page");
				if(!pg->IsFull())
				{
					debug_print("\nPage is not full");
					if(r.key <= tmp.key || ( r.key > tmp.key && pg->GetNextPageID() == EMPTY_PAGE) )
					{
						debug_print("\nPage should also be placed on this page");
						pg->PutTuple(r);
						pg->SetModified(true);
						debug_print("\nRecord inserted");
						return true;
					}
				}
				else
				{
					debug_print("\nPage is full");
					if(r.key <= tmp.key || ( r.key > tmp.key && pg->GetNextPageID() == EMPTY_PAGE) )
					{
						debug_print("\nGetting new page");
						pb.PinPage(pg);
						Page *pgnew = pb.GetNewPage();
						if(pgnew == NULL)
						{
							return false;
						}

						pgnew->SetNextPageID(pg->GetNextPageID());
						pgnew->SetPrevPageID(pg->GetID());
						debug_print1("\nCurrent page next set as next of new page: %d", pg->GetNextPageID());
						pg->SetNextPageID(pgnew->GetID());
						debug_print1("\nNew page id set as next of current page: %d", pgnew->GetID());
						debug_print("\nSplitting data from current page on new page");
						pg->HalfSplit(pgnew);
						debug_print("\nDo we put data on current page?");
						pg->GetLastTuple(tmp);
						debug_print1("\nCurrent page last tuple: %d", tmp.key);						
						
						debug_print("\before split");
						debug_print("\nsplit page 1");
						//pg->PrintAllTuples();
						debug_print("\nsplit page 2");
						//pgnew->PrintAllTuples();
						debug_print("\before split end");
						
						if(r.key <= tmp.key )
						{
							debug_print("\nYes");
							pg->PutTuple(r);
						}
						else
						{
							debug_print("\nNo. On newly splitted page :-)");
							pgnew->PutTuple(r);
						}
						pg->SetModified(true);
						pgnew->SetModified(true);
						pb.UnpinPage(pg);
						debug_print("\nRecord inserted");
						return true;
					}
				}
			//}
			debug_print("\ndid i got last tuple, no");
			if(pg->GetNextPageID() == EMPTY_PAGE)
			{
				break;
			}
			pg =  pb.GetPage(pg->GetNextPageID());
			if(pg == NULL)
			{
				break;
				//return false;
			}

		}
		Page * pgtmp = pb.GetNewPage();
		if(pgtmp == NULL)
		{
			return false;
		}

		pg->SetNextPageID(pgtmp->GetID());
		pgtmp->SetPrevPageID(pg->GetID());
		pg = pgtmp;
		pg->PutTuple(r);
		pg->SetModified(true);
		debug_print("\nRecord inserted");
		return true;
	}

	bool Delete(RECORD& r)
	{
		//This check is like optimistic search
		//Might be user need data from currently loaded page :-)
		//This will give efficiency if user queries consecutive in separate queries
		debug_print("\nFor delete in storage manager");
		debug_print("\nFor delete in storage manager1");
		
		/*
		//This case not working with swaped page
		if (pg != NULL)
		{
			debug_print("\nPage is not null");
			pg->PrintAllTuples();
			if(pg->GetTuple(r))
			{
				debug_print("\nRecord found in already loaded page");
				if(pg->RemoveTuple(r))
				{
					// Code to delete the page if its empty
					debug_print("\nShould we deleted the page???");
					debug_print1("\nCount tuple: %d", pg->CountTuples());
					if(pg->CountTuples() == 0)
					{
						debug_print("\nPage empty should be deleted");
						PID tmpPID = pg->GetPrevPageID();
						Page *tmppg;
						if (tmpPID == EMPTY_PAGE)
						{
							debug_print("\nIts the first page, no need to extract previous page");
							tmpPID = pg->GetNextPageID();
							if(tmpPID != EMPTY_PAGE)
							{
								dd.SetStartPage(pg->GetNextPageID());
							}
						}
						else
						{
							pb.PinPage(pg);
							tmppg = pb.GetPage(tmpPID);
							tmppg->SetNextPageID(pg->GetNextPageID());
							pb.UnpinPage(pg);
						}							
						pb.ReleasePage(pg);
					}
					
					//Since page is empty should be released
					//But since this method is declared as private so i commented it
					pg->SetModified(true);
					return true;
				}
			}
			else
			{
				debug_print("\nRecord not found in already loaded page, now traverse all data from start");
			}			
		}
		else
		{
			debug_print("\nNo data exists.");
			return false;
		}
		*/
		
		PID startpg = dd.GetStartPage();
		//If not data exists the PID could be EMPTY_PAGE
		if (startpg == EMPTY_PAGE )
			return false;
		
		//If data exists then load the		
		Page *tmppg = pb.GetPage(startpg);
		if(tmppg == NULL)
		{
			return false;
		}

		/*
		//This case was unlogical so ommited :-)
		if (tmppg->GetID() == pg->GetID())
		{
			//This means the currently loaded page was the first page
			//we have already search the currently loaded page optimistically and we are aware that it does not contain data
			return false;
		}
		*/
		
		//Make the newly get page as current page
		pg = tmppg;
		while(1) 
		{
			if(pg->GetTuple(r))
			{
				debug_print("\nRecord found");
				 
				
				if(pg->RemoveTuple(r))
				{
					// Code to delete the page if its empty
					debug_print("\nShould we deleted the page???");
					debug_print1("\nCount tuple: %d", pg->CountTuples());
					if(pg->CountTuples() == 0)
					{
						debug_print("\nPage empty should be deleted");
						PID tmpPID = pg->GetPrevPageID();
						Page *tmppg;
						if (tmpPID == EMPTY_PAGE)
						{
							debug_print("\nIts the first page, no need to extract previous page");
							tmpPID = pg->GetNextPageID();
							if(tmpPID != EMPTY_PAGE)
							{
								dd.SetStartPage(pg->GetNextPageID());
							}
							else
							{
								dd.SetStartPage(EMPTY_PAGE);								
							}
						}
						else
						{
							pb.PinPage(pg);
							tmppg = pb.GetPage(tmpPID);
							if(tmppg == NULL)
							{
								return false;
							}

							tmppg->SetNextPageID(pg->GetNextPageID());
							pb.UnpinPage(pg);
						}							
						pb.ReleasePage(pg);
					}
					
					//Since page is empty should be released
					pg->SetModified(true);
					debug_print("\nRecord deleted");
					return true;
				}
			}
			if(pg->GetNextPageID() == EMPTY_PAGE)
			{
				break;
			}
			pg =  pb.GetPage(pg->GetNextPageID());
			if(pg == NULL)
			{
				return false;
			}

		}
		
		debug_print("\nrecord not found");
		 
		return false;
	}
	//Write UnIndex End
	
	
	//Write Index
public:
	void StorageManager_dummy()
	{
		//debug_print("StorageManager BPTIndexWriteSupport\n");
	}

	bool PutData(RECORD& r)
	{
		if ( bpt.PutData(r) == false)
		{
			debug_print("\nRecord add failed");
			return false;
		}
		else
		{
			debug_print("\nRecord add successfull");
		}
		return true;
	}

	bool Delete(RECORD& r)
	{
		if ( bpt.Delete(r) == false)
		{
			debug_print("\nRecord delete successfull:");
			return false;
		}
		else
		{
			debug_print("\nRecord delete unsuccessfull");
		}
		return true;
	}
	//Write Index End
	
	
};
#endif