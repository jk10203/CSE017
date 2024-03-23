/***
 * Class to modify the LinkedList Class
 * @author Joy Kim
 * @version 0.1
 * Date of creation: April, 2023
 * Last Date Modified: April 20, 2023
 *
 * This program was made in order to modify the LinkedList class so that another inner class for ListIterator
 * is initialized which is a bidirectional iterator. This establishes a hasPrevious and previous methods that 
 * allows access to the previous values.
 */
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;
/**
    Linked List data structure (Singly Linked)
 */
public class LinkedList<E>{
    // Data members: references to the head and tail nodes
	private Node head, tail;
    // data member: size of the list
	int size;
    /**
        Inner class Node
     */ 
	private class Node{
		E value;
		Node next;
        Node previous;
        /**
            Constructor with one parameter
            sets the value and initializes next to null
            @param initialValue the value of the node
         */
		Node(E initialValue){
			value = initialValue; 
            next = null;
            previous = null;
		}
	}
    /**
        default constructor
        sets the head and tail to null and size to 0
     */ 
	public LinkedList() { 
		head = tail = null;
		size = 0;
	}

    /**
        Adding an item at the head of the list
        @param value of the node to be added
        @return true if the node was added successfully
    */
    public boolean addFirst(E value) {
		Node newNode = new Node(value);
		if(head == null) { //if empty
            head = tail = newNode; 
            head.previous = null;
            tail.next = null;
        }
		else { 
            head.previous = newNode; //old head previous is now the new value (newNode)
            newNode.next = head; //making it so that the value is added before the current obj at first
            //old value (head) = .next
			head = newNode; //@head is the value

            newNode.previous = null; //need this?


		}
		size++; 
        return true;
    }
    /**
        Adding an item at the end of the list
        @param value of the node to be added
        @return true if the node was added successfully
    */
    public boolean addLast(E value) {
		Node newNode = new Node(value);
		if(head == null) { //if empty
            head = tail = newNode; 
            head.previous = null;
            tail.next = null;
        }
		else { 
            tail.next = newNode; 
            newNode.previous = tail;
            tail = newNode; //think of tail as a pointer
            tail.next = null;
        }
		size++; 
        return true;
    }
    /**
        Adding an item at the tail of the list (inherited from Collection)
        @param value of the node to be added
        @return true if the node was added successfully
    */
    public boolean add(E value) {
		return addLast(value);
    }
    /**
        Retrieving the value of the first node in the list
        @return the value of the node at the head of the list
        @throws NoSuchElementException if the list is empty
        time complexity: O(1)
     */
    public E getFirst() {
		if (head == null)
			throw new NoSuchElementException();
		return head.value;
    }
    /**
        Retrieving the value of the last node in the list
        @return the value of the node at the tail of the list
        @throws NoSuchElementException if the list is empty
        time complexity: O(1)
     */
    public E getLast() {
		if (head == null)
			throw new NoSuchElementException();
		return tail.value;
    } 
    /**
        Removing the first node from the list
        @return true if the node was removed successfully
        @throws NoSuchElementException if the list is empty
     */
    public boolean removeFirst() {
		if (head == null){ 
            throw new NoSuchElementException();
        }
		head = head.next;

		if(head == null){ //if head.next is null (only 1 element in list)
            tail = null;
        } else {
            head.previous = null; //can't do null.previous so its else
        }
		size--; 
        return true;
    }
    /**
        Removing the last node from the list
        @return true if the node was removed successfully
        @throws NoSuchElementException if the list is empty
    */
    public boolean removeLast() {
        if (head == null){ //if empty
            throw new NoSuchElementException();
        }
        if (size == 1){ //if only 1 element in list
            return removeFirst();
        }
        tail = tail.previous;
        tail.next = null;
        size--;
        return true;
    }
    /**
        Get the value of the node at position index
        @param index of the node being accessed
        @return the value of the node at index
        @throws ArrayIndexOutOfBoundsException if index < 0 or index >= size
        time complexity: O(n)
     */
    public E get(int index){
        if(index >= size || index < 0)
            throw new ArrayIndexOutOfBoundsException();
        if(index == 0){
            return getFirst();
        }
        if(index == size-1){
            return getLast();
        }
        Node node = head;
        for(int i=0; i<index; i++){
            node = node.next;
        }
        return node.value;
    }
    /**
        Set the value of the node at position index
        @param index of the node being modified
        @return the old value of the modified node
        @throws ArrayIndexOutOfBoundsException if index < 0 or index >= size
        time complexity: O(n)
     */
    public E set(int index, E value){
        if(index >= size || index < 0)
            throw new ArrayIndexOutOfBoundsException();
        E old;
        if(index == 0){
            old = head.value;
            head.value = value;
            return old;
        }
        if(index == size-1){
            old = tail.value;
            tail.value = value;
            return old;
        }
        Node node = head;
        for(int i=0; i<index; i++){
            node = node.next;
        }
        old = node.value;
        node.value = value;
        return old;
    }

