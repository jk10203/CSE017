/***
 * Class to create the test class for Note, Contact, Organizer
 * @author Joy Kim
 * @version 0.1
 * Date of creation: March, 2023
 * Last Date Modified: March 21, 2023
 *
 * This program was made in order to test the functions of comparators and comparables, Note, Contact, and Organizer class.
 * It is also able to read through a textfile to get the Note and Contact elements.
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Test {
	public static void main(String[] args) {
		Organizer<Contact> contacts = new Organizer<>(20);
		Organizer<Note> notes = new Organizer<>(20);

		// Testing Organizer for type Note
		readNotes(notes, "notes.txt");
		System.out.println("List of notes:\n" + notes);
		try{
			Date d = new Date("02/22/2021");
			Note n = new Note(d, "Medicine", "Pick up at the pharmacy");
			notes.addElement(n);
			System.out.println("Note: (" + n + ") added successfully.");
			System.out.println("\nList of notes:\n" + notes);
			d = new Date("01/26/2021");
			n = new Note(d, "", "");
			n = notes.findElement(n);
			if (n == null)
				System.out.println("Note not found.");
			else {
				System.out.println("Note found: " + n);
				notes.removeElement(n);
				System.out.println("Note (" + n + ") removed successfully.");
			}
		}
		catch(InvalidDateTimeException e){
			System.out.println(e.getMessage());
		}


		System.out.println("\nList of notes:\n" + notes);
		notes.setComparator(new ComparatorByTitle());
		System.out.println("\nList of notes sorted by title:\n" + notes);

		// Testing Organizer for type Contact
		readContacts(contacts, "contacts.txt");
		System.out.println("\nList of contacts:\n" + contacts);
		Contact c = new Contact("Floss Albert", "610-222-2434", "afloss@lehigh.edu");
		contacts.addElement(c);
		System.out.println("Contact (" + c + ") added successfully.");
		System.out.println("\nList of contacts:\n" + contacts);
		c = new Contact("Philip Mensen", "", "");
		c = contacts.findElement(c);
		if (c == null)
			System.out.println("Contact not found.");
		else {
			System.out.println("Contact found: " + c);
			contacts.removeElement(c);
			System.out.println("Contact (" + c + ") removed successfully.");
		}
		c = new Contact("Albares Cammy", "", "");
		c = contacts.findElement(c);
		if (c == null)
			System.out.println("Contact not found.");
		else {
			System.out.println("Contact found: " + c);
			contacts.removeElement(c);
			System.out.println("Contact(" + c + ") removed successfully.");
		}
		System.out.println("\nList of contacts:\n" + contacts);
		contacts.setComparator(new ComparatorByEmail());
		System.out.println("\nList of contacts sorted by email:\n" + contacts);
	}
	/***
	 * Method to input elements in the organizer arraylist by reading through a textfile
	 * @param   notes for the notes array to have info inputted based on textfile
     * @param   filename for the name of the textfile that is to be read
	 * no return value
	 */
	// Definition of readNotes
	public static void readNotes(Organizer<Note> notes, String filename) {
		File file = new File(filename);
        try{
            Scanner read = new Scanner (file);
            while(read.hasNextLine()){
                String date = read.nextLine();
				Date dateD = new Date(date);
				String title = read.nextLine();
				String description = read.nextLine();
                Note note = new Note(dateD, title, description); //create note object
                notes.addElement(note);
            }
            read.close();

        }
        catch(FileNotFoundException e){
                System.out.println("File not found.");

        }
        catch(InvalidDateTimeException ex){ //if date and time is invalid
              System.out.println(ex.getMessage());
        }  
	}
	/***
	 * Method to input elements in the organizer arraylist by reading through a textfile
	 * @param   contacts for the contacts array to have info inputted based on textfile
     * @param   filename for the name of the textfile that is to be read
	 * no return value
	 */
	// Definition of readContacts
	public static void readContacts(Organizer<Contact> contacts, String filename) {
		File file = new File(filename);
        try{
            Scanner read = new Scanner (file);
            while(read.hasNext()){
                String first = read.next();
				String last = read.next();
				String name = first + " " + last;
				String phone = read.next();
				String email = read.next();
                Contact contact = new Contact(name, phone, email); //create note object
                contacts.addElement(contact);
            }
            read.close();

        }
        catch(FileNotFoundException e){
                System.out.println("File not found.");

        }
	}

}

