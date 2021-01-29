import java.util.*;
public class LinkedStack<T> implements StackInterface<T>
{
    private Node topNode;

    public LinkedStack()
    {
        topNode = null;
    }

    public void push(T newEntry)
    {
        topNode =  new Node(newEntry, topNode);
    }
    public T pop()
    {
        T top = peek(); // Might throw EmptyStackException
        // Assertion: topNode != null
        topNode = topNode.getNextNode();
        return top;
    }
    public T peek()
    {
        if (isEmpty())
            throw new EmptyStackException();
        else
            return topNode.getData();
    }
    public boolean isEmpty()
    {
        return topNode == null;
    }
    public void clear()
    {
        topNode = null;
    }

    private class Node
    {
        private T data;
        private Node next;

        private Node(T dataPortion)
        {
            this(dataPortion, null);
        }

        private Node(T dataPortion, Node nextNode)
        {
            data = dataPortion;
            next = nextNode;
        }

        public void setData(T data)
        {
            this.data = data;
        }
        public void setNextNode(Node next)
        {
            this.next = next;
        }

        public T getData()
        {
            return data;
        }

        public Node getNextNode()
        {
            return next;
        }
    }

}

