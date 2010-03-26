//NutOS Main
#include <cpp/nutcpp.h>

extern "C" {
#include <stdio.h>
#include <io.h>
#include <dev/usartavr.h>
#include <hardware/btn-hardware.h>	
}
//NutOS Main End

#include "Main.h"

//Main
Main * pM;


byte* (*LongEndian) ( byte* i, size_t size );

bool BigEndianSystem;

void InitEndian( void )
{
  byte SwapTest[2] = { 1, 0 };
  
  if( *(short *) SwapTest == 1 )
  {
    //little endian
	printf("\nLittle endian platform");
	 
    BigEndianSystem = false;

    //set func pointers to correct funcs
    //We choce to swap as we want to store data in bigendian format
    LongEndian = LongSwap;
  }
  else
  {
    //big endian
	printf("\nBig endian platform");
	 
    BigEndianSystem = true;

    //We choce not to swap as we want to store data in bigendian format, which the platform already is
    LongEndian = LongNoSwap;
  }
}


Main m;

int main(void)
{
	return Main::main();
}

/*static*/
int Main::main(void)
{
	init_term();

    printf("\nEDBMS Start");

	pM = &m;

	InitEndian();

	m.execute();

    printf("\nEDBMS Stop");

    return 0;
}
//Main

//NutOS Main
//currently only testing as execution
void Main::execute (void)
{
	printf("\nDemo testing on BTNODEv3 started :");					 

	printf("\nCreating database");					 
	this->CreateDBBT();

	printf("\nInserting data");
	this->PutData();

	printf("\nPrinting status");
	this->PrintStatus();

	printf("\nSelecting data");
	this->GetData();

	printf("\nClosing database");
	this->CloseDBBT();

	printf("\nOpening database");
	this->OpenDBBT();

	printf("\nDeleting data");
	this->DeleteData();

	printf("\nPrinting status");
	this->PrintStatus();

	printf("\nClosing database");
	this->CloseDBBT();

	printf("\nOpening database");
	this->OpenDBBT();

	printf("\nInserting data");
	this->PutData();

	printf("\nPrinting status");
	this->PrintStatus();

	printf("\nClosing database");
	this->CloseDBBT();

	printf("\nDemo testing on BTNODEv3 end :");

	m.test();
}

/*static*/
int Main::main(void) {
	int ret = super::main();
	while(1);
}

/*static */
void Main::init_term() {
	FILE *uart_terminal;
	u_long baud = 57600;

	btn_hardware_init();
	NutRegisterDevice(&APP_UART, 0, 0);
	uart_terminal = fopen(APP_UART.dev_name, "r+");    
	_ioctl(_fileno(uart_terminal), UART_SETSPEED, &baud);
	freopen(APP_UART.dev_name, "w", stdout);   
}
//NutOS Main End


//Test Main
void execute_dummy(void) {
	//super::execute();
}


uint16_t strlen(char* str) 
{
	if(str == NULL) return 0;
	uint16_t len = 0;
	while(str[len] != '\0')
		++len;
	return len;
}


bool EqualStr(char* str1, char* str2)
{
	uint16_t size = strlen(str1);
	if(size != strlen(str2)) return false;

	for(uint16_t i = 0; i < size; ++i)
	{
		if(str1[i] != str2[i])
		{
			return false;
		}
	}
	return true;
}

bool Main::testOpenDB(char* file)
{
    if(!dam.OpenDatabase(file)) {
        printf("\n!!! Failed to open DB: %s", file);

        printf("\n!!! we have to continue as it always fails :-()");
        
        return true;
    }
    return true;
}

bool Main::testCreateDB(char* file)
{
    if(!dam.CreateDatabase(file)) {
        printf("\n!!! Failed to created new DB: %s", file);
        return false;
    }
    return true;
}

bool Main::testCloseDB()
{
    dam.CloseDatabase();
    return true;
}

bool Main::testPut(uint16_t key, char* data)
{
    RECORD r;
    r.key = key;
    r.size = strlen(data)+1;
    r.value = (byte*)data;
    
    if(!dam.PutData(r)) {
        printf("\n!!! Failed to put data! key=%d; data=%s", key, data);
        return false;  
    }
    return true;
}

bool Main::testGet(uint16_t key, char*& data)
{
    RECORD r;
    r.key = key;
    r.size = 0;
    r.value = NULL;
    
    if(!dam.GetData(r)) {
        printf("\n!!! Failed to get data! key=%d", key);
        return false;  
    }
    
    data = (char*)r.value;
    return true;
}

bool Main::testDelete(uint16_t key)
{
    RECORD r;
    r.key = key;
    r.size = 0;
    r.value = NULL;
    
    if(!dam.Delete(r)) {
        printf("\n!!! Failed to delete data! key=%d", key);
        return false;  
    }
    
    return true;
}

