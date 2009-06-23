import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

// define shell of a Graph class

class Graph {
	LinkedList<Vertex> vertices;

	private LinkedList<EdgeIfc> edges;

	Graph() {
		vertices = new LinkedList<Vertex>();
		edges = new LinkedList<EdgeIfc>();
	}

	public VertexIter getVertices() {
		return new VertexIter(this);

	}

	public EdgeIter getEdges() {
		// if not using GEN than we must build the edges on the fly
		if (false && edgesNotBuilt) {
			edgesNotBuilt = false;
			edges = new LinkedList<EdgeIfc>();
			int k1 = 0, k2 = 0, sizeAV = 0;
			// G
			Neighbor tempNeighbor;// GN
			for (VertexIter vxiter = getVertices(); vxiter.hasNext();) {
				Vertex v = vxiter.next();
				sizeAV = v.adjacentVertices.size();// G
				sizeAV = v.adjacentNeighbors.size();// GN
				for (k2 = 0; k2 < sizeAV; k2++) {
					// G
					edges.add(new Edge(v, v.adjacentVertices.get(k2),
							v.weightsList.get(k2).intValue()));
					// GN
					tempNeighbor = v.adjacentNeighbors.get(k2);
					edges.add(new Edge(v, tempNeighbor.neighbor,
							tempNeighbor.weight));
				} // of k2
			} // of k1
		}

		return new EdgeIter() {
			private Iterator<EdgeIfc> iter = edges.iterator();

			public EdgeIfc next() {
				return iter.next();
			}

			public boolean hasNext() {
				return iter.hasNext();
			}
		};
	}

	boolean edgesNotBuilt = true;

	public void sortVertices(Comparator<Vertex> c) {
		Collections.sort(vertices, c);
	}

	public void sortEdges(Comparator<EdgeIfc> c) {
		Collections.sort(edges, c);
	}

	// methods whose bodies will be overridden later
	EdgeIfc addEdge(Vertex start, Vertex end, int weight) {
		// G
		start.addAdjacent(end);
		end.addAdjacent(start);// undirected
		start.setWeight(weight);
		end.addWeight(weight);
		if (true)
			return (EdgeIfc) start;
		// GN
		Neighbor e = new Neighbor(end, weight);
		addEdge(start, e);
		if (true)
			return e;
		// GEN
		Edge theEdge = new Edge(start, end, weight);
		edges.add(theEdge);
		start.addNeighbor(new Neighbor(end, theEdge));
		end.addNeighbor(new Neighbor(start, theEdge));// undirected
		if (true)
			return theEdge;
		return null;
	}

	public void addEdge(Vertex start, Neighbor theNeighbor) {
		start.addEdge(theNeighbor);
		Vertex end = theNeighbor.neighbor;
		end.addEdge(new Neighbor(start, theNeighbor.weight));
	}

	Vertex findsVertex(String name) {
		Vertex theVertex;

		// if we are dealing with the root
		if (name == null)
			return null;

		for (VertexIter vxiter = getVertices(); vxiter.hasNext();) {
			theVertex = vxiter.next();
			if (name.equals(theVertex.getName())) {
				return theVertex;
			}
		}
		return null;
	}

	void display() {
		System.out.println("******************************************");
		System.out.println("Vertices ");
		for (int i = 0; i < vertices.size(); i++)
			vertices.get(i).display();
		System.out.println("******************************************");
		System.out.println("Edges ");
		for (EdgeIter edgeiter = getEdges(); edgeiter.hasNext();)
			edgeiter.next().display();

		System.out.println("******************************************");
	}

	void addVertex(Vertex v) {
		vertices.add(v);
	}

	public Reader inFile; // File handler for reading

	public static int ch; // Character to read/write

	// timings
	static long last = 0, current = 0, accum = 0;

	public void runBenchmark(String FileName) throws IOException {
		try {
			inFile = new FileReader(FileName);
		} catch (IOException e) {
			System.out.println("Your file " + FileName + " cannot be read");
		}
	}

	public void stopBenchmark() throws IOException {
		inFile.close();
	}

	public int readNumber() throws IOException {
		int index = 0;
		char[] word = new char[80];
		int ch = 0;

		ch = inFile.read();
		while (ch == 32) {
			ch = inFile.read(); // skips extra whitespaces
		}

		while (ch != -1 && ch != 32 && ch != 10) // while it is not EOF, WS,
		// NL
		{
			word[index++] = (char) ch;
			ch = inFile.read();
		}
		word[index] = 0;

		String theString = new String(word);

		theString = new String(theString.substring(0, index)).trim();
		return Integer.parseInt(theString, 10);
	}

