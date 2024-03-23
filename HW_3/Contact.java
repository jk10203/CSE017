/***
 * Class to create the Contact class
 * @author Joy Kim
 * @version 0.1
 * Date of creation: March, 2023
 * Last Date Modified: March 21, 2023
 *
 * This program was made in order to establish elements of a Contact such as the name, phone, email. It
 * also defines equals and compareTo to compare the names.
 */

public class Contact implements Comparable<Contact> {
    /*The classes Note and Contact implement the interface Comparable and should have a
definition for the method compareTo that orders objects based on date for Note and name
for Contact. */

    private String name;
    private String phone;
    private String email;

    /***
	 * Constructor with three parameters
	 * @param	name for the name of the contact
     * @param   phone for the phone number for the contact
     * @param   email for the email of the contact
     */
    public Contact(String n, String p, String e){
        name = n;
        phone = p;
        email = e;
    }

    /***
	 * Getter for the name 
	 * no parameters
	 * @return	the value of the data member name
	 */
    public String getName(){
        return name;
    }
    /***
	 * Getter for the phone 
	 * no parameters
	 * @return	the value of the data member phone
	 */
    public String getPhone(){
        return phone;
    }
    /***
	 * Getter for the name 
	 * no parameters
	 * @return	the value of the data member email
	 */
    public String getEmail(){
        return email;
    }
    /***
	 * Setter for the name
	 * @param	name to set the data member name
	 * no return value
	 */
    public void setName(String name){
        this.name = name;
    }
    /***
	 * Setter for the phone number
	 * @param	phone to set the data member phone
	 * no return value
	 */
    public void setPhone(String phone){
        this.phone = phone;
    }
    /***
	 * Setter for the email
	 * @param	email to set the data member email
	 * no return value
	 */
    public void setEmail(String email){
        this.email = email;
    }
    /***
	 * Method to get the contact's information
	 * @param no parameters
	 * @return formatted string containing the value of the data members
	 */
    public String toString(){
        return String.format("%-20s\t%-15s\t%-35s\n", name, phone, email);
    }
     /***
	 * Method to compare the names
	 * @param o for the object
	 * @return a boolean if the name equals another name
	 */
    public boolean equals(Object o){
        //equals of class String
        Contact con = null;
        if (o instanceof Contact){
        con = (Contact) o; //safe
        }
        return  this.getName().equals(con.getName());//this.getFirst().equals(con.getFirst()); //compares memory so cant do ==
        //what to put here
    }
     /***
	 * Method to compare the name of two contacts
	 * @param c for the Contact object
	 * @return a value based on if the first string name is equal, bigger than, 
     * or smaller than the second string name
	 */
    public int compareTo(Contact c){
        //use compareTo of the String class
        return this.getName().compareTo(c.getName());
    }

}
    
