import static org.junit.Assert.*;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.ArrayList;
import java.util.HashMap;

public class AppTest {
    static HashMap<Vertex, Edge> graphs = new HashMap<Vertex, Edge>();
    static Routes route;
    static Vertex a, b, c, d, e;

    @BeforeClass
    public static void constructTestData() throws Exception {

        a = new Vertex("A");
        b = new Vertex("B");
        c = new Vertex("C");
        d = new Vertex("D");
        e = new Vertex("E");

        graphs.put(a, new Edge(a, b, 5).next(new Edge(a, d, 5).next(new Edge(a, e, 7))));
        graphs.put(b, new Edge(b, c, 4));
        graphs.put(c, new Edge(c, d, 8).next(new Edge(c, e, 2)));
        graphs.put(d, new Edge(d, c, 8).next(new Edge(d, e, 6)));
        graphs.put(e, new Edge(e, b, 3));

        route = new Routes(graphs);
    }

    @Test
    public void testCalculateDistance() throws Exception {
        ArrayList<Vertex> vertices = new ArrayList<Vertex>();
        vertices.add(a);
        vertices.add(b);
        vertices.add(c);
        vertices.add(d);
        vertices.add(c);
        assertEquals(25, route.calculateDistance(vertices));
    }

    @Test(expected=Exception.class)
    public void testCalculateDistanceNoRoute() throws Exception  {
        ArrayList<Vertex> vertices = new ArrayList<Vertex>();
        vertices.add(a);
        vertices.add(e);
        vertices.add(d);
        assertEquals(-1, route.calculateDistance(vertices));
    }

    @Test
    public void testFindRoutesForMaxStop() throws Exception {
        int numberOfRoutes = route.findRoutesForMaxStop(a, c, 0, 4);
        assertEquals(4, numberOfRoutes);
    }

    @Test
    public void testCalculateShortestRoute() throws Exception {
        int shortestRoute = route.calculateShortestRoute(a, c, 0, 0);
        assertEquals(9, shortestRoute);
    }

    @Test
    public void testFindRoutesForMaxDistance() throws Exception {
        int routesForMaxDistance = route.findRoutesLessThanMaxDistance(a, b, 0, 11);
        assertEquals(1, routesForMaxDistance);
    }

    @Test
    public void testFindRoutesForExactStop() throws Exception {
        int numberOfRoutes = route.findRoutesForExactStop(a,d,0,3);
        assertEquals(1, numberOfRoutes);
    }

    @Test
    public void testOverridedEquals() {
        Vertex a = new Vertex("A");
        Vertex b = new Vertex("B");
        Vertex c = new Vertex("B");

        assertEquals(false, a.equals(b));
        assertEquals(true, b.equals(c));
        assertEquals(true, (new Vertex("Dummy").equals(new Vertex("Dummy"))));
        assertEquals(false, (new Vertex("Dummy1").equals(new Vertex("Dummy2"))));
    }
}