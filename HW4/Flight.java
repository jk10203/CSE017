/***
 * Class to create the class Flight
 * @author Joy Kim
 * @version 0.1
 * Date of creation: March, 2023
 * Last Date Modified: March 28, 2023
 *
 * This program was made in order to make the class for flight which includes the flight number and 
 * the times for departure and arrival. 
 */

public class Flight{
    private String flight;
    private Time departure;
    private Time arrival;
    /***
	 * Constructor with 3 parameters
	 * @param f for the flight name
     * @param d for the departure time
     * @param a for the arrival time
	 */
    public Flight(String f, Time d, Time a){
        flight = f;
        departure = d;
        arrival = a;
    }
    /***
	 * Getter for the flight
	 * @param	no parameters
	 * @return	the value of the data member flight
	 */
    public String getFlight(){
        return flight;
    }
    /***
	 * Getter for the departure time
	 * @param	no parameters
	 * @return	the value of the data member departure
	 */
    public Time getDeparture(){
        return departure;
    }
    /***
	 * Getter for the arrival time
	 * @param	no parameters
	 * @return	the value of the data member arrival
	 */
    public Time getArrival(){
        return arrival;
    }
    /***
	 * Setter for the flight
	 * @param	flight to set the data member flight
	 * no return value
	 */
    public void setFlight(String flight){
        this.flight = flight;
    }
    /***
	 * Setter for the departure time
	 * @param	depart to set the data member depart
	 * no return value
	 */
    public void setDeparture (Time depart){
        departure = depart;
    }
    /***
	 * Setter for the arrival time
	 * @param	arr to set the data member arrival
	 * no return value
	 */
    public void setArrival(Time arr){
        arrival = arr;
    }
    /***
	 * Method to get the flight information
	 * @param no parameters
	 * @return formatted string containing the value of the data members
	 */
    public String toString(){
        return String.format("%-10s\t%-10s\t%-10s\t", flight, departure.toString(), arrival.toString());
    }
}