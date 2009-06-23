import java.util.Iterator;
import java.util.LinkedList;

class Vertex implements EdgeIfc, NeighborIfc {
	public String name = null;

	public Vertex assignName(String name) {
		this.name = name;
		return (Vertex) this;
	}

	public String getName() {
		return this.name;
	}

	public LinkedList<Vertex> adjacentVertices = new LinkedList<Vertex>();

	public LinkedList<Neighbor> adjacentNeighbors = new LinkedList<Neighbor>();

	public void addAdjacent(Vertex n) {
		adjacentVertices.add(n);
	}

	public void adjustAdorns(Vertex the_vertex, int index) {
		int the_weight = the_vertex.weightsList.get(index).intValue();
		weightsList.add(new Integer(the_weight));
	}

	public VertexIter getNeighbors() {
		if (true)
			return new VertexIter() {
				private Iterator<Vertex> iter = adjacentVertices.iterator();

				public Vertex next() {
					return iter.next();
				}

				public boolean hasNext() {
					return iter.hasNext();
				}
			};

		if (true)
			return new VertexIter() {
				private Iterator<Neighbor> iter = adjacentNeighbors.iterator();

				public Vertex next() {
					return (iter.next()).neighbor;
				}

				public boolean hasNext() {
					return iter.hasNext();
				}
			};

		return new VertexIter() {
			private Iterator<Neighbor> iter = neighbors.iterator();

			public Vertex next() {
				return (iter.next()).end;
			}

			public boolean hasNext() {
				return iter.hasNext();
			}
		};
	}

	// --------------------
	// from EdgeIfc
	// --------------------

	public Vertex getStart() {
		if (true)
			return this;
		else
			return null;
	}

	public Vertex getEnd() {
		return null;
	}

	public int getWeight() {
		return 0;
	}

	public Vertex getOtherVertex(Vertex vertex) {
		return this;
	}

	public void adjustAdorns(EdgeIfc the_edge) {
	}

	public void addEdge(Neighbor n) {
		adjacentNeighbors.add(n);
	}

	public void adjustAdorns(Neighbor sourceNeighbor) {
		adjacentNeighbors.getLast().weight = sourceNeighbor.weight;
	}

	public LinkedList<Neighbor> neighbors = new LinkedList<Neighbor>();

	public void addNeighbor(Neighbor n) {
		neighbors.add(n);
	}

	public EdgeIter getEdges() {
		// G
		if (true)
			return new EdgeIter() {
				private Iterator<Vertex> iter = adjacentVertices.iterator();

				public EdgeIfc next() {
					return iter.next();
				}

				public boolean hasNext() {
					return iter.hasNext();
				}
			};
		// GN
		if (true)
			return new EdgeIter() {
				private Iterator<Neighbor> iter = adjacentNeighbors.iterator();

				public EdgeIfc next() {
					return iter.next();
				}

				public boolean hasNext() {
					return iter.hasNext();
				}
			};
		// GEN
		return new EdgeIter() {
			private Iterator<Neighbor> iter = neighbors.iterator();

			public EdgeIfc next() {
				return iter.next().edge;
			}

			public boolean hasNext() {
				return iter.hasNext();
			}
		};
	}

	public LinkedList getNeighborsObj() {
		if (true)
			return adjacentNeighbors;
		if (true)
			return neighbors;
		return adjacentVertices;
	}

	public LinkedList<Integer> weightsList = new LinkedList<Integer>();

	public void addWeight(int weight) {
		weightsList.add(new Integer(weight));
		adjacentNeighbors.getLast().weight = weight;
	}

	public void setWeight(int weight) {
		addWeight(weight);
		adjacentVertices.getLast().addWeight(weight);
	}

	public boolean visited = false;

	public void init_vertex(WorkSpace w) {
		visited = false;
		w.init_vertex((Vertex) this);
	}

	public void nodeSearch(WorkSpace w) {
		int s, c;
		Vertex v;
		Vertex header;

		// Step 1: if preVisitAction is true or if we've already
		// visited this node
		w.preVisitAction((Vertex) this);

		if (visited) {
			return;
		}

		// Step 2: Mark as visited, put the unvisited neighbors in the queue
		// and make the recursive call on the first element of the queue
		// if there is such if not you are done
		visited = true;

		for (VertexIter vxiter = getNeighbors(); vxiter.hasNext();) {
			v = vxiter.next();
			w.checkNeighborAction((Vertex) this, v);
			v.nodeSearch(w);
		}

		// Step 3: do postVisitAction now, you are no longer going through the
		// node again, mark it as black
		w.postVisitAction((Vertex) this);

		// enqueues the vertices not visited
		for (VertexIter vxiter = getNeighbors(); vxiter.hasNext();) {
			v = vxiter.next();

			// if your neighbor has not been visited then enqueue
			if (!v.visited) {
				GlobalVarsWrapper.queue.add(v);
			}

		} // end of for

		// while there is something in the queue
		while (GlobalVarsWrapper.queue.size() != 0) {
			header = (Vertex) GlobalVarsWrapper.queue.get(0);
			GlobalVarsWrapper.queue.remove(0);
			header.nodeSearch(w);
		}
	}

	public int vertexNumber;

	public int componentNumber;

	public int finishTime;

	public int strongComponentNumber;

	public int VertexCycle;

	public int VertexColor; // white ->0, gray ->1, black->2

	public Vertex representative;

	public LinkedList members;

	public String pred; // the predecessor vertex if any

	public int key; // weight so far from s to it

	public void display() {
		// Weights
		System.out.print(" Weights : ");
		for (int i = 0; i < weightsList.size(); i++) {
			System.out.print(weightsList.get(i).intValue() + ", ");
		}
		// all
		System.out.print("Vertex " + name + " connected to: ");

		for (VertexIter vxiter = getNeighbors(); vxiter.hasNext();) {
			Vertex v = vxiter.next();
			System.out.print(v.getName() + ", ");
		}
		// searchbase
		if (visited)
			System.out.print("  visited");
		else
			System.out.println(" !visited");

		System.out.print(" # " + vertexNumber + " ");
		System.out.print(" comp# " + componentNumber + " ");
		System.out.print(" FinishTime -> " + finishTime + " SCCNo -> "
				+ strongComponentNumber);
		System.out.print(" VertexCycle# " + VertexCycle + " ");
		if (representative == null)
			System.out.print("Rep null ");
		else
			System.out.print(" Rep " + representative.getName() + " ");

		System.out.print(" Pred " + pred + " Key " + key + " ");
		System.out.println();
	}
}
