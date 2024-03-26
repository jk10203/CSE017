/***
 * Class to create the class Circle
 * @author Joy Kim
 * @version 0.1
 * Date of creation: February 11, 2023
 * Last Date Modified: February 15, 2023
 *
 * This program was made in order to make the subclass Circle which defines the characteristics a
 * circle should have which includes radius, perimeter, area, and a toString function.
 */
public class Circle extends Shape{
   
    private double radius;
    
    /***
	 * Default constructor
	 * No parameters
     * Utilizes super constructor to initialize color to string "none"
	 * Initializes radius to the double 1.0;
	 */
    public Circle(){
        super(); //constructor chaining
        //set fields of super class (shape class has color and name - field)
        radius = 1.0;
    }
    /***
	 * Constructor with two parameters
	 * @param	color for the color of what each shape will be
     * @param   radius for the radius of the circle
     */
    public Circle(String color, double radius) {
        super(color); //yellow is default and for this class, the default is circle
        this.radius = radius;
    }
    
    /***
	 * Getter for the radius of the circle
	 * @param	no parameters
	 * @return	the value of the data member radius
	 */
    //getter setter for radius values
    public double getRadius(){
        return radius;
    }

    /***
	 * Setter for the radius of the circle
	 * @param	radius to set the data member radius
	 * no return value
	 */
    public void setRadius(double radius){
        this.radius = radius;
    }
    
    //overriding / redefining values
    /***
	 * Method in order to calculate the area given that it is a circle
	 * no parameters
	 * @return area of a circle
	 */
    @Override
    public double getArea(){
        double area = (Math.PI * radius * radius);
        return area;
    }
    /***
	 * Method in order to calculate the perimeter given that it is a circle
	 * no parameters
	 * @return circumference of the circle
	 */
    @Override
    public double getPerimeter(){
        return 2 * Math.PI * radius;
    }
    /***
	 * Method to get the Circle's information
	 * @param no parameters
	 * @return formatted string containing the value of the data members
	 */
    @Override
    public String toString(){
        return String.format("%-10s\t%s\t%-30.2f\t\t%-10.2f\t%-10.2f", "Circle", super.toString(), radius, getArea(), getPerimeter());// + "\t radius: " + radius + "; Area: " + computeArea();
    }
    /***
	 * Interface to scale the sides of a circle directly
	 * @param   factor to know to what extent to scale the circle
	 * no return
	 */
    public void scale (double factor){
        radius = radius * factor;
    }
    /***
	 * Interface to make a deep copy of a certain circle
	 * no parameters
	 * no return
	 */
    public Object clone(){ //deep copy of this 
        return new Circle(getColor(), radius);
    }
}
