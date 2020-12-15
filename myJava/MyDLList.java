// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those who
// do.
// -- Bo Guan (jasonguan0107), Lauren Osborne(osbornelh72)
package prj5;

/**
 * Implements the ListADT with a doubly-linked list using sentinel nodes.
 * 
 * @author Lauren Osborne, Bo Guan
 * @version 2020-11-16
 * 
 * @param <E>
 *            The type of object the class will store.
 */
public class DLList<E> implements Iterable<E> {

    private Node<E> head;
    private Node<E> tail;
    private int length;

    /**
     * Creates a DLList object.
     */
    public DLList() {
        head = new Node<E>(null);
        tail = new Node<E>(null);
        head.setNext(tail);
        tail.setPrevious(head);
        length = 0;
    }


    /**
     * Gets the head node of the list.
     * 
     * @return The head node of the list.
     */
    public Node<E> head() {
        return head;
    }


    /**
     * Sets the length of the list.
     * 
     * @param length
     *            The new length of the list.
     */
    public void setLength(int length) {
        this.length = length;
    }


    /**
     * Gets the length of the list.
     * 
     * @return The length of the list.
     */
    public int getLength() {
        return length;
    }


    /**
     * Adds a new entry to the list.
     * 
     * @param data
     *            The data to be stored in the node.
     * 
     * @throws IllegalArgumentException
     *             if the parameter is null.
     */
    public void add(E data) {
        if (data == null) {
            throw new IllegalArgumentException("The data to be added is null.");
        }
        Node<E> toAdd = new Node<E>(data);
        if (length == 0) {
            head.setNext(toAdd);
            tail.setPrevious(toAdd);
            toAdd.setNext(tail);
            toAdd.setPrevious(head);
        }
        else {
            Node<E> before = tail.previous();
            before.setNext(toAdd);
            toAdd.setPrevious(before);
            tail.setPrevious(toAdd);
            toAdd.setNext(tail);
        }
        length++;
    }


    /**
     * Adds an entry to a specified position of the list.
     * 
     * @param data
     *            The data to be added.
     * @param position
     *            The position for the data to be added at.
     * @throws IndexOutOfBoundsException
     *             if the position is out of bounds.
     * 
     * @throws IllegalArgumentException
     *             if the data to be added is null.
     */
    public void addToPosition(int position, E data) {
        if (position < 0 || length < position) {
            throw new IndexOutOfBoundsException();
        }
        if (data == null) {
            throw new IllegalArgumentException("The entry can't be null.");
        }

        if (position == length) {
            add(data);
        }
        else {
            Node<E> after = getNodeAt(position);
            Node<E> toAdd = new Node<E>(data);
            toAdd.setPrevious(after.previous());
            toAdd.setNext(after);
            after.previous().setNext(toAdd);
            after.setPrevious(toAdd);
            length++;
        }

    }


    /**
     * Gets the node at the given position.
     * 
     * @param position
     *            The position.
     * 
     * @return The node at that position.
     */
    private Node<E> getNodeAt(int position) {
        Node<E> curr = head.next();
        for (int i = 0; i < position; i++) {
            curr = curr.next();
        }
        return curr;
    }


    /**
     * Determines if the list is empty.
     * 
     * @return True if list is empty and false if not.
     */
    public boolean isEmpty() {
        return length == 0;
    }


    /**
     * Removes the entry containing the given data from the list.
     * 
     * @param data
     *            The data of the entry to be removed.
     * 
     * @return True if the node is removed and false if not.
     */
    public boolean remove(E data) {
        Node<E> curr = head.next();
        while (!curr.equals(tail)) {
            if (curr.getData().equals(data)) {
                curr.previous().setNext(curr.next());
                curr.next().setPrevious(curr.previous());
                length--;
                return true;
            }
            curr = curr.next();
        }
        return false;
    }


    /**
     * Gets the position of the node storing the given data.
     * 
     * @param data
     *            The data of the node to be found.
     * 
     * @return The integer position of the node. -1 if it is not found.
     */
    public int getPosition(E data) {
        Node<E> curr = head.next();
        int position = 0;
        while (!curr.equals(tail)) {
            if (curr.getData().equals(data)) {
                return position;
            }
            position++;
            curr = curr.next();
        }
        return -1;
    }


    /**
     * Gets the entry at the given position.
     * 
     * @param position
     *            The position.
     * 
     * @return The data stored in the node at that position.
     */
    public E getAnEntry(int position) {
        if (position < 0 || length <= position) {
            throw new IndexOutOfBoundsException(
                "There is no entry at that index");
        }
        Node<E> curr = head.next();
        for (int i = 0; i < position; i++) {
            curr = curr.next();
        }
        return curr.getData();
    }


    /**
     * Determines if the list contains the data point.
     * 
     * @param data
     *            The data point.
     * 
     * @return True if the list contains it and false if not.
     */
    public boolean contains(E data) {
        int position = getPosition(data);
        return position != -1;
    }


    /**
     * Clears the DLList.
     */
    public void clear() {
        head = new Node<E>(null);
        tail = new Node<E>(null);
        head.setNext(tail);
        tail.setPrevious(head);
        length = 0;
    }


    /**
     * Converts the DLList to a String.
     * 
     * @return A String containing the data of the List.
     */
    public String toString() {
        StringBuilder builder = new StringBuilder("{");
        if (!isEmpty()) {
            Node<E> currNode = head.next();
            while (currNode != tail) {
                E element = currNode.getData();
                builder.append(element.toString());
                if (currNode.next() != tail) {
                    builder.append(", ");
                }
                currNode = currNode.next();
            }
        }

        builder.append("}");
        return builder.toString();
    }


    /**
     * Determines if two DLList objects are equal.
     * 
     * @param obj
     *            The object being compared to the List.
     * 
     * @return True if the two DLLists are equal and false if not.
     */
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        if (this.getClass().equals(obj.getClass())) {
            @SuppressWarnings("unchecked")
            DLList<E> other = (DLList<E>)obj;
            if (other.length == this.length) {
                Node<E> curr = this.head.next();
                Node<E> otherCurr = other.head.next();
                while (curr != tail) {
                    if (!curr.getData().equals(otherCurr.getData())) {
                        return false;
                    }
                    curr = curr.next();
                    otherCurr = otherCurr.next();
                }
                return true;
            }
        }
        return false;
    }


    /**
     * Creates an Iterator.
     * 
     * @return A DLListIterator.
     */
    public DLListIterator<E> iterator() {
        return new DLListIterator<E>(this);
    }
}