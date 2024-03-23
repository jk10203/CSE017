/***
 * Class to create the class Date
 * @author Joy Kim
 * @version 0.1
 * Date of creation: February 6, 2023
 * Last Date Modified: February 13, 2023
 *
 * This program was made in order to make the class for date and its components (month, day, year). 
 * It also includes an override method in order to format said information.
 */
public class Date{
    private int month;
    private int day;
    private int year;
    
    /***
	 * Default constructor
	 * No parameters
	 * Initializes month, day, year to the integer, 0
	 */
    public Date(){
        month = 0;
        day = 0;
        year = 0;
    }
    /***
	 * Constructor with one parameters that throws an InvalidDateTimeException
	 * @param	date for the date the event is on which is split up into month, day, and year
	 */
    public Date(String date) throws InvalidDateTimeException{
        String[] dateItems = date.split("/"); //splitting data (differentiated by "/") into an array
        month = Integer.parseInt(dateItems[0]);//first value before "/"
        day = Integer.parseInt(dateItems[1]); //second
        year = Integer.parseInt(dateItems[2]); //third
        if(month > 12 || month < 1) throw new InvalidDateTimeException("Invalid month (should be 1 to 12)");
        //makes sure month is in range
        if(day > 31 || day < 1) throw new InvalidDateTimeException("Invalid day (should be 1 to 31)");
        //makes sure day is in range
        if(year > 2030 || year < 1970) throw new InvalidDateTimeException("Invalid year (should be 1970 to 2030)");
        //makes sure the years are in range
        
        /*for (int i = 0; i< date.length(); i++){
            if(i == 0 || i == 1){
                String monthS = String.valueOf(date.charAt(0)) + String.valueOf(date.charAt(1));
                month = Integer.parseInt(monthS);
            } else if (i == 3 || i == 4){
                String dayS = String.valueOf(date.charAt(3)) + String.valueOf(date.charAt(4));
                day = Integer.parseInt(dayS);
            } else if (i == 6){
                String yearS = String.valueOf(date.charAt(6)) + String.valueOf(date.charAt(7)) + String.valueOf(date.charAt(8)) + String.valueOf(date.charAt(9));
                year = Integer.parseInt(yearS);
                //ate.charAt(6) + date.charAt(7) + date.charAt(8) + date.charAt(9);
            }      
        }*/
    }
    /***
	 * Getter for the month of the date
	 * @param	no parameters
	 * @return	the value of the data member month
	 */
    public int getMonth(){
        return month;
    }
    /***
	 * Getter for the day of the date
	 * @param	no parameters
	 * @return	the value of the data member day
	 */
    public int getDay(){
        return day;
    }
    /***
	 * Getter for the year of the date
	 * @param	no parameters
	 * @return	the value of the data member year
	 */
    public int getYear(){
        return year;
    }
    /***
	 * Setter for the month of the date
	 * @param	month to set the data member month
	 * no return value
	 */
    public void setMonth(int month)throws InvalidDateTimeException{
        this.month = month;
    }
    /***
	 * Setter for the day of the date
	 * @param	day to set the data member month
	 * no return value
	 */
    public void setDay(int day)throws InvalidDateTimeException{
        this.day = day;
    }
    /***
	 * Setter for the year of the date
	 * @param	year to set the data member month
	 * no return value
	 */
    public void setYear(int year)throws InvalidDateTimeException{
        this.year = year;
    }
     /***
	 * Method to get the date information
	 * @param no parameters
	 * @return formatted string containing the value of the data members
	 */
    @Override
    public String toString(){
        
        return String.format("%02d/%02d/%4d", month, day, year);
    }
}