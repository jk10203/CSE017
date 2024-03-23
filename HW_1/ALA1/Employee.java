/***
 * Class to model the entity Employee
 * @author Joy Kim
 * @version 0.1
 * Date of creation: January 24, 2023
 * Last Date Modified: January 24, 2023
 *
 * This program was made in order to create the child class Employee that specifies a person's id, position, and salary in addition to
 * the name, address, phone, email from the parent class (Person). It also includes an override method in order to format said information.
 */
public class Employee extends Person{
    private int id;
    private String position;
    private double salary;

    /***
	 * Default constructor
	 * No parameters
     * Utilizes super constructor to initialize name, address, phone, and email to the string "none"
	 * Initializes id to the int 0, position to the string "none", and salary to the double 0.0
	 */
    public Employee(){ 
        super(); //from super class Person
        id = 0;
        position = "none";
        salary = 0.0;
    }
    /***
	 * Constructor with seven parameters
     * @param	name for the name of a person
	 * @param	address for the address of a person
	 * @param	phone for the phone number of a person
	 * @param	email for the email address of a person
	 * @param	id for the id of a person
	 * @param	position for the position a person has
	 * @param	salary for the amount of salary a person has
	 */
    public Employee(String name, String address, String phone, String email, int id, String position, double salary){
        super(name, address, phone, email); //from super class Person
        this.id = id; //need this because the parameter id has the same name as the private data member id
        this.position = position; //etc
        this.salary = salary; //etc
    }
    /***
	 * Getter for the id of a person
	 * @param	no parameters
	 * @return	the value of the data member id
	 */
    public int getId(){
        return id;
    }
    /***
	 * Getter for the position a person has
	 * @param	no parameters
	 * @return	the value of the data member position
	 */
    public String getPosition(){
        return position;
    }
    /***
	 * Getter for the salary a person has
	 * @param	no parameters
	 * @return	the value of the data member salary
	 */
    public double getSalary(){
        return salary;
    }

    /***
	 * Setter for the ID of a person
	 * @param	id to set the data member id
	 * no return value
	 */
    public void setId(int id){
        this.id = id;
    }
    /***
	 * Setter for the position of a person
	 * @param	position to set the data member position
	 * no return value
	 */
    public void setPosition(String position){
        this.position = position;
    }
    /***
	 * Setter for the salary of a person
	 * @param	salary to set the data member salary
	 * no return value
	 */
    public void setSalary(double salary){
        this.salary = salary;
    }

    /***
	 * Method to get the Employee information
	 * no parameters
	 * @return formatted string containing the value of the data members (including the super's toString())
	 */
    @Override
    public String toString(){
        String out; //using "out" in order to make this process easier to see
        out = String.format("%s\nID: %s\nPosition: %s\nSalary: $%.2f", super.toString(), id, position, salary);
        return out;
    }
}