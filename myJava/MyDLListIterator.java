// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those who
// do.
// -- Bo Guan (jasonguan0107), Lauren Osborne(osbornelh72)
package prj5;

import java.util.NoSuchElementException;

/**
 * Implements the ListADT with a doubly-linked list using sentinel nodes.
 * 
 * @author Lauren Osborne, Bo Guan
 * @version 2020-11-16
 * @param <T>
 *            generic type
 */
public class DLListIterator<T> implements java.util.Iterator<T> {
    private Node<T> curr;
    private boolean canRemove;
    private DLList<T> list;

    /**
     * Creates a new DLListIterator.
     * 
     * @param list
     *            the input DLList
     */
    public DLListIterator(DLList<T> list) {
        this.list = list;
        curr = this.list.head();
        canRemove = false;
    }


    /**
     * Checks if there are more items in the list.
     * 
     * @return True if there are and false if not.
     */
    @Override
    public boolean hasNext() {
        return curr.next().getData() != null;
    }


    /**
     * Gets the next value in the list.
     * 
     * @return The next value in the list.
     * 
     * @throws NoSuchElementException
     *             if there are no more nodes in the list.
     */
    @Override
    public T next() {
        Node<T> temp = curr.next();
        if (temp.getData() == null) {
            throw new NoSuchElementException(
                "There are no more nodes in the list.");
        }
        curr = curr.next();
        canRemove = true;
        return temp.getData();
    }


    /**
     * Removes the most recent object returned by next().
     * 
     * @throws IllegalStateException
     *             if next() has not been called yet or the object has
     *             already been removed.
     */
    public void remove() {
        if (canRemove) {
            curr.previous().setNext(curr.next());
            curr.next().setPrevious(curr.previous());
            canRemove = false;
            list.setLength(list.getLength() - 1);
        }
        else {
            throw new IllegalStateException(
                "The next() method must be called to remove an object.");
        }
    }
}
