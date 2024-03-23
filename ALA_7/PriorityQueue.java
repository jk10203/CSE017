/***
 * Class to process Priority Queue
 * @author Joy Kim
 * @version 0.1
 * Date of creation: March, 2023
 * Last Date Modified: March 30, 2023
 *
 * This program was made in order to establish properties of PriorityQueue for generics.
 */
import java.util.Comparator;
public class PriorityQueue<E> {
   private ArrayList<E> list;
   private Comparator<E> comparator;

    /***
	 * Default constructor
	 * No parameters
	 * Initializes list to arraylist and comparator to null
	 */
   public PriorityQueue() { // using compareTo for the priority O(1)
     list = new ArrayList<>();
     comparator = null;
   }
   /***
	 * Constructor with 1 parameters
	 * @param c for the comparator
	 * Initializes list arraylist and comparator to c
	 */
   //O(1)
   public PriorityQueue(Comparator<E> c) { // using compare on the object comparator
      list = new ArrayList<>();
      comparator = c;
   }
   /***
	 * Method to get an element then remove it
	 * @param no parameters
	 * @return E object which is the value that was removed
	 */
   public E poll() { //O(n)
		E value = list.get(0);
		list.remove(0);
        return value;
   }
   /***
	 * Method to add an object item to an arraylist
	 * @param item for the object to be added to if it is possible
	 * no return value
	 */
   public void offer(E item) { //O(n)
    int i, c;
    for(i=0; i<list.size(); i++){
        if(comparator == null)
            c = ((Comparable<E>)item).compareTo(list.get(i));
        else
	        c = comparator.compare(item, list.get(i));
	    if(c < 0)
            break;
    }
    list.add(i, item);
   }
   /***
	 * Method to see what is at the beginning of the arraylist
	 * @param no parameters
	 * @return E object which is the object that is at the beginning of the arraylist
	 */
   public E peek() { //O(1)
      return list.get(0);
   }
   /***
	 * Method to get the PriorityQueue's information
	 * @param no parameters
	 * @return formatted string containing the value of the data members
	 */
   public String toString() { //O(n)
		return "Priority Queue: " + list.toString();
   }
    /***
	 * Method to get the Printqueue's size
	 * @param no parameters
	 * @return integer value of the PrintQueue Arraylist's size
	 */
   public int size() { //O(1)
    return list.size();
   }
   /***
	 * Method to remove all elements in the list
	 * @param no parameters
	 * no return value
	 */
   public void clear() { //O(1)
    list.clear();
   }
   /***
	 * Method to get a boolean of whether an arraylist is empty or not
	 * @param no parameters
	 * @return boolean which will show if it is empty or not
	 */
   public boolean isEmpty() { //O(1)
    return list.isEmpty();
   }
   /***
	 * Method to check if an object is in the arraylist elements
	 * @param o for the object to be searched for
	 * @return boolean which will show if it is in the arraylist or not 
	 */
    //can you invoke contains on arraylist? 
    //--> dont have to write contains again for the stack
    //o(n)
    public boolean contains(Object o){
        //can make binary search for more efficiency (o(logn)) bc alr sorted
        return list.contains(o); //linear search
        //priority queue is always sorted
    }
    /***
	 * Method to sort the arraylist list
	 * @param no parameters
	 * no return value
	 */
    //o(n^2)
    //(size)(size+1) / 2
    public void sort(){
        list.sort();
    }
    /***
	 * Method to sort the arraylist list using the comparator
	 * @param c for the comparator to be used
	 * no return value
	 */
    //O(n^2)
    public void sort(Comparator<E> c){
        list.sort(c);
        comparator = c;
    }
}