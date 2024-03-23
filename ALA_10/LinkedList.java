/***
 * Class to modify the LinkedList Class
 * @author Joy Kim
 * @version 0.1
 * Date of creation: April, 2023
 * Last Date Modified: April 25, 2023
 *
 * This program was made in order to modify the LinkedList class so that it can count the number of iterations
 * it performs when it does its operations.
 */
import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedList<E> {
    // Data members
    private Node head, tail;
    private int size;
    public static int containsIter, removeIter, addIter;

  
    // Inner class Node
    /***
	 * Inner Node class
	 * No parameters
	 * Initializes next to null and value to initialValue
	 */
    private class Node {
        E value;
        Node next; //holds refererence to type node
        Node(E initialValue) {
            value = initialValue;
            next = null;
        }
    }
    // Constructor
    /***
	 * Default constructor
	 * No parameters
	 * Initializes head and tail to null, and size to zero
	 */
    public LinkedList() {
        head = tail = null;
        size = 0;
    }
    // Adding an item to the list
    /***
	 * Method to add to the beginning of the list
	 * @param item for the item to be added
	 * @return true or false whether it was added or not
	 */
    public boolean addFirst(E item) {
        Node newNode = new Node(item);
        if (head == null) {
            head = tail = newNode;
        }
        else {
            newNode.next = head;
            head = newNode;
        }
        size++;
        return true;
    }
    /***
	 * Method to append to the end of the list
	 * @param item for the item to be added
	 * @return true or false whether it was added or not
	 */
    public boolean addLast(E item) {
        Node newNode = new Node(item);
        if (head == null) {
            head = tail = newNode;
        }
        else{
            tail.next = newNode;
            tail = newNode;
        }
        size++;
        return true;
    }
    /***
	 * Method to add to the endof the list
	 * @param item for the item to be added
	 * @return true or false whether it was added or not
	 */
    public boolean add(E item) {
        return addLast(item);
    }
    // Retrieving an item from the list
    /***
	 * Method to get the first element of the list 
	 * @param no parameters
	 * @return the element that is at the head
	 */
    public E getFirst() {
        if (head == null)
            throw new NoSuchElementException();
        return head.value;
    }
    /***
	 * Method to get the last element of the list 
	 * @param no parameters
	 * @return the element that is at the tail
	 */
    public E getLast() {
        if (head == null)
            throw new NoSuchElementException();
        return tail.value;
    }
    // Removing an item from the list
    //o(1)
    /***
	 * Method to get the remove the first element of the list 
	 * @param no parameters
	 * @return true or false whether the operation was successful or not
	 */
    public boolean removeFirst() {
        if (head == null)
            throw new NoSuchElementException();
        head = head.next;
        if (head == null)
            tail = null;
        size--;
        return true;
    }
    /***
	 * Method to get the remove the last element of the list 
	 * @param no parameters
	 * @return true or false whether the operation was successful or not
	 */
    public boolean removeLast() {
        if (head == null)
            throw new NoSuchElementException();
        if (size == 1)
            return removeFirst();
        Node current = head;
        Node previous = null;
        while (current.next != null) {
            previous = current;
            current = current.next;
        }
        previous.next = null;
        tail = previous;
        size--;
        return true;
    }
    // toString() method
    /***
	 * Method to get formatted string values of elements in linkedlist
	 * @param no parameters
	 * @return a string that is formatted
	 */
    public String toString() {
        String output = "[";
        Node node = head;
        while (node != null) {
            output += node.value + " ";
            node = node.next;
        }
        output += "]";
        return output;
    }
    // clear, check if empty, and size
    /***
	 * Method to clear the linked list
	 * no return value
	 */
    public void clear() {
        head = tail = null;
        size = 0;
    }
    /***
	 * Method to check if the linked list is empty
	 * @param no parameters
	 * @return true or false whether it is empty or not
	 */
    public boolean isEmpty() {
        return (size == 0);
    }
    /***
	 * Method to get the size of the linkedlist
	 * @param no parameters
	 * @return an integer that is the size of the linkedlist
	 */
    public int size() {
        return size;
    }

    // Generating an iterator for the list
    /***
	 * Method to make a new iterator for the linkedlist
	 * @param no parameters
	 * @return an iterator object for the linkedlist
	 */
    public Iterator<E> iterator() { //variable current to hold reference to node?
        return new LinkedListIterator();
    }

    private class LinkedListIterator implements Iterator<E> {
        private Node current = head;
        /***
	 * Method to check if the linkedList has a next element
	 * @param no parameters
	 * @return true or false whether it does or does not have a next element
	 */
        public boolean hasNext() {
            return (current != null);
        }
        /***
	 * Method to get the next value
	 * @param no parameters
	 * @return the object that is next
	 */
        public E next() {
            if (current == null)
                throw new NoSuchElementException();
            E value = current.value;
            current = current.next; //pointing to next node
            return value; //value of current node
        }
    }
    //contains method
    //o(n)
    /***
	 * Method to check if the linkedlist contains an element
	 * @param o for the object to be checked
	 * @return true or false whether it contains it or not
	 */
    public boolean contains (Object o){
        containsIter=0;
        E value = (E)o;
        Iterator<E> iter = iterator();
        while(iter.hasNext()){
            containsIter++;
            if(iter.next().equals(value)){
                return true;
            }
        }
        return false;
    }
    //o(n)
    /***
	 * Method to remove a specific object
	 * @param o for the object to remove
	 * @return true or false whether the operation was successful or not
	 */
    public boolean remove(Object o){ //no need to shift
        //previous.next = current.next (gets rid of current)
        removeIter = 0;
        Node current = head;
        Node previous = null;
        while (current!= null && !current.value.equals(o)){ //didn't reach end of list
            removeIter++;
            previous = current;
            current = current.next;
        }
        //either current is null or value and current are equal
        if (current==null){ //if value trying to remove is head, then previous is null
            return false;
        }
        if(previous == null){
            return removeFirst();
        } else {
            previous.next = current.next;
            if (current == tail){
                tail = previous; 
            }
            //decrement size bc we removed
            size--;
            return true;
        }
    }
    //O(n) --> index in the worst case can be size-1 (before tail) size (after tail)
    /***
	 * Method to add an object at a specific index
	 * @param index the position for the element to be added
     * @param element to be added
	 * @return true or false whether the operation was successful or not
	 */
    public boolean add(int index, E element){//previous to new then new to current
        addIter = 0;
        if (index < 0||index > size){
            throw new ArrayIndexOutOfBoundsException();
        }
        if (index == 0){
            return addFirst(element); //constant time
        }
        if (index == size){
            return addLast(element); // constant time
        }
        Node current = head;
        Node previous = null;
        for (int i=0; i<index; i++){ //to go element at index
            addIter++; //not constant time so put here
            previous = current;
            current = current.next;
        }
        //constant time
        Node newNode = new Node(element);
        previous.next = newNode;
        newNode.next = current;
        size++;
        return true;
    }
}