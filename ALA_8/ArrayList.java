/***
 * Class to modify the ArrayList Class
 * @author Joy Kim
 * @version 0.1
 * Date of creation: April, 2023
 * Last Date Modified: April 9, 2023
 *
 * This program was made in order to modify the ArrayList class so that it can count the number of iterations
 * it performs when it does its operations.
 */
import java.util.Iterator;
import java.util.Comparator;

public class ArrayList<E> {
    // data members
    private E[] elements;
    private int size;
    public static int containsIter, addIter, removeIter;
    // Constructors
    /***
	 * Default constructor
	 * No parameters
	 * Initializes arraylist to 10 elements and size to 0
	 */
    public ArrayList() {
	   elements = (E[]) new Object[10];
	   size = 0;
    }
    /***
	 * Constructor with one parameter
	 * @param capacity for the number of elements for the arrayList
	 * Initializes arraylist to capacity elements and size to 0
	 */
    public ArrayList(int capacity) {
        elements = (E[]) new Object[capacity];
        size = 0;
    }
   // Adding an item to the list (2 methods)
    /***
	 * Method to add an object at the end
     * @param element to be added
	 * @return true or false whether the operation was successful or not
	 */
    public boolean add(E item) {
		return add(size, item);
    }
    // Adding an item to the list at index
    /***
	 * Method to add an object at a specific index
	 * @param index the position for the element to be added
     * @param element to be added
	 * @return true or false whether the operation was successful or not
	 */
    public boolean add(int index, E item){
        addIter = 0;
        if(index > size || index < 0)
            throw new ArrayIndexOutOfBoundsException();
        ensureCapacity();
        for(int i=size-1; i>=index; i--){
            addIter++;
            elements[i+1] = elements[i];
        }
        elements[index] = item;
        size++;
        return true;
    }

    // Getter and Setter
    /***
	 * Method to get an element at a specific index in the list 
	 * @param index for the element to get at that specific index
	 * @return the element at that index
	 */
    public E get(int index) {
		checkIndex(index);
		return elements[index];
    }
    /***
	 * Method to set an element at a specific index in the list 
	 * @param index for the element to set at that specific index
	 * @return the element at that index that changed
	 */
    public E set(int index, E item) {
		checkIndex(index);
		E oldItem = elements[index];
		elements[index] = item;
		return oldItem;
    }
    // Size of the list
    /***
	 * Method to get the size of the ArrayList
	 * @param no parameters
	 * @return an integer that is the size of the ArrayList
	 */
    public int size() {
        return size;
    }
    // Clear the list
    /***    
	 * Method to clear the arrayList
     * @param no parameters
	 * no return value
	 */
    public void clear() {
        size = 0;
    }
    // Check if the list is empty
    /***
	 * Method to check if the ArrayList is empty
	 * @param no parameters
	 * @return true or false whether it is empty or not
	 */
    public boolean isEmpty() {
        return (size == 0);
    }

    // Removing an object from the list
    /***
	 * Method to remove a specific object
	 * @param o for the object to remove
	 * @return true or false whether the operation was successful or not
	 */
    public boolean remove(Object o) {
        removeIter = 0;
        E item = (E) o; // casting down o to type E
        for(int i=0; i<size; i++){
            removeIter++;
            if(elements[i].equals(item)){
                remove(i);
                return true;
            }
      }
      return false;
    }

    // Removing the item at index from the list
    /***
	 * Method to remove a specific object at a certain index
	 * @param index for the index at which the object to remove is at
	 * @return the item that was removed
	 */
    public E remove(int index) {
        checkIndex(index);
        E item = elements[index];
        for(int i=index; i<size-1; i++){
            removeIter++;
		    elements[i] = elements[i+1];
        }
        size--;
        return item;
    }

    // Shrink the list to size
    /***
	 * Method to trim the size of the ArrayList
	 * @param no parameters
	 * no return value
	 */
    public void trimToSize() {
		  if (size != elements.length) {
			    E[] newElements = (E[]) new Object[size];// capacity = size
			    for(int i=0; i<size; i++)
				    newElements[i] = elements[i];
			    elements = newElements;
		  }
    }
    // Grow the list if needed
    /***
	 * Method to increase the capacity of this ArrayList instance to ensure that it can hold 
     * at least the number of elements specified by the minimum capacity argument.
	 * @param no parameters
	 * no return value
	 */
    private void ensureCapacity() {
	    if(size >= elements.length) {
          int newCap = (int) (elements.length * 1.5);
		      E[] newElements = (E[]) new Object[newCap];
		      for(int i=0; i<size; i++){
                    addIter++;
				    newElements[i] = elements[i];
          }
		      elements = newElements;
	    }
    }
    // Check if the index is valid
    /***
	 * Method to check if that index is within bounds
	 * @param index the position to be checked
	 * no return value
	 */
    private void checkIndex(int index){
		  if(index < 0 || index >= size)
			    throw new ArrayIndexOutOfBoundsException(
              "Index out of bounds. Must be between 0 and "+(size-1));
    }
    // toString() method
    /***
	 * Method to get formatted string values of elements in ArrayList
	 * @param no parameters
	 * @return a string that is formatted
	 */
    public String toString() {
		  String output = "[";
		  for(int i=0; i<size-1; i++)
			    output += elements[i] + ", ";
		  output += elements[size-1] + "]";
		  return output;
    }
    // Iterator for the list
    /***
	 * Method to make a new iterator for the arraylist
	 * @param no parameters
	 * @return an iterator object for the arraylist
	 */
    public Iterator<E> iterator(){
		  return new ArrayIterator();
    }
    // Inner class that implements Iterator<E>

    private class ArrayIterator implements Iterator<E>{
	    private int current = -1;

        /***
	     * Method to check if the arrayList has a next element
        * @param no parameters
        * @return true or false whether it does or does not have a next element
        */
	    public boolean hasNext() {
        return current < size-1;
      }

        /***
        * Method to get the next value
        * @param no parameters
        * @return the object that is next
        */
	    public E next() {
        return elements[++current];
      }
    }

    // Method contains
    // O(n)
    /***
	 * Method to check if the ArrayList contains an element
	 * @param o for the object to be checked
	 * @return true or false whether it contains it or not
	 */
    public boolean contains(Object o){
        containsIter = 0;
        E value = (E) o;
        Iterator<E> iter = iterator();
        while(iter.hasNext()){
            containsIter++;
            if(iter.next().equals(value))
            {
                return true;
            }
        }
        return false;
    }
}