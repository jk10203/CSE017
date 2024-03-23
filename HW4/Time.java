/***
 * Class to create the class Time
 * @author Joy Kim
 * @version 0.1
 * Date of creation: March, 2023
 * Last Date Modified: March 28, 2023
 *
 * This program was made in order to make the class for time and its components (hours and minutes). 
 * It also includes an override method in order to format said information.
 */

public class Time implements Comparable<Time>{
    
    private int hours;
    private int minutes;

    /***
	 * Default constructor
	 * No parameters
	 * Initializes hours and minutes to the integer, 0
	 */
    public Time(){ 
        hours = 0;
        minutes = 0;
    }
    /***
	 * Constructor with one parameters that throws an InvalidDateTimeException
	 * @param	time for the time the event is on which is split up into hours and minutes
	 */
    public Time(String time) throws InvalidDateTimeException{
        //if(!date.matches("\\d{2}/\\d{2}/\\d{4}")) throw new InvalidDateTimeException("Invalid date (should be mm/dd/yyyy)");
        if (time.matches("\\d{2}:\\d{2}") == true){ //if the formatting matches...
            for (int i = 0; i< time.length(); i++){
                if(i == 0 || i == 1){ //now can use charAt to differentiate hours
                    String hoursStr = String.valueOf(time.charAt(0)) + String.valueOf(time.charAt(1));
                    hours = Integer.parseInt(hoursStr);
                } else if (i == 3 || i == 4){ //now can use charAt to differentiate minutes
                    String minutesStr = String.valueOf(time.charAt(3)) + String.valueOf(time.charAt(4));
                    minutes = Integer.parseInt(minutesStr);
                }
            }
        } else if (!time.matches("\\d{2}:\\d{2}")) throw new InvalidDateTimeException("Invalid time (should be HH:MM)");
    }
    /***
	 * Getter for the hours of the time
	 * @param	no parameters
	 * @return	the value of the data member hours
	 */
    public int getHours(){
        return hours;
    }
    /***
	 * Getter for the minutes of the time
	 * @param	no parameters
	 * @return	the value of the data member minutes
	 */
    public int getMinutes(){
        return minutes;
    }
    /***
	 * Setter for the hours of the time
	 * @param	hours to set the data member hours
	 * no return value
	 */
    public void setHours(int hours)throws InvalidDateTimeException{
        this.hours = hours;
    }
    /***
	 * Setter for the minutes of the time
	 * @param	minutes to set the data member minutes
	 * no return value
	 */
    public void setMinutes(int minutes)throws InvalidDateTimeException{
        this.minutes = minutes;
    }
    /***
	 * Method to get the difference between two times
	 * @param no parameters
	 * @return formatted string containing the value of the data members
	 */
    public int diff(Time t){
        /* The method diff returns the difference between this and t in minutes.*/
        return ((hours*60) + minutes)- (t.hours*60 + t.minutes);
    }
    /***
	 * Method to increase the time by one minute
	 * @param no parameters
	 * no return value
	 */
    public void tick(){
        /*increments the minutes by one and adjusts the time accordingly (if the minutes reach 60, 
        hours are incremented and minutes reset to 0, and if hours reach 24, hours are reset to 0)*/ 
        minutes++;
        if(minutes == 60){
            minutes = 0;
            hours++;
            if (hours == 24){
                hours = 0;
            }
        } 
    }
    /***
	 * Method to compare two times
	 * @param t for the Time to compare
	 * @return the value of the difference in time
	 */
    public int compareTo(Time t){
        if(getHours() == t.getHours()){
            return getMinutes() - t.getMinutes();
        } else {
            return getHours() - t.getHours();
        }
    }
    /***
	 * Method to get the time information
	 * @param no parameters
	 * @return formatted string containing the value of the data members
	 */
    @Override
    public String toString(){
        return String.format("%02d:%02d", hours, minutes);
    }
}
