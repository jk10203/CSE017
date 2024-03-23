/***
 * Class to create the Pair Class
 * @author Joy Kim
 * @version 0.1
 * Date of creation: April, 2023
 * Last Date Modified: April 20, 2023
 *
 * This program was made in order to establish generic elements first and second. It also includes a equals
 * method in order to check if the components first and second are identical to another object's first and second.
 */
 public class Pair<E1,E2>{
    private E1 first;
    private E2 second;

    /***
	 * Default constructor
	 * No parameters
	 * Initializes 2 generic variables to null
	 */
    public Pair(){
        first = null;
        second = null;
    }
    /***
	 * Constructor with two parameters
	 * @param f for the first generic variable
     * @param s for the second generic variable
	 * Initializes 2 generic variables f and s 
	 */
    Pair(E1 f, E2 s){
        first = f;
        second = s;
    }
    /***
	 * Getter for the first generic variable
	 * @param	no parameters
	 * @return	the value of the data member first
	 */
    public E1 getFirst(){
        return first;
    }
    /***
	 * Getter for the second generic variable
	 * @param	no parameters
	 * @return	the value of the data member second
	 */
    public E2 getSecond(){
        return second;
    }
    /***
	 * Setter for the first generic variable
	 * @param	f to set the data member first
	 * no return value
	 */
    public void setFirst(E1 f){
        first = f;
    }
    /***
	 * Setter for the second generic variable
	 * @param	s to set the data member second
	 * no return value
	 */
    public void setSecond(E2 s){
        second = s;
    }
    /***
	 * Method to get the Pair information
	 * @param no parameters
	 * @return formatted string containing the value of the data members
	 */
    public String toString(){
        return "(" + first.toString() + ", " + second.toString() + ")";
    }
    /***
	 * Method to compare the object's first and second variables
	 * @param o for the object
	 * @return a boolean if the first and second equals another first and second
	 */
    public boolean equals(Object o){
        // downcast o to Pair
        if(o instanceof Pair){//safe downcasting
            Pair<E1,E2> pair = (Pair) o;// downcasting
            return this.getFirst().equals(pair.getFirst()) && // equals for type E1
                   this.getSecond().equals(pair.getSecond()); // equals() for type E2
        }
        return false;
    }
}