package Week9_Complete.GraphPackage;


import Week9_Complete.ADTPackage.LinkedStack;
import Week9_Complete.ADTPackage.QueueInterface;
import Week9_Complete.ADTPackage.StackInterface;

public class MainUW {
    private static UndirectedGraph<String> myGraph = new UndirectedGraph<>();
    private static StackInterface<String> path = new LinkedStack<>();
    private static final String A = "A";
    private static final String B = "B";
    private static final String C = "C";
    private static final String D = "D";
    private static final String E = "E";
    private static final String F = "F";
    private static final String G = "G";
    private static final String H = "H";
    private static final String I = "I";

    public static void main(String[] args)
    {
        System.out.println("Testing a weighted graph like the one in Figure 29-19a, " +
                "but without directions on its edges:");
        setGraphFig29_19a();
        myGraph.displayEdges();
        checkVertexAndEdgeCount(9, 13);
        testEdgesFig29_19a();
        System.out.println("-------------------------------------------------------");

        testBFS(A);
        System.out.println("A B D E C G F H I  Expected");
        System.out.println("-------------------------------------------------------");

        testDFS(A);
        System.out.println("A B C F E H G D I Expected");
        System.out.println("-------------------------------------------------------");

        System.out.println("\n\nFinding the cheapest paths from vertex A:\n");
        testCheapestPath();
        System.out.println("-------------------------------------------------------");

        System.out.println("\n\nTesting Example 29.28 using the graph in Figure 29-3:\n");
        testExample29_28();

        System.out.println("Done.");
    }  // end main

    public static void setGraphFig29_19a()
    {
        setVerticesFig29_19a();       // Graph cleared before setting vertices
        setEdgesFig29_19aUndirected();
    } // end setGraphFig29_19a

    public static void checkVertexAndEdgeCount(int numberOfVertices, int numberOfEdges)
    {
        System.out.println("\nNumber of vertices = " + myGraph.getNumberOfVertices() +
                " (should be " + numberOfVertices + ")");
        System.out.println("Number of edges = " + myGraph.getNumberOfEdges() +
                " (should be " + numberOfEdges + ")");
    } // end checkVertexAndEdgeCount

    public static void testEdgesFig29_19a()
    {
        // Check existing edges
        boolean ok = true;
        ok = checkEdge(A, B, ok);
        ok = checkEdge(A, D, ok);
        ok = checkEdge(A, E, ok);
        ok = checkEdge(B, E, ok);
        ok = checkEdge(C, B, ok);
        ok = checkEdge(D, G, ok);
        ok = checkEdge(E, F, ok);
        ok = checkEdge(E, H, ok);
        ok = checkEdge(F, C, ok);
        ok = checkEdge(F, H, ok);
        ok = checkEdge(G, H, ok);
        ok = checkEdge(H, I, ok);

        // Check edges in opposite direction
        ok = checkEdge(B, A, ok);
        ok = checkEdge(D, A, ok);
        ok = checkEdge(E, A, ok);
        ok = checkEdge(E, B, ok);
        ok = checkEdge(B, C, ok);
        ok = checkEdge(G, D, ok);
        ok = checkEdge(F, E, ok);
        ok = checkEdge(H, E, ok);
        ok = checkEdge(C, F, ok);
        ok = checkEdge(H, F, ok);
        ok = checkEdge(H, G, ok);
        ok = checkEdge(I, H, ok);

        // Check some non-existing edges
        ok = checkNoEdge(A, I, ok);
        ok = checkNoEdge(C, E, ok);
        ok = checkNoEdge(C, A, ok);

        if (ok)
            System.out.println("Edges are OK.");
    } // end testEdgesFig29_19a

    private static boolean checkEdge(String vertex1, String vertex2, boolean ok)
    {
        boolean check = ok;
        if (!myGraph.hasEdge(vertex1, vertex2))
        {
            System.out.println("hasEdge error " + vertex1 + " " + vertex2);
            check = false;
        } // end if

        return check;
    } // end checkEdge

    private static boolean checkNoEdge(String vertex1, String vertex2, boolean ok)
    {
        boolean check = ok;
        if (myGraph.hasEdge(vertex1, vertex2))
        {
            System.out.println("hasEdge error " + vertex1 + " " + vertex2);
            check = false;
        } // end if

        return check;
    } // end checkNoEdge

    public static void testBFS(String v)
    {
        System.out.println("\n\nBreadth-First Traversal beginning at vertex " + v + ": ");
        QueueInterface<String> bfs = myGraph.getBreadthFirstTraversal(v);

        displayQueue(bfs);
        System.out.println(" Actual");
    } // end testBFS

