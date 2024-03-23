/***
 * Implementing String comparator
 * @author Joy Kim
 * @version 0.1
 * Date of creation: March, 2023
 * Last Date Modified: March 30, 2023
 *
 * This program was made in order to utilize comparators to make a compare method based on String length
 */
import java.util.Comparator;
public class StringComparator implements Comparator<String>{
    
    /***
	 * Method to establish the abstract method compare for strings
	 * @param s1 a string to compare with another string
     * @param s2 another string to compare with the first string
	 * @return an integer that tells you if string1 is longer or shorter than string2
	 */
    public int compare(String s1, String s2){
        return s1.length() - s2.length();
        //first longer than second --> pos
    }

}