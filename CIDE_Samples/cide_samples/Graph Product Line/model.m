SPL : [Base] [Benchmark] [Prog] Edges [Weighted] BaseImpl [SearchI] Algorithms :: GPL ;

Edges : Directed
	| Undirected ;

BaseImpl : EdgeImpl [EdgeObjects] :: _BaseImpl ;

EdgeImpl : GN_OnlyNeighbors | G_NoEdges | GEN_Edges ;

SearchI : SearchAlg SearchBase :: _SearchI ;

SearchAlg : DFS
	| BFS ;

Algorithms : [Number] [Connected] [Transpose] [MSTPrim] [MSTKruskal] [Shortest] [Cycle] [StronglyConnected] :: _Algorithms ;


%%

Prog implies Benchmark ;
GEN_Edges implies EdgeObjects ;
DFS or BFS or Number or Connected implies SearchBase;
Connected implies Undirected;
StronglyConnected implies Directed and DFS and Transpose;
Cycle implies DFS;
MSTPrim or MSTKruskal implies EdgeObjects and Undirected and Weighted;
Shortest implies Directed and Weighted;

