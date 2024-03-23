/***
 * Generic Class for 2 generic variables
 * @author Joy Kim
 * @version 0.1
 * Date of creation: February 28, 2023
 * Last Date Modified: March 8, 2023
 *
 * This program was made in order to create a generic class for 2 variables which can be of any type.
 * It overrides toString and equals so that the 2 variables can be displayed/compared.
 */
public class Pair<E1, E2>{
    private E1 first; //generic type / can be anything
    private E2 second;
    
    /***
	 * Default constructor
	 * No parameters
     * Intitializes first and second to null
	 */
    public Pair(){
        first = null;
        second = null;
    }
    /***
	 * Constructor with two parameter
	 * @param	f for the first E1 generic type
     * @param	s for the second E2 generic type
     */
    public Pair(E1 f, E2 s){
        first = f;
        second = s;
    }
    /***
	 * Getter for E1 First
	 * @param	no parameters
	 * @return	the value of the data member First
	 */
    public E1 getFirst(){
        return first;
    }
    /***
	 * Getter for E2 Second
	 * @param	no parameters
	 * @return	the value of the data member Second
	 */
    public E2 getSecond(){
        return second;
    }
    /***
	 * Setter for the first of E1 type (generic type)
	 * @param	f to set the data member first
	 * no return value
	 */
    public void setFirst(E1 f){
        first = f;
    }
    /***
	 * Setter for the Second of E2 type (generic type)
	 * @param	s to set the data member second
	 * no return value
	 */
    public void setSecond(E2 s){
        second = s;
    }
    /***
	 * Method to get the Pair's information
	 * @param no parameters
	 * @return formatted string containing the value of the data members first and second
	 */
    public String toString(){
        return "(" + first.toString() + ", " + second.toString() + ")";
        //nothing calls toString unless we implicitly call
        //"(" + first + ", " + second + ")"; --> MEMORY LOCATION
        
    }
    /***
	 * Method to establish a method for .equals
	 * @param no parameters
	 * @return a boolean that will show if the corresponding data types matches
	 */
    public boolean equals(Object o){
        //first check if o is instance of pair THEN downcast object o to pair
        Pair<E1,E2> pair = (Pair) o; //safe
        return this.getFirst().equals(pair.getFirst())
            && this.getSecond().equals(pair.getSecond()); //compares memory so cant do ==
    }
}