bool Main::testReadWriteData2DB(char* description, uint16_t keys[], uint16_t count)
{
    char* dbName = "DB.dat";
    
    printf("\n\nTesting %s ... ", description);
    
    if(!testCreateDB(dbName)) return false;
    
    if(!WriteData(description, keys, count)) return false;
    
     printf("\n   Writing %s data OK!\n", description);
    
    if(!ReadData (description, keys, count)) return false;
    
    printf("\n   Reading %s data OK!\n", description);

	printf("\n   Closing DB.");
    if(!testCloseDB()) return false; 

	printf("\n   Reopening DB.\n");
    if(!testOpenDB(dbName)) return false;
    if(!ReadData (description, keys, count)) return false;
    
    if(!testCloseDB()) return false;
    printf("\n-> OK");
    return true;
 }


uint16_t keysSortedAsc[]  = {0,   1,  2,  3,  4,  5,  6,  7,  8,  9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60};
uint16_t keysSortedDesc[] = {20, 19, 18, 17, 16, 15, 14, 13, 12, 11, 10,  9,  8,  7,  6,  5,  4,  3,  2,  1,  0};
uint16_t keysRandom1[]    = { 2,  9, 17, 13,  7,  1, 20,  8,  3,  4,  0,  19, 10, 5,  11, 6, 12, 18, 14, 16, 15,  100, 30, 40, 99, 32, 34, 41, 500, 48, 499, 29, 399, 299, 199, 198, 197, 451};
uint16_t keysRandom2[]    = { 200,  9, 170, 130,  7,  100, 20,  8,  30,  40,  0,  190, 1, 50,  110, 600, 120, 180, 140, 16, 150};

// ====================================================================

#define ARRAY_COUNT(a) (sizeof(a)/(sizeof(a[0])))
    
bool Main::test()
{
    if(!testReadWriteData2DB("SortedAsc",  keysSortedAsc,  ARRAY_COUNT(keysSortedAsc)))  return false;

    if(!testReadWriteData2DB("SortedDesc", keysSortedDesc, ARRAY_COUNT(keysSortedDesc))) return false;

    if(!testReadWriteData2DB("Random1",    keysRandom1,    ARRAY_COUNT(keysRandom1)))    return false;

    if(!testReadWriteData2DB("Random2",    keysRandom2,    ARRAY_COUNT(keysRandom2)))    return false;

	printf("\nFinish");

    return true;
}


char buffer[9];    
bool Main::WriteData(char* description, uint16_t keys[], uint16_t count)
{
	for(size_t i = 0; i < count; ++i)
	{
		sprintf(buffer, "DATA_%d", keys[i]);
		printf("\n   Putting data: key %d, data: %s", keys[i], buffer);
		if(!testPut(keys[i], buffer)) return false;
	}
	return true;
}

bool Main::ReadData(char* description, uint16_t keys[], uint16_t count)
{
	for(size_t i = 0; i < count; ++i)
	{
		char* data = NULL;

		printf("\n   Reading data: key %d", keys[i]);
		if(!testGet(keys[i], data)) return false;

		sprintf(buffer, "DATA_%d", keys[i]);
		if(!EqualStr(data, buffer)) 
		{
			printf("\n  !!! %s: Failed to read correct data! Read: %s; Expected: %s", description, data, buffer);
			return false;
		}
	}
	return true;
}
//Test Main End

//Windows Main
void Main::execute (void)
{
	char ip;
	do
	{
		drawmenu();
		ip = getchar();

		fflush(stdin);
		executecommand_sub(ip);
	}
	while( ip != '\n' );
}

bool Main::executecommand_sub(char ip)
{
	switch(ip)
	{
	case 'N' : case 'n':
		printf("\nCreate New Database");
		this->CreateDB();
		break;
	case 'C' : case 'c':
		printf("\nClose Database");
		this->CloseDB();
		//exit(1);
		break;
	case 'O' : case 'o':
		printf("\nOpen existing Database");
		this->OpenDB();
		break;
	case 'P' : case 'p':
		printf("\nPut Data");
		this->PutData();
		break;
	case 'G' : case 'g':	
		printf("\nGet Data");
		this->GetData();
		break;
	case 'D' : case 'd':	
		printf("\nDelete Data");
		this->DeleteData();
		break;
	case 'S' : case 's':	
		printf("\nDBMS Status");
		this->PrintStatus();
		break;
	case '\n':
		return false;
	default :
		printf("\nInvalid Selection, Please retry:"); 
		break;
	}

	return true;
}
//Windows Main End

//Windows Test
void Main::drawmenuitems(void) {
	printf("\n To run tests press (T):");
	drawmenuitems_sub();
};

bool Main::executecommand(char ip)
{
	switch(ip)
	{
	case 'T' : case 't':	
		printf("\nRunning tests...");
		this->test();
		break;
	default :
		return executecommand_sub(ip);
	}

	return true;
}
//Windows Test End

/*
//NutOS Main
// The following lines are copied from btnut_system_1.8.tar.gz\nut\cpp\nutcpp.cc 
// If we include these lines here, we don't need to link to nutcpp library!

// == Adapt NutOS main() hack for gcc to C++ ==================================

#if defined(__cplusplus) && defined(__GNUC__) && defined(main)

extern "C" {
int _cxx_pre_main_(void) {
	return main();
}
}

#endif // defined(__cplusplus) && defined(__GNUC__) && defined(main)

//NutOS Main End
*/