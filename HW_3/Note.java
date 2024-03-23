/***
 * Class to create the Note class
 * @author Joy Kim
 * @version 0.1
 * Date of creation: March, 2023
 * Last Date Modified: March 21, 2023
 *
 * This program was made in order to establish elements of a note such as the date, title, description. It
 * also defines equals and compareTo to compare the dates.
 */

public class Note implements Comparable<Note>{ //is this righht
    /*The classes Note and Contact implement the interface Comparable and should have a
definition for the method compareTo that orders objects based on date for Note and name
for Contact. */
    private Date date;
    private String title;
    private String description;
    /***
	 * Default constructor
	 * No parameters
     * Intitializes date to null and title and description to "none"
	 */
    public Note(){
        date = null;
        title = "none";
        description = "none";
    }
    /***
	 * Constructor with three parameters
	 * @param	date for the date of the note
     * @param   t for the title of the note
     * @param   descr for the description of the note
     */
    public Note(Date date, String t, String descr){
        this.date = date;
        title = t;
        description = descr;
    }
    /***
	 * Getter for the date 
	 * no parameters
	 * @return	the value of the data member date
	 */
    public Date getDate(){
        return date;
    }
    /***
	 * Getter for the title
	 * no parameters
	 * @return	the value of the data member title
	 */
    public String getTitle(){
        return title;
    }
    /***
	 * Getter for the description
	 * no parameters
	 * @return	the value of the data member description
	 */
    public String getDescription(){
        return description;
    }
    /***
	 * Setter for the date
	 * @param	date to set the date member date
	 * no return value
	 */
    public void setDate(Date date){
        this.date = date;
    }
    /***
	 * Setter for the title
	 * @param	title to set the date member title
	 * no return value
	 */
    public void setTitle(String title){
        this.title = title;
    }
    /***
	 * Setter for the description
	 * @param	description to set the date member description
	 * no return value
	 */
    public void setDesctiption(String description){
        this.description = description;
    }
    /***
	 * Method to get the note's information
	 * @param no parameters
	 * @return formatted string containing the value of the data members
	 */
    public String toString(){
        return String.format("%-15s\t%-20s\t%-30s\n", date.toString(), title, description);
    }
    /***
	 * Method to compare the date
	 * @param o for the object
	 * @return a boolean if the date equals another date
	 */
    public boolean equals(Object o){
        Note not = (Note) o;
        return this.getDate().equals(not.getDate());
    }
    /***
	 * Method to compare the date
	 * @param n for the note object
	 * @return a value based on if the first date is equal, bigger than, 
     * or smaller than the second date
	 */
    public int compareTo(Note n){ //same as date compareto?
        int compare;
        compare = this.getDate().compareTo(n.getDate());
        return compare;
    }

}
    