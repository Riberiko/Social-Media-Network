package Week8_Complete.TreePackage.BinarySearchTree;

import java.util.Iterator;

public class MainAlt {
    public static void main(String[] args)
    {
        SearchTreeInterface<Integer> aTree = createTree(); // Tests add
        testTreeOperations(aTree);                        // Tests isEmpty, getRootData, getHeight, and getNumberOfNodes
        traverse(aTree);                                  // Tests getInorderIterator
        testSearchTreeOperations(aTree);                  // Tests getEntry, contains, and remove
        System.out.println("Done.");
    }  // end main

    /** Tests add. */
    public static SearchTreeInterface<Integer> createTree()
    {
        System.out.println("\n\nCreating a binary search tree:");
        SearchTreeInterface<Integer> aTree = new BinarySearchTree<>();
        System.out.println("Initial tree should be empty; isEmpty() returns " + aTree.isEmpty());

        // Test add
        System.out.println("\n\nTesting add():\n");
        System.out.println("Adding 38:           return " + aTree.add(38)    + " (should be null)");
        System.out.println("Adding 32:           return " + aTree.add(32)    + " (should be null)");
        System.out.println("Adding 44:         return " + aTree.add(44)  + " (should be null)");
        System.out.println("Adding 16:        return " + aTree.add(16) + " (should be null)");
        System.out.println("Adding 8:            return " + aTree.add(8)     + " (should be null)");
        System.out.println("Adding 31:            return " + aTree.add(31)     + " (should be null)");
        System.out.println("Adding 60:          return " + aTree.add(60)   + " (should be null)");
        System.out.println("Adding 27:          return " + aTree.add(27)   + " (should be null)");
        System.out.println("Adding 40:         return " + aTree.add(40)  + " (should be null)");
        System.out.println("Adding 10:          return " + aTree.add(10)   + " (should be null)");
        System.out.println("Adding 34:          return " + aTree.add(34)   + " (should be null)");
        System.out.println("Adding 33:            return " + aTree.add(33)     + " (should be null)");
        System.out.println("Adding 36:            return " + aTree.add(36)     + " (should be null)");
        System.out.println("Adding 9:            return " + aTree.add(9)     + " (should be null)");
        System.out.println("Adding 35:            return " + aTree.add(35)     + " (should be null)");
        return aTree;
    } // end createTree

    /** Displays the tree's nodes using an inorder traversal. */
    public static void traverse(SearchTreeInterface<Integer> bst)
    {
        Iterator<Integer> traverser = bst.getInorderIterator();

        while (traverser.hasNext())
            System.out.print(traverser.next() + ", ");
        System.out.println();
    } // end traverse

    /** Tests isEmpty, getRootData, getHeight, and getNumberOfNodes. */
    public static void testTreeOperations(SearchTreeInterface<Integer> aTree)
    {
        System.out.println("\n\nTesting isEmpty, getRootData, getHeight, and getNumberOfNodes:\n");
        if (aTree.isEmpty())
            System.out.println("Tree is empty, but should not be.");
        else
            System.out.println("Tree is not empty.");

        // Display root, height, number of nodes
        System.out.println("Root of tree is "   + aTree.getRootData()      + " (should be 38)");
        System.out.println("Height of tree is " + aTree.getHeight()        + " (should be 6)");
        System.out.println("Tree has "          + aTree.getNumberOfNodes() + " nodes (should be 15)");

    } // end testTreeOperations

    /** Tests getEntry, contains, and remove. */
    public static void testSearchTreeOperations(SearchTreeInterface<Integer> aTree)
    {
        testGetEntry(aTree);
        testContains(aTree);
        testRemove(aTree);
    } // end testSearchTreeOperations

    // Tests getEntry.
    private static void testGetEntry(SearchTreeInterface<Integer> aTree)
    {

        System.out.println("\n\nTesting getEntry():\n");
        System.out.println("\nGetting 34:    return " + aTree.getEntry(34)    + " (should be 34)");
        System.out.println("\nGetting 36:     return " + aTree.getEntry(36)       + " (should be 36)");
        System.out.println("\nGetting 31:     return " + aTree.getEntry(31)       + " (should be 31)");
        System.out.println("\nGetting 27:   return " + aTree.getEntry(27)     + " (should be 27)");
        System.out.println("\nGetting 8:  return " + aTree.getEntry(8)    + " (should be 8)");
    } // end testGetEntry

    // Test contains.
    private static void testContains(SearchTreeInterface<Integer> aTree)
    {
        System.out.println("\n\n\nTesting contains():\n");

        if ( aTree.contains(32) )
            System.out.println("\n32 is in tree - OK");
        else
            System.out.println("Error in contains()");

        if ( aTree.contains(40) )
            System.out.println("\n40 is in tree - OK");
        else
            System.out.println("Error in contains()");

        if ( aTree.contains(31) )
            System.out.println("\n31 is in tree - OK");
        else
            System.out.println("Error in contains()");

        if ( aTree.contains(8) )
            System.out.println("\n8 is in tree - OK");
        else
            System.out.println("Error in contains()");

        if (!aTree.contains(50))
            System.out.println("\n50 is not in tree - OK");
        else
            System.out.println("Error in contains()");
    } // end testContains

    // Test remove.
    private static void testRemove(SearchTreeInterface<Integer> aTree)
    {
        System.out.println("\n\n\nTesting remove():\n");
        System.out.println("Removing 10:   return " + aTree.remove(10) + " (should be 10)");  // leaf
        traverse(aTree);

        System.out.println("Removing 32:   return " + aTree.remove(32)   + " (should be 32)");  // leaf
        traverse(aTree);

        System.out.println("Removing 38:    return " + aTree.remove(38)    + " (should be 38)");   // one child
        traverse(aTree);

        System.out.println("\n\nThe tree contains " + aTree.getNumberOfNodes() + " (should be 12) items, as follows:\n");
        traverse(aTree);
    } // end testRemove
}
