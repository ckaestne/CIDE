#ifndef PAGE_H_
#define PAGE_H_

/*
*  14.02.2008 Syed Saif Ur Rahman <srahman@iti.cs.uni-magdeburg.de>: Initial version 
*/

#include "include.h"
#include "RECORD.h"

/* 
 * Page class hold the page instance. Components manipulates memory pages using this class
 */

#define GetRecDataPtr(rec) (buffer + (rec)->offset)
#define GetRecDataOffsetFromPtr(ptr) (((byte*)(ptr)) - (byte*)buffer)
#define HEADER_SIZE (2+2+2+1+2+2+2)
 
class Page
{
	
private:
/*
	typedef struct Header
	{
		//ID of the current page
		PID id;
		//ID of the next page
		PID next;
		//ID of the previous page
		PID prev;
		//Type of page, to be used in BTREE implementation
		PageType pt;
		//Tuple count in page
		uint16_t tuplecount;
		//Offset table size        
		uint16_t otsize;        
		//Maximum possible tuple in page
		uint16_t maxtuple;        
	};
*/	
	typedef struct OffsetTable
	{
		int16_t  key;
		uint16_t size;
		uint16_t offset;
	};
	
private:
	//ID of the current page
	PID id;
	//ID of the next page
	PID next;
	//ID of the previous page
	PID prev;
	//Type of page, to be used in BTREE implementation
	PageType pt;
	//Tuple count in page
	uint16_t tuplecount;
	//Flag to pin the page to avoid swap
	bool ispinned;
	//Flag to mark the page as modified
	bool ismodified;
	//Memory buffer
    //byte buffer[PAGE_SIZE];
    byte buffer[PAGE_SIZE-HEADER_SIZE];
	//Header pointer
	//void* headerptr;
	//Offsettable pointer
	OffsetTable* otptr;
	//Offsettable start pointer
	void* otptrstart;
	//Current record pointer
	//void* recptr;
	//Offset table size
	uint16_t otsize;
	//Maximum possible tuple in page
	uint16_t maxtuple;
	//Maximum possible tuple in page
	uint16_t freebuf;

	//Write
private:
	//Temporary buffers
	char b0[TUPLEDATAMAX];
	char b1[TUPLEDATAMAX];
	char b2[TUPLEDATAMAX];
	//Write End


public:

	//Method to be used as constructor for the page, to be called manually and must be called for initialization
	void PageC(void)
	{
		debug_print("Page Constructor\n");
		//debug_print1("Size of pagetype: %d", sizeof(PageType));
		id = 0;
		next = 0;
		prev = 0;
		tuplecount = 0;

		//For testing endian in buffer code
		for(size_t looper=0; looper<sizeof(buffer); ++looper)
		{
			buffer[looper] = 0;
		}

		//Initializing memory addresses
		//headerptr = (struct Header *)buffer;
		otptr = (struct OffsetTable *)(buffer /*+ sizeof(struct Header)*/);
		//debug_print1("\nSize of struct header: %d", sizeof(struct Header));
		//debug_print1("\nSize of struct offsettable: %d", sizeof(struct OffsetTable));
		//debug_print1("\nSize of class RECORD: %d", sizeof(class RECORD));
		otptrstart = otptr;
		otsize = 0;
		tuplecount = 0;
		this->pt = DATA;
		
		/*
		//Max tuple calculation old
		int eintuple = 0;
		eintuple = (sizeof(struct Header) * BYTEFACTOR);
		eintuple += (sizeof(class RECORD) * BYTEFACTOR);
		eintuple += (sizeof(struct OffsetTable) * BYTEFACTOR);
		////debug_print1("Ein tuple sind: %d", eintuple);
		//maxtuple = (int)PAGE_SIZE - (sizeof(struct Header) * BYTEFACTOR);
		//maxtuple = maxtuple / (sizeof(class RECORD) * BYTEFACTOR);
		maxtuple = (int)PAGE_SIZE / eintuple;
		//RecycleStack.Constructor(maxtuple);
		*/

		//Max tuple calculation new
		debug_print1("\nPAGESIZE: %d", PAGE_SIZE);
		freebuf = sizeof(buffer); // - (sizeof(struct Header) * BYTEFACTOR);
		//debug_print1("\nSpace for header: %d", (sizeof(struct Header) * BYTEFACTOR));
		debug_print1("\nFree space after header: %d", freebuf);

		uint16_t eintuple = (sizeof(struct OffsetTable) * BYTEFACTOR);
		eintuple += ((TUPLEDATAMAX + 1) * BYTEFACTOR);
		//debug_print1("\nEin tuple sind: %d", eintuple);
		maxtuple = freebuf / eintuple;
		debug_print1("\nNow max possible tuples in free space:%d",maxtuple);
		 

		//recptr = (class RECORD *)(buffer + (PAGE_SIZE - sizeof(class RECORD)));
		
		/*
		//debug_print1("\nPagesize : %d", PAGE_SIZE);
		//debug_print1("\nMaxtuple : %d", maxtuple);
		*/
		
//		Header h;
//		this->SetHeader(h);
	}

	//Method to be used as swapconstructor for the page, 
	//to be called manually and must be called for initialization after swap
	void PageS(void)
	{
		//debug_print("Page Continous, Swap\n");

		//Initializing memory addresses
//		headerptr = (struct Header *)buffer;
		otptr = (struct OffsetTable *)(buffer /*+ sizeof(struct Header)*/);
		//debug_print1("\nSize of struct header: %d", sizeof(struct Header));
		//debug_print1("\nSize of struct offsettable: %d", sizeof(struct OffsetTable));
		//debug_print1("\nSize of class RECORD: %d", sizeof(class RECORD));
		otptrstart = otptr;

		//Getting header information from swaped back page
//		Header h;
//		this->GetHeader(h);
		
		/*
		//Rely on previous maxtuple values :-)
		//debug_print1("\nPAGESIZE: %d", PAGE_SIZE);
		freebuf = PAGE_SIZE - (sizeof(struct Header) * BYTEFACTOR);
		//debug_print1("\nSpace for header: %d", (sizeof(struct Header) * BYTEFACTOR));
		//debug_print1("\nFree space after header: %d", freebuf);
		freebuf = freebuf - (otsize * (sizeof(struct OffsetTable) * BYTEFACTOR));
		//debug_print1("\nSpace for offsettable: %d", (otsize * (sizeof(struct OffsetTable) * BYTEFACTOR)));
		//debug_print1("\nFree space after offsettable: %d", freebuf);
		RECORD r;
		this->GetLastTuple(r);
		freebuf = freebuf - ((buffer + PAGE_SIZE) - (byte *)r.value);
		//debug_print1("\n1>>>:%d", (buffer + PAGE_SIZE));
		//debug_print1("\n2>>>:%d", (byte *)r.value);
		//debug_print1("\nSpace for records: %d", ((buffer + PAGE_SIZE) - (byte *)r.value));
		//debug_print1("\nFree space after records: %d", freebuf);

		int eintuple = 0;
		eintuple = (sizeof(struct OffsetTable) * BYTEFACTOR);
		eintuple += ((TUPLEDATAMAX + 1) * BYTEFACTOR);
		//debug_print1("\nEin tuple sind: %d", eintuple);
		maxtuple = (int)freebuf / eintuple;
		//debug_print1("\nNow max possible tuples in free space:%d",maxtuple);
		maxtuple = maxtuple + tuplecount;
		//debug_print1("\nNow max possible tuples on page:%d",maxtuple);
		*/

		//recptr = NULL; //(class RECORD *)(buffer + (PAGE_SIZE - sizeof(class RECORD)));
		
	}

