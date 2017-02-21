public interface Constants {
    //error messages
    public static final String USAGE =
            "###############################################################################################################\n" +
            "# Usage: <input_graphs> <operations>\n" +
            "# This application takes two files as arguments\n" +
            "# The order and format of the input files is as below:\n" +
            "#  <input_graphs>: File consists of comma(,) separated graphs in a single line each graph should \n" +
            "#                  start with two letters that first represents the source vertex, and second \n" +
            "#                  represents the destination vertex.\n" +
            "#                  Following characters should be numeric which represents the distance between \n" +
            "#                  two vertices. For example CD3, AB6 \n" +
            "#  <operations>:   File lists the operations to be performed by application. Each line corresponds \n" +
            "#                  to one operation. Parameters will be parsed by space character. \n" +
            "#                  If any line starts with '#' character the line will be ignored. \n" +
            "#                  List of operations as below: \n" +
            "#                  DISTANCE <vertices>:            Calculates the distance for a given \n" +
            "#                                                  route. Example: DISTANCE ADC \n" +
            "#                  MAXSTOP <vertices> <number>:    Calculates the number of possible \n" +
            "#                                                  trips between two vertices which has stop points \n" +
            "#                                                  less than or equal to provided <number> \n" +
            "#                                                  Example: MAXSTOP BC 5 \n" +
            "#                  EXACTSTOP <vertices> <number>:  Calculates the number of possible \n" +
            "#                                                  trips between two vertices with stop points \n" +
            "#                                                  equal to provided <number> \n" +
            "#                                                  Example: EXACTSTOP AD 3 \n" +
            "#                  SHORTEST <vertices>:            Calculates the shortest distance between \n" +
            "#                                                  two given vertices. Example: SHORTEST BD \n" +
            "#                  MAXROUTE <vertices> <number>:   Calculates the number of different routes \n" +
            "#                                                  between <vertices> with a distance of less than provided <number> \n" +
            "###############################################################################################################";
    public static final String FILE_DOES_NOT_EXIST = "File does not exist";
    public static final String WRONG_GRAPH_PARAMETER = "Graph inputs should be at least 3 character and in a single line. Wrong config is: ";
    public static final String WRONG_EDGE_LENGTH_PARAMETER = "Values should be numeric after the second character of graph. Wrong graph value is: ";
    public static final String INVALID_OPERATION_PARAMETER = "Invalid operation name: ";
    public static final String INVALID_ROUTE_PARAMETER = "Route should include one origin and one destination vertex. Current route: ";
    public static final String INVALID_NUMBER_FORMAT = "Third parameter should be numeric for operation ";
    public static final String NO_ROUTE_FOUND = "NO SUCH ROUTE";

    //operation names
    public static final String DISTANCE = "DISTANCE";
    public static final String MAXSTOP = "MAXSTOP";
    public static final String EXACTSTOP = "EXACTSTOP";
    public static final String SHORTEST = "SHORTEST";
    public static final String MAXROUTE = "MAXROUTE";

}