	public static void startProfile() {
		accum = 0;
		current = System.currentTimeMillis();
		last = current;
	}

	public static void stopProfile() {
		current = System.currentTimeMillis();
		accum = accum + (current - last);
	}

	public static void resumeProfile() {
		current = System.currentTimeMillis();
		last = current;
	}

	public static void endProfile() {
		current = System.currentTimeMillis();
		accum = accum + (current - last);
		System.out.println("Time elapsed: " + accum + " milliseconds");
	}

	// method is extended later
	public void run(Vertex v) {
		//
		connectedComponents();
		//
		System.out.println(" Cycle? " + cycleCheck());
		// 
		Graph gaux = kruskal();
		Graph.stopProfile();
		gaux.display();
		Graph.resumeProfile();
		//
		Graph gaux1 = prim(v);
		Graph.stopProfile();
		gaux1.display();
		Graph.resumeProfile();
		//
		numberVertices();
		//
		Graph gaux2 = strongComponents();
		Graph.stopProfile();
		gaux2.display();
		Graph.resumeProfile();
	}

	public EdgeIfc findsEdge(Vertex theSource, Vertex theTarget) {
		Vertex v1 = theSource;
		for (EdgeIter edgeiter = v1.getEdges(); edgeiter.hasNext();) {
			EdgeIfc theEdge = edgeiter.next();
			Vertex v2 = theEdge.getOtherVertex(v1);
			if ((v1.getName().equals(theSource.getName()) && v2.getName()
					.equals(theTarget.getName()))
					|| (v1.getName().equals(theTarget.getName()) && v2
							.getName().equals(theSource.getName())))
				return theEdge;
		}
		return null;
	}

	public void graphSearch(WorkSpace w) {
		// Step 1: initialize visited member of all nodes
		VertexIter vxiter = getVertices();
		if (vxiter.hasNext() == false) {
			return;
		}

		// Showing the initialization process
		while (vxiter.hasNext()) {
			Vertex v = vxiter.next();
			v.init_vertex(w);
		}

		// Step 2: traverse neighbors of each node
		for (vxiter = getVertices(); vxiter.hasNext();) {
			Vertex v = vxiter.next();
			if (!v.visited) {
				w.nextRegionAction(v);
				v.nodeSearch(w);
			}
		} // end for
	}

	public void numberVertices() {
		graphSearch(new NumberWorkSpace());
	}

	public void connectedComponents() {
		graphSearch(new RegionWorkSpace());
	}

	public Graph strongComponents() {

		FinishTimeWorkSpace FTWS = new FinishTimeWorkSpace();

		// 1. Computes the finishing times for each vertex
		graphSearch(FTWS);

		// 2. Order in decreasing & call DFS Transposal
		sortVertices(new Comparator<Vertex>() {
			public int compare(Vertex v1, Vertex v2) {
				if (v1.finishTime > v2.finishTime)
					return -1;

				if (v1.finishTime == v2.finishTime)
					return 0;
				return 1;
			}
		});

		// 3. Compute the transpose of G
		// Done at layer transpose
		Graph gaux = computeTranspose((Graph) this);

		// 4. Traverse the transpose G
		WorkSpaceTranspose WST = new WorkSpaceTranspose();
		gaux.graphSearch(WST);

		return gaux;

	} // of Strong Components

	public Graph computeTranspose(Graph the_graph) {
		int i;
		String theName;
		Map newVertices = new HashMap();

		// Creating the new Graph
		Graph newGraph = new Graph();

		// Creates and adds the vertices with the same name
		for (VertexIter vxiter = getVertices(); vxiter.hasNext();) {
			theName = vxiter.next().getName();
			Vertex v = new Vertex().assignName(theName);
			newGraph.addVertex(v);
			newVertices.put(theName, v);
		}

		Vertex theVertex, newVertex;
		Vertex theNeighbor;
		Vertex newAdjacent;
		EdgeIfc newEdge;

		// adds the transposed edges
		VertexIter newvxiter = newGraph.getVertices();
		for (VertexIter vxiter = getVertices(); vxiter.hasNext();) {
			// theVertex is the original source vertex
			// the newAdjacent is the reference in the newGraph to theVertex
			theVertex = vxiter.next();

			newAdjacent = newvxiter.next();

			for (VertexIter neighbors = theVertex.getNeighbors(); neighbors
					.hasNext();) {
				// Gets the neighbor object
				theNeighbor = neighbors.next();

				// the new Vertex is the vertex that was adjacent to theVertex
				// but now in the new graph
				newVertex = (Vertex) newVertices.get(theNeighbor.getName());

				// Creates a new Edge object and adjusts the adornments
				newEdge = newGraph.addEdge(newVertex, newAdjacent, 0);
			} // all adjacentNeighbors
		} // all the vertices

		return newGraph;

	} // of ComputeTranspose

