/***
 * Class to manage Events and its properties
 * @author Joy Kim
 * @version 0.1
 * Date of creation: February 23, 2023
 * Last Date Modified: March 5, 2023
 *
 * This program was made in order to create a way to edit and modify an event object
 * directly without relying on methods made in the test class. This can include reading
 * a list of events from a textfile and putting into an array, viewing the array, adding 
 * an event element, removing an event element, finding an event based on date and time or a
 * description, and sorting
 */
import java.io.FileNotFoundException;
import java.io.File; 
import java.util.Scanner;
import java.io.IOException;
import java.util.InputMismatchException;
import java.io.PrintWriter;
public class Calendar extends Event{
    private Event[] events;
    private int count;
    
    /***
	 * Default constructor
	 * No parameters
     * Utilizes super constructor to initialize description, location, date, time to the string "none"
	 * Initializes events array and count is initialized to zero
	 */
    public Calendar(){
        super();
        events = new Event[50];
        count = 0;
    }
    /***
	 * Constructor with one parameter
	 * @param	filename to get file contents and input them into events array
	 */
    public Calendar(String filename){
        events = new Event[50];
        readEvents(filename);          
    }

    /***
	 * Method to get array elements by reading through a textfile
     * @param   filename for the name of the textfile that is to be read
	 * no return value
	 */
    private void readEvents(String filename){
        //what does it mean - same as calendar 1 param?
        int i=0;
        File file = new File(filename);
        try(Scanner fileScanner = new Scanner(file)){ 
            //Scanner fileScanner = new Scanner(file);
            
            while(fileScanner.hasNextLine()) { //while textfile has data to be read run this loop
                //common values for appointment and meeting
                String type = fileScanner.nextLine();
                if(type.equals(" ")){
                    break;
                }
                String description = fileScanner.nextLine();
                if(description.equals(" ")){
                    break;
                }
                String location = fileScanner.nextLine();
                if(location.equals(" ")){
                    break;
                }
                String date = fileScanner.nextLine();
                if(date.equals(" ")){
                    break;
                }
                String time = fileScanner.nextLine();
                if(type.equalsIgnoreCase("appointment")){ //unique values to appointment
                    String contact = fileScanner.nextLine();
                    events[i] = new Appointment (description, location, date, time, contact);
                    i++;
                }
                else if(type.equalsIgnoreCase("meeting")){ //unique values to meeting
                    String host = fileScanner.nextLine();
                    int guests = Integer.parseInt(fileScanner.nextLine()); //bc int
                    events[i] = new Meeting (description, location, date, time, host, guests);
                    i++;
                }
            
            }
            count = i;
            fileScanner.close();
        }
        catch (FileNotFoundException e){ //if file is not found
            System.out.println("Cannot open file \"events.txt\""); 
        }
        catch(InvalidDateTimeException ex){ //if date and time is invalid
              System.out.println(ex.getMessage());
        }                
    }
    /***
	* Method to save events that are edited
	* @param   filename for the name of the textfile that is to be edited
	* no return value
	*/
    public void saveEvents(String filename){
        //make new file and input event list there?
        try{
        File file = new File("events.txt");
        PrintWriter fileWriter = new PrintWriter(file);
        int j =0;
        for(int i =0; i < events.length; i++){
     
                if(events[j] instanceof Appointment){
                    Appointment app = (Appointment)events[j];
                    fileWriter.print("appointment\n" + events[j].getDescription()+ "\n" + events[j].getLocation()+ "\n" + events[j].getDate() + 
                    "\n"+ events[j].getTime() +"\n"+ app.getContact() +"\n");
                    j++;
                }
                if(events[j] instanceof Meeting){
                Meeting meet = (Meeting)events[j];
                fileWriter.print("meeting\n" + events[j].getDescription()+ "\n" + events[j].getLocation()+ "\n" + events[j].getDate() + "\n"+ 
                events[j].getTime() +"\n"+ meet.getHost() +"\n" + meet.getGuests()+ "\n");
                j++;
                }
                if(i == events.length-1){
                    fileWriter.print(" ");
                }
    
        }
            fileWriter.close();
        } catch (FileNotFoundException e){
            e.getMessage();
        }  
                        /*if (input.hasNextLine()){
                            newAnimal = input.nextLine();
                            count = addAnimal(animals, newAnimal, count);
                            System.out.println("Successfully added.\n");*/

    }
    /***
	 * Method to find an event given an input of description
     * @param   description for the input of description from the user to compare with the events array descriptions
	 * @return a shallow copy of the event element that holds that event with the matching description
	 */
    public Event findEvent(String description){
        for (int i =0; i< count; i++){
            if (description.compareTo(events[i].getDescription())==0){
                Event calendar1 = events[i]; //shallow copy
                return calendar1;
            }
        }
        return null;
    }
    /***
	 * Method to find an event given an input of date
     * @param   date for the input of date from the user to compare with the events array date
	 * @return the array that holds the event(s) with the matching dates
	 */
    public Event[] findEvents(String date){
        int j = 0;
        Event[] foundList = new Event[count]; //to store the multiple events that are at a date
        for (int i = 0; i<count; i++){
            if (date.compareTo(events[i].getDate().toString()) == 0 ){
                foundList[j] = events[i];
                j++;
            } 
        }
        return foundList;
    }

    /***
	 * Method to add an event given an input of an event element
     * @param  e for the event element to be added into the array
	 * @return true or false depending on whether there is space in the array
	 */
    public boolean addEvent(Event e){
        if(count == events.length) {
            return false;
        } else {
            events[count] = e;
            count++;
            return true;
        }
    }
    /***
	 * Method to remove an event given an input of an event's description
     * @param  e for the event element to be removed from the array
	 * @return true or false depending on whether there is a decription in the array to remove
	 */
    public boolean removeEvent(String description){ //is this allowed
        int counter = 0;
        Event eventer = null;
            for(int i =0; i< count; i++){
                    if (description.compareTo(events[i].getDescription())==0){
                        for(int j=i; j<count-1; j++){
                        events[j] = events[j+1];
                        }
                        count--;
                        return true;
                    }
            }
            return false;
          
            /*if(events[i] != null){
                if (events[i].getDescription().equals(description)||counter!=0){
                    events[i] = events[i+1];
                    if (counter == 0){
                        count--;
                    }
                    counter++;
                    return true;
                } else {
                    return false;
                }*/
                // If an event is found at index k, it is removed by shifting all the events from k to count-1 one position down.
    }
    /***
	* Method to display events initialized above
    * No parameters
	* No return value
	*/
    public void viewEvents(){
        System.out.println(String.format("%-10s\t%-20s\t%-30s\t%-10s\t%-10s\t%-20s\t%-10s\t","Type", "Description", "Location", "Date", "Time", "Contact/Host", "Guests"));
        for (int i = 0; i < count; i++){ 
            System.out.println(events[i] + " "); //needs the " " in orded to use toString indirectly
            
        }
    }
    /***
	 * Method to sort events according to date AND time
     * no parameters
	 * no return value
     * Utilizes Arrays.sort to sort
	 */
    public void sortEvents(){
        int countie = 0;
        for (int i =0; i<events.length; i++){
            if (events[i] != null){
                countie++;
            }
        }
        java.util.Arrays.sort(events, 0, countie); //COUNTIE prolly should be count but we not risking it rn 
    }
    /***
	 * Method to get the event's information
	 * @param no parameters
	 * @return formatted string containing the value of the data members from super class
	 */
    @Override
    public String toString(){
        return super.toString();
    }
}