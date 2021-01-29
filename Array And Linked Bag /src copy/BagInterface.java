public interface BagInterface<T>
{

    /** combines the contents of the bag that is calling this method and bag in the argument
     and creates a new bag with both there contents and returns it
     @param bag that you want to combine with the bag calling the method
     @return a new bag of type BagInterface that is a combination of the two bags*/
    public BagInterface<T> union(BagInterface<T> bag);

    /** creates a new bag with only what the bag using the method and the bag in thew argument
     have in common, returns that new bag
     @param bag2 that you want to compare with the bag that is calling the method
     @return a new bag that is only contains entries that both the bags have, this can include
     duplicate items if both the bags have duplicate pairs of the same entry*/
    public BagInterface<T> intersection(BagInterface<T> bag2);

    /** creates a new bag that only contains unique entries to the bag calling the method
     @param bag2 is the bag you want to compare the bag calling the method with
     @return a bag that has only the unique entries that are in the bag calling the method and
     not in the bag that is in the argument
     */
    public BagInterface<T> difference(BagInterface<T> bag2);

    /** gets the current number of entries in the bag
     @return the integer number of entries in bag */
    public int getCurrentSize();

    /** checks to see if bag is empty or not
     @return true if bag is empty, false otherwise */
    public boolean isEmpty();

    /** adds new entry to the bag
     @param newEntry is the object to be added in the bag
     @return true if addition to the bag was successful, false otherwise */
    public boolean add(T newEntry);

    /** removes a unspecified object from the bag if possible
     @return removed entry, if it was successful, and null otherwise */
    public T remove();

    /** removes once occurrence of a specified object from the bag, if possible
     @param anEntry the entry to be removed
     @return true if removal was successful or false otherwise */
    public boolean remove(T anEntry);

    /** Removes all entries from the bag */
    public void clear();

    /** counts the number of times a given entry appears in this bag
     @param anEntry the entry to be counted
     @return the integer number of how many times the entry occurred in the bag */
    public int getFrequencyOf(T anEntry);

    /** test if a bag contains a specific entry
     @param anEntry is the entry we are checking for in the bag
     @return true if the bag contains the anEntry, false otherwise */
    public boolean contains(T anEntry);

    /** gets all entries in the bag and puts them into an array
     @return a newly allocated array of all entries in the bag
     Note: if the bag is empty it will return a empty array */
    public T[] toArray();



}