	//To extract the ID of the current page
	PID GetID()
	{
		return this->id;
	}
	//To extract the PAGE SIZE
	uint16_t GetPageSize()
	{
		return PAGE_SIZE;
	}
	//To extract the previous page ID
	PID GetPrevPageID()
	{
		return this->prev;
	}
	//To extract the next page ID
	PID GetNextPageID()
	{
		return this->next;
	}

	//To mark the page as modified
	bool IsModified()
	{
		return this->ismodified;
	}
	//To get the type of the page 
	PageType GetType()
	{
		return this->pt;
	}
	//To get the tuple count in page
	uint16_t CountTuples()
	{
		return this->tuplecount;
	}
	//To get the max tuple count in page
	uint16_t MaxTuples()
	{
		return this->maxtuple;
	}

	//To extract the tuple with the provided key in parameter
	uint16_t GetTuple(RECORD& r)
	{
		/*
		 * Code to search the tuple from page
		 * if record not found on page it will return false
		 * other wise will populate RECORD structure with the record
		 */
		//start from here
		debug_print("\nGetting record");
		OffsetTable * otptrtmp = (OffsetTable *)otptrstart;
		//debug_print("\notsize : %d", this->otsize);
		for(size_t looper = 0; looper<otsize; ++looper)
		{
			if (looper == 0 )
			{
				//For first record offsettable start is used
				otptrtmp = (OffsetTable *)otptrstart;
			}
			else
			{
				otptrtmp = (struct OffsetTable *)(buffer /*+ sizeof(struct Header)*/ + (sizeof(struct OffsetTable) * BYTEFACTOR * looper) );
			}
			if(otptrtmp->key == r.key)
			{
				debug_print("\nTuple found:");
				debug_print1("\nOT Key : %d", otptrtmp->key);
				debug_print1("\nValue size : %d", otptrtmp->size);
				//debug_print1("\nValue : %s", (char*)otptrtmp->ptr);
				//PrintValue((byte*)otptrtmp->ptr, otptrtmp->size);
				//PrintValue(GetRecDataPtr(otptrtmp), otptrtmp->size);				
				r.size = otptrtmp->size;
				//debug_print("\n>>>");
				//We can't copy this as user didn't know how much memory it should give for this record, we have to give our memory reference :-) quite risky
				//this->CopyBuffer((byte *)otptrtmp->ptr, (byte *)r.value, otptrtmp->size);
				r.value = GetRecDataPtr(otptrtmp);
				//debug_print("\n???");
				//debug_print1("\nKey : %d", r.key);
				////debug_print1("\nValue : %s", r.value);
				//debug_print1("\nsize : %d", r.size);
				return true;
			}
		}
		debug_print("\nTuple not found:");
		return false;
	}

	//To extract the record as per index provided as parameter
	bool GetTupleByIndex(size_t index, RECORD& r)
	{
		/*
		 * Code to search the tuple from page
		 * if record not found on page it will return false
		 * other wise will populate RECORD structure with the record
		 */
		//start from here
		debug_print("\nGetting record");
		OffsetTable * otptrtmp = (OffsetTable *)otptrstart;
		//debug_print("\notsize : %d", otsize);
		for(size_t looper = 0; looper<otsize; ++looper)
		{
			if (looper == 0 )
			{
				//For first record offsettable start is used
				otptrtmp = (OffsetTable *)otptrstart;
			}
			else
			{
				otptrtmp = (struct OffsetTable *)(buffer /*+ sizeof(struct Header)*/ + (sizeof(struct OffsetTable) * BYTEFACTOR * looper) );
			}
			if(looper == index)
			{
				debug_print("\nTuple at index found:");
				debug_print1("\nOT Key : %d", otptrtmp->key);
				//RECORD * rtmp = (class RECORD *)otptrtmp->ptr;
				r.key = otptrtmp->key;
				r.value = GetRecDataPtr(otptrtmp);
				r.size = otptrtmp->size;
				debug_print1("\nKey : %d", r.key);
				////debug_print1("\nValue : %s", r.value);
				debug_print1("\nsize : %d", r.size);
				return true;
			}
		}
		debug_print("\nTuple index not found:");
		return false;
	}
	
	//To print all the tuples in the current page
	bool PrintAllTuples()
	{
		/*
		byte stream[PAGE_SIZE];
		debug_print("Writing to stream:");
		this->WriteToByteStream(stream);

		for(size_t looper=0; looper<PAGE_SIZE; ++looper)
		{
			buffer[looper] = 0;
		}

		debug_print("Reading from stream:");
		this->ReadFromByteStream(stream);
		debug_print("\nproblem here???");
		*/
		printf("\nPage Start");
//		Header h;
//		this->GetHeader(h);
		printf("\nHeader Info:");
		printf("\n ID : %d", id);
		printf("\n NEXT : %d", next);
		printf("\n PREV : %d", prev);
		printf("\n TYPE : %d", pt);
		printf("\n TUPCNT : %d", tuplecount);
		printf("\n MAXTUP : %d", maxtuple);
		printf("\n OTSIZE : %d", otsize);
		 
		
		OffsetTable * otptrtmp = (OffsetTable *)otptrstart;
		for(size_t looper = 0; looper<otsize; ++looper)
		{
			if (looper == 0 )
			{
				otptrtmp = (OffsetTable *)otptrstart;
			}
			else
			{
				otptrtmp = (struct OffsetTable *)(buffer /*+ sizeof(struct Header)*/ + (sizeof(struct OffsetTable) * BYTEFACTOR * looper) );
			}
			//debug_print1("\nOT Address : %d", (int)otptrtmp);
			printf("\nKey : %d", otptrtmp->key);
			printf("\nsize : %d", otptrtmp->size);
#ifdef WIN32
			printf("\nValue : %s", (char*)GetRecDataPtr(otptrtmp));
#endif			
			printf("\nValue (HEX): ");
			 
            
            for(size_t i = 0; i < otptrtmp->size; ++i)
            {
            	printf("%.2X ", GetRecDataPtr(otptrtmp)[i]);
            }
			 
            
			//debug_print1("\nValue Address : %d", (int)otptrtmp->ptr);
		}
		printf("\nPage End");
		 
		/*
		//Testing endian in buffer code
		debug_print("\nprinting buffer start>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>:\n");
		for(size_t looper=0; looper<PAGE_SIZE; ++looper)
		{
			debug_print1("%d", buffer[looper]);
		}
		debug_print("\nprinting buffer end  >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>:");
		
		byte stream[PAGE_SIZE];
		debug_print("Writing to stream:");
		this->WriteToByteStream(stream);
		
		debug_print("\nprinting stream start>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>:\n");
		for(size_t looper=0; looper<PAGE_SIZE; ++looper)
		{
			debug_print1("%d", stream[looper]);
		}
		debug_print("\nprinting stream end  >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>:");

		for(size_t looper=0; looper<PAGE_SIZE; ++looper)
		{
			buffer[looper] = 0;
		}
		debug_print("\nBefore read printing buffer start>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>:\n");
		for(size_t looper=0; looper<PAGE_SIZE; ++looper)
		{
			debug_print1("%d", buffer[looper]);
		}
		debug_print("\nprinting buffer end  >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>:");
		
		debug_print("Reading from stream:");
		this->ReadFromByteStream(stream);
		debug_print("\nproblem here???");

		debug_print("\nprinting readed stream start>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>:\n");
		for(size_t looper=0; looper<PAGE_SIZE; ++looper)
		{
			debug_print1("%d", buffer[looper]);
		}
		debug_print("\nprinting readed stream end  >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>:");
		*/
		return true;
	}
	
