public class LinkedBagTest
{
    public static void main(String[] args)
    {

        BagInterface<String> aBag = new LinkedBag<>();
        BagInterface<String> aBag2 = new LinkedBag<>();
        String[] bag1 = {"A", "B", "C", "D", "A", "H", "Z"};
        String[] bag2 = {"Z", "J", "X", "L", "A", "A", "A", "A"};
        for (int index = 0; index < bag1.length; index++) {
            aBag.add(bag1[index]);
        }
        for (int index = 0; index < bag2.length; index++) {
            aBag2.add(bag2[index]);
        }

        System.out.println("Contents of aBag: ");
        printValues(aBag);
        System.out.println("Contents of aBag2: ");
        printValues(aBag2);

        BagInterface<String> union = aBag.union(aBag2);
        System.out.println("Union");
        printValues(union);
        BagInterface<String> intersection = aBag.intersection(aBag2);
        System.out.println("intersection");
        printValues(intersection);


        BagInterface<String> difference = aBag.difference(aBag2);
        System.out.println("Difference1");
        printValues(difference);
        BagInterface<String> difference2 = aBag2.difference(aBag);
        System.out.println("Difference2");
        printValues(difference2);


        System.out.print("Frequency of \"A\" in aBag: ");
        System.out.println(aBag.getFrequencyOf("A"));
        System.out.print("Frequency of \"A\" in aBag2: ");
        System.out.println(aBag2.getFrequencyOf("A"));
        System.out.println("Current size of aBag: " + aBag.getCurrentSize());
        System.out.println("Current size of aBag2: " + aBag2.getCurrentSize());
        System.out.println("Using remove method with no argument for aBag (bag removed: " + aBag.remove() + ")");
        System.out.println("Using remove method with argument \"A\"");
        aBag.remove("A");
        System.out.println("Current contents of aBag");
        printValues(aBag);
        System.out.println("Checking to see if aBag is empty");
        if (aBag.isEmpty())
            System.out.println("aBag is empty");
        else
            System.out.println("aBag is not empty");

        System.out.println("Checking to see if aBag2 contains \"Z\"");
        if (aBag2.contains("Z"))
            System.out.println("aBag2 contains \"Z\"");
        else
            System.out.println("aBag does not contain \"Z\"");

        union = aBag.union(aBag2);
        System.out.println("Union");
        printValues(union);
        intersection = aBag.intersection(aBag2);
        System.out.println("Intersection");
        printValues(intersection);
        difference = aBag.difference(aBag2);
        System.out.println("Difference 1");
        printValues(difference);
        difference2 = aBag2.difference(aBag);
        System.out.println("Difference 2");
        printValues(difference2);

        System.out.println("Using clear on aBag");
        aBag.clear();
        System.out.print("Contents in aBag: ");
        printValues(aBag);
        union = aBag.union(aBag2);
        System.out.println("Union");
        printValues(union);
        intersection = aBag.intersection(aBag2);
        System.out.println("Intersection");
        printValues(intersection);
        difference = aBag.difference(aBag2);
        System.out.println("Difference 1");
        printValues(difference);
        difference2 = aBag2.difference(aBag);
        System.out.println("Difference 2");
        printValues(difference2);
    }
        public static void printValues(BagInterface aBag)
        {
            Object[] test = aBag.toArray();
            for(Object val: test)
                System.out.print(val + " ");
            System.out.println();
        }
}