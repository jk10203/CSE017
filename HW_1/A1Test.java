/***
 * Test Class for Event, Meeting, Appointment
 * @author Joy Kim
 * @version 0.1
 * Date of creation: February 2, 2023
 * Last Date Modified: February 2, 2023
 *
 * This program was made in order to organize a list of given events depending on user input from the menu options below. 
 * There is a variety of options which include displaying the events array, sorting, finding based on location and description, 
 * and exitting out of the menu loop. 
 */
import java.util.Scanner;
public class A1Test{
    public static void main(String[]args){
        Scanner input = new Scanner(System.in);

        Event [] events = new Event[10]; //new Event array events

        events[0] = new Appointment ("Dentist", "Lehigh Dentists","01/26/2023", "10:30", "Beth Clark");
        events[1] = new Meeting ("Science Club", "PA-220", "02/03/2023", "16:30", "Will Johns", 25);
        events[2] = new Meeting ("Movie Club", "PA-220", "01/31/2023", "17:00", "Grace Williams", 10);
        events[3] = new Appointment ("Instructor", "PA-254", "01/30/2023", "11:15", "Mark Jones");
        events[4] = new Meeting ("Provost", "Memorial Building", "05/05/2023", "14:30", "Lisa Zuppe", 100);
        events[5] = new Meeting ("Group work", "Zoom", "02/07/2023", "18:45", "Jack taylor", 5);
        events[6] = new Appointment ("Doctor", "Lehigh Doctors", "04/22/2023", "13:45", "Kathy Bell");
        events[7] = new Meeting ("Programming Club", "PA-220", "03/15/2023", "19:00", "Billy Steward", 20);
        events[8] = new Appointment ("Advising", "PA-252", "03/11/2023", "12:15", "Sharon Kraft");
        events[9] = new Appointment ("Bank Account", "Wells Fargo", "03/25/2023", "10:30", "Sarah Thomson");

        boolean menuOp = true; //for menu repetition
        int choice=0; //initializing
        String descr = "";
        String location = "";
        do{
            System.out.println("Please choose from the following operations: ");
            System.out.println("1. View the events");
            System.out.println("2. Search event by description");
            System.out.println("3. Search events by location");
            System.out.println("4. Sort the events by location");
            System.out.println("5. Exit the program");
            
            choice = getChoice(input);

            switch(choice){
                case 1:
                    System.out.println(String.format("%-10s\t%-20s\t%-30s\t%-10s\t%-10s\t%-20s\t%-10s\t","Type", "Description", "Location", "Date", "Time", "Contact/Host", "Guests"));
                    //20 for desciription and 30 for location because of the long length of the strings that are classified as such
                    printEvents(events);
                    System.out.println(" "); //for formatting
                    break;
                case 2:
                    System.out.println("Please enter a description: ");

                    String garbage = input.nextLine(); //to clear the menu input from before?

                    descr = input.nextLine(); //getting description from user input
                    System.out.println(" ");//formatting
                    int index = findEvent(events, descr); //the return for this method is the index so setting a variable outside of method as that number
                    //checking index values outside of method
                    if (index != -1){ //if index is a number other than -1 --> found
                        System.out.println("Event found: \n" + events[index] + " ");
                    } else if (index == -1){ //-1 is not found (initialized number)
                        System.out.println("Event not found.");
                    }
                    System.out.println(" ");
                    break;

                case 3:
                    System.out.println("Please enter a location: ");

                    String garbage2 = input.nextLine(); //to clear the menu input from before?

                    location = input.nextLine(); //getting location from user input
                    System.out.println(" ");

                    Event[] foundList = new Event[10]; //in initializing another event array in order to store return array from method
                    foundList = findEvents(events, location); //gives array
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
                                System.out.println(counterEvent + " events found at " + location + "!");
                                counter++; 
                            } else if (counter == 0 && counterEvent == 1){ //if only 1 event was found (for grammar issues)
                                System.out.println(counterEvent + " event found at " + location + "!");
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
                    sortEvents(events); //Method to first sort
                    System.out.println(String.format("%-10s\t%-20s\t%-30s\t%-10s\t%-10s\t%-20s\t%-10s\t","Type", "Description", "Location", "Date", "Time", "Contact/Host", "Guests"));
                    printEvents(events); //then print the original events array which is now sorted
                    System.out.println(" ");
                    break;

                case 5:
                    System.out.println("Exitting...");
                    menuOp = false; //exits out of loop
                    break; //exits out of switch statement
            }
        
        } while (menuOp);

    }
    /***
	 * Method to display events initialized above
	 * @param   list for the events array above to be inputted and printed
	 * no return value
	 */
    public static void printEvents(Event[] list){
        for (int i = 0; i<10; i++){ 
            System.out.println(list[i] + " "); //needs the " " in orded to use toString indirectly
        }
    }
    /***
	 * Method to sort events alphabetically according to location
	 * @param list for the events array above to be inputted and sorted according to location
	 * no return value
	 */
    public static void sortEvents(Event[] list){
       for(int i=1; i<list.length; i++){
           int j=i;
           Event currentVal = list[i];
           while(j>0 && currentVal.getLocation().compareTo(list[j-1].getLocation())<0){
               list[j] = list[j-1];
               j--;
           }
           list[j] = currentVal;
       }
    }
    /***
	 * Method to find an event given an input of description
	 * @param list for the events array above to be inputted in order to find a matching description
     * @param descr for the input of description from the user to compare with the events array descriptions
	 * @return the index that holds the event with the matching description
	 */
    public static int findEvent(Event[] list, String descr){
        int index = -1;
        int index2 = -1;
        for (int i =0; i<10; i++){
            index2++;
            if (descr.compareTo(list[i].getDescription()) == 0 ){
                index = index2;
                break;
            }
        }
        return index; 
    }
    /***
	 * Method to find an event given an input of location
	 * @param list for the events array above to be inputted in order to find a matching description
     * @param location for the input of location from the user to compare with the events array locations
	 * @return the array that holds the event(s) with the matching location
	 */
    public static Event[] findEvents(Event[] list, String location){
        int j = 0;
        Event[] foundList = new Event[10];
        for (int i =0; i<10; i++){
            if (location.compareTo(list[i].getLocation()) == 0 ){
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