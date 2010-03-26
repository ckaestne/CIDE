#ifndef MAIN_H_
#define MAIN_H_

/*
*  14.02.2008 Wei Tao <weitaoly@msn.com>: Initial version 
*/

#include  "include.h"
#include  "RECORD.h"
#include  "DataAndAccessManager.h"

/* 
 * Main class control the demo execution of the database
 */

class Main
{
private:
    char filename[8];
public:
	char buff[TUPLEDATAMAX];
	DataAndAccessManager dam;
	RECORD r;
	Main()
	{
		//printf("\nMain constructor");
	}
	
	~Main()
	{
		//printf("\nMain destructor");
	}

	static int main(void);

	//Hook for initialization
	static void init_term() { }
	/*
	 * Execute methods contains the menu call for demo database use
	 */

	//execute should be implemented in the system dependent features
	//=0

private:
      
      void CreateDBBT()
      {
        if(dam.CreateDatabase(filename))
        {
            printf("\nDatabase created");        	 
        }
        else
        {
            printf("\nDatabase not created");        	 
        }
     }

      void OpenDBBT()
      {
          if(dam.OpenDatabase(filename))
              printf("\nDB opened successfully!");
          else
              printf("\nFailed to open DB!");
         
      }
      
      void CloseDBBT()
      {
        dam.CloseDatabase();
        printf("\nClosed DB!");
         
      }

      
      void CreateDB()
      {
		printf("\nEnter file name of new DB: ");
        gets(filename);
        if(dam.CreateDatabase(filename))
            printf("\nNew DB created successfully!");
        else
            printf("\nFailed to created new DB!");
        
      }
      
      void OpenDB()
      {
		printf("\nEnter file name of existing DB: ");
        gets(filename);
        if(dam.OpenDatabase(filename))
            printf("\nDB opened successfully!");
        else
            printf("\nFailed to open DB!");
      
      }
      
      void CloseDB()
      {
        dam.CloseDatabase();
        printf("\nClosed DB!");
      }
      
		/*
		 * This method put the data into dbms
		 */
	  void PutData (void)
	  {
#ifdef WIN32//btnode3
		  this->KeyInput(r);
		  this->DataInput(r);
		  dam.PutData(r);
#endif
#ifdef btnode3//WIN32
		  /* Dummy ascending test data to check the functionality of putting */
		  
		  for (int looper = 0; looper < 21; looper ++)
		  {
			  r.key = looper;
			  //r.value = (char *)malloc(sizeof(r.key) + 1);
			  sprintf((char*)buff, "%d", r.key + 100);
			  r.value = (byte *)buff;			  
			  r.size = this->CountValue((char*)r.value) + 1;
			  *(r.value + r.size) = '\0';
			  printf("\nEntered Key:%d", r.key);
			  printf("\nEntered value :%s", r.value);
			  PrintValue(r.value, r.size);
			  printf("\nEntered value size:%d\n", r.size);
			  //COmmented for dummy checking on btnode3
			  dam.PutData(r);
		  }
		  
		  /* Dummy descending test data to check the functionality of putting */
		  /*
		  for (int looper = 20; looper > 0; looper --)
		  {
			  r.key = looper;
			  //r.value = (char *)malloc(sizeof(r.key) + 1);
			  sprintf((char*)buff, "%d", r.key + 100);
			  r.value = (byte *)buff;			  
			  r.size = this->CountValue((char*)r.value) + 1;
			  *(r.value + r.size) = '\0';
			  printf("\nEntered Key:%d", r.key);
			  printf("\nEntered value :%s", r.value);
			  PrintValue(r.value, r.size);
			  printf("\nEntered value size:%d\n", r.size);
			  //COmmented for dummy checking on btnode3
			  dam.PutData(r);
		  }
		  */
		  /* Dummy mixed test data to check the functionality of putting */
		  /*
		  for (size_t looper = 3; looper < 4; looper = looper + 2)
		  {
			  r.key = looper;
			  //r.value = (byte *)malloc(sizeof(r.key));
			  r.value = (byte *)buff;
			  sprintf((char*)r.value, "%d", r.key + 100);
			  *(r.value + sizeof(r.key)) = '\0';
			  r.size = this->CountValue((char*)r.value) + 1;
			  //printf("\nKey:%d", r.key);
			  ////printf("\nvalue :%s", r.value);
			  //PrintValue(r.value, r.size);
			  //printf("\nsize:%d\n", r.size);
			  //COmmented for dummy checking on btnode3
			  //printf("\n1.1");
			   
			  dam.PutData(r);
			  printf("\n1.2");
			   
			  r.key = looper - 1;
			  //r.value = (byte *)malloc(sizeof(r.key));
			  r.value = (byte *)buff;
			  sprintf((char*)r.value, "%d", r.key + 100);
			  *(r.value + sizeof(r.key)) = '\0';
			  r.size = this->CountValue((char*)r.value) + 1;
			  printf("\nEntered Key:%d", r.key);
			  //printf("\nEntered value :%s", r.value);
			  PrintValue(r.value, r.size);
			  printf("\nEntered value size:%d\n", r.size);
			  //COmmented for dummy checking on btnode3
			  dam.PutData(r);
		  }
		  */
#endif
	  }

