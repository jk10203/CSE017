/***
 * Class to classify an Event as an Appointment
 * @author Joy Kim
 * @version 0.1
 * Date of creation: February 2, 2023
 * Last Date Modified: February 2, 2023
 *
 * This program was made in order to add to the parent Class Event by creating the subclass appointment that also
 * specifies the contact of that event which add to the super class's elements. It also includes an override method 
 * in order to format said information.
 */
public class Appointment extends Event{
    private String contact;
    
    /***
	 * Default constructor
	 * No parameters
     * Utilizes super constructor to initialize description, location, date, time to the string "none"
	 * Initializes contact to the string "none"
	 */
    public Appointment(){
        super();
        contact = "none";
    }
    /***
	 * Constructor with five parameters
	 * @param	description for the description of the place the event is taking place
	 * @param	location for the location of the event
	 * @param	date for the date the event is happening
	 * @param	time for the time the event is happening
     * @param   contact for the person to contact about the event
	 */
    public Appointment(String description, String location, String date, String time, String contact){
        super(description, location, date, time);
        this.contact = contact;
    }
     /***
	 * Getter for the contact that the event is happening by
	 * @param	no parameters
	 * @return	the value of the data member contact
	 */
    public String getContact(){
        return contact;
    }
    /***
	 * Setter for the contact that the event is happening by
	 * @param	contact to set the data member contact
	 * no return value
	 */
    public void setDescription(String contact){
        this.contact = contact;
    }
    /***
	 * Method to get the Appointment information
	 * @param no parameters
	 * @return formatted string containing the value of the data members
	 */
    @Override
    public String toString(){
        String out; //using "out" in order to make it easier to see what is happening
        out = String.format("%-10s\t%s\t%-10s", "Appointment", super.toString(), contact); //using super to use Event's toString
        return out;
    }
}