    /**
        @override toString from class Object
        @return formatted string with all the values in the list
        time complexity: O(n)
     */
    public String toString() {
		String output = "[";
		Node node = head;
		while(node != null) {
			output += node.value + " ";
			node = node.next;
		}
		output += "]";
		return output;
    }
    /**
        clear the list
        sets head and tail to null and size to 0
        time complexity: O(1)
     */
    public void clear() {
        head = tail = null; 
        size = 0; 
    }
    /**
        check if the list is empty
        @return true if the list is empty, false otherwise
        time complexity: O(1)
     */
    public boolean isEmpty() {
        return (size == 0);
    }
    /**
        get the size of the list
        @return the number of nodes in the list
        time complexity: O(1)
     */
    public int size() {
        return size; 
    } 
    /**
        Get an iterator for the list
        @return an iterator object pointing to the first node in the list
        time complexity: O(1)
     */
    public Iterator<E> iterator(){
		  return new LinkedListIterator();
    }
    /**
        Inner class that implements the interface Iterator
     */
    private class LinkedListIterator implements Iterator<E>{
        // data member: reference to the node referenced by the iterator
		private Node current = head;
        /**
            check if the iterator has a next node
            @return true if the iterator has access to the next node, false otherwise
            time complexity: O(1)
         */
		public boolean hasNext() {
			return (current != null);
		}
        /**
            get the value of the current noe and move the iterator to the next node
            @return the value of the current node
            time complexity: O(1)
         */
	    public E next() {
            if(current == null)
			  throw new NoSuchElementException();
			E value = current.value;
			current = current.next;  //wouldn't it have to return current
            return value;
		}
    }
    
    /**
        Method to get a bidirectional iterator for the list
        @return an iterator object pointing to the first node of the list
     */
     //BIG O NOTATION: O(1)
    public ListIterator<E> listIterator(){
        return new LinkedIterator();
    }
    /**
        Method to get a bidirectional iterator for the list
        @param index where the iterator should start
        @return the iterator object pointing to the element at index
        @throws ArrayIndexOutOfBoundsExeption if index is less than 0 or index is greater than size
     */
     //BIG O NOTATION: O(1)
    public ListIterator<E> listIterator(int index){
        if (index < 0 || index > size){
            throw new ArrayIndexOutOfBoundsException();
        }
        return new LinkedIterator(index);
    }
     /**
        Inner class that implements the interface ListIterator<E>
     */
    public class LinkedIterator implements ListIterator<E>{
        private Node current; 

        /***
        * Default constructor
        * No parameters
        * Intitializes current to head
        */
        public LinkedIterator(){
            current = head;
        }
        /***
        * Constructor with one parameter
        * @param	index for the starting index of the iterator
        */
        public LinkedIterator(int index){
            //for loop for linked list
            current = head;
            for (int i=0; i< index; i++){
                current = current.next;
            }
        }
        /**
            check if the ListIterator has a next node
            @return true if the ListIterator has access to the next node, false otherwise
            time complexity: O(1)
         */
        public boolean hasNext() {
            //returns true if this list iterator has more elements when traversing the list in the forward direction.
			return (current != null);  
		}
        /**
            get the value of the current node and move the ListIterator to the next node
            @return the value of the current node
            time complexity: O(1)
         */
	    public E next() {
            if(current == null)
			  throw new NoSuchElementException();
			E value = current.value;
			current = current.next; 
            return value;
		}
        /**
            check if the ListIterator has a previous node
            @return true if the LinkedIterator has access to the previous node, false otherwise
            time complexity: O(1)
         */
        public boolean hasPrevious() {
            return (current != null); 
		}
        /**
            get the value of the current node and move the ListIterator to the previous node
            @return the value of the current node
            time complexity: O(1)
         */
	    public E previous() {
            if(current == null){ //current is null bc iterator is null
			    throw new NoSuchElementException();
            }
			E value = current.value;
			current = current.previous; 
            return value;
		}
        public void add(E value){
            throw new UnsupportedOperationException();
        }
        public void remove(){
            throw new UnsupportedOperationException();
        }
        public void set(E value){
            throw new UnsupportedOperationException();
        }
        public int nextIndex(){
            throw new UnsupportedOperationException();
        }
        public int previousIndex(){
            throw new UnsupportedOperationException();
        }
    }
    
}