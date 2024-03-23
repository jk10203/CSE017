/***
 * Class to modify comparator
 * @author Joy Kim
 * @version 0.1
 * Date of creation: March, 2023
 * Last Date Modified: March 21, 2023
 *
 * This program was made in order to be able to compare two instances of Note. It utilizes compareTo
 * in order to help in sorting the titles. 
 */
import java.util.Comparator;
public class ComparatorByTitle implements Comparator<Note>{
/*The class ComparatorByTitle implements the interface Comparator for type Note and
defines the method compare() to order two Note objects using their titles.
*/
    /***
	 * Method to establish the abstract method compare for Note element, title
	 * @param n1 a note's title used to compare with another note's title
     * @param n2 a note's title used to compare with another note's title
	 * @return an integer that tells you if n1's title came before or after n2's title
	 */
    public int compare(Note n1, Note n2){ 
        return (n1.getTitle()).compareTo(n2.getTitle());
    } 
 
}
