/**
 * Abstract Class Payment
 * @author Houria Oudghiri
 * Date of creation: May 10, 2022
 */
public abstract class Payment {
	private double amount;
	/**
	 * Constructor
	 * @param a	amount of the payment
	 */
	protected Payment(double a) {
		amount = a;
	}
	/**
	 * Accessor for the amount
	 * @return amount
	 */
	public double getPayment() {
		return amount;
	}
	/**
	 * Mutator for the amount
	 * @param a	amount
	 */
	public void setPayment(double a) {
		amount = a;
	}
	/**
	 * returns the amount as a string
	 */
	public String toString() {
		return "" + amount;
	}
}

