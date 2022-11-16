package Week8_Complete.HeapPackage;

public class MainAlt {
    public static void main(String[] args)
    {
        MaxHeapInterface<Integer> aHeap = new MaxHeap<>();
        aHeap.add(3);
        aHeap.add(6);
        aHeap.add(10);
        aHeap.add(6);
        aHeap.add(2);
        aHeap.add(1);
        aHeap.add(8);
        aHeap.add(12);
        aHeap.add(15);

        aHeap.display();

        if (aHeap.isEmpty())
            System.out.println("The heap is empty - INCORRECT");
        else
            System.out.println("The heap is not empty; it contains " +
                    aHeap.getSize() + " entries.");

        System.out.println("The largest entry is " + aHeap.getMax());

        System.out.println("\n\nRemoving entries in descending order:");
        //while (!aHeap.isEmpty())
        System.out.println("Removing " + aHeap.removeMax());
        aHeap.display();


        System.out.println("\n\nTesting constructor with array parameter:\n");
        String[] nameArray = {"Jared", "Jazmin", "Doug", "Tia", "Megan",
                "Jada", "Jamal", "Brittany", "Regis", "Zeke"};
        MaxHeapInterface<String> anotherHeap = new MaxHeap<>(nameArray);

        if (anotherHeap.isEmpty())
            System.out.println("The heap is empty - INCORRECT");
        else
            System.out.println("The heap is not empty; it contains " +
                    anotherHeap.getSize() + " entries.");

        System.out.println("The largest entry is " + anotherHeap.getMax());

        System.out.println("\n\nRemoving entries in descending order:");
        while (!anotherHeap.isEmpty())
            System.out.println("Removing " + anotherHeap.removeMax());
    }  // end main
}
