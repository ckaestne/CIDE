#ifndef Stack_h__included
#define Stack_h__included

//Layer Main

#line 1 "C:/workspace/FAME/FAMEDB/src/Main/Stack.h"
#include "include.h"
template <class T>


#line 3 "C:/workspace/FAME/FAMEDB/src/Main/Stack.h"
class Stack
{
//**** Layer Main ****
private:
#line 4 "C:/workspace/FAME/FAMEDB/src/Main/Stack.h"

private:
	int size ;  // Number of elements on Stack
	int top ;  
	T* stackPtr ;  
public:
	Stack()
	{
		size = 1;
		top = -1;
		stackPtr = (T *)malloc(sizeof(T) * size); 
	}
	Stack(int s)
	{
		size = s > 0 && s < 1000 ? s : 10 ;  
		top = -1 ;  // initialize stack
		stackPtr = (T *)malloc(sizeof(T) * size) ; 
	}
	void Constructor(int s)
	{
		size = s > 0 && s < 1000 ? s : 10 ;  
		top = -1 ;  // initialize stack
		stackPtr = (T *)malloc(sizeof(T) * size) ; 
	}
	
	//~Stack() { free(stackPtr) ; }
	
	int push(const T& item)
	{
		if (!isFull())
		{
			stackPtr[++top] = item ;
			return 1 ;  // push successful
		}
		return 0 ;  // push unsuccessful
	}
	
	//int push_back(const T& item)
	int push_back(T item)
	{
		printf("\nEntering data:\n");
		if (!isFull())
		{
			printf("\nEntering data1:\n");
			   printf("\n-------%d--------1\n", item.length());
			   printf("\n-------%d--------2\n", item.size());
			   printf("\n-------%d--------3\n", item.max_size());
			printf("\nEntering data:%s\n", item);
			//&stackPtr[++top] = (T *)malloc(sizeof(T)) ;
			stackPtr[++top] = item ;
			printf("\nEntering data2:\n");
			printf("\nEntered data:%s\n", stackPtr[top - 1]);
			return 1 ;  // push successful
		}
		return 0 ;  // push unsuccessful
	}
	
	int pop(T& popValue) 
	{
		if (!isEmpty())
		{
			popValue = stackPtr[top--] ;
			return 1 ;  // pop successful
		}
		return 0 ;  // pop unsuccessful
	}
	void clear()
	{
		top = -1;		
	}
	
	void print()
	{
		for(int looper = top; looper>-1; looper--)
		{
			printf("---%d---\n", top);
			printf("---%d---\n", looper);
			printf("---%s---\n", stackPtr[looper]);
			printf("---%d---\n", stackPtr[looper]);
		}
	}
	
	int isEmpty()const { return top == -1 ; } 
	
	int isFull() const { return top == size - 1 ; } 

#line 89 "C:/workspace/FAME/FAMEDB/src/Main/Stack.h"
} ;
#endif //Stack_h__included

