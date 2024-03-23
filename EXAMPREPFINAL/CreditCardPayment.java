/**
 * Class CreditCardPayment derived from Payment
 * @author Houria Oudghiri
 * Date of creation: May 10, 2022
 */
public class CreditCardPayment extends Payment{
	String creditCard;
	String number;
	String expiryDate;
	/**
	 * COnstructor
	 * @param a	amount of the payment
	 * @param ccn		credit card number
	 * @param cct	 	credit card type
	 * @param exDate	credit card expiry date
	 */
	public CreditCardPayment(double a, String ccn, 
						     String cct, String exDate) {
		super(a);
		creditCard = cct;
		number = ccn;
		expiryDate = exDate;
	}
	/**
	 * Accessor for the credit card type
	 * @return	credit card type
	 */
	public String getCreditCard() {
		return creditCard;
	}
	/**
	 * Accessor for the credit card number
	 * @return	credit card number
	 */
	public String getNumber() {
		return number;
	}
	/**
	 * Accessor for the credit card expiry date
	 * @return credit card expiry date
	 */
	public String getExpiryDate() {
		return expiryDate;
	}
	/**
	 * Mutator for the credit card type
	 * @param cc	credit card type
	 */
	public void setCreditCard(String cc) {
		creditCard = cc;
	}
	/**
	 * Mutator for the credit card number
	 * @param n	credit card number
	 */
	public void setNumber(String n) {
		number = n;
	}
	/**
	 * Mutator for the credit card expiry date
	 * @param d	credit card expiry date
	 */
	public void setExpiryDate(String d) {
		expiryDate = d;
	}
	/**
	 * returns the attributes of the credit card
	 * separated by |
	 */
	public String toString() {
		String output = "credit|" + super.toString() + "|" + number + "|" + creditCard + "|" + expiryDate;
		return output;
	}
}

