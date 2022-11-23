package Week9_Complete.GraphPackage;

import Week9_Complete.ADTPackage.LinkedStack;
import Week9_Complete.ADTPackage.QueueInterface;
import Week9_Complete.ADTPackage.StackInterface;

public class MainUU {
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
        System.out.println("Testing an undirected, unweighted graph like the one in Figure 29-10, " +
                "but without directions on its edges.");
        setGraphFig29_10();
        myGraph.displayEdges();
        checkVertexAndEdgeCount(9, 13);
        testEdgesFig29_10();
        System.out.println("-------------------------------------------------------");

        testBFS(A);
        System.out.println("A B D E C G F H I  Expected");
        System.out.println("-------------------------------------------------------");

        testDFS(A);
        System.out.println("A B C F E H G D I Expected");
        System.out.println("-------------------------------------------------------");

        System.out.println("\nFinding the shortest paths between given vertices:\n");
        testShortestPath();
        System.out.println("-------------------------------------------------------");

        System.out.println("\n\nTesting Example 29.26 to create the graph in Figure 29-22:\n");
        testExample29_26();
        System.out.println("Done.");
    }  // end main

    public static void setGraphFig29_10()
    {
        setVerticesFig29_10(); // Graph cleared before setting vertices
        setEdgesFig29_10Undirected();
    } // end setGraphFig29_10

    public static void checkVertexAndEdgeCount(int numberOfVertices, int numberOfEdges)
    {
        System.out.println("\nNumber of vertices = " + myGraph.getNumberOfVertices() +
                " (should be " + numberOfVertices + ")");
        System.out.println("Number of edges = " + myGraph.getNumberOfEdges() +
                " (should be " + numberOfEdges + ")");
    } // end checkVertexAndEdgeCount

    public static void testEdgesFig29_10()
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
    } // end testEdgesFig29_10

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

    public static void testShortestPath()
    {
        showPath(A, B, "A B");
        showPath(A, C, "A B C");
        showPath(A, D, "A D");
        showPath(A, E, "A E");
        showPath(A, F, "A E F");
        showPath(A, G, "A D G");
        showPath(A, H, "A E H");
        showPath(A, I, "A E F I");
        showPath(E, D, "E A D");
    } // end testShortestPath

    private static void showPath(String vertex1, String vertex2, String expectedPath)
    {
        System.out.print("\nThe shortest path from " + vertex1 + " to " + vertex2 + " is ");
        int length = myGraph.getShortestPath(vertex1, vertex2, path);
        displayStack(path);
        System.out.println(" and has a length of " + length + ".");
        System.out.println("Expected path:                   " + expectedPath);
    } // end showPath

    public static void setVerticesFig29_10()
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
    } // end setVerticesFig29_10

    private static void setEdgesFig29_10Undirected()
    {
        myGraph.addEdge(A, B);
        myGraph.addEdge(A, D);
        myGraph.addEdge(A, E);

        myGraph.addEdge(B, C);
        myGraph.addEdge(B, E);

        myGraph.addEdge(C, F);

        myGraph.addEdge(D, G);

        myGraph.addEdge(E, F);
        myGraph.addEdge(E, H);

        myGraph.addEdge(F, H);
        myGraph.addEdge(F, I);

        myGraph.addEdge(G, H);

        myGraph.addEdge(H, I);

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
    } // end setEdgesFig29_10Undirected

    public static void testExample29_26()
    {
        BasicGraphInterface<String> airMap = new UndirectedGraph<String>();

        airMap.addVertex("Boston");
        airMap.addVertex("Provincetown");
        airMap.addVertex("Nantucket");
        airMap.addEdge("Boston", "Provincetown");
        airMap.addEdge("Boston", "Nantucket");
        System.out.println("Number of vertices = " + airMap.getNumberOfVertices() +
                " (should be 3)");
        System.out.println("Number of edges = " + airMap.getNumberOfEdges() +
                " (should be 2)");
    } // end testExample29_26

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
