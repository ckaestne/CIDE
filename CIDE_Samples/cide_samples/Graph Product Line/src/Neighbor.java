// *************************************************************************

public class Neighbor implements EdgeIfc, NeighborIfc {
	public Vertex neighbor;

	// This constructor has to be present here so that the default one
	// Called on Weighted can call it, i.e. it is not longer implicit
	public Neighbor() {
		neighbor = null;
		end = null;
		edge = null;
	}

	public Neighbor(Vertex theNeighbor, int aweight) {
		neighbor = theNeighbor;
		weight = aweight;
	}

	public void display() {
		System.out.print(neighbor.name + " ,");
	}

	public Vertex getStart() {
		return null;
	}

	public Vertex getEnd() {
		return neighbor;
	}

	public int weight;

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public int getWeight() {
		return this.weight;
	}

	public Vertex getOtherVertex(Vertex vertex) {
		return neighbor;
	}

	public void adjustAdorns(EdgeIfc the_edge) {
	}

	public Vertex end;

	public Edge edge;

	public Neighbor(Vertex v, Edge e) {
		end = v;
		edge = e;
	}
}