	//To extract the first tuple on the page
	void GetFirstTuple(RECORD& r)
	{
		if(this->otsize != 0)
		{
			OffsetTable * rtmp = (struct OffsetTable *)otptrstart;
			r.key = rtmp->key;
			//debug_print1("\nKey for first record: %d", r.key);
		}
	}
	
	//To extract the last tuple on the page
	void GetLastTuple(RECORD& r)
	{
		OffsetTable* ottmp;
		if(this->otsize != 0)
		{
			if(this->otsize == 1)
			{
				ottmp = (struct OffsetTable *)(buffer /*+ sizeof(struct Header)*/);
			}
			else
			{
				ottmp = (struct OffsetTable *)(buffer /*+ sizeof(struct Header)*/ + (sizeof(struct OffsetTable) * BYTEFACTOR * (this->otsize - 1)) );
			}
			//debug_print1("\n----> OT Size: %d", this->otsize);
			//debug_print1("\n----> Last key: %d", ottmp->key);
			//this->CopyBuffer((byte *)ottmp->key, (byte*)&r.key, sizeof(ottmp->key));
			r.key = ottmp->key;
			r.size = ottmp->size;
			r.value = GetRecDataPtr(ottmp);
			//debug_print1("\nKey for last record: %d", r.key);
	
			/*
			//Temporary checking must be deleted
			if(otsize == 2)
			{
				OffsetTable * rtmp = (struct OffsetTable *)otptrstart;
				r.key = rtmp->key;
				//debug_print1("\nOT = 2: Key for first record: %d", r.key);
			}
			//Temporary checking must be deleted end
			 */
		}
	}
/*
	void GetHeader(Header& h)
	{
		this->CopyBuffer((byte *)headerptr, (byte *)&h, sizeof(h) );
		
		//Endian conversion done at writetobytestream method
		//h.LittleEndian();
		
		this->id = h.id;
		this->next = h.next;
		this->prev = h.prev;
		this->pt = h.pt;
		this->tuplecount = h.tuplecount;
		this->maxtuple = h.maxtuple;
		this->otsize = h.otsize;
	}
*/

	//To make sure that page read the buffer as endian independent
	void ReadFromByteStream(byte * stream)
	{	

		id = ReadPidFromMem(stream);
		stream += sizeof(id);

		next = ReadPidFromMem(stream);
		stream += sizeof(next);

		prev = ReadPidFromMem(stream);
		stream += sizeof(prev);

        pt = *stream;
        stream += sizeof(pt);
		
        tuplecount = ReadBigEndian16FromMem(stream);
		stream += sizeof(tuplecount);

		otsize = ReadBigEndian16FromMem(stream);
		stream += sizeof(otsize);

		maxtuple = ReadBigEndian16FromMem(stream);
		stream += sizeof(maxtuple);
		
//		Header h;
//		this->SetHeader(h);

		byte* streamstart = stream - 1;
		OffsetTable ottmp;
		//Now reading the offsettable information
        for(size_t i = 0; i < this->otsize; ++i)
        {        
    		void* otptrtmp = this->buffer /*+ sizeof(Header)*/ + (sizeof(OffsetTable) * i);

    		//debug_print("\n");
    		for(size_t l = 0; l < sizeof(buffer); ++l)
    		{
    			//debug_print1(" %.2X ", stream[l]);
    		}

            //debug_print("\n key in stream: ");
            //printValue(stream, sizeof(ottmp.key));
    		ottmp.key = ReadBigEndian16FromMem(stream);
        	stream += sizeof(ottmp.key);
            //debug_print1(": key : %d", ottmp.key);

            
            //debug_print("\n size in stream: ");
            //printValue(stream, sizeof(ottmp.size));
        	ottmp.size= ReadBigEndian16FromMem(stream);
        	stream += sizeof(ottmp.size);
            //debug_print1(": size : %d", ottmp.size);

            //debug_print("\n offset in stream: ");
            //printValue(stream, sizeof(ottmp.offset));
    		ottmp.offset = ReadBigEndian16FromMem(stream);
        	stream += sizeof(ottmp.offset);        	
            //debug_print1(": ottmp.offset : %d", ottmp.offset);

            //debug_print("\nValue in stream: ");
            //printValue((byte*)(streamstart + ottmp.offset), ottmp.size);

    		this->CopyBuffer((byte*)(streamstart + ottmp.offset), GetRecDataPtr(&ottmp), ottmp.size);

            //debug_print("\nValue as readed stream: ");
            //printValue(GetRecDataPtr(&ottmp), ottmp.size);
    		
    		this->CopyBuffer((byte*)&ottmp, (byte*)otptrtmp, sizeof(ottmp));
            //debug_print("\nIs every thing ok");
        }
	}

	
	//Write
public:
	//To set the current page ID
	void SetID(PID id)
	{
		this->id = id;
	}
	//To set the previous page for current page
	void SetPrevPageID(PID id)
	{
		this->prev = id;
	}
	//To set the next page for current page
	void SetNextPageID(PID id)
	{
		this->next = id;
	}
	//To set the type of the page, to be used in BTREE implmentation
	void SetType(PageType pt)
	{
		this->pt = pt;
	}

	//To mark the page as pinned to avoid swap
	void SetPinned(bool pinned)
	{
		this->ispinned = pinned;
	}

