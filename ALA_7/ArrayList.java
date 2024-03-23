/***
 * Class to process ArrayList<E>
 * @author Joy Kim
 * @version 0.1
 * Date of creation: March, 2023
 * Last Date Modified: March 30, 2023
 *
 * This program was made in order to establish properties of Arraylist class for generics.
 */
import java.util.Iterator;
import java.util.Comparator;
public class ArrayList<E> {
   // data members
   private E[] elements;
   private int size;
   public static int containsIter, sortIter, comparatorSortIter;
   
   // Constructors
   /***
	 * Default constructor
	 * No parameters
	 * Initializes elements array to size 10 and size to 0
	 */
   public ArrayList() {
	   elements = (E[]) new Object[10];
	   size = 0;
   }
   /***
	 * Constructor with 1 parameter
	 * @param capacity for the length of the array to be set
	 * Initializes elements array to size capacity and size to 0
	 */
   public ArrayList(int capacity) {
     elements = (E[]) new Object[capacity];
     size = 0;
   }
   /***
	 * Method to add an object to an array depending on size
	 * @param item for the object to be added
	 * @return boolean to see if it added successfully
	 */
   // Adding an item to the list (2 methods)
    public boolean add(E item) {
		  return add(size, item);	// adding at the first free index
    }
    /***
	 * Method to add an object to an array at a certain index
	 * @param index for the place in the array that it should be added
   * @param item for the object to be added
	 * @return boolean to see if it added successfully
	 */
    // Adding an item to the list at index
    public boolean add(int index, E item){
		  if(index > size || index < 0)
			  throw new ArrayIndexOutOfBoundsException();
		  ensureCapacity();
		  for(int i=size-1; i>=index; i--){
			  elements[i+1] = elements[i];
      }
		  elements[index] = item;
		  size++;
		  return true;
    }
    
    // Getter and Setter
    /***
	 * Getter for the element at a certain index
	 * @param index for which element you want to get
	 * @return	the value of the array element at that index
	 */
    public E get(int index) {
		  checkIndex(index);
		  return elements[index];
    }
    /***
	 * Setter for the element at a certain index
	 * @param	index for which element you want to set
   * @param item for the object you want to set
	 * @return the element that was replaced
	 */
    public E set(int index, E item) {
		  checkIndex(index);
		  E oldItem = elements[index];
		  elements[index] = item;
		  return oldItem;
    }

    // Size of the list
    /***
	 * Method to get the array's size
	 * @param no parameters
	 * @return integer value of the array's size
	 */
    public int size() {
      return size;
    }
    // Clear the list
    /***
	 * Method to clear the array by setting size to 0
	 * @param no parameters
	 * no return value
	 */
    public void clear() {
      size = 0;
    }
    // Check if the list is empty
    /***
	 * Method to check if an array is empty by using a boolean 
	 * @param no parameters
	 * @return boolean which will show if it is empty or not
	 */
    public boolean isEmpty() {
      return (size == 0);
    }

    // Removing an object from the list
    /***
	 * Method to remove an element
	 * @param o for the object to be removed
	 * @return boolean which will show if it was removed successfully or not
	 */
    public boolean remove(Object o) {
      E item = (E) o; // casting down o to type E
      for(int i=0; i<size; i++)
		    if(elements[i].equals(item)){
            remove(i);
            return true;
        }
      return false;
    }

    // Removing the item at index from the list
    /***
	 * Method to remove an element depending on index
	 * @param index for which object you want removed in the array
	 * @return boolean which will show if it was removed successfully or not
	 */
    public E remove(int index) {
      checkIndex(index);
      E item = elements[index];
      for(int i=index; i<size-1; i++)
			  elements[i] = elements[i+1];
      size--;
      return item;
    }
  /***
	 * Method to change the size of the array
	 * @param no parameters
	 * no return value
	 */
    // Shrink the list to size
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
	 * Method to change the size of the array to make sure the array is big enough to hold all elements
	 * @param no parameters
	 * @return an integer telling you the size of the array
	 */
    private int ensureCapacity() {
        int iterations = 0;
	    if(size >= elements.length) {
          int newCap = (int) (elements.length * 1.5);
		      E[] newElements = (E[]) new Object[newCap];
		      for(int i=0; i<size; i++){
                    iterations++;
				    newElements[i] = elements[i];
              }
		      elements = newElements;
	    }
        return iterations;
    }
    // Check if the index is valid
    /***
	 * Method to check if the index is within the size of the array
	 * @param index for the index number to be checked
	 * no return value
	 */
    private void checkIndex(int index){
		  if(index < 0 || index >= size)
			    throw new ArrayIndexOutOfBoundsException(
              "Index out of bounds. Must be between 0 and "+(size-1));
    }
    // toString() method
    /***
	 * Method to get the ArrayList's information
	 * @param no parameters
	 * @return formatted string containing the value of the array
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
	 * Method to make an iterator
	 * @param no parameters
	 * @return a new array iterator for the list
	 */
    public Iterator<E> iterator(){
		  return new ArrayIterator();
    }
    // Inner class that implements Iterator<E>
    /***
	 * Class for ArrayIterator using an iterator object
	 */
    private class ArrayIterator implements Iterator<E>{
	    private int current = -1;

	    public boolean hasNext() {
        return current < size-1;
      }

	    public E next() {
        return elements[++current];
      }
    }    
    //linear search
    /***
	 * Method to check if an object is in the array 
	 * @param o for the object to be searched for
	 * @return boolean which will show if it is in the array or not 
	 */
    public boolean contains(Object o){ 
        // worst case: going through every single element o the list (o(n))
        containsIter = 0;
        Iterator<E> iter = iterator();
        while(iter.hasNext()){
            containsIter++; // count iterations
            E obj = iter.next();
            if(obj.equals(o)){
                return true;
            }
        }
        return false;
    }
    //sort method that uses the natural ordering
    /***
	 * Method to sort the array 
	 * @param no parameters
	 * no return value
	 */
    //O(n^2)
    public void sort(){ //all generic
      sortIter = 0;
        for(int i=0; i < size; i++){
            int minIndex = i;
            sortIter++;
            for(int j = i; j < size; j++){
                sortIter++;
                //cast comparable to use compareTo
                Comparable<E> min = (Comparable<E>) elements[j];
                //cast one we are invoking compareTo on
                if(min.compareTo(elements[minIndex])> 0){
                    minIndex = j;
                }
            }
            E temp = elements[i];
            elements[i] = elements[minIndex];
            elements[minIndex] = temp;
        }
    }
    /***
	 * Method to sort the array using a comparator
	 * @param c for the comparator to be used
	 * no return value
	 */
    //(o(n^2))
    public void sort(Comparator<E> c){
        comparatorSortIter = 0;
        for(int i=0; i < size; i++){
          comparatorSortIter++;
            int minIndex = i;
            for(int j = i; j < size; j++){
                //cast comparable to use compareTo
                comparatorSortIter++;
                Comparable<E> min = (Comparable<E>) elements[minIndex];
                //cast one we are invoking compareTo on
                if(c.compare(elements[j], elements[minIndex])< 0){
                    minIndex = j;
                }
            }
            E temp = elements[i];
            elements[i] = elements[minIndex];
            elements[minIndex] = temp;
        }
    }

}