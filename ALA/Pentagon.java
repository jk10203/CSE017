/***
 * Class to create the class Pentagon
 * @author Joy Kim
 * @version 0.1
 * Date of creation: February 11, 2023
 * Last Date Modified: February 15, 2023
 *
 * This program was made in order to make the subclass Pentagon which defines the characteristics a
 * pentagon should have which includes a side (5 of them but same value), perimeter, area, and a toString function.
 */
public class Pentagon extends Shape{
   
    private double side;
    /***
	 * Default constructor
	 * No parameters
     * Utilizes super constructor to initialize color to string "none"
	 * Initializes side to the double 1.0
	 */
    public Pentagon(){
        super(); //constructor chaining
        //set fields of super class (shape class has color and name - field)
        side = 1.0;

    }
    /***
	 * Constructor with two parameters
	 * @param	color for the color of what each shape will be
     * @param   side for the value of the side of the pentagon
     */
    public Pentagon(String color, double side) {
        super(color); //yellow is default and for this class, the default is circle
        this.side = side;
    }
    /***
	 * Getter for the side of the pentagon
	 * @param	no parameters
	 * @return	the value of the data member radius
	 */
    //getter setter for radius values
    public double getSide(){
        return side;
    }
    /***
	 * Setter for the side of the pentagon
	 * @param	s to set the data member side
	 * no return value
	 */
    public void setSide(double s){
        side = s;
    }

    //overriding / redefining values
    /***
	 * Method in order to calculate the area given that it is a pentagon
	 * no parameters
	 * @return area of a pentagon
	 */
    @Override
    public double getArea(){
        double a = (0.25) * (Math.sqrt(5*(5 + 2 * Math.sqrt(5))));
        a *= side * side;
        return a;
    }
    /***
	 * Method in order to calculate the perimeter given that it is a pentagon
	 * no parameters
	 * @return area of a pentagon
	 */
    @Override
    public double getPerimeter(){
        return (side * 5);
    }
    
    /***
	 * Method to get the Pentagon's information
	 * @param no parameters
	 * @return formatted string containing the value of the data members
	 */
    @Override
    public String toString(){
        return String.format("%-10s\t%s\t%-30.2f\t\t%-10.2f\t%-10.2f", super.toString(), "Pentagon", side, getArea(), getPerimeter());// + "\t radius: " + radius + "; Area: " + computeArea();
    }
    /***
	 * Interface to scale the sides of a pentagon directly
	 * @param   factor to know to what extent to scale the pentagon
	 * no return
	 */
    public void scale (double factor){
        side = side * factor;
    }
    /***
	 * Interface to make a deep copy of a certain pentagon
	 * no parameters
	 * no return
	 */
    public Object clone(){ //deep copy of this 
        return new Pentagon(getColor(), side);
    }
}
