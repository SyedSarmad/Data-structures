public class LinkedBag<T> implements BagInterface<T>
{
    private Node firstNode;
    private int numberOfEntries;

    public LinkedBag()
    {
        firstNode = null;
        numberOfEntries = 0;
    }

    public BagInterface<T> union(BagInterface<T> bag2)
    {
        BagInterface<T> union = new LinkedBag<T>();
        Node currentNode = firstNode;
        while(currentNode != null)
        {
            union.add(currentNode.getData());
            currentNode = currentNode.getNextNode(); //.next
        }
        T[] bag2Contents = bag2.toArray();
        for (int index = 0; index < bag2Contents.length; index ++)
        {
            union.add(bag2Contents[index]);
        }
        return union;
    }
    public BagInterface<T> intersection(BagInterface<T> bag2)
    {
        T[] bag1Contents = this.toArray();
        BagInterface<T> bag2Copy = duplicate(bag2);
        BagInterface<T> intersection = new LinkedBag<T>(); //creates new bag to be returned
        for (int index = 0; index < bag1Contents.length; index++)
        {
            if (bag2Copy.contains(bag1Contents[index])) //checks if bag2Copy has the entry at current index
            {
                intersection.add(bag1Contents[index]);
                bag2Copy.remove(bag1Contents[index]);
                //bag1Contents[index] = null;
            }
        }
        return intersection;
    }
    public BagInterface<T> difference(BagInterface<T> bag2)
    {
        T[] bag1Contents = this.toArray();
        T[] bag2Contents = bag2.toArray();
        BagInterface<T> bag1Copy = duplicate(bag1Contents);
        for (int index = 0; index < bag2Contents.length; index++)
        {
            if (bag1Copy.contains(bag2Contents[index]))
            {
                bag1Copy.remove(bag2Contents[index]);
                //bag2Contents[index] = null;
            }
        }
        return bag1Copy;
    }

    /** method that creates a deep copy of the the bag given in argument
     @param bag is type BagInterface<T>
     @return bag of BagInterface<T> type */
    private BagInterface<T> duplicate(BagInterface<T> bag)
    {
        BagInterface<T> copy = new LinkedBag<T>();
        T[] bagContents = bag.toArray();
        for (int index = 0; index < bagContents.length; index ++)
        {
            copy.add(bagContents[index]);
        }
        return copy;
    }

    /** method that creates a bag given the array
     @param bagContents is an array that contains a bag's contents in the method calling method duplicate()
     @return bag of BagInterface<T> type */
    private BagInterface<T> duplicate(T[] bagContents)
    {
        BagInterface<T> copy = new LinkedBag<T>();
        for (int index = 0; index < bagContents.length; index ++)
        {
            copy.add(bagContents[index]);
        }
        return copy;
    }


    public boolean add(T newEntry)
    {
        Node newNode = new Node(newEntry);
        newNode.setNextNode(firstNode); //newNode.next = first node
        firstNode = newNode;
        numberOfEntries++;
        return true;
    }
    public T remove()
    {
        T result = null;
        if (firstNode != null)
        {
            result = firstNode.getData(); //firstNode.data
            firstNode = firstNode.getNextNode(); //firstNode.next
            numberOfEntries--;
        }
        return result;
    }
    public boolean remove(T anEntry)
    {
        boolean result = false;
        Node nodeToRemove = getReferenceTo(anEntry);
        if (nodeToRemove != null)
        {
            nodeToRemove.setData(firstNode.data); //nodeToRemove.data = firstNode.data
            firstNode = firstNode.getNextNode(); //firstNode = firstNode.next
            numberOfEntries--;
            result = true;
        }
        return result;
    }

    public int getFrequencyOf(T anEntry)
    {
        int frequency = 0;
        int loopCounter = 0;
        Node currentNode = firstNode;
        while((currentNode != null) && (loopCounter < numberOfEntries))
        {
            if (currentNode.data.equals(anEntry))
                frequency++;
            loopCounter++;
            currentNode = currentNode.getNextNode(); //currentNode = currentNode.next
        }
        return frequency;
    }

    public T[] toArray()
    {
        // the cast is safe because the new array contains null entries
        @SuppressWarnings("unchecked")
        T[] result = (T[])new Object[numberOfEntries];
        int index = 0;
        Node currentNode = firstNode;
        while((currentNode != null) && (index < numberOfEntries))
        {
            result[index] = currentNode.getData(); //result[index] = currentNode.data
            index++;
            currentNode = currentNode.getNextNode(); //currentNode = currentNode.next
        }
        return result;

    }

    public boolean contains(T anEntry)
    {
        return getReferenceTo(anEntry) != null;
    }

    public int getCurrentSize()
    {
        return numberOfEntries;
    }

    public void clear()
    {
        while(!isEmpty())
            remove();
    }

    public boolean isEmpty()
    {
        return numberOfEntries == 0;
    }

    // Locates a given entry within the bag
    // Returns a reference to the node containing the entry, if located, null otherwise
    private Node getReferenceTo(T anEntry)
    {
        Node currentNode = firstNode;
        while((currentNode != null))
        {
            if(currentNode.getData().equals(anEntry)) //currentNode.data.equals(anEntry)
                return currentNode;
            currentNode = currentNode.getNextNode(); // currentNode = currentNode.next
        }
        return null;
    }



    class Node
    {
        private T data; //Entry in bag (data portion)
        private Node next; //Link to next node (link portion)

        private Node(T dataPortion)
        {
            this(dataPortion, null);
        }

        private Node(T dataPortion, Node nextNode)
        {
            data = dataPortion;
            next = nextNode;
        }
        private void setData(T newData)
        {
            data = newData;
        }
        private void setNextNode(Node nextNode)
        {
            next = nextNode;
        }
        private T getData()
        {
            return data;
        }
        private Node getNextNode()
        {
            return next;
        }
    }
}
