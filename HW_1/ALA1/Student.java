/***
 * Class to model the entity Student
 * @author Joy Kim
 * @version 0.1
 * Date of creation: January 24, 2023
 * Last Date Modified: January 24, 2023
 *
 * This program was made in order to create the child class Student that specifies a person's id and major in addition to
 * the name, address, phone, email from the parent class (Person). It also includes an override method in order to format said information.
 */
 
public class Student extends Person{
    private int id;
    private String major;

    /***
	 * Default constructor
	 * No parameters
     * Utilizes super constructor to initialize name, address, phone, and email to the string "none"
	 * Initializes id to the int 0 and major to the string "none"
	 */
    public Student(){ 
        super(); //referring to super class' default constructor (Person)
        id = 0; //setting id equal to 0 (an int)
        major = "none"; //setting major equal to "none" for the default
    }
    /***
	 * Constructor with six parameters
     * @param	name for the name of a person
	 * @param	address for the address of a person
	 * @param	phone for the phone number of a person
	 * @param	email for the email address of a person
	 * @param	id for the id of a person
	 * @param	major for the major of a person
	 */
    public Student(String name, String address, String phone, String email, int id, String major){
        super(name, address, phone, email); //from super class Person
        this.id = id; //need this because the parameter id has the same name as the private data member id
        this.major = major;//need this because the parameter major has the same name as the private data member major
    }
    /***
	 * Getter for the id of a person
	 * @param	no parameters
	 * @return	the value of the data member id
	 */
    public int getID(){
        return id;
    }
    /***
	 * Getter for the major a person has
	 * @param	no parameters
	 * @return	the value of the data member major
	 */
    public String getMajor(){
        return major;
    }
    /***
	 * Setter for the ID of a person
	 * @param	id to set the data member id
	 * no return value
	 */
    public void setID(int id){
        this.id = id;
    }
    /***
	 * Setter for the major of a person
	 * @param	major to set the data member major
	 * no return value
	 */
    public void setMajor(String major){
        this.major = major;
    }

    /***
	 * Method to get the Student information
	 * no parameters
	 * @return formatted string containing the value of the data members (including the super's toString())
	 */
    @Override
    public String toString(){
        String out; //using out in order to make it easier to see
        out = String.format("%s\nID: %s\nMajor: %s", super.toString(), id, major);
        return out;
    }
}