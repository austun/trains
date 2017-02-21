public class Edge {
    public Vertex origin;       //origin vertex
    public Vertex destination;  //destination vertex
    public Edge next;           //next adjacent edge
    public int length;          //length of edge

    public Edge(Vertex origin, Vertex destination, int length) {
        this.origin = origin;
        this.destination = destination;
        this.next = null;
        this.length = length;
    }

    public void addToEnd(Vertex origin, Vertex destination, int length) {
        Edge temp = this;
        while (temp.next != null) {
            temp = temp.next;
        }
        temp.next = new Edge(origin, destination, length);
    }

    public Edge next(Edge edge) {
        this.next = edge;
        return this;
    }
}