    public static void testDFS(String v)
    {
        System.out.println("\n\nDepth-First Traversal beginning at vertex " + v + ": ");
        QueueInterface<String> dfs = myGraph.getDepthFirstTraversal(v);

        displayQueue(dfs);
        System.out.println(" Actual");
    } // end testDFS

    public static void testCheapestPath()
    {
        showPath(A, B, "A B", 2);
        showPath(A, C, "A B C", 5);
        showPath(A, D, "A D", 5);
        showPath(A, E, "A B E", 3);
        showPath(A, F, "A B E F", 6);
        showPath(A, G, "A D G", 7);
        showPath(A, H, "A D G H", 8);
        showPath(A, I, "A B E F I", 7);
    } // end testCheapestPath

    private static void showPath(String vertex1, String vertex2, String expectedPath, int expectedCost)
    {
        System.out.print("\nThe cheapest path from " + vertex1 + " to " + vertex2 + " is ");
        double cost = myGraph.getCheapestPath(vertex1, vertex2, path);
        displayStack(path);
        System.out.println("and has a cost of " + cost + ".");
        System.out.println("Expected path:                   " + expectedPath + " " +
                "                  " + expectedCost);
    } // end showPath

    private static void setVerticesFig29_19a()
    {
        myGraph.clear();

        myGraph.addVertex(A);
        myGraph.addVertex(B);
        myGraph.addVertex(C);
        myGraph.addVertex(D);
        myGraph.addVertex(E);
        myGraph.addVertex(F);
        myGraph.addVertex(G);
        myGraph.addVertex(H);
        myGraph.addVertex(I);
    } // end setVerticesFig29_19a

    private static void setEdgesFig29_19aUndirected()
    {
        myGraph.addEdge(A, B, 2);
        myGraph.addEdge(A, D, 5);
        myGraph.addEdge(A, E, 4);

        myGraph.addEdge(B, C, 3);
        myGraph.addEdge(B, E, 1);

        myGraph.addEdge(C, F, 4);

        myGraph.addEdge(D, G, 2);

        myGraph.addEdge(E, F, 3);
        myGraph.addEdge(E, H, 6);

        myGraph.addEdge(F, H, 3);
        myGraph.addEdge(F, I, 1);

        myGraph.addEdge(G, H, 1);

        myGraph.addEdge(H, I, 1);

      /* Since additions are made to the end of each edge list,
       these lists appear as follows:
       A: B -> D -> E
       B: A -> C -> E
       C: B -> F
       D: A -> G
       E: A -> B -> F -> H
       F: C -> E -> H -> I
       G: D -> H
       H: E -> F -> G -> I
       I: F -> H
       We can predict the BFS and DFS traversals knowing this. */
    } // end setEdgesFig29_19aUndirected

    public static void testExample29_28()
    {
        UndirectedGraph<String> roadMap = new UndirectedGraph<>();

        roadMap.addVertex("Provincetown");
        roadMap.addVertex("Truro");
        roadMap.addVertex("Orleans");
        roadMap.addVertex("Chatham");
        roadMap.addVertex("Barnstable");
        roadMap.addVertex("Hyannis");
        roadMap.addVertex("Sandwich");
        roadMap.addVertex("Falmouth");

        roadMap.addEdge("Provincetown", "Truro", 10);
        roadMap.addEdge("Truro", "Orleans", 17);
        roadMap.addEdge("Orleans", "Chatham", 9);
        roadMap.addEdge("Chatham", "Hyannis", 19);
        roadMap.addEdge("Hyannis", "Barnstable", 4);
        roadMap.addEdge("Barnstable", "Sandwich", 12);
        roadMap.addEdge("Barnstable", "Orleans", 19);
        roadMap.addEdge("Hyannis", "Falmouth", 20);

        roadMap.displayEdges();

        StackInterface<String> bestRoute = new LinkedStack<>();
        double distance = roadMap.getCheapestPath("Truro", "Falmouth", bestRoute);
        System.out.println("\nThe shortest route from Truro to Falmouth is " +
                distance + " miles long and " +
                "passes through the following towns:");
        while (!bestRoute.isEmpty())
            System.out.println(bestRoute.pop());
        System.out.println();
    } // end testExample29_28

    public static void displayStack(StackInterface<String> s)
    {
        while (!s.isEmpty())
            System.out.print(s.pop() + " ");

        assert(s.isEmpty());
    } // end displayStack

    public static void displayQueue(QueueInterface<String> q)
    {
        while (!q.isEmpty())
            System.out.print(q.dequeue() + " ");
    } // end displayQueue
}
