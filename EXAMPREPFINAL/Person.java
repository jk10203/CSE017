/**
 * Abstract class Person
 * @author Houria Oudghiri
 * Date of creation: May 10, 2022
 */
public abstract class Person {
	private String name, phone, email;
	/**
	 * Constructor
	 * @param n	name of the person
	 * @param p	phone number
	 * @param e	email address
	 */
	protected Person(String n, String p, String e) {
		name =n;
		phone = p;
		email = e;
	}
	/**
	 * Accessor for the name attribute 
	 * @return	name
	 */
	public String getName() {
		return name;
	}
	/**
	 * Accessor for the phone number attribute
	 * @return	phone number
	 */
	public String getPhone() {
		return phone;
	}
	/**
	 * Accessor for the email address attribute
	 * @return	email address
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * Mutator for name
	 * @param n	name
	 */
	public void setName(String n) {
		name = n;
	}
	/**
	 * Mutator for phone
	 * @param p	phone number
	 */
	public void setPhone(String p) {
		phone = p;
	}
	/**
	 * Mutator for email
	 * @param e	email address
	 */
	public void setEmail(String e) {
		email = e;
	}
	/**
	 * returns the attributes name, phone, and email separated by |
	 */
	public String toString() {
		String output = "";
		output += name + "|" + phone + "|" + email;
		return output;
	}
}

