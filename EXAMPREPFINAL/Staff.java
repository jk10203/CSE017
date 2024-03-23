/**
 * Class Staff derived from Person
 * @author Houria Oudghiri
 * Date of creation: May 10, 2022
 */
public class Staff extends Person{
	private String username;
	private String password;
	/**
	 * Constructor
	 * @param n	name
	 * @param p	phone number
	 * @param e	email address
	 * @param u	username
	 * @param pass	password
	 */
	public Staff(String n, String p,
				 String e, String u, String pass) {
		super(n, p, e);
		username = u;
		password = pass;
	}
	/**
	 * Accessor for the username
	 * @return username
	 */
	public String getUsername() {
		return username;
	}
	/**
	 * Accessor for the password
	 * @return password
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * Mutator for the username
	 * @param u	username
	 */
	public void setUsername(String u) {
		username = u;
	}
	/**
	 * Mutator for the password
	 * @param p	password
	 */
	public void setPassword(String p) {
		password = p;
	}
	/**
	 * returns the attributes of a staff separated by | 
	 */
	public String toString() {
		String out = super.toString() + "|" + username + "|" + password;
		return out;
	}
	/**
	 * returns true if two Staff objects have the same 
	 * username and password
	 */
	public boolean equals(Object o) {
		if(o instanceof Staff) {
			Staff s = (Staff) o;
			return username.equals(s.username) &&
				   password.equals(s.password);
		}
		return false;
	}
}

