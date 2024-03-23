/***
 * Class to modify comparator
 * @author Joy Kim
 * @version 0.1
 * Date of creation: March, 2023
 * Last Date Modified: March 21, 2023
 *
 * This program was made in order to be able to compare two instances of Contact. It utilizes compareTo
 * in order to help in sorting the emails. 
 */

import java.util.Comparator;
public class ComparatorByEmail implements Comparator<Contact>{
    /***
	 * Method to establish the abstract method compare for Contact element, emails
	 * @param c1 a contact's emails used to compare with another contact's ema
     * @param c2 a contact type used to compare with another contact type
	 * @return an integer that tells you if c1's email came before or after c2's email
	 */
    public int compare(Contact c1, Contact c2){ 
    
        return (c1.getEmail()).compareTo(c2.getEmail());
    } 
}

