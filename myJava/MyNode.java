// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those who
// do.
// -- Bo Guan (jasonguan0107), Lauren Osborne(osbornelh72)
package prj5;

/**
 * Node class for DDList.
 * 
 * @author Lauren Osborne, Bo Guan
 * @version 2020-11-16
 * @param <T>
 *            Generic type
 */
public class Node<T> {
    private Node<T> next;
    private Node<T> previous;
    private T data;

    /**
     * Creates a new node with the given data
     *
     * @param d
     *            the data to put inside the node
     */
    public Node(T d) {
        data = d;
    }


    /**
     * Sets the node after this node
     *
     * @param n
     *            the node after this one
     */
    public void setNext(Node<T> n) {
        next = n;
    }


    /**
     * Sets the node before this one
     *
     * @param n
     *            the node before this one
     */
    public void setPrevious(Node<T> n) {
        previous = n;
    }


    /**
     * Gets the next node
     *
     * @return the next node
     */
    public Node<T> next() {
        return next;
    }


    /**
     * Gets the node before this one
     *
     * @return the node before this one
     */
    public Node<T> previous() {
        return previous;
    }


    /**
     * Gets the data in the node
     *
     * @return the data in the node
     */
    public T getData() {
        return data;
    }
}
