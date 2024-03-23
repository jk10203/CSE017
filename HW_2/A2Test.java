/***
 * Test Class for Event, Meeting, Appointment, Date, Time
 * @author Joy Kim
 * @version 0.1
 * Date of creation: February 6, 2023
 * Last Date Modified: February 13, 2023
 *
 * This program was made in order to organize a list of given events depending on user input from the menu options below. 
 * There is a variety of options which include displaying the events array, sorting based on time and date, finding based 
 * on description and date, and exitting out of the menu loop. 
 */
import java.io.FileNotFoundException;
import java.io.File; 
import java.util.Scanner;
import java.io.IOException;
import java.util.InputMismatchException;
public class A2Test{
    public static void main(String[]args){
        Scanner input = new Scanner(System.in);
        //question on readEvent / file connecting with the program
        //the sorting with date and time how to do interchangeably
        //also how to connect date and time with event's methods/getter/setter for date and time

        Event [] events = new Event[100]; //new Event array events

        boolean menuOp = true; //for menu repetition
        int choice=0; //initializing
        String descr = "";
        File file = new File("events.txt");
        String dateInput = "";
        int count = readEvents(events, "events.txt");


        do{
            try{

                System.out.println("Please choose from the following operations: ");
                System.out.println("1. View the events");
                System.out.println("2. Search event by description");
                System.out.println("3. Search events by date");
                System.out.println("4. Sort the events by date and time");
                System.out.println("5. Exit the program");
                
                choice = getChoice(input);

                switch(choice){
                    case 1:
                        System.out.println(String.format("%-10s\t%-20s\t%-30s\t%-10s\t%-10s\t%-20s\t%-10s\t","Type", "Description", "Location", "Date", "Time", "Contact/Host", "Guests"));
                        //20 for desciription and 30 for location because of the long length of the strings that are classified as such
                        printEvents(events, count);
                        System.out.println(" "); //for formatting
                        break;
                    case 2:
                        System.out.println("Please enter a description: ");

                        String garbage = input.nextLine(); //to clear the menu input from before?

                        descr = input.nextLine(); //getting description from user input
                        System.out.println(" ");//formatting
                        int index = findEvent(events, descr, count); //the return for this method is the index so setting a variable outside of method as that number
                        
                        //checking index values outside of method
                        if (index != -1){ //if index is a number other than -1 --> found
                            System.out.println("Event found: " + events[index] + " ");
                        } else if (index == -1){ //-1 is not found (initialized number)
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

                        Event[] foundList = new Event[10]; //in initializing another event array in order to store return array from method
                        foundList = findEvents(events, dateInput, count); //gives array
                        int counter =0;
                        int counterEvent = 0;
                        for (int i = 0; i<10; i++){ 
                            if (foundList[i] != null){
                                counterEvent++;
                                //going through to see how many events were found by using counter
                            }
                        }

                        for (int i = 0; i<10; i++){
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
                        sortEvents(events, count); //Method to first sort
                        System.out.println(String.format("%-10s\t%-20s\t%-30s\t%-10s\t%-10s\t%-20s\t%-10s\t","Type", "Description", "Location", "Date", "Time", "Contact/Host", "Guests"));
                        printEvents(events, count); //then print the original events array which is now sorted
                        System.out.println(" ");
                        break;

                    case 5:
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
	 * Method to get the number of elements in an array by reading through a textfile
	 * @param   list for the events array to have info inputted based on textfile
     * @param   filename for the name of the textfile that is to be read
	 * @return  the number of elements in the array created from the textfile that is read
	 */
    public static int readEvents(Event[] list, String filename){
        int i=0;
        File file = new File(filename);
        try{ 
            Scanner fileScanner = new Scanner(file);
            
            while(fileScanner.hasNextLine()) { //while textfile has data to be read run this loop
                //common values for appointment and meeting
                String type = fileScanner.nextLine();
                String description = fileScanner.nextLine();
                String location = fileScanner.nextLine();
                String date = fileScanner.nextLine();
                String time = fileScanner.nextLine();
                if(type.equalsIgnoreCase("appointment")){ //unique values to appointment
                    String contact = fileScanner.nextLine();
                    list[i] = new Appointment (description, location, date, time, contact);
                    i++;
                }
                else if(type.equalsIgnoreCase("meeting")){ //unique values to meeting
                    String host = fileScanner.nextLine();
                    int guests = Integer.parseInt(fileScanner.nextLine()); //bc int
                    list[i] = new Meeting (description, location, date, time, host, guests);
                    i++;
                }
            }
            fileScanner.close();
        }
        catch (FileNotFoundException e){ //if file is not found
            System.out.println("Cannot open file \"events.txt\""); 
        }
        catch(InvalidDateTimeException ex){ //if date and time is invalid
              System.out.println(ex.getMessage());
        }               
        
        return i; 
    //return value of readEvents() indicates the number of non-null elements in the array events.
    }
    //point of this
    //return the number of events then use that to edit the for loops in other methods based off that

    /***
	* Method to display events initialized above
	* @param   list for the events array above to be inputted and printed
    * @param   count for the number of elements in the array created from the textfile that is read
	* no return value
	*/
    public static void printEvents(Event[] list, int count){
        for (int i = 0; i < count; i++){ 
            System.out.println(list[i] + " "); //needs the " " in orded to use toString indirectly
        }
    }
    /***
	 * Method to sort events according to date AND time
	 * @param   list for the events array above to be inputted and sorted according to date AND time
     * @param   count for the number of elements in the array created from the textfile that is read
	 * no return value
	 */
    public static void sortEvents(Event[] list, int count){
        for(int i=1; i<count; i++){
            int j=i;
            Event currentVal = list[i];
            //String current = currentVal.getLocation(); dont need this bc we alr made a method for compareto
            while(j>0 && currentVal.compareTo(list[j-1])<0){ //using compareTo method in Event 
                list[j] = list[j-1];
                j--;
           }
           list[j] = currentVal;
        }
    }
    /***
	 * Method to find an event given an input of description
	 * @param   list for the events array above to be inputted in order to find a matching description
     * @param   descr for the input of description from the user to compare with the events array descriptions
     * @param   count for the number of elements in the array created from the textfile that is read
	 * @return the index that holds the event with the matching description
	 */
    public static int findEvent(Event[] list, String descr, int count){
        int index = -1;
        int index2 = -1;
        for (int i =0; i<count; i++){
            index2++;
            if (descr.compareTo(list[i].getDescription()) == 0 ){//parameters are not event obj so the alr defined compareTo method is used
                index = index2;
                break;
            }
        }
        return index; 
    }
    /***
	 * Method to find an event given an input of date
	 * @param   list for the events array above to be inputted in order to find a matching description
     * @param   date for the input of date from the user to compare with the events array date
     * @param   count for the number of elements in the array created from the textfile that is read
	 * @return the array that holds the event(s) with the matching dates
	 */
    public static Event[] findEvents(Event[] list, String date, int count){ //finding by date
        int j = 0;
        Event[] foundList = new Event[10]; //to store the multiple events that are at a date
        for (int i = 0; i<count; i++){
            if (date.compareTo(list[i].getDate().toString()) == 0 ){
                foundList[j] = list[i];
                j++;
            } 
        }
        
        return foundList;
    }
    /***
	 * Method to input validate the menu choices (1-5)
	 * @param sc for the user input to be validated to make sure it is 1-5
	 * @return the number that the user inputted IF it is 1-5
	 */
    public static int getChoice(Scanner sc){ //method to input validate :D
        int choice;
        int choice2 = 0;
        boolean inVal = true;
        do{
            if (sc.hasNextInt()){
                choice = sc.nextInt();
                if (choice>0 && choice<6){
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