	//To mark the page as modified
	void SetModified(bool modified)
	{
		this->ismodified = modified;
	}
	//To insert tuple
	/*
	 *Special Case1: 
	 *It is not the responsibility of this method to check if the page is full
	 *Above layer should call this method if the page contains enough space to hold the record
	*/
	bool PutTuple(RECORD& r)
	{
		
		this->SetPointers();		

		bool sortedinsert = true;
		RECORD temper;
		if(this->CountTuples()>0)
		{
			this->GetLastTuple(temper);

			debug_print("\nLast tuple before put:");
			debug_print1("\nKey : %d", temper.key);
			//debug_print("\nValue : %s", temper.value);
			debug_print1("\nSize : %d", temper.size);
		}

		/*
		 * Code to add the tuple to page
		 * if record not added to page it will return false		 
		 */

		if (otsize == 0 || (r.key>temper.key && this->CountTuples()<this->maxtuple))
		{
            void* recptr;

			if(otsize == 0)
			{
				debug_print("\nCase for very first record");
				recptr = (class RECORD *)(buffer + (sizeof(buffer) - (r.size * BYTEFACTOR)));
				//debug_print1("\n Buffer : %d", buffer);
				//debug_print1("\n sizeof(buffer) : %d", sizeof(buffer));
				//debug_print1("\n (sizeof(buffer) - (r.size * BYTEFACTOR)) : %d", (sizeof(buffer) - (r.size * BYTEFACTOR)));
				//debug_print1("\n recptr : %d", recptr);
			}
            else
			{
				debug_print("\nCase for very last record on page");
				recptr = (byte*)temper.value - (r.size * BYTEFACTOR);
				//debug_print1("\n temper.value : %d", temper.value);
				//debug_print1("\n (r.size * BYTEFACTOR) : %d", (r.size * BYTEFACTOR));
				//debug_print1("\n recptr : %d", recptr);
			}

			//Here we should also check that that record should be inserted as per its sorted location
			//recptr = (class RECORD *)(buffer + (PAGE_SIZE - (sizeof(r) * this->tuplecount * BYTEFACTOR)));

			debug_print("\nRecord larger then last record and tuple count is less then max");
			debug_print1("\nKey : %d", r.key);
			//debug_print1("\nValue : %s", r.value);
			debug_print1("\nSize : %d", r.size);
			
			
			this->CopyBuffer((byte *)r.value, (byte *)recptr, r.size );

			
			//debug_print("\nValue to copy");
			////debug_print("\nValue : %s", r.value);
			//debug_print("\n");

			char* r1;
			r1 = (char *)recptr;
			
			//debug_print("\nValue copied");
			////debug_print("\nValue : %s", r1);
			//debug_print("\n");
			
			//These values are subjected to change as the header informton is also kept for each page
			
			OffsetTable ot;
			ot.key = r.key;
			ot.size = r.size;
			ot.offset = GetRecDataOffsetFromPtr(recptr);
			this->CopyBuffer((byte *)&ot, (byte *)otptr, sizeof(ot) );
			
			debug_print("\nOT to copy");
			debug_print1("\nKey : %d", ot.key);
			////debug_print1("\nPtr : %s", ot.ptr);
			debug_print("\n");

			OffsetTable* ot1;
			ot1 = (struct OffsetTable *)otptr;
			
			debug_print("\nOT copied");
			debug_print1("\nKey : %d", ot1->key);
			//debug_print("\nPtr : %s", ot1->ptr);
			debug_print("\n");
			
			//Now update record pointer to next point
			
			//debug_print1("\nRecord pointer before:%d", this->recptr);
			//debug_print1("\nSize of record:%d", (r.size * BYTEFACTOR));
			
			//this->recptr = (byte*)this->recptr - (r.size * BYTEFACTOR);
			
			//debug_print1("\nRecord pointer after:%d", this->recptr);
			
		}
		else if(sortedinsert = true)
		{
			//Case to insert record in between the records in page

			debug_print("\nEfforts for sorted insert :-)");
			
			OffsetTable * otptrtmp = (OffsetTable *)otptrstart;
			OffsetTable otptrbuf;
			RECORD rbuf;
			rbuf.key = 0;//To suppress comiler warnings
			rbuf.size = 0;//To suppress comiler warnings
			rbuf.value = NULL;//To suppress comiler warnings
			OffsetTable otptrbuf1;
			RECORD rbuf1;
			rbuf1.value = NULL;
			//RECORD* rtmp;  unused variable?!
			void* recptrtmp = NULL;
			bool shift = false;
			bool rbufini = false;
			bool rbuf1ini = false;

			size_t looper = 0;
			for(looper = 0; looper<otsize; ++looper)
			{
				if (looper == 0 )
				{
					otptrtmp = (OffsetTable *)otptrstart;
					recptrtmp = (class RECORD *)(buffer + (sizeof(buffer) - (otptrtmp->size * BYTEFACTOR)));
					//debug_print1("\nrecptrtmp first:%d", recptrtmp);
				}
				else
				{
					otptrtmp = (struct OffsetTable *)(buffer /*+ sizeof(struct Header)*/ + (sizeof(struct OffsetTable) * BYTEFACTOR * looper ) );
					//debug_print1("\nrecptrtmp before:%d", recptrtmp);
					//debug_print1("\nsize to add:%d", otptrtmp->size);
					recptrtmp = GetRecDataPtr(otptrtmp);//(byte*)recptrtmp - (otptrtmp->size * BYTEFACTOR);
					//debug_print1("\nrecptrtmp other:%d", recptrtmp);
					//recptrtmp = (class RECORD *)(buffer + (PAGE_SIZE - (sizeof(RECORD) * BYTEFACTOR * (looper + 1))));
				}
				
				if (shift == true)
				{
					otptrbuf1.key = otptrtmp->key;
					otptrbuf1.size = otptrtmp->size;
					otptrbuf1.offset = otptrtmp->offset;

					rbuf1.key = otptrtmp->key;
					rbuf1.size = otptrtmp->size;
					//rbuf1.value = (byte *)malloc(otptrtmp->size);				
					rbuf1.value = (byte*)b0;				
					rbuf1ini = true;
					this->CopyBuffer(GetRecDataPtr(otptrtmp), (byte *)rbuf1.value, r.size);
										
					debug_print("\nValue in buffer for shifting");
					debug_print1("\nKey : %d", rbuf.key);
					////debug_print("\nValue : %s", rbuf.value);
					debug_print1("\nsize : %d", rbuf.size);
					
					this->CopyBuffer((byte *)recptrtmp, (byte *)rbuf1.value, otptrtmp->size);
					
					debug_print("\nAfter:Value buffered for next shifting");
					debug_print1("\nKey : %d", rbuf1.key);
					////debug_print1("\nValue : %s", rbuf1.value);
					debug_print1("\nsize : %d", rbuf1.size);

					recptrtmp = ((byte*)recptrtmp + otptrtmp->size) - otptrtmp->size;

					//this->CopyBuffer((byte *)&rbuf, (byte *)otptrtmp->ptr, sizeof(rbuf));
					this->CopyBuffer((byte *)rbuf.value, (byte *)recptrtmp, otptrtmp->size);
					
					otptrbuf.offset = GetRecDataOffsetFromPtr(recptrtmp);
										
					this->CopyBuffer((byte *)&otptrbuf, (byte *)otptrtmp, sizeof(otptrbuf) );

					otptrbuf.key = otptrbuf1.key;
					otptrbuf.size = otptrbuf1.size;
					otptrbuf.offset = otptrbuf1.offset;

					rbuf.key = rbuf1.key;
					rbuf.size = rbuf1.size;
					//rbuf.value = rbuf1.value;
					this->CopyBuffer((byte *)rbuf1.value, (byte *)rbuf.value, rbuf1.size );
					
				}				
				else if(r.key < otptrtmp->key)
				{
					
					//Some special work for variable size records
					void* recptrtmps;
					//void* recptrtmpsprev;
					OffsetTable * otptrtmps;
					//OffsetTable * otptrtmpsprev;
					for (size_t looper1 = otsize - 1; looper1>=looper && looper1>=0 ; --looper1)
					{
						//debug_print1("\nLooper1 : %d", looper1);
						//debug_print1("\nLooper : %d", looper);
								  /*(struct OffsetTable *)(buffer + (sizeof(struct OffsetTable) * BYTEFACTOR * looper ) );*/
						otptrtmps = (struct OffsetTable *)(buffer + /*sizeof(struct Header) +*/ (sizeof(struct OffsetTable) * BYTEFACTOR * looper1 ) );						
						recptrtmps = GetRecDataPtr(otptrtmps)/*otptrtmps->ptr*/;
						//debug_print("\nrecptrtmp in loop %d : %d", looper1,  recptrtmp);

						debug_print("\nshifting");
						debug_print1("\nfrom : %d", recptrtmps);
						debug_print1("\nkey : %d", otptrtmps->key);
						debug_print1("\nsize : %d", otptrtmps->size);
						//PrintValue((byte*)recptrtmps, otptrtmp->size);

						for(int looper2 = 0; looper2 <TUPLEDATAMAX ; looper2++)
						{
							b0[looper2] = '\0';
						}
						
						this->CopyBuffer((byte*)recptrtmps, (byte*)b0, otptrtmps->size/*otptrtmp->size*/);
						otptrtmps->offset = GetRecDataOffsetFromPtr((byte*)recptrtmps - r.size)/*(byte*)recptrtmps - r.size*/;
						this->CopyBuffer((byte*)b0, (byte*)((byte*)recptrtmps - r.size)/*otptrtmps->ptr*/, otptrtmps->size);
						debug_print1("\nto : %d", otptrtmps->offset);
						//debug_print("\nBuffer");
						//PrintValue((byte*)b0, otptrtmp->size);

						debug_print("\nshifting offset");
						debug_print1("\nfrom : %d", otptrtmps);
						debug_print1("\nkey : %d", otptrtmps->key);
						debug_print1("\nsize : %d", otptrtmps->size);
						byte* ottmp = ((byte*)otptrtmps + sizeof(struct OffsetTable));
						this->CopyBuffer((byte*)otptrtmps, (byte*)ottmp, sizeof(struct OffsetTable));
						debug_print("\ncopied offset");
						debug_print1("\noriginal : %d", otptrtmps);
						debug_print1("\nadded : %d", sizeof(struct OffsetTable));
						debug_print1("\nto : %d", ottmp);
						debug_print1("\nkey : %d", ((OffsetTable *)ottmp)->key);
						debug_print1("\nsize : %d", ((OffsetTable *)ottmp)->size);
					}
					
					debug_print1("\ncurrent at : %d", recptrtmp);
					debug_print1("\nkey : %d", r.key);
					debug_print1("\nsize : %d", r.size);
					//PrintValue((byte*)r.value, r.size);
					recptrtmp = ((byte*)recptrtmp + otptrtmp->size) - r.size;
					this->CopyBuffer((byte *)r.value, (byte *)recptrtmp, r.size );
					
					OffsetTable ot;
					ot.key = r.key;
					ot.size = r.size;
					ot.offset = GetRecDataOffsetFromPtr(recptrtmp)/*recptrtmp*/;
					debug_print1("\ncurrent offset at : %d", otptrtmp);
					this->CopyBuffer((byte *)&ot, (byte *)otptrtmp, sizeof(ot) );

					shift = true;

					
					/*
					//debug_print("\nSpace for sorted insert found");
					otptrbuf.key = otptrtmp->key;
					otptrbuf.size = otptrtmp->size;
					otptrbuf.offset = otptrtmp->offset;
				
					rbuf.key = otptrtmp->key;
					rbuf.size = otptrtmp->size;

					//debug_print("\nCurrent value to set: mitte:");
					//debug_print1("\nKey : %d", r.key);
					//debug_print1("\nSize : %d", r.size);
					////debug_print1("\nValue : %s", r.value);
					//debug_print1("\nr.value pointer: %d", (int)r.value);
					
					//These malloc causing problems ;-(
					//rbuf.value = (byte*)malloc(rbuf.size);
					rbuf.value = (byte*)b1;
					rbufini = true;
					this->CopyBuffer(GetRecDataPtr(otptrtmp), (byte *)rbuf.value, otptrtmp->size );
					//This will cause value overriding
					//rbuf.value = (char*)otptrbuf.ptr;
					
					//debug_print("\nCurrent value to set:");
					//debug_print1("\nKey : %d", r.key);
					//debug_print1("\nSize : %d", r.size);
					////debug_print1("\nValue : %s", r.value);
					
					//debug_print("\n1st Value buffered for shifting");
					//debug_print1("\nKey : %d", rbuf.key);
					////debug_print1("\nValue : %s", rbuf.value);
					//debug_print1("\nSize : %d", rbuf.size);
					
					//this->CopyBuffer((byte *)&r, (byte *)this->recptr, sizeof(r) );
					this->CopyBuffer((byte *)r.value, (byte *)recptrtmp, r.size );

					//free (tmpvalue);

					//debug_print("\nValue to copy");
					//debug_print1("\nKey : %d", r.key);
					////debug_print1("\nValue : %s", r.value);
					//debug_print1("\nSize : %d", r.size);
					//debug_print("\n");

					char* r1;
					//r1 = (class RECORD *)this->recptr;
					r1 = (char *)recptrtmp;
					
					//debug_print("\nValue copied");
					////debug_print1("\nValue : %s", r1);
					//debug_print("\n");
					
					OffsetTable ot;
					ot.key = r.key;
					ot.size = r.size;
					ot.offset = GetRecDataOffsetFromPtr(recptrtmp);
					this->CopyBuffer((byte *)&ot, (byte *)otptrtmp, sizeof(ot) );

					//debug_print("\nOT to copy");
					//debug_print1("\nKey : %d", ot.key);
					//debug_print1("\nSize : %d", ot.size);
					////debug_print1("\nPtr : %s", (char*)ot.ptr);
					//debug_print("\n");

					OffsetTable* ot1;
					ot1 = (struct OffsetTable *)otptrtmp;
					
					//debug_print("\nOT copied");
					//debug_print1("\nKey : %d", ot1->key);
					//debug_print1("\nSize : %d", ot1->size);
					////debug_print1("\nPtr : %s", (char*)ot1->ptr);
					//debug_print("\n");

					//debug_print("\nNow starts shifting");
					shift = true;	
					*/
					
				}
				
				if(shift == true)
				{
					//debug_print("\nI hope every thing is shifted by now, breaking");
					break;
				}
			}		
			
			/*
			//debug_print1("\nFinal out:", looper);
			//debug_print1("\nLooper : %d", looper);
			
			//debug_print1("\nExit Looper : %d", looper);
			//debug_print1("\nOT Size : %d", otsize);

			//debug_print1("\nPage ID : %d", this->GetID());

			//debug_print1("\nValue In Last shifted in buffer");
			//debug_print1("\nKey : %d", rbuf.key);
			////debug_print1("\nValue : %s", rbuf.value);
			//debug_print1("\nsize : %d", rbuf.size);
			
			//here the case for last record
			otptrtmp = (struct OffsetTable *)(buffer  + (sizeof(struct OffsetTable) * BYTEFACTOR * looper ) );
			//debug_print1("\nrecptrtmp before:%d", (int)recptrtmp);
			//debug_print1("\nsize to add:%d", (int)rbuf.size);
			recptrtmp = (byte*)recptrtmp - rbuf.size;
			//debug_print1("\nrecptrtmp after:%d", (int)recptrtmp);
			
			otptrbuf.key = rbuf.key;
			otptrbuf.size = rbuf.size;

			//debug_print("\nBefore: Value In Last shifted in buffer");
			//debug_print1("\nKey : %d", rbuf.key);
			////debug_print1("\nValue : %s", rbuf.value);
			//debug_print1("\nsize : %d", rbuf.size);

			//this->CopyBuffer((byte *)&rbuf, (byte *)otptrtmp->ptr, sizeof(rbuf) );
			this->CopyBuffer((byte *)rbuf.value, (byte *)recptrtmp, rbuf.size);
					
			otptrbuf.offset = GetRecDataOffsetFromPtr(recptrtmp);
			
			this->CopyBuffer((byte *)&otptrbuf, (byte *)otptrtmp, sizeof(otptrbuf) );
			////debug_print1("\nValue in last shifted to buffer:%s", (char*)recptrtmp);

			//debug_print("\nAfter: Value In Last shifted in buffer");
			//debug_print1("\nKey : %d", rbuf.key);
			////debug_print1("\nValue : %s", rbuf.value);
			//debug_print1("\nsize : %d", rbuf.size);
			
			//we should free the temporary buffer used for shifting :-)
			 */
		}

		debug_print1("\nOT count before: %d",otsize);
		this->otsize++;
		debug_print1("\nOT count after: %d",otsize);
		debug_print1("\nTUple count before: %d",tuplecount);
		this->tuplecount++;
		debug_print1("\nTUple count after: %d",tuplecount);
		
		this->SetPointers();		
//		Header h;
//		this->SetHeader(h);

		/*
		debug_print("\nBuffer after puttuple: \n");
		for(int l = 0; l < sizeof(buffer); l++)
		{
			debug_print1(" %.2X ", buffer[l]);
		}
		*/
		
		return true;
	}
	
