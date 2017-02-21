import java.util.ArrayList;
import java.util.HashMap;

public class Routes {
    public HashMap<Vertex, Edge> routes;

    public Routes(HashMap<Vertex, Edge> routes) {
        this.routes = routes;
    }

    int calculateDistance(ArrayList<Vertex> towns) throws Exception {
        if (towns.size() < 2) {
            return 0;
        }

        int distance, depth, i;
        distance = depth = i = 0;

        while (i < towns.size() - 1) {
            if (routes.containsKey(towns.get(i))) {
                Edge edge = routes.get(towns.get(i));
                while (edge != null) {
                    if (edge.destination.equals(towns.get(i + 1))) {
                        distance += edge.length;
                        depth++;
                        break;
                    }
                    edge = edge.next;
                }
            } else {
                throw new Exception(Constants.NO_ROUTE_FOUND);
            }
            i++;
        }

        if (depth != towns.size() - 1) {
            throw new Exception(Constants.NO_ROUTE_FOUND);
        }

        return distance;
    }

    int findRoutesForMaxStop(Vertex start, Vertex end, int depth, int maxStops) throws Exception {
        int foundedRoutes = 0;

        if (this.routes.containsKey(start) && this.routes.containsKey(end)) {
            depth++;
            if (depth > maxStops) {
                return 0;
            }

            start.isVisited = true;
            Edge edge = this.routes.get(start);

            while (edge != null) {
                if (edge.destination.equals(end) && depth <= maxStops) {
                    edge = edge.next;
                    foundedRoutes++;
                    continue;
                } else if (!edge.destination.isVisited && depth < maxStops) {
                    foundedRoutes += findRoutesForMaxStop(edge.destination, end, depth, maxStops);
                    depth--;
                }
                edge = edge.next;
            }
        } else {
            throw new Exception(Constants.NO_ROUTE_FOUND);
        }

        start.isVisited = false;
        return foundedRoutes;
    }

    int findRoutesForExactStop(Vertex start, Vertex end, int depth, int exactStop) throws Exception {
        int foundedRoutes = 0;

        if (this.routes.containsKey(start) && this.routes.containsKey(end)) {
            depth++;
            if (depth > exactStop) {
                return 0;
            }
            start.isVisited = true;
            Edge edge = this.routes.get(start);
            while (edge != null) {
                if (edge.destination.equals(end) && depth==exactStop) {
                    edge = edge.next;
                    foundedRoutes++;
                    continue;
                } else if (!edge.destination.isVisited && depth < exactStop) {
                    foundedRoutes += findRoutesForExactStop(edge.destination, end, depth, exactStop);
                    depth--;
                    exactStop++;
                }
                edge = edge.next;
            }
        } else {
            throw new Exception(Constants.NO_ROUTE_FOUND);
        }

        start.isVisited = false;
        return foundedRoutes;
    }

    int calculateShortestRoute(Vertex start, Vertex end, int length, int shortestRoute) throws Exception {
        if (routes.containsKey(start) && routes.containsKey(end)) {
            start.isVisited = true;
            Edge edge = routes.get(start);
            while (edge != null) {
                if (edge.destination == end || !edge.destination.isVisited) {
                    length += edge.length;
                }

                if (edge.destination.equals(end)) {
                    if (length < shortestRoute || shortestRoute == 0) {
                        shortestRoute = length;
                    }
                    start.isVisited = false;
                    return shortestRoute;
                } else if (!edge.destination.isVisited) {
                    shortestRoute = calculateShortestRoute(edge.destination, end, length, shortestRoute);
                    length -= edge.length;
                }

                edge = edge.next;
            }
        } else {
            throw new Exception(Constants.NO_ROUTE_FOUND);
        }

        start.isVisited = false;
        return shortestRoute;
    }

    int findRoutesLessThanMaxDistance(Vertex start, Vertex end, int length, int maxDistance) throws Exception {
        int foundedRoutes = 0;

        if (this.routes.containsKey(start) && this.routes.containsKey(end)) {
            Edge edge = this.routes.get(start);
            while (edge != null) {
                length += edge.length;
                if (length <= maxDistance) {
                    if (edge.destination.equals(end)) {
                        foundedRoutes++;
                        foundedRoutes += findRoutesLessThanMaxDistance(edge.destination, end, length, maxDistance);
                        edge = edge.next;
                        continue;
                    } else {
                        foundedRoutes += findRoutesLessThanMaxDistance(edge.destination, end, length, maxDistance);
                        length -= edge.length;
                    }
                } else {
                    length -= edge.length;
                }

                edge = edge.next;
            }
        } else {
            throw new Exception(Constants.NO_ROUTE_FOUND);
        }

        return foundedRoutes;
    }
}