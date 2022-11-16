package Week8_Complete.HeapPackage;

public class Main {
    public static void main(String[] args)
    {
        String jared    = "Jared";
        String brittany = "Brittany";
        String jazmin   = "Jazmin";
        String doug     = "Doug";
        String megan    = "Megan";
        String tia      = "Tia";
        String jada     = "Jada";
        String jamal    = "Jamal";
        String regis    = "Regis";
        String zeke     = "Zeke";

        MaxHeapInterface<String> aHeap = new MaxHeap<>();
        aHeap.add(jared);
        aHeap.add(brittany);
        aHeap.add(jazmin);
        aHeap.add(doug);
        aHeap.add(megan);
        aHeap.add(tia);
        aHeap.add(jada);
        aHeap.add(jamal);
        aHeap.add(regis);

        if (aHeap.isEmpty())
            System.out.println("The heap is empty - INCORRECT");
        else
            System.out.println("The heap is not empty; it contains " +
                    aHeap.getSize() + " entries.");

        System.out.println("The largest entry is " + aHeap.getMax());

        System.out.println("\n\nRemoving entries in descending order:");
        while (!aHeap.isEmpty())
            System.out.println("Removing " + aHeap.removeMax());


        System.out.println("\n\nTesting constructor with array parameter:\n");
        String[] nameArray = {jared, jazmin, doug, tia, megan,
                jada, jamal, brittany, regis, zeke};
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
