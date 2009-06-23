SPL :  [Logging] ConcurrTrans Persistance [Statistics] BTree Ops [Memory_Budget] :: generatedSPL ;

Logging : [Logging_Finer] [Logging_Config] [Logging_Severe] [Logging_Evictor] [Logging_Cleaner] [Logging_Recovery] [Logging_DbLogHandler] [Logging_ConsoleHandler] [Logging_Info] Logging_Base [Logging_FileHandler] [Logging_Fine] [Logging_Finest] :: _Logging ;

ConcurrTrans : [Latches] [Transactions] [CheckLeaks] [FSync] :: _ConcurrTrans ;

Persistance : [Checksum] IIO [Environment_Locking] Checkpointer [DiskFullErro] [FileHandleCache] IICleaner :: _Persistance ;

IIO : [SynchronizedIO] IO :: OldIO
	| NIOAccess [DirectNIO] :: NewIO ;

NIOAccess : ChunkedNIO
	| NIO ;

Checkpointer : [CP_Bytes] [CP_Time] [Checkpointer_Daemon] :: _Checkpointer ;

IICleaner : [CleanerDaemon] Cleaner [LookAHEADCache] :: _IICleaner ;

BTree : [INCompressor] [IEvictor] [Verifier] :: _BTree ;

IEvictor : [Critical_Eviction] [EvictorDaemon] Evictor :: _IEvictor ;

Ops : [DeleteOp] [RenameOp] [TruncateOp] :: _Ops ;


%% //Semantic Dependencies
Evictor or EvictorDaemon or LookAHEADCache implies Memory_Budget;
Critical_Eviction implies INCompressor;
CP_Bytes implies CP_Time;
DeleteOp implies Evictor and INCompressor and Memory_Budget;
Memory_Budget implies Evictor and Latches;
TruncateOp implies DeleteOp;
Verifier implies INCompressor;