	public boolean cycleCheck() {
		CycleWorkSpace c = new CycleWorkSpace();
		graphSearch(c);
		return c.AnyCycles;
	}

	public Graph kruskal() {

		// 1. A <- Empty set
		LinkedList A = new LinkedList();

		// 2. for each vertex v E V[G]
		// 3. do Make-Set(v)

		for (VertexIter vxiter = getVertices(); vxiter.hasNext();) {
			Vertex v = vxiter.next();
			v.representative = v; // I am in my set
			v.members = new LinkedList(); // I have no members in my set
		}

		// 4. sort the edges of E by nondecreasing weight w
		// Creates the edges objects
		// int j;
		LinkedList Vneighbors = new LinkedList();
		// Vertex u;

		// this was added to support GnR and GR because there are no
		// edge objects b4 this point.
		EdgeIter dummyIter = getEdges();

		// Sort the Edges in non decreasing order
		sortEdges(new Comparator<EdgeIfc>() {
			public int compare(EdgeIfc e1, EdgeIfc e2) {
				if (e1.getWeight() < e2.getWeight())
					return -1;
				if (e1.getWeight() == e2.getWeight())
					return 0;
				return 1;
			}

		});

		// 5. for each edge in the nondecresing order
		Vertex vaux, urep, vrep;

		for (EdgeIter edgeiter = getEdges(); edgeiter.hasNext();) {
			// 6. if Find-Set(u)!=Find-Set(v)
			EdgeIfc e1 = edgeiter.next();
			Vertex u = e1.getStart();
			Vertex v = e1.getEnd();

			if (!(v.representative.getName())
					.equals(u.representative.getName())) {
				// 7. A <- A U {(u,v)}
				A.add(e1);

				// 8. Union(u,v)
				urep = u.representative;
				vrep = v.representative;

				if ((urep.members ).size() > (vrep.members ).size()) { // we
					// add
					// elements
					// of v
					// to u
					for (int j = 0; j < (vrep.members ).size(); j++) {
						vaux = (Vertex) (vrep.members ).get(j);
						vaux.representative = urep;
						(urep.members ).add(vaux);
					}
					v.representative = urep;
					vrep.representative = urep;
					(urep.members ).add(v);
					if (!v.equals(vrep))
						(urep.members ).add(vrep);
					(vrep.members ).clear();
				} else { // we add elements of u to v
					for (int j = 0; j < (urep.members ).size(); j++) {
						vaux = (Vertex) (urep.members ).get(j);
						vaux.representative = vrep;
						(vrep.members ).add(vaux);
					}
					u.representative = vrep;
					urep.representative = vrep;
					(vrep.members ).add(u);
					if (!u.equals(urep))
						(vrep.members ).add(urep);
					(urep.members ).clear();

				} // else

			} // of if

		} // of for numedges

		// 9. return A
		// Creates the new Graph that contains the SSSP
		String theName;
		Graph newGraph = new Graph();

		// Creates and adds the vertices with the same name
		for (VertexIter vxiter = getVertices(); vxiter.hasNext();) {
			theName = vxiter.next().getName();
			newGraph.addVertex(new Vertex().assignName(theName));
		}

		// Creates the edges from the NewGraph
		Vertex theStart, theEnd;
		Vertex theNewStart, theNewEnd;
		EdgeIfc theEdge;

		// For each edge in A we find its two vertices
		// make an edge for the new graph from with the correspoding
		// new two vertices
		for (int i = 0; i < A.size(); i++) {
			// theEdge with its two vertices
			theEdge = (EdgeIfc) A.get(i);
			theStart = theEdge.getStart();
			theEnd = theEdge.getEnd();

			// Find the references in the new Graph
			theNewStart = newGraph.findsVertex(theStart.getName());
			theNewEnd = newGraph.findsVertex(theEnd.getName());

			// Creates the new edge with new start and end vertices
			// in the newGraph
			// and ajusts the adorns based on the old edge
			// Adds the new edge to the newGraph
			EdgeIfc theNewEdge = newGraph.addEdge(theNewStart, theNewEnd,
					theEdge.getWeight());
			theNewEdge.adjustAdorns(theEdge);
		}
		return newGraph;

	} // kruskal

