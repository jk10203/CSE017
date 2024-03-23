/**
 * Class Room
 * @author Houria Oudghiri
 *
 */
public class Room {
	// enumerated type for room status
	public enum STATUS {RESERVED, FREE};
	private String number;
	private String type;
	private double price;
	private STATUS status;
	
	/**
	 * Constructor
	 * @param n  room number
	 * @param t	 room type
	 * @param p	 room price
	 * @param s	 room status
	 */
	public Room(String n, String t,
				double p, STATUS s) {
		number = n;
		type = t;
		price = p;
		status = s;
	}
	/**
	 * Accessor for the room number
	 * @return number
	 */
	public String getNumber() {
		return number;
	}
	/**
	 * Accessor for the room type
	 * @return	type
	 */
	public String getType() {
		return type;
	}	
	/**
	 * Accessor for the room price
	 * @return	price
	 */
	public double getPrice() {
		return price;
	}
	/**
	 * Accessor for the room status
	 * @return	status
	 */
	public STATUS getStatus() {
		return status;
	}
	/**
	 * Mutator for the room number
	 * @param n	room number
	 */
	public void setNumber(String n) {
		number = n;
	}
	/**
	 * Mutator for the room type
	 * @param t	room type
	 */
	public void setType(String t) {
		type = t;
	}
	/**
	 * Mutator for the room price
	 * @param p	price
	 */
	public void setPrice(double p) {
		price = p;
	}
	/**
	 * Mutator for the room status
	 * sets the status to RESERVED
	 */
	public void reserve() {
		status = STATUS.RESERVED;
	}
	/**
	 * Mutator for the room status
	 * sets the status to FREE
	 */
	public void free() {
		status = STATUS.FREE;
	}
	/**
	 * returns the attributes of a room
	 * separated by |
	 */
	public String toString() {
		String output = "";
		output += number + "|" + type + "|" + price + "|";
		if(status == STATUS.RESERVED)
			output +=  "reserved";
		else
			output +=  "free";
		return output;
	}
}

