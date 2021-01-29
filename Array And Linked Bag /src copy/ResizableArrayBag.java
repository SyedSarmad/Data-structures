import java.util.Arrays;
public class ResizableArrayBag<T> implements BagInterface<T>
{
    private T[] bag;
    private int numOfEntries;
    private static final int DEFAULT_CAPACITY = 25;
    private static final int MAX_CAPACITY = 10000;
    private boolean integrityOK;

    public ResizableArrayBag()
    {
        this(DEFAULT_CAPACITY);
    }
    public ResizableArrayBag(int capacity)
    {
        integrityOK = false;
        if (capacity <= MAX_CAPACITY)
        {
            //Cast is safe because new array contains null entries
            @SuppressWarnings("unchecked")
            T[] tempBag = (T[]) new Object[capacity];
            bag = tempBag;
            numOfEntries = 0;
            integrityOK = true;
        }
        else
            throw new IllegalStateException("Attempt to create a bag whose" +
                    "capacity exceeds allowed maximum");
    }

    public BagInterface<T> union(BagInterface<T> bag2)
    {
        BagInterface<T> union = duplicate(bag2); //creates a deep copy of argument bag2 so we can add entries
        for (int i = 0; i < numOfEntries; i++)
        {
            union.add(bag[i]); //adds all entries to the current bag into the copy of argument bag2
        }
        return union;
    }
    public BagInterface<T> intersection(BagInterface<T> bag2)
    {
        BagInterface<T> intersection; //creates a bag to return
        T[] bag1Contents = this.toArray(); //Makes a copy of an array of the contents of this bag
        BagInterface<T> bag2Copy = duplicate(bag2); //creates a deep copy of the argument bag2

        /** this if or else statement is to determine the size of the
        bag to return, we only need to make the size the smallest
        size of both bags because the size will be at most the
        smallest bag's size for the intersection*/
        if (bag1Contents.length < bag2Copy.getCurrentSize())
            intersection = new ResizableArrayBag<T>(bag1Contents.length);
        else
            //this case is for if bag2 is bigger or is the same size as bag1Contents
            intersection = new ResizableArrayBag<T>(bag2Copy.getCurrentSize());

        for (int index = 0; index < numOfEntries; index++)
        {
            if (bag2Copy.contains(bag1Contents[index])) //checks if bag2Copy has the value at the index of array
            {
                intersection.add(bag1Contents[index]); //adds to bag being returned
                bag2Copy.remove(bag1Contents[index]); //removes the entry from the bag
                //bag1Contents[index] = null;
            }
        }
        return intersection;
    }

    public BagInterface<T> difference(BagInterface<T> bag2)
    {
        BagInterface<T> bag1Copy = duplicate(bag); //makes deep copy of current bag
        T[] bag2Contents = bag2.toArray(); //deep copy of the argument bag2 contents
        for (int index = 0; index < bag2.getCurrentSize(); index++)
        {
            if (bag1Copy.contains(bag2Contents[index])) //checks
            {
                bag1Copy.remove(bag2Contents[index]);
                //bag2Contents[index] = null;
            }
        }
        return bag1Copy;
    }

    /** method that creates a deep copy of the the bag given in argument
    @param aBag is type BagInterface<T>
    @return bag of BagInterface<T> type */
    private BagInterface<T> duplicate(BagInterface<T> aBag)
    {
        T[] contents = aBag.toArray();
        BagInterface<T> copy = new ResizableArrayBag<T>(aBag.getCurrentSize() + bag.length);
        for (int index = 0; index < aBag.getCurrentSize(); index++)
            copy.add(contents[index]);
        return copy;
    }

    /** method that creates a bag given the array
     @param aBag is an array that contains a bag's contents in the method calling method duplicate()
     @return bag of BagInterface<T> type */
    private BagInterface<T> duplicate(T[] aBag)
    {
        BagInterface<T> copy = new ResizableArrayBag<T>(numOfEntries);
        for (int index = 0; index < numOfEntries; index++)
            copy.add(aBag[index]);
        return copy;
    }

    public boolean add(T newEntry)
    {
        checkIntegrity();
        if (isArrayFull())
            doubleCapacity();
        bag[numOfEntries] = newEntry;
        numOfEntries++;
        return true;
    }

    public T remove()
    {
        checkIntegrity();
        T result = removeEntry(numOfEntries - 1);
        return result;
    }

    public boolean remove(T anEntry)
    {
        checkIntegrity();
        int index = getIndexOf(anEntry);
        T result = removeEntry(index);
        return result.equals(anEntry);
    }

    public int getFrequencyOf(T anEntry)
    {
        checkIntegrity();
        int frequency = 0;
        for (int index = 0; index < numOfEntries; index++)
        {
            if (bag[index].equals(anEntry))
                frequency++;
        }
        return frequency;
    }

    public boolean contains(T anEntry)
    {
        checkIntegrity();
        return getIndexOf(anEntry) > -1;
    }

    public T[] toArray()
    {
        @SuppressWarnings("unchecked")
        T[] result = (T[])new Object[numOfEntries];
        for (int index = 0; index < numOfEntries; index++)
        {
            result[index] = bag[index];
        }
        return result;
    }
    public void clear()
    {
        while(!isEmpty())
            remove();
    }

    public int getCurrentSize()
    {
        return numOfEntries;
    }

    public boolean isEmpty()
    {
        return numOfEntries == 0;
    }

    private void checkIntegrity()
    {
        if (!integrityOK)
            throw new SecurityException("Bag has been corrupted");
    }
    private void checkCapacity(int newLength)
    {
        if (newLength > MAX_CAPACITY)
            throw new IllegalStateException("Bag has exceeded maximum capacity: " + MAX_CAPACITY);
    }
    private void doubleCapacity()
    {
        int newLength = bag.length * 2;
        checkCapacity(newLength);
        bag = Arrays.copyOf(bag,newLength);
    }
    private int getIndexOf(T anEntry)
    {
        for (int index = 0; index < numOfEntries; index++)
        {
            if (bag[index].equals(anEntry))
                return index;
        }
        return -1;
    }
    private T removeEntry(int index)
    {
        T result = null;
        if (!isEmpty() && index >= 0)
        {
            result = bag[index];
            bag[index] = bag[numOfEntries -1];
            bag[numOfEntries -1] = null;
            numOfEntries--;
        }
        return result;
    }
    private boolean isArrayFull()
    {
        return numOfEntries >= bag.length;
    }

}
