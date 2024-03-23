/***
 * Class to modify comparator
 * @author Joy Kim
 * @version 0.1
 * Date of creation: February 28, 2023
 * Last Date Modified: March 8, 2023
 *
 * This program was made in order to be able to compare two instances of the same type E2. It utilizes compareTo
 * in order to help in sorting. 
 */
import java.util.Comparator;
public class ComparatorBySecond<E1,E2 extends Comparable<E2>> implements Comparator<Pair<E1,E2>>{
    /***
	 * Method to establish the abstract method compare for E2
	 * @param p1 a general type used to compare with another general type
     * @param p2 a general type used to compare with another general type
	 * @return an integer that tells you if p1 came before or after p2
	 */
    public int compare(Pair<E1,E2> p1, Pair <E1,E2> p2){
        E2 p1Second = p1.getSecond();
        E2 p2Second = p2.getSecond();
        return p1Second.compareTo(p2Second);
    }
}