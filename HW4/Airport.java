/***
 * Class to create the class Airport
 * @author Joy Kim
 * @version 0.1
 * Date of creation: March , 2023
 * Last Date Modified: March 28, 2023
 *
 * This program was made in order to make the class for Airport. Airport is a testclass to
 * test Flight and Time by running a simulation of passing time (8 hours) and manipulating the times
 * for the flight related elements.
 */

import java.util.NoSuchElementException;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Airport{
    /***
	 * Method to read through a given textfile and insert into arraylist
	 * @param list for the arraylist to have info inputted into
     * @param filename for the file to be read
	 * no return value
	 */
    public static void readFlights(ArrayList<Flight> list, String filename){
        File file = new File(filename);
        try{
            Scanner read = new Scanner (file);
            while(read.hasNextLine()){
                String flightS = read.next();
				String departure = read.next();
                Time depart = new Time(departure);
				String arrival = read.next();
                Time arrive = new Time(arrival);
                Flight flight = new Flight(flightS, depart, arrive); //create note object
                list.add(flight);
            }
            read.close();

        }
        catch(FileNotFoundException e){
                System.out.println("File not found.");

        } 
        catch (InvalidDateTimeException ex){
            System.out.println("Invalid Time");
        }
	}
    /***
	 * Method to find the flight whose arrival time matches the given time
	 * @param list for the arraylist to be searched
     * @param time for the given time to be searched in the array for
	 * @return the index that contains the flight we are looking for
	 */
    public static int findLanding(ArrayList<Flight> list, Time time){
        int counter = 0;
        for (int i =0; i< list.size(); i++){
            if (time.compareTo(list.get(i).getArrival()) == 0){
                return i;
            } 
        }
        return -1;
    }
    /***
	 * Method to find the flight whose departure time matches the given time
	 * @param list for the arraylist to be searched
     * @param time for the given time to be searched in the array for
	 * @return the index that contains the flight we are looking for
	 */
    public static int findTakeoff(ArrayList<Flight> list, Time time){
        for (int i =0; i< list.size(); i++){
            if (time.compareTo(list.get(i).getDeparture()) == 0){
                return i;
            } 
        }
        return -1;
    }

    public static void main(String[]args){
        Scanner input = new Scanner(System.in);
        try{
            Queue<Flight> landingQueue = new LinkedList<>(); 
            Queue<Flight> takeoffQueue = new LinkedList<>();
            ArrayList<Flight> landingList = new ArrayList<>(landingQueue); 
            ArrayList<Flight> takeoffList = new ArrayList<>(takeoffQueue); 

            readFlights(landingList, "landing.txt");
            readFlights(takeoffList, "takeoff.txt");


            int landingTime = 12;
            int takeoffTime = 7;

            int runway = 0; //free if zero, busy if otherwise

            Time simulationD =  new Time("12:00");
            int simD = 480;
            int indL =0;
            int indT = 0;
            int waitingTime = 0; 
            int counterL=0;
            int counterT = 0;
            String[] simulationDL = new String[30];
            String[] simulationDT = new String[25];
            int[] waitingL = new int[30];
            int[] waitingT = new int[25];
            Flight[] flightL = new Flight[30];
            Flight[] flightT = new Flight[25];

            int avgLanding = 0;
            int avgTO = 0;
            for(int time=0; time < simD; time++){
                indL = findLanding(landingList, simulationD); //change?
                while (indL != -1){
                    landingQueue.offer(landingList.get(indL));
                    System.out.println("A landing request has been added to the landing queue at " + simulationD);
                    landingList.remove(indL);
                    indL = findLanding(landingList, simulationD);
                }
                indT = findTakeoff(takeoffList, simulationD);
                while (indT !=-1){
                    takeoffQueue.offer(takeoffList.get(indT));
                    System.out.println("A takeoff request has been added to the takeoff queue at " + simulationD);
                    takeoffList.remove(indT);
                    indT = findTakeoff(takeoffList, simulationD);
                }
                if (runway != 0){
                    runway--;
                } else if (runway == 0){
                    if (landingQueue.isEmpty() == false){
                        Flight f = landingQueue.poll();
                        // determine its waiting time, and set the runway to landingTime ????
                        flightL[counterL] = f;
                        waitingTime = simulationD.diff(f.getArrival());
                        waitingL[counterL] = waitingTime;
                        avgLanding += waitingTime;
                        
                        simulationDL[counterL] = simulationD.toString();
                        counterL++;
                        
                        runway = landingTime;

                    } else {
                        if (takeoffQueue.isEmpty() == false){
                            Flight fl = takeoffQueue.poll();
                            flightT[counterT] = fl;

                            waitingTime = simulationD.diff(fl.getDeparture());
                            waitingT[counterT] = waitingTime;
                            avgTO += waitingTime;
                            
                            simulationDT[counterT] = simulationD.toString();
                            counterT++;
                            
                            runway = takeoffTime;
                            //how to use diff to get diff between what values
                        }
                    }
                }
                simulationD.tick();
            }
            //display report
        

            System.out.println("Landing Report");
            System.out.printf("%-10s\t%-10s\t%-10s\t%-10s\t%-10s\t", "Flight", "Departure",
            "Arrival", "Arrived", "Waiting time");
            System.out.println("");
            for (int i=0; i<30; i++){
                if (flightL[i]!= null){
                    System.out.printf("%s%-10s\t%-10s\t", flightL[i], simulationDL[i], waitingL[i]); //how to get arrived 
                    System.out.println("");
                }
            }
            System.out.println("Average waiting time for landing requests: " + (avgLanding / counterL) + " minutes\n"); 

            System.out.println("Takeoff Report");
            System.out.printf("%-10s\t%-10s\t%-10s\t%-10s\t%-10s\t", "Flight", "Departure",
            "Arrival", "Departed", "Waiting time");
            System.out.println("");
            for (int i=0; i<25; i++){
                if (flightT[i]!= null){
                    System.out.printf("%s%-10s\t%-10s\t", flightT[i], simulationDT[i], waitingT[i]); //how to get arrived 
                    System.out.println("");
                }
            }
            System.out.println("Average waiting time for takeoff requests: " + (avgTO / counterT) + " minutes\n"); 

        } catch(InvalidDateTimeException exe){
            System.out.println("Invalid Time");
        }
    }
}