	//To delete tuple from the page
	bool RemoveTuple(RECORD& r)
	{
		/*
		 * Code to remove the tuple from page
		 * if record not found on page or record not delete for any reason it will return false
		 * other wise will delete the record
		 */
		OffsetTable * otptrtmp = (OffsetTable *)otptrstart;
		debug_print1("\nOffsettable start for while delete: %d", otptrstart);
		bool shift = false;
		OffsetTable * otptrprev = NULL;
		void * rprev = NULL;
		size_t sizeprev = 0;
		int sizeprevacc = 0;
		size_t looper;
		for(looper = 0; looper<otsize; ++looper)
		{
			if (looper == 0 )
			{
				otptrtmp = (OffsetTable *)otptrstart;
				//debug_print1("\nOffsettable for first value: %d", otptrtmp);
			}
			else
			{
				otptrtmp = (struct OffsetTable *)(buffer /*+ sizeof(struct Header)*/ + (sizeof(struct OffsetTable) * BYTEFACTOR * looper) );
				//debug_print2("\nOffsettable for %d value: %d", looper, otptrtmp);
			}			
			
			if(shift == true)
			{
				/*
				 * critical point for variable length records
				 * We have to update this to handle variable length records as well
				 */
				
				debug_print("\nValue shifted upon up:");
				debug_print1("\nKey : %d", otptrprev->key);
				debug_print1("\nsize : %d", otptrprev->size);
				//debug_print1("\nvalue : %s", rprev);
				debug_print1("\nvalue address : %d", rprev);

				debug_print("\nValue shifted up:");
				debug_print1("\nKey : %d", otptrtmp->key);
				debug_print1("\nsize : %d", otptrtmp->size);
				//debug_print1("\nvalue : %s", otptrtmp->ptr);
				//debug_print1("\nvalue address : %d", otptrtmp->ptr);
							
				
				//Shifting record
				//Caused a lot of problem
				//Consumed 2 days of mine :-)
				
				//debug_print1("\nfrom value : %s", otptrtmp->ptr);
				//debug_print1("\nfrom value address : %d", otptrtmp->ptr);
				//debug_print1("\nfrom value address : %d", otptrtmp->ptr);
				//debug_print1("\nto value : %s", rprev);
				debug_print1("\nto value address : %d", rprev);
				
				//debug_print("\nBefore : rprev : %d : , sizeprev : %d : , otptrtmp->size : %d",rprev,sizeprev,otptrtmp->size);
				rprev = ((byte*)rprev + sizeprev) - (otptrtmp->size + sizeprevacc);
				sizeprevacc += abs(sizeprev  - otptrtmp->size);

				//debug_print("\nrprev : %d : , sizeprev : %d : , otptrtmp->size : %d",rprev,sizeprev,otptrtmp->size);
				this->CopyBuffer(GetRecDataPtr(otptrtmp), (byte *)rprev, otptrtmp->size);
				//rprev = otptrtmp->ptr;

				
				char *tmp = (char*)GetRecDataPtr(otptrtmp); 
				
				otptrtmp->offset = (byte*)rprev-(byte*)buffer;
				
				//Shifting offset table
				this->CopyBuffer((byte *)otptrtmp, (byte *)otptrprev, sizeof(OffsetTable) );

				
				debug_print("\nIs Value shifted up???:");
				debug_print1("\nKey : %d", otptrprev->key);
				debug_print1("\nsize : %d", otptrprev->size);
				
				otptrprev->offset = GetRecDataOffsetFromPtr(rprev);
				/*
				//debug_print1("\nvalue : %s", rprev);
				debug_print1("\nvalue address: %d", rprev);

				//debug_print1("\nvalue : %s", otptrtmp->ptr);
				debug_print1("\nvalue address : %d", otptrtmp->ptr);
				*/

				//Now make current as previous for next shifting
				//if(!((looper + 1) == otsize))
				//{
					otptrprev = otptrtmp;
					rprev = tmp;
					sizeprev = otptrtmp->size;
					/*
					debug_print("\nValue again buffered for shifting upon:");
					debug_print1("\nKey : %d", otptrprev->key);
					debug_print1("\nsize : %d", otptrprev->size);
					//debug_print1("\nvalue : %s", rprev);
					debug_print1("\nvalue address: %d", rprev);
					*/
				//}
			}
			else if(otptrtmp->key == r.key)
			{
				debug_print("\nTuple found:");
				debug_print1("\nKey : %d", otptrtmp->key);
				debug_print1("\nsize : %d", otptrtmp->size);
				//debug_print1("\nvalue : %s", (char*)otptrtmp->ptr);
				//PrintValue(GetRecDataPtr(otptrtmp), otptrtmp->size);
				//debug_print1("\nvalue address: %d", (int)otptrtmp->ptr);

				shift = true;
				otptrprev = otptrtmp;
				rprev = GetRecDataPtr(otptrtmp);
				
				
				
				/*
				debug_print("\nFirst value buffered for shifting upon after remove:");
				debug_print1("\nKey : %d", otptrprev->key);
				debug_print1("\nsize : %d", otptrprev->size);
				//debug_print1("\nvalue : %s", rprev);
				debug_print1("\nvalue address: %d", rprev);
				*/
			}
			if(shift == false)
			{
				/*
				debug_print1("\nCurrent key:%d", otptrtmp->key);
				debug_print1("\nCurrent size:%d", otptrtmp->size);
				//debug_print1("\nCurrent value:%s", otptrtmp->ptr);
				debug_print1("\nCurrent value address:%d", otptrtmp->ptr);
				debug_print1("\nKey to compage:%d",r.key);
				*/
			}
		}
		
		if(shift == false)
		{
			debug_print("\nTuple not found:");
			return false;
		}
		else
		{
			//Not needed in this implementation, as its buffer manager which handle all these after release call
			//free (otptrtmp->ptr);
		}

		this->tuplecount--;
		this->otsize--;
		this->SetPointers();
//		Header h;
//		this->SetHeader(h);
		return true;
	}	
	
