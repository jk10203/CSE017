/***
 * Class to model the entity Faculty
 * @author Joy Kim
 * @version 0.1
 * Date of creation: January 24, 2023
 * Last Date Modified: January 24, 2023
 *
 * This program was made in order to create the sub class Faculty that specifies a person's rank in addition to the id, position, salary
 * name, address, phone, email from the parent classes (Person AND Employee). It also includes an override method in order to format said information.
 */
public class Faculty extends Employee{
    private String rank;
    
     /***
	 * Default constructor
	 * No parameters
     * Utilizes super constructor to initialize name, address, phone, and email to the string "none",
     * id to the int 0, position to the string "none", and salary to the double 0.0
     * Initializes rank to the String "none"
	 */
    public Faculty(){ 
        super(); //constructor chaining
        rank = "none"; //setting equal to default "none"
    }
    public Faculty(String name, String address, String phone, String email, int id, String position, double salary, String rank){
        super(name, address, phone, email, id, position, salary); //super constructor that utilizes constructor chaining
        //in Employee then Person
        this.rank = rank; //need this because the parameter rank has the same name as the private data member rank
    }
    //could also be shortened to below
    /* public Faculty(String n, String a, String ph, String e, int id, String po, double s, String r){
        super(n, a, ph, e, id, po, s);
        rank = r
    }
    */ 
    public String getRank(){
        return rank;
    }
    public void setRank(String rank){
        this.rank = rank;
    }
    
    /***
	 * Method to get the Faculty information
	 * no parameters
	 * @return formatted string containing the value of the data members (including the super's toString())
	 */
    @Override
    public String toString(){
        String out; //using "out" in order to make it easier to see what is happening
        out = String.format("%s\nRank: %s", super.toString(), rank);
        return out;
    }
}