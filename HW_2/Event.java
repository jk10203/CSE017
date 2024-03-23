/***
 * Class to create the class Event
 * @author Joy Kim
 * @version 0.1
 * Date of creation: February 6, 2023
 * Last Date Modified: February 13, 2023
 *
 * This program was made in order to make the parent class for a series of Events with information that includes
 * description, location, date, time. Date and time is also taken from another class named respectively as such,
 * and utilizes this information to also give conditions for sorting based on this, and getting the result.
 * It also includes an override method in order to format said information.
 */
public class Event{
    private String description;
    private String location;
    Date date;
    Time time;

    /***
	 * Default constructor
	 * No parameters
	 * Initializes description, location, date, time to the string "none"
	 */
    public Event(){
        description = "none";
        location = "none";
    }
    /***
	 * Constructor with four parameters
	 * @param	description for the description of the place the event is taking place
	 * @param	location for the location of the event
	 * @param	dateS for the date the event is happening
	 * @param	timeS for the time the event is happening
	 */
    public Event(String description, String location, String dateS, String timeS) throws InvalidDateTimeException{
        this.description = description;
        this.location = location;
        date = new Date(dateS);
        time = new Time (timeS);
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
    public Date getDate(){
        
        return date;
    }
     /***
	 * Getter for the time the event takes place
	 * @param	no parameters
	 * @return	the value of the data member time
	 */
    public Time getTime(){
        
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
	 * Setter for the date that the event is happening on by utilizing the date class
	 * @param	date to set the data member date
	 * no return value
	 */
    public void setDate(String date2) throws InvalidDateTimeException{
        date = new Date(date2);
    }
    /***
	 * Setter for the time that the event is taking place by utilizing the time class
	 * @param	time to set the data member time
	 * no return value
	 */
    public void setTime(String time2) throws InvalidDateTimeException{
        time = new Time(time2);
    }
    /***
	 * Method to give conditions for sorting date AND time
	 * @param e to compare one event object to another to get an integer value 
	 * @return an integer value indicating its relative order in a sorted list
	 */
    public int compareTo(Event e){
        if(getDate().getYear() == e.getDate().getYear()){
            if(getDate().getMonth() == e.getDate().getMonth()){
                if(getDate().getDay() == e.getDate().getDay()){
                    if(getTime().getHours() == e.getTime().getHours()){
                        return getTime().getMinutes() - e.getTime().getMinutes();
                    } else {
                        return getTime().getHours() - e.getTime().getHours();
                    }
                }
                else {
                    return getDate().getDay() - e.getDate().getDay();
                }
            }
            else{
                return getDate().getMonth() - e.getDate().getMonth();
            }
        }
        else {
            return getDate().getYear() - e.getDate().getYear();
        }
    }
    /***
	 * Method to get the Event information
	 * @param no parameters
	 * @return formatted string containing the value of the data members
	 */
    @Override
    public String toString(){
        //20 and 30 for description and location because of the long length of those variables
        return String.format("%-20s\t%-30s\t%-10s\t%-10s", description, location, date.toString(), time.toString());
    }
}
