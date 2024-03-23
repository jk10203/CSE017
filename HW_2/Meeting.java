/***
 * Class to classify an Event as a Meeting
 * @author Joy Kim
 * @version 0.1
 * Date of creation: February 6, 2023
 * Last Date Modified: February 13, 2023
 *
 * This program was made in order to create a subclass Meeting for the parent class Event. It specifies the host and guests which 
 * add to the super class's elements. It also includes an override method in order to format said information.
 */
public class Meeting extends Event{
    private String host;
    private int guests;

    /***
	 * Default constructor
	 * No parameters
     * Utilizes super constructor to initialize description, location, date, time to the string "none"
	 * Initializes host to the string "none" and guests to the integer 0
	 */
    public Meeting(){
        super();
        host = "none";
        guests = 0;
    }
    /***
	 * Constructor with six parameters
	 * @param	description for the description of the place the event is taking place
	 * @param	location for the location of the event
	 * @param	date for the date the event is happening
	 * @param	time for the time the event is happening
     * @param   host for the person that is holding the event
     * @param   guests for the amount of people showing up to the event
	 */
    public Meeting(String description, String location, String date, String time, String host, int guests) throws InvalidDateTimeException{
        super(description, location, date, time);
        this.host = host;
        this.guests = guests;
    }
     /***
	 * Getter for the host of the event
	 * @param	no parameters
	 * @return	the value of the data member host
	 */
     public String getHost(){
        return host;
    }
     /***
	 * Getter for the amount of guests
	 * @param	no parameters
	 * @return	the value of the data member guests
	 */
     public int getGuests(){
        return guests;
    }
    /***
	 * Setter for the host that is holding the event
	 * @param	host to set the data member host
	 * no return value
	 */
    public void setHost(String host){
        this.host = host;
    }
    /***
	 * Setter for the amount of guests
	 * @param	guests to set the data member guests
	 * no return value
	 */
    public void setGuests(int guests){
        this.guests = guests;
    }
    /***
	 * Method to get the Meeting information
	 * @param no parameters
	 * @return formatted string containing the value of the data members
	 */
    @Override
    public String toString(){
        String out; //using "out" in order to make it easier to see what is happening
        out = String.format("%-10s\t%s\t%-20s\t%-10s", "Meeting", super.toString(), host, guests); //using super to use Event's toString
        return out;
    }
}