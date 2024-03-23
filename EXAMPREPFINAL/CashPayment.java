/**
 * Class CashPayment derived from Payment
 * @author Houria Oudghiri
 * Date of creation: May 10, 2022
 */
public class CashPayment extends Payment{
	/**
	 * Constructor
	 * @param a	amount of the payment
	 */
	public CashPayment(double a) {
		super(a);
	}
	/**
	 * returns the amount with the word cash for the type of payment
	 */
	public String toString() {
		return "cash|" + super.toString();
	}
}

