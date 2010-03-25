fgl_ : fgl+ :: _fgl ;

fgl : UnlabeldGraph+ :: UnlabeldGraph_
	| DynGraph
	| Graphalgorithmen+ :: Graphalgorithmen_
	| StaticMonadGraph
	| GraphViz ;

UnlabeldGraph : UnlabeldEdges
	| UnlabeldNodes ;

Graphalgorithmen : ArtPointM+ :: ArtPointM_
	| ConnectedComponent
	| BFSM+ :: BFSM_
	| DFS
	| Dominators
	| GVD
	| IndependentComponents
	| MaxFlow
	| MaxFlow2
	| MST
	| TransitiveClosure ;

ArtPointM : ArtPoint
	| DFS_Tree ;

BFSM : ShortestPath
	| BFS ;

