public class LinkedList<T> implements ListInterface<T> {
    private Node<T> firstNode;

    // the size of the linked list
    private int size;

    /**
     * Creates a new LinkedList object
     */
    public LinkedList() {
        firstNode = null;
        size = 0;
    }


    /**
     * Gets head of list
     *
     * @return head of linked list
     */
    public Node<T> getFirstNode() {
        return firstNode;
    }


    /**
     * Gets the number of elements in the list
     *
     * @return the number of elements
     */
    @Override
    public int getLength() {
        return size;
    }


    /**
     * Ensures indices that are within the appropriate bounds
     *
     * @throws IndexOutOfBoundsException
     *             if either newPosition less than 0 or newPosition greater
     *             than
     *             getLength() + 1.
     */
    public void indexInBounds(int index) {
        if (index < 0 || index > getLength()) {
            throw new IndexOutOfBoundsException("Index " + index
                + " out of bounds");
        }
    }


    /**
     * Adds the object to the position in the list
     *
     * @precondition obj cannot be null
     * @param index
     *            where to add the object
     * @param obj
     *            the object to add
     * @throws IndexOutOfBoundsException
     *             if index is less than zero or greater than size
     * @throws IllegalArgumentException
     *             if obj is null
     */
    @Override
    public void add(int index, T obj) {
        // check if the object is null
        if (obj == null) {
            throw new IllegalArgumentException("Object is null");
        }

        // check if the index is out of bounds
        indexInBounds(index);

        Node<T> current = firstNode;

        // empty stack case
        if (isEmpty() || index == 0) {
            Node<T> newNode = new Node<T>(obj);
            newNode.setNext(firstNode);
            firstNode = newNode;
        }

        // all other cases
        else {
            int currentIndex = 0;
            while (current != null) {
                if ((currentIndex + 1) == index) {
                    Node<T> nextNext = current.next;
                    Node<T> newNode = new Node<T>(obj);
                    current.setNext(newNode);
                    newNode.setNext(nextNext);

                }
                current = current.next();
                currentIndex++;

            }
        }
        size++;
    }


    /**
     * Adds the object to the end of the list.
     *
     * @precondition obj cannot be null
     * @param obj
     *            the object to add
     * @throws IllegalArgumentException
     *             if obj is null
     */
    @Override
    public void add(T obj) {
        // check if the object is null
        if (obj == null) {
            throw new IllegalArgumentException("Object is null");
        }

        Node<T> current = firstNode;

        // empty stack case
        if (isEmpty()) {
            firstNode = new Node<T>(obj);
        }

        // other cases
        else {
            while (current.next != null) {
                current = current.next;
            }
            current.setNext(new Node<T>(obj));
        }
        size++;
    }


    /**
     * Checks if the array is empty
     *
     * @return true if the array is empty
     */
    @Override
    public boolean isEmpty() {
        return (size == 0);
    }


    /**
     * Removes the first instance of the given object from the list
     *
     * @param obj
     *            the object to remove
     * @return true if successful
     */
    @Override
    public boolean remove(T obj) {
        Node<T> current = firstNode;

        // account for matching firstNode
        if ((null != firstNode) && (obj.equals(current.data))) {
            firstNode = firstNode.next;
            size--;
            return true;
        }

        // account for 2+ size
        while (getLength() >= 2 && (current.next != null)) {
            if ((obj.equals(current.next.data))) {

                current.setNext(current.next.next);

                size--;
                return true;
            }
            current = current.next;
        }

        // this accounts for the isEmpty case or the object does not exist
        return false;
    }


    /**
     * Removes the object at the given position
     *
     * @param index
     *            the position of the object
     * @return true if the removal was successful
     * @throws IndexOutOfBoundsException
     *             if there is not an element at the index
     */
    @Override
    public boolean remove(int index) {
        // if the index is invalid
        indexInBounds(index);

        Node<T> current = firstNode;
        if (index == 0) {
            firstNode = firstNode.next;
            size--;
            return true;
        }
        int currentIndex = 0;

        while (current.next != null) {
            if ((currentIndex + 1) == index) {
                Node<T> newNext = current.next.next;
                current.setNext(newNext);
                size--;
                return true;
            }
            current = current.next;
            currentIndex++;
        }

        // if the element was never found, this also handles empty case
        throw new IndexOutOfBoundsException("Index " + index
            + " out of bounds");

    }


    /**
     * Gets the object at the given position
     *
     * @param index
     *            where the object is located
     * @return The object at the given position
     * @throws IndexOutOfBoundsException
     *             if no node at the given index
     */
    @Override
    public T getEntry(int index) {
        indexInBounds(index);
        Node<T> current = firstNode;
        int currentIndex = 0;
        while (current != null) {
            if (currentIndex == index) {
                return current.data;
            }
            currentIndex++;
            current = current.next;
        }

        // If we get here then the index is out of bounds
        throw new IndexOutOfBoundsException("Index " + index
            + " out of bounds");
    }


