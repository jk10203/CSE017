/***
 * Class to create/extend exception InvalidDateTimeException
 * @author Joy Kim
 * @version 0.1
 * Date of creation: March, 2023
 * Last Date Modified: March 21, 2023
 *
 * This program was made in order to make the extend the Exception class by adding an InvalidDateTimeException.
 */
public class InvalidDateTimeException extends Exception{
    /***
	 * Default constructor
	 * No parameters
	 * Utilizes super to set message specific to date and time errors
	 */
    public InvalidDateTimeException(){
        super("Invalid Date Format.");
    }
    /***
	 * Constructor with one parameters 
	 * @param	message for the message that will show specific to date and time errors utilizing super
	 */
    public InvalidDateTimeException(String message){
        super(message);
    }
}
