/***
 * Class to modify comparator
 * @author Joy Kim
 * @version 0.1
 * Date of creation: February 28, 2023
 * Last Date Modified: March 8, 2023
 *
 * This program was made in order to be able to compare two instances of the same type E1. It utilizes compareTo
 * in order to help in sorting. 
 */
import java.util.Comparator;
public class ComparatorByFirst<E1 extends Comparable<E1>, E2> implements Comparator<Pair<E1,E2>>{
    /***
	 * Method to establish the abstract method compare for E1
	 * @param p1 a general type used to compare with another general type
     * @param p2 a general type used to compare with another general type
	 * @return an integer that tells you if p1 came before or after p2
	 */

    public int compare(Pair<E1 ,E2> p1, Pair<E1,E2> p2){ //method compare uses 2 pairs
        E1 p1First = p1.getFirst();
        E1 p2First = p2.getFirst();
        return p1First.compareTo(p2First);
    }
    //if E1 does not have compareTo, it will throw error
}














/*  Comparable<E>{ 
        int compareTo(E obj); takes ONE parameter
        instance method
        this. = obj
        Student S1.compareTo(S2)
        S1 is an instance of a class and has to be defined inside the class
        one criteria / ordering (name, gpa, etc)
    }
    
    Comparator<E>{
        int compare(E obj1, E obj2; takes TWO parameter
        }
        does not have to be an instance of a class and does not have to be 
        inside the class
        multiple criteria
    both compares 2 objects of type E
*/