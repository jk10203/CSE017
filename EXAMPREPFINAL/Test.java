import java.util.Scanner;
/**
 * Test Class for the hotel hierarchy of classes
 * @author Houria Oudghiri
 *
 */
public class Test {
	/**
	 * Main method
	 */
	public static void main(String[] args) {
		HotelManager hotel  = new HotelManager("guests.txt",
											   "staff.txt",
											   "rooms.txt");
		Scanner keyboard = new Scanner(System.in);
		if(staffLogin(keyboard, hotel)) {
			staffOperations(keyboard, hotel);
		}
		else {
			// customer
			guestOperations(keyboard, hotel);
		}
	}
	/**
	 * Method for staff member operations
	 * @param keyboard Scanner object for input
	 * @param hotel HotelManager object
	 */
	public static void staffOperations(Scanner keyboard, HotelManager hotel) {
		int operation;
		boolean modified = false;
		do {
			printStaffMenu();
			operation = keyboard.nextInt();
			switch(operation) {
				case 1: // check out room
					System.out.println("Enter the guest full name: ");
					String name = keyboard.next() + " " + keyboard.next();
					Guest c = hotel.findGuest(name);
					if(c == null) {
						System.out.println("No guest with name " + name + " found.");
					}
					else {
						System.out.println("Enter the room number: ");
						String number = keyboard.next();
						if(c.checkOut(number)) {
							Room r = hotel.findRoom(number);
							r.free();
							modified = true;
						}
						else {
							System.out.println("Checkout failed for Room #" + number);
						}
					}
					
					break;
				case 2: // print customer invoice
					System.out.println("Enter the customer name: ");
					name = keyboard.next() + " " + keyboard.next();
					Guest g = hotel.findGuest(name);
					if(g != null) {
						hotel.printGuestInvoice(g);
					}
					else {
						System.out.println("Customer not found.");
					}
					break;
				case 3: // update rooms.txt and customers.txt
					if(modified) {
						hotel.saveGuests("guests.txt");
						hotel.saveRooms("rooms.txt");
					}
					break;
				default:
					System.out.println("Invalid operation (1 to 3)");
			}
			
		} while (operation != 3);
	}
	/**
	 * Method for guest operations
	 * @param keyboard Scanner object for input
	 * @param hotel HotelManager object
	 */
	public static void guestOperations(Scanner keyboard, HotelManager hotel) {
		boolean modified = false;
		int operation;
		do {
			printGuestMenu();
			operation = keyboard.nextInt();
			switch(operation) {
				case 1: // view available rooms by price
					hotel.printFreeRooms();
					break;
				case 2: // check in room
					System.out.println("Enter the room number: ");
					String number = keyboard.next();
					Room r = hotel.findRoom(number);
					if(r!=null && r.getStatus() == Room.STATUS.FREE) {	
						System.out.println("Enter the number of nights: ");
						int nights = keyboard.nextInt();
						System.out.println("Enter your full name: ");
						String name = keyboard.next() + " " + keyboard.next();
						Guest g = hotel.findGuest(name);
						if(g == null) {
							System.out.println("Enter your phone number: ");
							String phone = keyboard.next();
							System.out.println("Enter your email: ");
							String email = keyboard.next();
							while(true) {
								try {
									System.out.println("Enter your email: ");
									email = keyboard.next();
									checkEmail(email);
									break;
								}
								catch(Exception e) {
									System.out.println(e.getMessage());
									System.out.println("Try again");
									continue;
								}
							}
							System.out.println("Enter the type of payment (cash/credit): ");
							String type = keyboard.next();
							Payment p=null;
							if(type.equals("credit")) {
								System.out.println("Enter the type of credit card (Visa/Master): ");
								String cct = keyboard.next();
								System.out.println("Enter the credit card number: ");
								String ccn = keyboard.next();
								System.out.println("Enter the credit card expiry date (MM/DD/YYYY): ");
								String ed = keyboard.next();
								p = new CreditCardPayment(0, ccn, cct, ed);
									
							}
							else {
								p = new CashPayment(0);
							}
							g = new Guest(name, phone, email);
							g.setPayment(p);
							hotel.addGuest(g);
						}
						modified = true;
						if(g.checkIn(number)) {
							Payment p = g.getPayment();
							p.setPayment(p.getPayment() + r.getPrice() * nights);
							r.reserve();
						}
						else {
							System.out.println("Check In failed for Room #" + number);
						}
					}
					else {
						System.out.println("Room not available.");
					}
					break;
				case 3: // view invoice
					System.out.println("Enter your full name: ");
					String name = keyboard.next() + " " + keyboard.next();
					Guest g = hotel.findGuest(name);
					if(g == null) {
						System.out.println("Guest with name " + name + " not found.");
					}
					else {
						hotel.printGuestInvoice(g);
					}
					break;
				case 4: // update rooms.txt and guests.txt
					if(modified) {
						hotel.saveGuests("guests.txt");
						hotel.saveRooms("rooms.txt");
					}
					break;
				default:
					System.out.println("Invalid operation (1 to 4).");
			}
				
		} while (operation != 4);
	}
	/**
	 * Method to check if the email address is valid
	 * @param email	email address
	 * @return	true if the email address is valid
	 * @throws Exception if the email address is not valid
	 */
	public static boolean checkEmail(String email) throws Exception {
		if (email.matches("([a-zA-Z0-9_\\-\\.]+)@([a-zA-Z0-9_\\-\\.]+)\\.(com|org|edu)")) {
            return true;
        }
        else {
            throw new Exception("Invalid email address");
        }
	}
	/**
	 * prints the menu for the staff member operations
	 */
	public static void printStaffMenu() {
		System.out.println("\nSelect an operation: ");
		System.out.println("1: Check out a room");
		System.out.println("2: Print customer invoice");
		System.out.println("3: Quit");
		
	}
	/**
	 * prints the menu for the guest operations
	 */
	public static void printGuestMenu() {
		System.out.println("\nSelect an operation: ");
		System.out.println("1: View free rooms");
		System.out.println("2: Check in a room");
		System.out.println("3: Print invoice");
		System.out.println("4: Quit");
		
	}
	public static boolean staffLogin(Scanner sc, HotelManager h) {
		System.out.println("\nEnter username or guest:");
		String username = sc.next();
		if(username.equals("guest")) {
			return false;
		}
		else {
			System.out.println("enter password:");
			String passwd = sc.next();
			if(h.findStaff(username, passwd)) {
				return true;
			}
			else {
				int index = 0;
				while(index<2 && !h.findStaff(username, passwd)) {
					System.out.println("Login Failed. Try again.");
					System.out.println("enter username:");
					username = sc.next();
					System.out.println("enter password:");
					passwd = sc.next();
					index++;
				}
				if(!h.findStaff(username,  passwd)) {
					System.out.println("Login Failed. Three attempts only.");
					System.exit(0);
				}
				return true;
			}
			
		}
	}
}
