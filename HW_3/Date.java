/***
 * Class to create the class Date
 * @author Joy Kim
 * @version 0.1
 * Date of creation: March, 2023
 * Last Date Modified: March 21, 2023
 *
 * This program was made in order to make the class for date and its components (month, day, year). 
 * It also includes an override method in order to format said information.
 */
public class Date implements Comparable<Date>{
    private int month;
    private int day;
    private int year;
    
    /***
	 * Default constructor
	 * No parameters
	 * Initializes month, day, year to the integers 1, 1, and 1970 respectively
	 */
    public Date(){
        month = 1;
        day = 1;
        year = 1970;
    }

    /***
	 * Constructor with one parameters that throws an InvalidDateTimeException
	 * @param	date for the date the event is on which is split up into month, day, and year
	 */
    public Date(String date) throws InvalidDateTimeException {
        if(date.matches("\\d{2}/\\d{2}/\\d{4}")){
            String[] items = date.split("/");
            month = Integer.parseInt(items[0]);
            if(month < 1 || month > 12)
                throw new InvalidDateTimeException("Invalid month. Month should be from 1 to 12.");
            day = Integer.parseInt(items[1]);
            if(day < 1 || day > 31)
                throw new InvalidDateTimeException("Invalid day. Day should be from 1 to 31.");
            year = Integer.parseInt(items[2]);
            if(year < 1970 || year > 2030)
                throw new InvalidDateTimeException("Invalid year. Month should be from 1970 to 2030.");
        }
        else
          throw new InvalidDateTimeException("Invalid Date Format (mm/dd/yyyy)");
    }
    /***
	 * Getter for the month of the date
	 * @param	no parameters
	 * @return	the value of the data member month
	 */
    public int getMonth() { return month;}
    /***
	 * Getter for the day of the date
	 * @param	no parameters
	 * @return	the value of the data member day
	 */
    public int getDay() { return day;}
    /***
	 * Getter for the year of the date
	 * @param	no parameters
	 * @return	the value of the data member year
	 */
    public int getYear() { return year;}
    /***
	 * Setter for the month of the date
	 * @param	m to set the data member month
	 * no return value
	 */
    public void setMonth(int m) throws InvalidDateTimeException{
        if(m < 1 || m > 12)
            throw new InvalidDateTimeException("Invalid month. Month should be from 1 to 12.");
        month = m;
    }
    /***
	 * Setter for the day of the date
	 * @param	d to set the data member month
	 * no return value
	 */
    public void setDay(int d) throws InvalidDateTimeException{
        if(d < 1 || d > 31)
           throw new InvalidDateTimeException("Invalid day. Day should be from 1 to 31.");
        day = d;
    }
    /***
	 * Setter for the year of the date
	 * @param	y to set the data member month
	 * no return value
	 */
    public void setYear(int y) throws InvalidDateTimeException{
        if(y < 1970 || y > 2030)
                throw new InvalidDateTimeException("Invalid year. Year should be from 1970 to 2030.");
        year = y;
    }
    /***
	 * Method to get the date information
	 * @param no parameters
	 * @return formatted string containing the value of the data members
	 */
    public String toString(){
        return String.format("%02d/%02d/%04d", month, day, year);
    }
    /***
	 * Method to give conditions for sorting date
	 * @param date to compare one date object to another to get an integer value 
	 * @return an integer value indicating its relative order in a sorted list
	 */
    public int compareTo(Date date){
        if(this.getYear() == date.getYear()){
            if(this.getMonth() == date.getMonth()){
                return this.getDay() - date.getDay();
            }
            return this.getMonth() - date.getMonth();
        }
        return this.getYear() - date.getYear();
    }
}