    /**
     * Checks if the list contains the given object
     *
     * @param obj
     *            the object to check for
     * @return true if it contains the object
     */
    @Override
    public boolean contains(T obj) {
        Node<T> current = firstNode;
        while (current != null) {
            if (obj.equals(current.data)) {
                return true;
            }
            current = current.next;
        }

        return false;
    }


    /**
     * Removes all of the elements from the list
     */
    @Override
    public void clear() {
        // make sure we don't call clear on an empty list
        if (firstNode != null) {
            firstNode.setNext(null);
            firstNode = null;
            size = 0;
        }

    }


    /**
     * Gets the last time the given object is in the list
     *
     * @param obj
     *            the object to look for
     * @return the last position of it. -1 If it is not in the list
     */
    @Override
    public int lastIndexOf(T obj) {
        int lastIndex = -1;
        Node<T> current = firstNode;
        int currentIndex = 0;
        while (current != null) {
            if (obj.equals(current.data)) {
                lastIndex = currentIndex;
            }
            currentIndex++;
            current = current.next;

        }
        return lastIndex;
    }


    /**
     * Returns a string representation of the list If a list contains A, B,
     * and
     * C, the following should be returned "{A, B, C}" (Without the
     * quotations)
     *
     * @return a string representing the list
     */
    @Override
    public String toString() {
        String result = "{";

        Node<T> current = firstNode;
        while (current != null) {
            result += "" + current.data;
            current = current.next;
            if (current != null) {
                result += ", ";
            }
        }
        result += "}";
        return result;
    }


    /**
     * Returns an array representation of the list If a list contains A, B,
     * and
     * C, the following should be returned {A, B, C}, If a list
     * contains A, B, C, and C the following should be returned {A, B, C, C}
     *
     * @return an array representing the list
     */
    public Object[] toArray() {

        Object[] array = new Object[this.getLength()];

        Node<T> current = firstNode;
        int count = 0;
        while (current != null) {
            array[count] = current.getData();
            current = current.next;
            count++;
        }

        return array;
    }


    /**
     * Returns true if both lists have the exact same contents
     * in the exact same order
     *
     * @return a boolean of whether two lists have the same contents,
     *         item per item and in the same order
     */
    @SuppressWarnings("unchecked")
    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (this.getClass() == obj.getClass()) {

            LinkedList<T> other = ((LinkedList<T>)obj);
            if (other.getLength() == this.getLength()) {
                Node<T> current = firstNode;
                Node<T> otherCurrent = other.firstNode;
                while (current != null) {
                    if (!current.getData().equals(otherCurrent.getData())) {
                        return false;
                    }
                    current = current.next();
                    otherCurrent = otherCurrent.next();
                }
                return true;
            }
        }

        return false;
    } // end equals


    /**
     * replaces item at givenPosition with data represented by newEntry
     *
     * @param givenPosition
     *            index in Linked List to replace
     * @param newEntry
     *            new value to put in at givenPosition
     * @return data that was overwritten
     *
     * @throws IndexOutOfBoundsException
     *             if index out of bounds
     */
    @Override
    public T replace(int givenPosition, T newEntry) {
        indexInBounds(givenPosition);
        T oldData;
        if (givenPosition == 0) {
            oldData = firstNode.getData();
            firstNode.setData(newEntry);
            return oldData;
        }
        else {
            Node<T> current = firstNode;
            int currentIndex = 0;
            while (current != null) {
                if (currentIndex == givenPosition) {
                    oldData = firstNode.getData();
                    current.setData(newEntry);
                    return oldData;
                }
                current = current.next();
                currentIndex++;

            }
        }

        // if the element was never found, this also handles empty case
        throw new IndexOutOfBoundsException("Index " + givenPosition
            + " out of bounds");

    }// end Replace


} // end LinkedChain


/**
 * This represents a node in a singly linked list. This node stores data
 * along with having a pointer to the next node in the list
 *
 * @param <D>
 *            This is the type of object that this class will store
 */
public static final class Node<D> {

    // The data element stored in the node.
    private D data;

    // The next node in the sequence.
    private Node<D> next;

    /**
     * Creates a new node with the given data
     *
     * @param d
     *            the data to put inside the node
     */
    public Node(D d) {
        this(d, null);
    }

    public Node(D d, Node<D> n) {
        data = d;
        next = n;
    }


    /**
     * Sets the node after this node
     *
     * @param n
     *            the node after this one
     */
    public void setNext(Node<D> n) {
        next = n;
    }


    /**
     * Gets the next node
     *
     * @return the next node
     */
    public Node<D> next() {
        return next;
    }


    /**
     * Gets the data in the node
     *
     * @return the data in the node
     */
    public D getData() {
        return data;
    }


    public void setData(D newData) {
        data = newData;
    }
} // End Node