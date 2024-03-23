/***
 * Class to create the Organizer class
 * @author Joy Kim
 * @version 0.1
 * Date of creation: March, 2023
 * Last Date Modified: March 21, 2023
 *
 * This program was made in order to establish elements of the organizer such as Arraylist and Comparators.
 * It has many functions that can edit an Arraylist which include removing elements, adding elements, finding elements
 * , and setting comparators for sorting.
 */
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Collection;

public class Organizer<E>{
    private ArrayList<E> elements;
    private Comparator<E> comparator;

    /*comparator --> when you want compareTo to compare different elements of an object
    ex. name or email or phone, etc
    especially because the natural ordering of compareTo is for Date so if no comparator is specified
    it will use compareTo for Date*/

    /***
	 * Constructor with one parameters
	 * @param	cap for the capacity of the arraylist
    */
    public Organizer(int cap){ 
        /*The first constructor sets the capacity of the arraylist elements to cap and comparator to null.*/
        elements = new ArrayList<>(cap);
        comparator = null;
    }
    /***
	 * Constructor with two parameters
	 * @param	cap for the capacity of the arraylist
     * @param   comp for the comparator you are using
     */
    public Organizer(int cap, Comparator<E> comp){
        /*The second constructor sets the capacity of the arraylist elements to cap and comparator to comp. */
        elements = new ArrayList<>(cap);
        comparator = comp;
    }
    /***
	 * Method to add an element to the arraylist
	 * @param el or the object to be added
	 * no return value
	 */
     //time complexity: o(n)
    public void addElement(E el){ 
        if (elements.size()==0){
            elements.add(0, el);
        } else {
            boolean boolAdd = false;
            for(int i =0; i< elements.size(); i++){
                if(comparator == null){
                    Comparable c = ((Comparable) (elements.get(i)));
                    if (c.compareTo(el)>0){
                        elements.add(i, el);
                        boolAdd = true;
                        break;
                    }
                } else {
                    if(comparator.compare(elements.get(i), el)>0){
                        elements.add(i, el);
                        boolAdd = true;
                        break;
                    }
                }
            }
            if (!boolAdd){
                elements.add(el);
            }
        }
    }

    /***
	 * Method to find an element in an arraylist
	 * @param el for the element to be searched for
	 * @return the element if it is found
	 */
     //time complexity: o(n)
    public E findElement(E el){
        int first = 0;
        int last = elements.size()-1;
        return findElement(el, first, last);
    }
    /***
	 * Helper method to utilize recursive binary search to find element
	 * @param el for the element to be searched for
     * @param first for the first/starting index
     * @param last for the  last.ending index
	 * @return the method itself if not found vor the element that is found
	 */
     //time complexity: o(n)
    public E findElement(E el, int first, int last){
        if (first> last){
            return null;
        }
        int middle = (last + first) / 2;
        if (comparator == null){
            Comparable c = ((Comparable)(elements.get(middle)));
            if (c.compareTo(el) == 0){
                return elements.get(middle);
            }
            else if (c.compareTo(el) > 0){
                last = middle - 1;
                return findElement(el, first, last);
            } else {
                first = middle + 1;
                return findElement(el, first, last);
            }
        } else {
            if(comparator.compare(elements.get(middle), el) == 0){
                return elements.get(middle); 
            } else if (comparator.compare(elements.get(middle), el) > 0){
                last = middle - 1; 
                return findElement(el, first, last);
            } else{
                first = middle + 1; 
                return findElement(el, first, last);
            }

        }
    }

    /***
	 * Method to remove an element in an arraylist
	 * @param el for the element to be removed
	 * @return the element if it is removed or null if it does not exist
	 */
     //time complexity: o(n)
    public E removeElement(E el){
        for(int i=0; i<elements.size(); i++){
            if(comparator == null){
                Comparable c = ((Comparable)(elements.get(i)));
                if(c.equals(el)){
                    elements.remove(c);
                }
            } else {
                if (comparator.compare(elements.get(i), el) == 0){
                    elements.remove(i);
                    return elements.get(i);
                }
            }
        }
        if (elements.remove(el)){
            return el;
        } else if (elements.remove(el) == false){
            return null;
        }
        return null;
    }
    /***
	 * Method to set the comparator to be used for sorting
	 * @param com for the comparator object
	 * no return value
	 */
     //time complexity: o(nlog(n))
    public void setComparator(Comparator <E> com){
        /*The method setComparator sets the comparator object to its parameter and invokes 
        the sort method on the arraylist elements to sort it using the new comparator object. */
        comparator = com;
        elements.sort(comparator);
    }
    /***
	 * Method to get the Organizer's information
	 * @param no parameters
	 * @return formatted string containing the value of the data members
	 */
     //time complexity: o(n)
    public String toString(){
        String out = "";
        for(E element: elements){
            out += element + "";
        }
        return out;
    }

}
