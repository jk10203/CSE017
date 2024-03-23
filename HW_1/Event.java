/***
 * Class to create the class Event
 * @author Joy Kim
 * @version 0.1
 * Date of creation: February 2, 2023
 * Last Date Modified: February 2, 2023
 *
 * This program was made in order to make the parent class for a series of Events with information that includes
 * description, location, date, time. It also includes an override method in order to format said information.
 */
public class Event{
    private String description;
    private String location;
    private String date;
    private String time;

    /***
	 * Default constructor
	 * No parameters
	 * Initializes description, location, date, time to the string "none"
	 */
    public Event(){
        description = "none";
        location = "none";
        date = "none"; 
        time = "none"; 
    }
    /***
	 * Constructor with four parameters
	 * @param	description for the description of the place the event is taking place
	 * @param	location for the location of the event
	 * @param	date for the date the event is happening
	 * @param	time for the time the event is happening
	 */
    public Event(String description, String location, String date, String time){
        this.description = description;
        this.location = location;
        this.date = date;
        this.time = time;
    }
     /***
	 * Getter for the description of the event
	 * @param	no parameters
	 * @return	the value of the data member description
	 */
    public String getDescription(){
        return description;
    }
     /***
	 * Getter for the location name
	 * @param	no parameters
	 * @return	the value of the data member location
	 */
    public String getLocation(){
        return location;
    }
     /***
	 * Getter for the date of the event
	 * @param	no parameters
	 * @return	the value of the data member date
	 */
    public String getDate(){
        return date;
    }
     /***
	 * Getter for the time the event takes place
	 * @param	no parameters
	 * @return	the value of the data member time
	 */
    public String getTime(){
        return time;
    }
    /***
	 * Setter for the description of the event
	 * @param	description to set the data member description
	 * no return value
	 */
    public void setDescription(String description){
        this.description = description;
    }
    /***
	 * Setter for the location of where the event is
	 * @param	location to set the data member location
	 * no return value
	 */
    public void setLocation(String location){
        this.location = location;
    }
    /***
	 * Setter for the date that the event is happening on
	 * @param	date to set the data member date
	 * no return value
	 */
    public void setDate(String date){
        this.date = date;
    }
    /***
	 * Setter for the time that the event is taking place
	 * @param	time to set the data member time
	 * no return value
	 */
    public void setTime(String time){
        this.time = time;
    }
    /***
	 * Method to get the Event information
	 * @param no parameters
	 * @return formatted string containing the value of the data members
	 */
    @Override
    public String toString(){
        String out;
        out = String.format("%-20s\t%-30s\t%-10s\t%-10s", description, location, date, time);
        //20 and 30 for description and location because of the long length of those variables
        return out;
    }
    
}