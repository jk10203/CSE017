import java.util.ArrayList;
/**
 * Class Guest derived from Person
 * @author Houria Oudghiri
 * Date of creation: May 10, 2022
 */
public class Guest extends Person{
	private Payment payment;
	ArrayList<String> rooms;
	/**
	 * Constructor
	 * creates an empty arraylist for the rooms
	 * @param n	name
	 * @param p	phone
	 * @param e	email address
	 */
	public Guest(String n, String p,
					String e) {
		super(n, p, e);
		rooms = new ArrayList<>();
		payment = null;
	}
	/**
	 * Mutator for the attribute payment
	 * @param p payment object
	 */
	public void setPayment(Payment p) {
		payment = p;
	}
	/**
	 * Accessor for payment
	 * @return payment
	 */
	public Payment getPayment() {
		return payment;
	}
	/**
	 * Accessor for rooms
	 * @return the list of rooms of the guest
	 */
	public ArrayList<String> getRooms(){
		return rooms;
	}
	/**
	 * CheckIn method
	 * @param roomNumber
	 * @return true if the room was added successfully
	 *         false if the room is already checked in
	 */
	
	public boolean checkIn(String roomNumber) {
		if(!rooms.isEmpty()) {
			if(rooms.contains(roomNumber)) {
				return false;
			}
		}
		rooms.add(roomNumber);
		return true;
	}
	/**
	 * CheckOut method
	 * @param roomNumber
	 * @return true if the room number is found and removed
	 *         false if the room number is not found in the list
	 */
	public boolean checkOut(String roomNumber) {
		if(!rooms.isEmpty()) {
			if(rooms.contains(roomNumber)) {
				rooms.remove(roomNumber);
				return true;
			}
		}
		return false;
	}
	/**
	 * returns the attributes of the guest
	 * (name, phone, email address, payment, list of rooms)
	 *  separated by |
	 */
	public String toString() {
		String out = super.toString() + "|";
		out += payment.toString() + "|" ;
		if(rooms.size() > 0) {
			for(int i=0; i<rooms.size()-1; i++) {
				out += rooms.get(i) + "|";
			}
			out += rooms.get(rooms.size()-1);
		}
		return out;
	}
	
}

