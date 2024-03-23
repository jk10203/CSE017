/***
 * Class to create the class Airplane
 * @author Joy Kim
 * @version 0.1
 * Date of creation: January 31, 2023
 * Last Date Modified: February 9, 2023
 *
 * This program was made in order to simulate a airplane reservation system. It includes menu choices that are able to 
 * free or reserve a seat depending on availability.
 */
import java.util.Scanner;
import java.util.InputMismatchException;
public class SeatReservation{
    public static void main(String[]args){
        Scanner input = new Scanner(System.in);
        Airplane myAirplane = new Airplane("seatsmap.txt"); 
        //read from file and initializes seatmap if file exists
        int operation = 0;
        String seatNumber = " ";
        do{
            System.out.println(myAirplane); //don't need toString -- invoked IMPLICITLY
            System.out.println("Select an operation: ");
            System.out.println("1: Reserve a seat");
            System.out.println("2: Free a seat");
            System.out.println("3: Quit");
            try{
                operation = input.nextInt();
                switch(operation){
                    case 1:
                    //reserve
                        System.out.println("Enter a seat number: ");
                        seatNumber = input.next();
                        if(myAirplane.reserveSeat(seatNumber)){
                            System.out.println(seatNumber + " successfully reserved.");
                        } else { //already reserved
                            System.out.println(seatNumber + " already reserved.");
                        }
                        break;
                    case 2: 
                    //free
                        System.out.println("Enter a seat number: ");
                        seatNumber = input.next();
                        if(myAirplane.freeSeat(seatNumber)){
                            System.out.println(seatNumber + " successfully freed.");
                        } else { //already free
                            System.out.println(seatNumber + " already free.");
                        }
                        break;
                    case 3:
                    //exit
                        myAirplane.saveMap("seatsmap.txt");
                        System.out.println("Quitting...");
                        break;

                    default:
                        System.out.println("Invalid Operation (1 to 3)");
                }

            }
            catch(InvalidSeatException e){
                System.out.println(e.getMessage());
            }
            catch(InputMismatchException e){
                System.out.println("Invalid input operation");
                input.next(); //GARBAGE (discarding)
            }


        } while(operation !=3); 
    }
}