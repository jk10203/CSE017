/***
 * Test Class for Event, Meeting, Appointment, Date, Time, Calendar
 * @author Joy Kim
 * @version 0.1
 * Date of creation: February 23, 2023
 * Last Date Modified: March 5, 2023
 *
 * This program was made in order to organize a list of given events depending on user input from the menu options below. 
 * There is a variety of options which include displaying the events array, sorting based on time and date, finding based 
 * on description and date, adding and removing an event, and exitting out of the menu loop. 
 */
import java.io.FileNotFoundException;
import java.io.File; 
import java.util.Scanner;
import java.io.IOException;
import java.util.InputMismatchException;
public class CalendarManager{
    public static void main(String[]args){
        Scanner input = new Scanner(System.in);
        //GARBAGE IS FOR THE \n INPUT FROM USER!!!!

        Calendar calendarA = new Calendar("events.txt");

        boolean menuOp = true; //for menu repetition
        int choice=0; //initializing
        String descr = "";
        File file = new File("events.txt");
        String dateInput = "";


        do{
            try{

                System.out.println("Please choose from the following operations: ");
                System.out.println("1. View the events");
                System.out.println("2. Search event by description");
                System.out.println("3. Search events by date");
                System.out.println("4. Add a new event");
                System.out.println("5. Remove an existing event");
                System.out.println("6. Sort by date and time");
                System.out.println("7. Exit the program");

                
                choice = getChoice(input);

                switch(choice){
                    case 1:
                        calendarA.saveEvents("events.txt");
                        calendarA.viewEvents();
                        System.out.println(" "); //for formatting
                        break;
                    case 2:
                        System.out.println("Please enter a description: ");
                        Event cal = null;

                        String garbage = input.nextLine(); //to clear the menu input from before?

                        descr = input.nextLine(); //getting description from user input
                        System.out.println(" ");//formatting
                        cal = calendarA.findEvent(descr);

                        //checking index values outside of method
                        if (cal != null){ //if index is a number other than -1 --> found
                            System.out.println("Event found: " + cal.toString() + " ");
                        } else if (cal == null){ //-1 is not found (initialized number)
                            System.out.println("Event not found.");
                        }
                        System.out.println(" ");
                        break;

                    case 3:
                        System.out.println("Please enter a date (MM/DD/YYYY): ");
                        
                        String garbage2 = input.nextLine(); //to clear the menu input from before?

                        dateInput = input.nextLine(); //getting location from user input
                        Date d = new Date(dateInput);
                        System.out.println(" ");

                        Event [] foundList = new Event[50]; //in initializing another event array in order to store return array from method
                        foundList = calendarA.findEvents(dateInput); //gives array
                        int counter =0;
                        int counterEvent = 0;
                        for (int i = 0; i<foundList.length; i++){ //has to be foundList.length because ?
                            if (foundList[i] != null){
                                counterEvent++;
                                //going through to see how many events were found by using counter
                            }
                        }

                        for (int i = 0; i<foundList.length; i++){
                            if (foundList[i] != null){
                                if (counter == 0 && counterEvent > 1){//to make sure that it doesn't enter this if statement after the first time (using counter)
                                    System.out.println(counterEvent + " events found at " + dateInput + "!");//FIXXXXX
                                    System.out.println(String.format("%-10s\t%-20s\t%-30s\t%-10s\t%-10s\t%-20s\t%-10s\t","Type", "Description", "Location", "Date", "Time", "Contact/Host", "Guests"));
                                    counter++; 
                                } else if (counter == 0 && counterEvent == 1){ //if only 1 event was found (for grammar issues)
                                    System.out.println(counterEvent + " event found at " + dateInput + "!");
                                    System.out.println(String.format("%-10s\t%-20s\t%-30s\t%-10s\t%-10s\t%-20s\t%-10s\t","Type", "Description", "Location", "Date", "Time", "Contact/Host", "Guests"));
                                    counter++; //utilizing the same counter as before if statement to make sure only one or the other is entered ONCE
                                }
                                System.out.println(foundList[i] + " "); //displaying the events that are in the array
                            } 
                        }
                        if (counter == 0){ //if the counter is never incremented, means no event was found
                            System.out.println("Event not found.");
                        }
                        System.out.println(" ");
                        break;

                    case 4:
                        Event newEve = null;
                        System.out.println("Please enter the type: ");
                        garbage2 = input.nextLine();
                        String type = input.nextLine();
                        System.out.println("Please enter the description: ");
                        String description = input.nextLine();
                        System.out.println("Please enter the location: ");
                   
                        String location = input.nextLine();
                        System.out.println("Please enter the date: ");
             
                        String date = input.nextLine();
                        System.out.println("Please enter the time: ");
               
                        String time = input.nextLine();
                        System.out.println("Please enter the contact/host: ");
           
                        String contactHost = input.nextLine();
                        if(type.equalsIgnoreCase("appointment")){ //unique values to appointment
                            newEve = new Appointment (description, location, date, time, contactHost);
                    
                        }
                        else if(type.equalsIgnoreCase("meeting")){ //unique values to meeting
                            System.out.println("Please enter the number of guests: ");
                            int guests = input.nextInt(); //bc int
                            newEve = new Meeting (description, location, date, time, contactHost, guests);
                 
                        }
                        boolean fullArray = calendarA.addEvent(newEve);
                        if (fullArray==false){
                            System.out.println("Array is full!");
                        } 
                        if(fullArray ==true){
                            System.out.println("Successful!\n");
                        }
                        calendarA.viewEvents(); //then print the original events array which is now sorted
                        System.out.println(" ");
                        break;
                    case 5:
                        System.out.println("Enter the description of the event you want to remove: ");
                        garbage = input.nextLine(); //to clear the menu input from before?
                        String descr2 = input.nextLine(); //getting description from user input
                        System.out.println(" ");//formatting
                        calendarA.removeEvent(descr2);
                        break;

                    case 6:
                        calendarA.sortEvents();
                        calendarA.viewEvents();
                        System.out.println(" "); //for formatting
                        break;
                    case 7:
                        calendarA.saveEvents("events.txt");
                        System.out.println("Exitting...");
                        menuOp = false; //exits out of loop
                        break; //exits out of switch statement
                }
            } catch (InvalidDateTimeException e){
                System.out.println(e.getMessage());
                System.out.println(" ");
            }

        } while (menuOp);

    }

    /***
	 * Method to input validate the menu choices (1-7)
	 * @param sc for the user input to be validated to make sure it is 1-7
	 * @return the number that the user inputted IF it is 1-7
	 */
    public static int getChoice(Scanner sc){ //method to input validate :D
        int choice;
        int choice2 = 0;
        boolean inVal = true;
        do{
            if (sc.hasNextInt()){
                choice = sc.nextInt();
                if (choice>0 && choice<8){
                    choice2 = choice;
                    inVal = false;
                    break;
                } else {
                    System.out.println("Please enter a valid option. >:(");
                    sc.nextLine();
                }
            } else {
                System.out.println("Please enter a valid option. >:(");
                sc.nextLine();
            }
        } while (inVal);
        return choice2;
    }



}