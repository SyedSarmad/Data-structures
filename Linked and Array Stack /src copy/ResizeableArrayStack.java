import java.util.*;
public class ResizeableArrayStack<T> implements StackInterface<T>
{
    private T[] stack;
    private int topIndex;
    //private boolean integrityOK = false;
    private static final int DEFAULT_CAPACITY = 50;
    private static final int MAX_CAPACITY = 10000;

    public ResizeableArrayStack()
    {
        this(DEFAULT_CAPACITY);
    }
    public ResizeableArrayStack(int initialCapacity)
    {
        //integrityOK = false;
        checkCapacity(initialCapacity);

        //Cast is safe because array contains null entries
        @SuppressWarnings("unchecked")
        T[] tempStack = (T[])new Object[initialCapacity];
        stack = tempStack;
        topIndex = -1;
        //integrityOK = true;
    }

    public void push(T newEntry)
    {
        //checkIntegrity();
        ensureCapacity();
        stack[topIndex + 1] = newEntry;
        topIndex++;
    }

    public T pop()
    {
        //checkIntegrity();
        if (isEmpty())
            throw new EmptyStackException();
        else
        {
            T pop = stack[topIndex];
            stack[topIndex] = null;
            topIndex--;
            return pop;
        }
    }

    public T peek()
    {
        //checkIntegrity();
        if (isEmpty())
            throw new EmptyStackException();
        else
            return stack[topIndex];
    }

    public boolean isEmpty()
    {
        return topIndex < 0;
    }

    public void clear()
    {
        //checkIntegrity();
        while(topIndex > -1)
        {
            stack[topIndex] = null;
            topIndex--;
        }
    }

    private void checkCapacity(int newLength)
    {
        if (newLength > MAX_CAPACITY)
            throw new IllegalStateException("Bag has exceeded maximum capacity: " + MAX_CAPACITY);
    }

    private void ensureCapacity()
    {
        if (topIndex >= stack.length - 1)  // If array is full, double its size
        {
            int newLength = 2 * stack.length;
            checkCapacity(newLength);
            stack = Arrays.copyOf(stack, newLength);
        }
    }
}