	  	/*
		 * This method get the data from dbms
		 */
	  void GetData (void)
	  {
#ifdef WIN32
		  RECORD r;
		  this->KeyInput(r);
		  if(dam.GetData(r))
			  PrintValue(r.value, r.size);
		  else
			  printf("\nFailed to get data!");	  

#endif
#ifdef btnode3
		  /* Dummy data to check the functionality of getting */
		  
		  for (size_t looper = 0; looper < 21; looper ++)
		  {
			  r.key = looper;
			  printf("\nEntered Key:%d", r.key);
			  if(dam.GetData(r))
				PrintValue(r.value, r.size);
			  else
				printf("\nFailed to get data!");
		  }
		  
#endif
	  }

	  /*
		 * This method removes/delete the data from dbms
		 */
	  void DeleteData (void)
	  {
#ifdef WIN32
		  RECORD r;
		  this->KeyInput(r);
		  printf("\nManual key to delete: %d", r.key);
		  if(dam.Delete(r))
			PrintValue(r.value, r.size);
		  else
		    printf("\nFailed to delete data!");
#endif
#ifdef btnode3
		  //printf("\ncheck manually inserted key status");
		  /* Dummy data to check the functionality of getting */
		  
		  for (size_t looper = 0; looper < 21; looper ++)
		  {
			  r.key = looper;
			  printf("\nEntered Key:%d", r.key);
			  if(dam.Delete(r))
				PrintValue(r.value, r.size);
			  else
				printf("\nFailed to delete data!");
		  }
		  
#endif
	  }
	  void PrintStatus(void)
	  {
		  dam.PrintStatus();
	  }
		/*
		 * This method count the length of the value provided by the user
		 */
	  int CountValue(char* value)
	  {
		  int count = -1;
		  while (*(value + ++count)!= '\0')
		  {
		  }
		  return count;
	  }
		/*
		 * This method take the key value input for demo
		 */
	  void KeyInput(RECORD& r)
	  {
		  printf("\nEnter Key (only numeric values excepted):");
		  char c[TUPLEKEYMAX];
		  int count = 0;
		  do		  
		  {
			  c[count] = getchar();
			  int ascii = static_cast<unsigned int>(c[count]);
			  if(ascii == 10)
			  {
				  break;
			  }			  
			  if(ascii>47 && ascii<58)
			  {
				  count++;
				  if (count == TUPLEKEYMAX)
				  {
					  break;
				  }
			  }
		  }
		  while(1);
		  r.key = atoi(c);
		  fflush(stdin);
		  printf("\nExcepted Key: %d", r.key);
	  }
		/*
		 * This method take the data value input for demo
		 */
	  void DataInput(RECORD& r)
	  {
		  char value[TUPLEDATAMAX + 1];
		  printf("\nEnter Value(max %d char):", TUPLEDATAMAX);

		  gets(value);
		  r.size = this->CountValue(value);
		  //r.value = (byte *)malloc(r.size + 1);
		  r.value = (byte *)buff;
		  //r.value = value;
		  size_t looper = 0;
		  for (looper = 0; looper < r.size; looper++)
		  {
			  *(r.value+looper) = *(value+looper); 
		  }
		  *(r.value+looper) = '\0'; 
		  r.size = r.size + 1;
		  printf("Value size = %d", r.size);
	  }
	  
	  
	  //Main Test
private:
    bool testOpenDB(char* file);
    bool testCreateDB(char* file);
    bool testCloseDB();
  
    bool testReadWriteData2DB(char* description, uint16_t keys[], uint16_t count);
    bool testPut(uint16_t key, char* data);
    bool testGet(uint16_t key, char*& data);
    bool testDelete(uint16_t key);
	

	bool ReadData(char* description, uint16_t keys[], uint16_t count);
	bool WriteData(char* description, uint16_t keys[], uint16_t count);
	  //Main Test End

	//NutOS Main
public:
	//Hook for initialization
	static void init_term();

	//currently only testing as execution
	void execute (void);

	static int main(void);
	//NutOS Main End
	
	//Windows Main
public:
	void execute (void); bool Main::test ();
private:
	void drawmenuitems (void);
	/*
	* This method contains the defination to draw the console menu
	*/
	void drawmenu(void)
	{
		printf("\n\"Welcome to Embedded DB\"");
		drawmenuitems_sub();
	}
	void drawmenuitems_sub(void) {
		printf("\n To create new database press (N):");
		printf("\n To open existing database press (O):");
		printf("\n To close database press (C):");
		printf("\n To put data press (P):");
		printf("\n To get data press (G):");
		printf("\n To delete data press (D):");
		printf("\n To printf DBMS status press (S):");
		printf("\n To exit press (Empty Return):");
	};
	//Windows Main End
	
	//Windows Test Main
protected:
	bool executecommand_sub(char ip);
	bool executecommand (char ip);
	//Windows Test Main End
};
#endif