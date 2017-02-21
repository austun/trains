import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Operations {
    public HashMap<Vertex, Edge> allRoutes;
    public Map<String, Vertex> vertices;

    public Operations(HashMap<Vertex, Edge> allRoutes, Map<String, Vertex> vertices) {
        this.allRoutes = allRoutes;
        this.vertices = vertices;
    }

    public void processFile(File file) {
        try {
            Scanner input = new Scanner(file);
            while (input.hasNextLine()) {
                String config = input.nextLine();
                if (config.substring(0, 1).equals("#")) {
                    continue;
                }
                String[] parameters = config.split("[\\s]");
                try {
                    validateAndProcessParameters(parameters);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println(Constants.FILE_DOES_NOT_EXIST + file.getName());
            System.exit(2);
        }
    }

    public void validateAndProcessParameters(String[] parameters) throws Exception {
        Routes routes = new Routes(allRoutes);
        Vertex start = null;
        Vertex end = null;
        int result, input = 0;

        if (!parameters[0].equals(Constants.DISTANCE)) {
            start = vertices.get(parameters[1].substring(0, 1));
            end = vertices.get(parameters[1].substring(1, 2));

            if (parameters[1].length() != 2) {
                throw new Exception(Constants.INVALID_ROUTE_PARAMETER + parameters[1]);
            }
        }
        try {
            switch (parameters[0]) {
                case Constants.DISTANCE:
                    ArrayList<Vertex> vertices = new ArrayList<Vertex>();
                    for (int i = 0; i < parameters[1].length(); i++) {
                        vertices.add(this.vertices.get(parameters[1].substring(i, i + 1)));
                    }
                    result = routes.calculateDistance(vertices);
                    System.out.println("The distance of the route " + parameters[1] + " = " + result);
                    break;
                case Constants.EXACTSTOP:
                    input = Integer.parseInt(parameters[2]);
                    result = routes.findRoutesForExactStop(start, end, 0, input);
                    System.out.println("The number of trips between " + parameters[1] + " with a exactly " + input + " stops = " + result);
                    break;
                case Constants.MAXSTOP:
                    input = Integer.parseInt(parameters[2]);
                    result = routes.findRoutesForMaxStop(start, end, 0, input);
                    System.out.println("The number of trips between " + parameters[1] + " with a maximum of " + input + " stops = " + result);
                    break;
                case Constants.MAXROUTE:
                    input = Integer.parseInt(parameters[2]);
                    result = routes.findRoutesLessThanMaxDistance(start, end, 0, input);
                    System.out.println("The number of different routes between " + parameters[1] + " with a distance of less than " + input + " = " + result);
                    break;
                case Constants.SHORTEST:
                    result = routes.calculateShortestRoute(start, end, 0, 0);
                    System.out.println("The length of the shortest route between " + parameters[1] + " = " + result);
                    break;
                default:
                    System.out.println(Constants.INVALID_OPERATION_PARAMETER + parameters[0]);
            }
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(Constants.INVALID_NUMBER_FORMAT + parameters[0]);
        }
    }
}
