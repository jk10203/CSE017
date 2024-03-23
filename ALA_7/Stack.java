/***
 * Class to process Stack<E>
 * @author Joy Kim
 * @version 0.1
 * Date of creation: March, 2023
 * Last Date Modified: March 30, 2023
 *
 * This program was made in order to establish properties of Stack class for generics.
 */
import java.util.Comparator;
public class Stack<E>{
    private ArrayList<E> elements;
    /***
	 * Default constructor
	 * No parameters
	 * Initializes elements arraylist
	 */
    public Stack(){
        elements = new ArrayList<>(); // creates an empty stack with capacity 10
    }
    /***
	 * Method to add an object to an arraylist
	 * @param no parameters
	 * no return value
	 */
    public void push(E item){
        elements.add(item);// add at the end of elements (top of the stack)
    }
    /***
	 * Method to see what is at the end of the arraylist
	 * @param no parameters
	 * @return E object which is the object that is at the end of the arraylist
	 */
    public E peek(){
        int lastIndex = elements.size()-1; // get the index of the top
        return elements.get(lastIndex); // return the value at the top
    }
    /***
	 * Method to get an element then remove it
	 * @param no parameters
	 * @return E object which is the value that was removed
	 */
    public E pop(){
        int lastIndex = elements.size()-1; // get the index of the top
        E value =  elements.get(lastIndex);// get the value of the top
        elements.remove(lastIndex); // remove the top
        return value;
    }
    /***
	 * Method to get a boolean of whether an arraylist is empty or not
	 * @param no parameters
	 * @return boolean which will show if it is empty or not
	 */
    public boolean isEmpty(){ //o(1)
        return elements.isEmpty();
    }
    /***
	 * Method to get the Stack's size
	 * @param no parameters
	 * @return integer value of the Stack's size
	 */
    public int size(){ //o(1)
        return elements.size();
    }
    /***
	 * Method to get the Stack's information
	 * @param no parameters
	 * @return formatted string containing the value of the data members
	 */
     //o(1)
    public String toString(){
        return "Stack: " + elements.toString();
    }
     /***
	 * Method to check if an object is in the arraylist elements
	 * @param o for the object to be searched for
	 * @return boolean which will show if it is in the arraylist or not 
	 */
    //o(n)
    public boolean contains(Object o){
        return elements.contains(o);
    }
    /***
	 * Method to sort the arraylist elements
	 * @param no parameters
	 * no return value
	 */
    //o(n^2)
    public void sort(){
        elements.sort();
    }
    /***
	 * Method to sort the arraylist elements using the comparator
	 * @param c for the comparator to be used
	 * no return value
	 */
    //o(n^2)
    public void sort(Comparator<E> c){
        elements.sort(c);
    }
}