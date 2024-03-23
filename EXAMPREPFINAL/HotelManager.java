import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
/**
 * Class HotelManager
 * @author Houria Oudghiri
 * Date of creation: May 10, 2022
 */
public class HotelManager {
	// List of the guests stored in a treemap (guest name is the key)
	private TreeMap<String, Guest> guests;
	// List of staff members stored in  a hashtable (username is the key)
	private HashMap<String, Staff> staff;
	// List of rooms stored in a linked list
	private LinkedList<Room> rooms;
	/**
	 * Constructor
	 * creates instances of the classes TreeMap, HashMap, and LinkedList
	 * reads the data from the files into the three data structures
	 * @param guestFile	file with the list of guests
	 * @param staffFile	file with the list of staff members
	 * @param roomFile  file with the list of rooms
	 */
	public HotelManager(String guestFile,
					    String staffFile,
					    String roomFile) {
		guests = new TreeMap<String, Guest>(String.CASE_INSENSITIVE_ORDER);
		staff = new HashMap<String, Staff>(500);
		rooms = new LinkedList<Room>();
		readGuests(guestFile);
		readStaff(staffFile);
		readRooms(roomFile);
	}
	/**
	 * Method to read the guest information from a file
	 * @param filename	with the list of guests
	 */
	private void readGuests(String filename) {
		File file = new File(filename);
		try {
			Scanner read = new Scanner(file);
			int i;
			while(read.hasNextLine()) {
				String line = read.nextLine();
				String[] tokens = line.split("\\|");
				String name = tokens[0];
				String phone = tokens[1];
				String email = tokens[2];
				String pType = tokens[3];
				double amount = Double.parseDouble(tokens[4]);
				Payment p;
				if(pType.equals("cash")) {
					p = new CashPayment(amount);
					i=5;
				}
				else {
					String ccn = tokens[5];
					String cct = tokens[6];
					String edate = tokens[7];
					p = new CreditCardPayment(amount, ccn, cct, edate);
					i=8;
				}
				Guest g = new Guest(name, phone, email);
				g.setPayment(p);
				for(; i<tokens.length; i++) {
					g.checkIn(tokens[i]);
				}
				addGuest(g);
			}
			read.close();
		}
		catch(FileNotFoundException e) {
			System.out.println("File " + filename + " not found.");
			System.exit(0);
		}
	}
	/**
	 * Method to read the staff information from a file
	 * @param filename	file with the staff member information
	 */
	private void readStaff(String filename) {
		File file = new File(filename);
		try {
			Scanner read = new Scanner(file);
			while (read.hasNextLine()) {
				String line = read.nextLine();
				String[] tokens = line.split("\\|");
				String name = tokens[0];
				String phone = tokens[1];
				String email = tokens[2];
				String username = tokens[3];
				String password = tokens[4];
				Staff s = new Staff(name, phone, email, username, password);
				addStaff(s);
			}
			read.close();
		}
		catch(FileNotFoundException e) {
			System.out.println("File " + filename + " not found.");
			System.exit(0);
		}
	}
	/**
	 * Method to read the room information from a file
	 * @param filename with the list of rooms
	 */
	private void readRooms(String filename) {
		File file = new File(filename);
		try {
			Scanner read = new Scanner(file);
			while(read.hasNextLine()) {
				String line = read.nextLine();
				String[] tokens = line.split("\\|");
				String number = tokens[0];
				String type = tokens[1];
				double price = Double.parseDouble(tokens[2]);
				String status = tokens[3];
				Room r;
				if(status.equals("reserved")) {
					r =  new Room(number, type, price, Room.STATUS.RESERVED);
				}
				else {
					r =  new Room(number, type, price, Room.STATUS.FREE);
				}
				addRoom(r);
			}
			read.close();
		}
		catch(FileNotFoundException e) {
			System.out.println("File " + filename + " not found.");
			System.exit(0);
		}
	}
	/**
	 * Method to add a guest to the tree map
	 * @param g	guest object to be added
	 * @return	true if the guest was added successfully
	 * 			false if the guest is already in the treemap
	 */
	public boolean addGuest(Guest g) {
		return guests.add(g.getName(), g);
	}
	/**
	 * Method to add a room to the linked list
	 * @param r	room object to be added
	 * @return true if the room was added successfully
	 */
	public boolean addRoom(Room r) {
		return rooms.add(r);
	}
	/**
	 * Method to add a staff member to the hashmap
	 * @param s	staff object to be added
	 */
	public void addStaff(Staff s) {
		staff.put(s.getUsername(), s);
	}
	/**
	 * Method to search for a guest in the treemap
	 * @param name	name of the guest
	 * @return	the guest object if found, null otherwise
	 */
	public Guest findGuest(String name) {
		return guests.get(name);
	}
	/**
	 * Method to search for a staff member in the hashmap
	 * @param username
	 * @param passwd
	 * @return true if a staff member was found with the same username and password
	 *         false otherwise
	 */
	public boolean findStaff(String username, String passwd) {
		Staff s = new Staff("", "", "", username, passwd);
		Staff f = staff.get(username);
		if(f!= null && f.equals(s)) {
			return true;
		}
		return false;
	}
	/**
	 * Method to search for a room in the linked list
	 * @param number room number
	 * @return  Room object if found, null otherwise
	 */
	public Room findRoom(String number) {
		Iterator<Room> iter = rooms.iterator();
		while(iter.hasNext()) {
			Room r = iter.next();
			if(r.getNumber().equals(number)) {
				return r;
			}
		}
		return null;
	}
	/**
	 * Method to print the invoice of a given guest
	 * @param g guest
	 */
	public void printGuestInvoice(Guest g) {
		System.out.printf("%-10s\t%-10s\t%-10s\n", "Room#", "Type", "Price");
		ArrayList<String> guestRooms = new ArrayList<>();
		guestRooms = g.getRooms();
		double total = 0;
		for (int i = 0; i < guestRooms.size(); i++) {
			Room r = findRoom(guestRooms.get(i));
			System.out.printf("%-10s\t%-10s\t$%-10.2f\n",r.getNumber(), r.getType(), r.getPrice());
			total += r.getPrice();
		}
		System.out.printf("%-10s\t%-20.2f\n", "Total", total*2);
		

			/*Iterator<Room> iter = rooms.iterator();
		LinkedList<Room> list = new LinkedList<>();
		while(iter.hasNext()) {
			Room r = iter.next();
			if(r.getStatus() == Room.STATUS.FREE) {
				list.add(r);
			}
		}
		list.bubbleSort(new RoomComparator());
		iter = list.iterator();
		System.out.printf("%-10s\t%-10s\t%-10s\n", "Room#", "Type", "Price/night");
		while(iter.hasNext()) {
			Room r = iter.next();
			System.out.printf("%-10s\t%-10s\t$%-10.2f\n",r.getNumber(), r.getType(), r.getPrice());
		}*/

		
	}
	/**
	 * Method to print the free rooms sorted by price
	 */
	public void printFreeRooms() {
		Iterator<Room> iter = rooms.iterator();
		LinkedList<Room> list = new LinkedList<>();
		while(iter.hasNext()) {
			Room r = iter.next();
			if(r.getStatus() == Room.STATUS.FREE) {
				list.add(r);
			}
		}
		list.bubbleSort(new RoomComparator());
		iter = list.iterator();
		System.out.printf("%-10s\t%-10s\t%-10s\n", "Room#", "Type", "Price/night");
		while(iter.hasNext()) {
			Room r = iter.next();
			System.out.printf("%-10s\t%-10s\t$%-10.2f\n",r.getNumber(), r.getType(), r.getPrice());
		}
	}
	/**
	 * Method to write the list of guests to a file
	 * @param guestFile file where the list of guests will be saved
	 */
	public void saveGuests(String guestFile) {
		guests.inorder(guestFile);
	}
	/**
	 * Method to write the list of rooms to a file
	 * @param roomFile file where the list of rooms will be saved
	 */
	public void saveRooms(String roomFile) {
		Iterator<Room> iterR = rooms.iterator();
		File rFile = new File(roomFile);
		try {
			PrintWriter write = new PrintWriter(rFile);
			while(iterR.hasNext()) {
				Room r = iterR.next();
				write.println(r);
			}
			write.close();
		}
		catch(FileNotFoundException e) {
			System.out.println("Cannot write to files.");
			System.exit(0);
		}
	}
	
}

