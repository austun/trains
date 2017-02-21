import java.io.File;

public class Main {
    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println(Constants.USAGE);
            System.exit(1);
        }

        File inputGraphsFile = new File(args[0]);
        checkFile(inputGraphsFile);

        File operationsFile = new File(args[1]);
        checkFile(operationsFile);

        Graph graph = new Graph();
        graph.processFile(inputGraphsFile);

        Operations operations = new Operations(graph.routes, graph.vertices);
        operations.processFile(operationsFile);
    }

    public static void checkFile(File file) {
        if (!file.exists()) {
            System.out.println(file.getName() + Constants.FILE_DOES_NOT_EXIST);
            System.exit(2);
        }
    }
}
