/***
 * Class to create the class Airplane
 * @author Joy Kim
 * @version 0.1
 * Date of creation: January 31, 2023
 * Last Date Modified: February 9, 2023
 *
 * This program was made in order to add an exception to an exception class with a personalized message.
 * This is able to help with pinpointing errors and making it clear what caused an error.
 */
public class InvalidSeatException extends Exception{
    /***
	 * Default constructor
	 * No parameters
     * Utilizes super constructor to initialize the exception message to a string
	 */
    public InvalidSeatException(){
        //add (forgot hehe)
        super("Invalid Seat Number");
    }
     /***
	 * Constructor with one parameter
	 * @param	message for an inputted message to be used instead of the no arg string message
	 */
    public InvalidSeatException(String message){
        super(message);
    }

}