#ifndef INCLUDE_H_
#define INCLUDE_H_
  
#define PAGE_BUFFER_MAX_SIZE (4*1024)

/*
 * This file contains the all system level include used in this project
 */

//This will make sure that c specific header files also works for C++ compiler
extern "C"
{
    #include <stdio.h>
    #include <stdlib.h>
}

//To handle platform specific implementation
#ifdef WIN32

	typedef unsigned __int8 uint8_t;
	typedef unsigned __int16 uint16_t ;
    typedef unsigned __int32 uint32_t ;
    typedef __int16 int16_t;
    typedef __int16 KEY;


#elif btnode3

    typedef  int16_t KEY;
	typedef u_char uint8_t;
    
	//#error "here i am"
	extern "C"
	{
        #include <stdint.h>
		#include <sys/timer.h>
	}

#else

#error "Platform not passed with compiler option -D"

#endif

typedef unsigned char byte; 

//Type of page, to be used in BTREE implementation
typedef uint8_t PageType;
#define DATA 1
#define INDEX 2
//typedef enum PageType { DATA, INDEX };

//This variable hold the page identifier for in memory pages
// 0 = No page in memory, means till now now data exists.
//For page linkedlist PID = 0 for next and previous means no page reference
typedef uint16_t PID; 
typedef uint16_t BID;

#define EMPTY_PAGE 0
#define EMPTY_BLOCK 0
//Minimum page_size should be 105 PC, PH
//Minimum page_size should be 80(105 Btree usage) for PO max 90 for btnode for 4 pages :-)
#define PAGE_SIZE 105

#define BYTEFACTOR 1
#define TUPLEKEYMAX 5
#define TUPLEDATAMAX 8
#define MININDEXKEY -1

/*
 * This method count the length of the value provided by the user
 */
static inline int CountValue(char* value)
{
  int count = -1;
  while (*(value + ++count)!= '\0')
  {
  }
  return count;
}


static inline uint16_t ReadBigEndian16FromMem(const void* mem)
{
    const byte* src = (const byte*)mem;
    return (uint16_t)((src[0] << 8) | src[1]);
}

static inline void WriteBigEndian16ToMem(void* mem, uint16_t integer)
{
    byte* dest = (byte*)mem;
    dest[0] = (byte)((integer&0xFF00)>>8); 
    dest[1] = (byte)(integer&0xFF); 
}

static inline uint32_t ReadBigEndian32FromMem(const void* mem)
{
    const byte* src = (const byte*)mem;
    return (((uint32_t)src[0] << 24) | 
			((uint32_t)src[1] << 16) |
			((uint32_t)src[2] << 8)  |
             (uint32_t)src[3]);
}

static inline void WriteBigEndian32ToMem(void* mem, uint32_t integer)
{
    byte* dest = (byte*)mem;
    dest[0] = (byte)((integer&0xFF000000)>>24); 
    dest[1] = (byte)((integer&0x00FF0000)>>16); 
    dest[2] = (byte)((integer&0x0000FF00)>>8); 
    dest[3] = (byte)(integer&0x000000FF); 
}

// #define ReadPIDFromMem(mem)  ((((unsigned char*)mem)[0] << 8) | (((unsigned char*)mem)[1]))
static inline PID ReadPidFromMem(const void* mem)
{
    return (PID)ReadBigEndian16FromMem(mem);
}

// #define WritePidToMem(pid,mem) { ((unsigned char*)mem)[0] = (unsigned char)((pid&0xFF00)>>8); ((unsigned char*)mem)[1] = (unsigned char)(pid&0xFF); }
static inline void WritePidToMem(void* mem, PID pid)
{
    WriteBigEndian16ToMem(mem, (uint16_t)pid);
}

//To copy buffer
static inline void CopyBuffer(byte * copyfrom, byte * copyto, size_t copysize)
{
	//printf("\nCopy buffer trace:");
	for (unsigned int looper = 0; looper < copysize * BYTEFACTOR; looper++)
	{
		*(copyto+looper) = *(copyfrom+looper);
		/*
		printf("\nLooper :%d", looper);
		printf("\nCopy from :%d", (copyfrom+looper));
		printf("\nCopy to :%d", (copyto+looper));
		*/
	}
	//printf("\nCopy buffer trace end:");
}

static inline byte* LongSwap (byte* i, size_t size)
{
	byte b1, b2, b3, b4;	
	uint16_t itmp1 = 0;	
	uint32_t itmp = 0;

	//printf("\nSwapping");
	
	switch(size * 8)
	{
		
		case 16:
			CopyBuffer(i, (byte*)&itmp1, size);
			b1 = itmp1 & 255;
			b2 = ( itmp1 >> 8 ) & 255;
			return (byte*)(b2 | ((uint16_t)b1 << 8));
			
		case 32:
			CopyBuffer(i, (byte*)&itmp, size);
			b1 = itmp & 255;
			b2 = ( itmp >> 8 ) & 255;
			b3 = ( itmp >> 16 ) & 255;
			b4 = ( itmp >> 24 ) & 255;
		
			return (byte*)(b4 | ((uint32_t)b3 << 8) | ((uint32_t)b2 << 16) | ((uint32_t)b1 << 24));
		default:
			//printf("\nNothing worked");
			return i;
	}
}

static inline byte* LongNoSwap(byte* i, size_t size)
{
	//printf("\nNo Swapping");
	return i;
}

static inline void PrintValue(byte* value, size_t size)
{	
#ifdef WIN32
	printf("\nValue = : %s", (char*)value);
#endif
	
	printf("\nValue = :");
	for(size_t looper=0; looper<size; ++looper)
	{
		printf(" %.2X ", value[looper]);
	}
}

/*
#define debug_print(x) printf(x)
#define debug_print1(x1,x2) printf(x1,x2)
#define debug_print2(x1,x2,x3) printf(x1,x2,x3)
#define debug_print3(x1,x2,x3,x4) printf(x1,x2,x3,x4)
*/

#define debug_print(x) 
#define debug_print1(x1,x2) 
#define debug_print2(x1,x2,x3) 
#define debug_print3(x1,x2,x3,x4) 

#endif /*INCLUDE_H_*/
