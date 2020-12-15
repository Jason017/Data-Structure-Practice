public class ArrayQueue<T> implements QueueInterface<T> {
    private T[] contents;
    private int front;
    private int rear;
    private int cur;
    private int cap;
    private static final int DEFAULT_CAPACITY = 50;

    public ArrayQueue() {
        this(DEFAULT_CAPACITY);
    }


    @SuppressWarnings("unchecked")
    public ArrayQueue(int initialCapacity) {
        // The new array contains null entries
        contents = (T[])new Object[initialCapacity + 1];
        front = 0;
        rear = contents.length - 1;
    }


    @Override
    public void enqueue(T newEntry) {
        if (isFull()) doubleSize();
        rear++;
        contents[rear%cap] = newEntry;
        cur++;
    }


    @Override
    public T dequeue() {
        T e = getFront();
        contents[front%cap] = null; // for garbage collection
        front++;
        cur--;
        return e;
    }


    @Override
    public T getFront() {
        if (isEmpty()) {
            throw new EmptyQueueException();
        }
        return contents[front];
    }


    @Override
    public boolean isEmpty() {
        return (((rear + 1) % contents.length) == front);
    }


    public boolean isFull()
    {
        return cur == cap;
    }


    @Override
    public void clear() {
        while (!isEmpty()) {
            dequeue();
        }
    }


    private void doubleSize()
    {
        T[] newArray = (T[]) new Object[2*cap];

        //copy items
        for(int i = front; i <= rear; i ++)
            newArray[i-front] = contents[i%cap];
        contents = newArray;
        front = 0;
        rear = cur-1;
        cap *= 2;
    }
}

    T in = nodeToInsert.getData();
    T fn = firstNode.getData();
    if(firstNode == null || fn.compareTo(in) > 0) {
        nodeToInsert.setNext(firstNode);
        firstNode = nodeToInsert;
    }

    Node<T> temp = firstNode;
    while(temp.next != null && temp.next.getData().compareTo(nodeToInsert.getData()) < 0){
        temp = temp.next;
    }
    nodeToInsert.setNext(temp.next());
    temp.setNext(nodeToInsert);


    Node<T> sort = firstNode;
    Node<T> unsort = firstNode.next;
    sort.setNext(null);
    
    while(unsort.next() != null){
        Node<T> temp = sort;
        unsort = unsort.next();
        insertIntoSorted(temp);
    }


    T data = nodeToInsert.getData();
    Node<T> curr = firstNode;
    Node<T> prev = null;
    
    while((curr != null) && data.compareTo(curr.getData()) > 0){
        prev = curr;
        curr = curr.next();
    }
    
    if(prev != null){
        prev.setNext(nodeToInsert);
        nodeToInsert.setNext(curr);
    }
    else{
        nodeToInsert.setNext(firstNode);   
        firstNode = nodeToInsert;
    }