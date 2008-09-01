fgl_ : fgl+ :: _fgl ;

fgl : [UnlabeldEdges] [UnlabeldNodes] :: UnlabeldGraph
	| DynGraph
	| Graphalgorithmen
	| StaticMonadGraph
	| GraphVisualation ;

Graphalgorithmen : DFS_TreeM ;

DFS_TreeM : ArtPoint
	| DFS_Tree ;

