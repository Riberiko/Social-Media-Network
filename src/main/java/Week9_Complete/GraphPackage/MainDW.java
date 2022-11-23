package Week9_Complete.GraphPackage;


import Week9_Complete.ADTPackage.LinkedStack;
import Week9_Complete.ADTPackage.QueueInterface;
import Week9_Complete.ADTPackage.StackInterface;

public class MainDW {
    private static DirectedGraph<String> myGraph = new DirectedGraph<>();
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
        System.out.println("Testing the directed, weighted graph in Figure 29-19a.");
        setGraphFig29_19a();
        myGraph.displayEdges();
        checkVertexAndEdgeCount(9, 13);
        testEdgesFig29_19a();
        System.out.println("-------------------------------------------------------");

        testBFS(A);
        System.out.println("A B D E G F H C I  Expected");
        System.out.println("-------------------------------------------------------");

        testDFS(A);
        System.out.println("A B E F C H I D G  Expected");
        System.out.println("-------------------------------------------------------");

        System.out.println("\nFinding the cheapest path in the graph in Figure 29-19a:\n");
        testCheapestPath();
        System.out.println("Done.");
    }  // end main

    public static void setGraphFig29_19a()
    {
        setVerticesFig29_19a(); // Graph cleared before setting vertices
        setEdgesFig29_19a();
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
        ok = checkEdge(I, F, ok);

        // Check some non-existing edges
        ok = checkNoEdge(A, I, ok);
        ok = checkNoEdge(C, E, ok);
        ok = checkNoEdge(C, F, ok);

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

        printQueue(bfs);
    } // end testBFS

    public static void testDFS(String v)
    {
        System.out.println("\n\nDepth-First Traversal beginning at vertex " + v + ": ");
        QueueInterface<String> dfs = myGraph.getDepthFirstTraversal(v);

        printQueue(dfs);
    } // end testDFS

    public static void testCheapestPath()
    {
        // WEIGHTED graph in Figure 29-19a

        setVerticesFig29_19a(); // graph cleared before setting vertices
        setEdgesFig29_19a();

        showPath(A, B);
        showPath(A, C);
        showPath(A, D);
        showPath(A, E);
        showPath(A, F);
        showPath(A, G);
        showPath(A, H);
        showPath(A, I);
    } // end testCheapestPath

    private static void showPath(String vertex1, String vertex2)
    {
        System.out.println("The cheapest path from " + vertex1 + " to " + vertex2 + " is ");
        double cost = myGraph.getCheapestPath(vertex1, vertex2, path);
        printStack(path);
        System.out.println("and has a cost of " + cost + ".");
        System.out.println();
    } // end showPath

    public static void setVerticesFig29_19a()
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

    public static void setEdgesFig29_19a()
    {
        myGraph.addEdge(A, B, 2);
        myGraph.addEdge(A, D, 5);
        myGraph.addEdge(A, E, 4);

        myGraph.addEdge(B, E, 1);

        myGraph.addEdge(C, B, 3);

        myGraph.addEdge(D, G, 2);

        myGraph.addEdge(E, F, 3);
        myGraph.addEdge(E, H, 6);

        myGraph.addEdge(F, C, 4);
        myGraph.addEdge(F, H, 3);

        myGraph.addEdge(G, H, 1);

        myGraph.addEdge(H, I, 1);

        myGraph.addEdge(I, F, 1);

/* Since additions are made to the end of each edge list,
   these lists appear as follows:
		A: B -> D -> E
		B: E
		C: B
		D: G
		E: F -> H
		F: C -> H
		G: H
		H: I
		I: F
   We can predict the BFS and DFS traversals knowing this. */
    } // end setEdgesFig29_19a

    public static void printStack(StackInterface<String> s)
    {
        while (!s.isEmpty())
            System.out.print(s.pop() + " ");
        System.out.println();
    } // end printStack

    public static void printQueue(QueueInterface<String> q)
    {
        while (!q.isEmpty())
            System.out.print(q.dequeue() + " ");
        System.out.println(" Actual");
    } // end printQueue

}