	public Graph prim(Vertex r) {
		Vertex root;

		root = r;
		Vertex x;

		// 2. and 3. Initializes the vertices
		for (VertexIter vxiter = getVertices(); vxiter.hasNext();) {
			x = vxiter.next();
			x.pred = null;
			x.key = Integer.MAX_VALUE;
		}

		// 4. and 5.
		root.key = 0;
		root.pred = null;

		// 2. S <- empty set

		// 1. Queue <- V[G], copy the vertex in the graph in the priority queue
		LinkedList<Vertex> queue = new LinkedList<Vertex>();
		Set<String> indx = new HashSet<String>();

		// Inserts the root at the head of the queue
		queue.add(root);
		indx.add(root.getName());
		for (VertexIter vxiter = getVertices(); vxiter.hasNext();) {
			x = vxiter.next();
			if (x.key != 0) // this means, if this is not the root
			{
				queue.add(x);
				indx.add(x.getName());
			}
		}

		// Inserts the root at the head of the queue
		// Queue.addFirst( root );

		// 6. while Q!=0
		int k;
		LinkedList<NeighborIfc> uneighbors;
		Vertex u, v;
		EdgeIfc en;
		NeighborIfc vn;

		int wuv;
		boolean isNeighborInQueue = false;

		// Queue is a list ordered by key values.
		// At the beginning all key values are INFINITUM except
		// for the root whose value is 0.
		while (queue.size() != 0) {
			// 7. u <- Extract-Min(Q);
			// Since this is an ordered queue the first element is the min
			u = (Vertex) queue.removeFirst();
			indx.remove(u.getName());

			// 8. for each vertex v adjacent to u
			uneighbors = u.getNeighborsObj();

			k = 0;
			for (EdgeIter edgeiter = u.getEdges(); edgeiter.hasNext(); k++) {
				vn = (NeighborIfc) uneighbors.get(k);
				en = edgeiter.next();

				v = en.getOtherVertex(u);

				// Check to see if the neighbor is in the queue
				isNeighborInQueue = false;

				// if the Neighor is in the queue
				if (indx.contains(v.getName()))
					isNeighborInQueue = true;
				wuv = en.getWeight();

				// 9. Relax (u,v w)
				if (isNeighborInQueue && (wuv < v.key)) {
					v.key = wuv;
					v.pred = u.getName();
					uneighbors.set(k, vn); // adjust values in the neighbors

					// update the values of v in the queue
					// Remove v from the Queue so that we can reinsert it
					// in a new place according to its new value to keep
					// the Linked List ordered
					Object residue = (Object) v;
					queue.remove(residue);
					// Object residue = Queue.remove( indexNeighbor );

					indx.remove(v.getName());

					// Get the new position for v
					int position = Collections.binarySearch(queue, v,
							new Comparator<Vertex>() {
								public int compare(Vertex v1, Vertex v2) {
									if (v1.key < v2.key)
										return -1;
									if (v1.key == v2.key)
										return 0;
									return 1;
								}
							});

					// Adds v in its new position in Queue
					if (position < 0) // means it is not there
					{
						queue.add(-(position + 1), v);
					} else // means it is there
					{
						queue.add(position, v);
					}
					indx.add(v.getName());

				} // if 8-9.
			} // for all neighbors
		} // of while

		// Creates the new Graph that contains the SSSP
		String theName;
		Graph newGraph = new Graph();

		// Creates and adds the vertices with the same name
		for (VertexIter vxiter = getVertices(); vxiter.hasNext();) {
			Vertex vtx = vxiter.next();
			theName = vtx.name;

			newGraph.addVertex(new Vertex().assignName(theName));
		}

		// Creates the edges from the NewGraph
		Vertex theVertex, thePred;
		Vertex theNewVertex, theNewPred;
		EdgeIfc e;

		// Creates and adds the vertices with the same name
		for (VertexIter vxiter = getVertices(); vxiter.hasNext();) {
			// theVertex and its Predecessor
			theVertex = vxiter.next();

			thePred = findsVertex(theVertex.pred);

			// if theVertex is the source then continue we dont need
			// to create a new edge at all
			if (thePred == null)
				continue;

			// Find the references in the new Graph
			theNewVertex = newGraph.findsVertex(theVertex.name);
			theNewPred = newGraph.findsVertex(thePred.name);

			// Creates the new edge from predecessor -> vertex in the newGraph
			// and ajusts the adorns based on the old edge
			EdgeIfc theNewEdge = newGraph.addEdge(theNewPred, theNewVertex, 0);
			e = findsEdge(thePred, theVertex);
			theNewEdge.adjustAdorns(e);
		}
		return newGraph;

	} // MST
}