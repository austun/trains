import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Graph {

    public Map<String, Vertex> vertices = new TreeMap<String, Vertex>();
    public HashMap<Vertex, Edge> routes = new HashMap<Vertex, Edge>();

    public void processFile(File file) {
        try {
            Scanner input = new Scanner(file);
            input.useDelimiter(", *");
            while (input.hasNext()) {
                String config = input.next();
                if (config.length() < 3) {
                    throw new IllegalArgumentException(Constants.WRONG_GRAPH_PARAMETER + config);
                }

                String firstVertexName = config.substring(0, 1);
                String secondVertexName = config.substring(1, 2);

                int length;
                try {
                    length = Integer.parseInt(config.substring(2));
                } catch (NumberFormatException e) {
                    throw new IllegalArgumentException(Constants.WRONG_EDGE_LENGTH_PARAMETER + config.substring(2));
                }

                Vertex firstVertex = insertAndGetVertex(firstVertexName);
                Vertex secondVertex = insertAndGetVertex(secondVertexName);

                createGraph(firstVertex, secondVertex, length);
            }

        } catch (FileNotFoundException e) {
            System.out.println(Constants.FILE_DOES_NOT_EXIST + file.getName());
            System.exit(2);
        }
    }

    public Vertex insertAndGetVertex(String name) {
        Vertex vertex = vertices.get(name);
        if (vertex == null) {
            vertex = new Vertex(name);
            vertices.put(name, vertex);
        }
        return vertex;
    }

    public void createGraph(Vertex firstVertex, Vertex secondVertex, int length) {
        if (!this.routes.containsKey(firstVertex)) {
            this.routes.put(firstVertex, new Edge(firstVertex, secondVertex, length));
        } else {
            //New routes should be added to the end of existing route in iteration
            routes.get(firstVertex).addToEnd(firstVertex, secondVertex, length);
            this.routes.put(firstVertex, routes.get(firstVertex));
        }
    }
}

