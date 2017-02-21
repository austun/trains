public class Vertex {
    public String name;
    public boolean isVisited;

    public Vertex(String name) {
        this.name = name;
        this.isVisited = false;
    }

    @Override
    public boolean equals(Object a) {
        if (a == null || a.getClass() != getClass()) {
            return false;
        }
        Vertex b = (Vertex) a;
        return this.name.equals(b.name);
    }
}