	//To copy buffer
	void CopyBuffer(byte * copyfrom, byte * copyto, size_t copysize)
	{
		//debug_print("\nCopy buffer trace:");
		for (unsigned int looper = 0; looper < copysize * BYTEFACTOR; looper++)
		{
			*(copyto+looper) = *(copyfrom+looper);
			/*
			debug_print1("\nLooper :%d", looper);
			debug_print1("\nCopy from :%d", (copyfrom+looper));
			debug_print1("\nCopy to :%d", (copyto+looper));
			*/
		}
		//debug_print("\nCopy buffer trace end:");
	}
	
	//To check if the page is full
	bool IsFull()
	{
		if (tuplecount >= maxtuple)
		{
			//No space available
			debug_print("\nPage is full");
			return true;
		}
		return false;
	}
	
	//To split the page into two when page is full and current record is to be inserted into same page
	bool HalfSplit(Page* pg)
	{
		//start from here
		debug_print("\nSplitting");
		OffsetTable * otptrtmp = (OffsetTable *)otptrstart;
		//debug_print1("\notsize : %d", otsize);
		size_t looper = (otsize/2) + 1;
		while( looper<=otsize)//looper is not incremented, because delete will do shift so increment is not needed, instead otsize will decrease automatically :-)
		{
			if (looper == 0 )
			{
				otptrtmp = (OffsetTable *)otptrstart;
			}
			else
			{
				otptrtmp = (struct OffsetTable *)(buffer /*+ sizeof(struct Header)*/ + (sizeof(struct OffsetTable) * BYTEFACTOR * (looper - 1)) );
			}

			RECORD rtmp;
			rtmp.key = otptrtmp->key;
			rtmp.size = otptrtmp->size;
			//rtmp.value = (byte*)malloc(100);
			rtmp.value = (byte*)b2;
			this->CopyBuffer(GetRecDataPtr(otptrtmp), (byte *)rtmp.value, otptrtmp->size );
			
			//rtmp.value = (char*)otptrtmp->ptr;
			//rtmp.value = (char*)malloc(otptrtmp->size);

			debug_print("\nValue to shift:");
			debug_print1("\nKey : %d", rtmp.key);
			debug_print1("\nsize : %d", rtmp.size);
			////debug_print1("\nvalue : %s", rtmp.value);
			//debug_print1("\nvalue address: %d", (int)rtmp.value);
			
			//debug_print("\n>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
			//debug_print("\nSplit condition before");
			//debug_print("\n>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
			//this->PrintAllTuples();
			//pg->PrintAllTuples();
			//debug_print("\n>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
			//debug_print("\nNow inserting tuple on new page");
			pg->PutTuple(rtmp);
			//debug_print("\nNow removing tuple from current page");
			this->RemoveTuple(rtmp);
			this->SetPointers();
			//free (rtmp.value);
			//debug_print("\n>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
			//debug_print("\nSplit condition after");
			//debug_print("\n>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
			//this->PrintAllTuples();
			//pg->PrintAllTuples();
			//debug_print("\n>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		}

		/*
		debug_print("\n>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		debug_print("\nSplit condition");
		debug_print("\n>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		this->PrintAllTuples();
		pg->PrintAllTuples();
		debug_print("\n>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		*/

		return true;
	}
	
	//To split the page into two when page is full and current record is to be inserted into same page
	bool HalfiSplit(Page* pg)
	{
		//start from here
		debug_print("\nSplitting");
		OffsetTable * otptrtmp = (OffsetTable *)otptrstart;
		//debug_print1("\notsize : %d", otsize);
		size_t looper = (otsize/2) + 2;
		while(looper<=otsize)//looper is not incremented, because delete will do shift so increment is not needed, instead otsize will decrease automatically :-)
		{
			if (looper == 0 )
			{
				otptrtmp = (OffsetTable *)otptrstart;
			}
			else
			{
				otptrtmp = (struct OffsetTable *)(buffer /*+ sizeof(struct Header)*/ + (sizeof(struct OffsetTable) * BYTEFACTOR * (looper - 1)) );
			}

			RECORD rtmp;
			rtmp.key = otptrtmp->key;
			rtmp.size = otptrtmp->size;
			//rtmp.value = (byte*)malloc(100);
			rtmp.value = (byte*)b2;
			this->CopyBuffer(GetRecDataPtr(otptrtmp), (byte *)rtmp.value, otptrtmp->size );
			
			//rtmp.value = (char*)otptrtmp->ptr;
			//rtmp.value = (char*)malloc(otptrtmp->size);

			//debug_print("\nValue to shift:");
			//debug_print1("\nKey : %d", rtmp.key);
			//debug_print1("\nsize : %d", rtmp.size);
			////debug_print1("\nvalue : %s", rtmp.value);
			//debug_print1("\nvalue address: %d", (int)rtmp.value);
			
			//debug_print("\n>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
			//debug_print("\nSplit condition before");
			//debug_print("\n>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
			//this->PrintAllTuples();
			//pg->PrintAllTuples();
			//debug_print("\n>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
			//debug_print("\nNow inserting tuple on new page");
			pg->PutTuple(rtmp);
			//debug_print("\nNow removing tuple from current page");
			this->RemoveTuple(rtmp);
			this->SetPointers();
			//free (rtmp.value);
			//debug_print("\n>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
			//debug_print("\nSplit condition after");
			//debug_print("\n>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
			//this->PrintAllTuples();
			//pg->PrintAllTuples();
			//debug_print("\n>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		}

		/*
		//debug_print("\n>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		//debug_print("\nSplit condition");
		//debug_print("\n>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		this->PrintAllTuples();
		pg->PrintAllTuples();
		//debug_print("\n>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		*/

		return true;
	}
	
	//To reset the pointer for tableoffset and record pointer
	void SetPointers(void)
	{
		
		//recptr = (class RECORD *)(buffer + (PAGE_SIZE - (sizeof(RECORD) * BYTEFACTOR * (this->tuplecount + 1))));
		//debug_print1("\n (sizeof(r) * BYTEFACTOR * (this->tuplecount + 1)) : %d", (sizeof(r) * BYTEFACTOR * (this->tuplecount + 1)));
		//debug_print1("\n (PAGE_SIZE - (sizeof(r) * BYTEFACTOR * (this->tuplecount + 1))) : %d", (PAGE_SIZE - (sizeof(r) * BYTEFACTOR * (this->tuplecount + 1))));

		//debug_print1("\nRECORD Pointer : %d", recptr);
		
		otptr = (struct OffsetTable *)(buffer /*+ sizeof(struct Header)*/ + (sizeof(struct OffsetTable) * BYTEFACTOR * this->otsize) );
		//debug_print1("\n (sizeof(struct OffsetTable) * BYTEFACTOR * this->otsize) : %d", (sizeof(struct OffsetTable) * BYTEFACTOR * this->otsize));
		//debug_print1("\n sizeof(struct Header) + (sizeof(struct OffsetTable) * BYTEFACTOR * this->otsize) : %d", sizeof(struct Header) + (sizeof(struct OffsetTable) * BYTEFACTOR * this->otsize));
		//debug_print1("\nSet pointer Value added to buffer for offset table:%d", (sizeof(struct Header) + (sizeof(struct OffsetTable) * BYTEFACTOR * this->otsize)));

	}
	
	//To update tuple on current page
	bool UpdateTuple(RECORD& r)
	{
		if(this->RemoveTuple(r))
		{
			debug_print("Update performed");
			return this->PutTuple(r);
		}
		else
		{
			debug_print("Update not performed");
			return false;
		}
	}
	
	//To move tuple from current page to the page provided as parameter
	bool MoveTuple(int index, Page* pg)
	{
		RECORD r;
		this->GetTupleByIndex(index, r);
		pg->PutTuple(r);
		return this->RemoveTuple(r);
		/*
		//start from here
		////debug_print("\nSplitting");
		OffsetTable * otptrtmp = (OffsetTable *)otptrstart;
		////debug_print1("\notsize : %d", otsize);
		for(size_t looper = 0; looper<otsize; ++looper)
		{
			if (looper == 0 )
			{
				otptrtmp = (OffsetTable *)otptrstart;
			}
			else
			{
				otptrtmp = (struct OffsetTable *)(buffer + sizeof(struct Header) + (sizeof(struct OffsetTable) * BYTEFACTOR * looper) );
			}

			if(looper == index)
			{
				RECORD * rtmp = (class RECORD *)otptrtmp->ptr;
				pg->PutTuple(*rtmp);
				this->RemoveTuple(*rtmp);
				this->SetPointers();
				return true;
			}
		}
		//debug_print("\nIndex to move not found");
		return false;
		*/
	}
	
/*	void SetHeader(Header& h)
	{
		h.id = this->id;
		h.next = this->next;
		h.prev = this->prev;
		h.pt = this->pt;
		h.tuplecount = this->tuplecount;
		h.otsize = this->otsize;
		h.maxtuple = this->maxtuple;
		
		//Endian conversion done at readfrombytestream method
		//h.LittleEndian();
		
		//debug_print1("\nSetting header : %d",sizeof(h));
		this->CopyBuffer((byte *)&h, (byte *)headerptr, sizeof(h) );
	}
*/	
	//To make sure that page write the buffer as endian independent
	void WriteToByteStream(byte * stream)
	{
		byte* streambegin = stream;
		//debug_print("\nWriting################################################################");

		WritePidToMem(stream, id);
		stream += sizeof(id);
		//debug_print1("\n header size : %d ", sizeof(id));

		WritePidToMem(stream, next);
		stream += sizeof(next);
		//debug_print1(" %d ", sizeof(next));

		WritePidToMem(stream, prev);
		stream += sizeof(prev);
		//debug_print1(" %d ", sizeof(prev));

        *stream = pt;
        stream += sizeof(pt);
		//debug_print1(" %d ", sizeof(pt));

        WriteBigEndian16ToMem(stream, tuplecount);
		stream += sizeof(tuplecount);
		//debug_print1(" %d ", sizeof(tuplecount));

		WriteBigEndian16ToMem(stream, otsize);
		stream += sizeof(otsize);
		//debug_print1(" %d ", sizeof(otsize));

		WriteBigEndian16ToMem(stream, maxtuple);
		stream += sizeof(maxtuple);
		//debug_print1(" %d ", sizeof(maxtuple));

		byte* streamstart = stream - 1;
		OffsetTable ottmp;
		//Now reading the offsettable information
		byte* tmpstr = stream;		
        for(size_t i = 0; i < this->otsize; ++i)
        {        
    		void* otptrtmp = this->buffer /*+ sizeof(Header)*/ + (sizeof(OffsetTable) * i);
    		this->CopyBuffer((byte*)otptrtmp, (byte*)&ottmp, sizeof(ottmp));    		

            //debug_print("\n As written");
            WriteBigEndian16ToMem(stream, ottmp.key);            
            //debug_print1(": Key after");
            //printValue(stream, sizeof(ottmp.key));
        	stream += sizeof(ottmp.key);
            //debug_print1(": Key : %d", ottmp.key);
            
        	WriteBigEndian16ToMem(stream, ottmp.size);
            //debug_print(": size after");
            //printValue(stream, sizeof(ottmp.size));
        	stream += sizeof(ottmp.size);
            //debug_print1(": size : %d", ottmp.size);
    		
    		WriteBigEndian16ToMem(stream, ottmp.offset);
            //debug_print(": offset after");
            //printValue(stream, sizeof(ottmp.offset));
        	stream += sizeof(ottmp.offset);
            //debug_print1(": Offset : %d", ottmp.offset);
    		
    		this->CopyBuffer(GetRecDataPtr(&ottmp), (byte*)(streamstart + ottmp.offset), ottmp.size);    		
            //debug_print("\nValue after: ");
            //printValue((byte*)(streamstart + ottmp.offset), ottmp.size);
            //debug_print("\nValue as written: ");
            //printValue(GetRecDataPtr(&ottmp), ottmp.size);

            //debug_print(": offset after value");
            //printValue(stream - sizeof(ottmp.offset), sizeof(ottmp.offset));
            //debug_print1(": Offset : %d", ottmp.offset);

        }	
        
        
        //debug_print("\n");
        
		for(size_t l = 0; l < sizeof(buffer); ++l)
		{
			//debug_print1(" %.2X ", tmpstr[l]);
		}
        
		//debug_print("\n\n\nReading again################################################################");
        //Testing the stream as we give it
        ReadFromByteStream(streambegin);
        
        //this->printAllTuples();

		//char c = getchar();
		
        
	}
	//Write End
	
};
#endif