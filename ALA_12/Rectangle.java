/***
 * Class to create the class Rectangle
 * @author Joy Kim
 * @version 0.1
 * Date of creation: February 11, 2023
 * Last Date Modified: February 15, 2023
 *
 * This program was made in order to make the subclass Rectangle which defines the characteristics a
 * rectangle should have which includes length, width, perimeter, area, and a toString function.
 */
public class Rectangle extends Shape{
   
    private double length, width;
  
    /***
	 * Default constructor
	 * No parameters
     * Utilizes super constructor to initialize color to string "none"
	 * Initializes length and width to the double 1.0;
	 */
    public Rectangle(){
        super(); //constructor chaining
        //set fields of super class (shape class has color and name - field)
        length = width = 1.0;

    }

    /***
	 * Constructor with three parameters
	 * @param	color for the color of what each shape will be
     * @param   length for the length of the rectangle
     * @param   width for the width of the rectangle
     */
    public Rectangle(String color, double length, double width) {
        super(color); //yellow is default and for this class, the default is circle
        this.length = length;
        this.width = width;

    }

    /***
	 * Getter for the length of a rectangle
	 * @param	no parameters
	 * @return	the value of the data member length
	 */
    //getter setter for radius values
    public double getLength(){
        return length;
    }
    /***
	 * Getter for the width of a rectangle
	 * @param	no parameters
	 * @return	the value of the data member width
	 */
    public double getWidth(){
        return width;
    }
    /***
	 * Setter for length of the rectangle
	 * @param	l to set the data member length
	 * no return value
	 */
    public void setLength(double l){
        length = l;
    }
    /***
	 * Setter for the width of the rectangle
	 * @param	w to set the data member width
	 * no return value
	 */
    public void setWidth(double w){
        width = w;
    }

    //overriding / redefining values
    /***
	 * Method in order to calculate the area given that it is a rectangle
	 * no parameters
	 * @return area of a rectangle
	 */
    @Override
    public double getArea(){
        double area = (length * width);
        return area;
    }
    /***
	 * Method in order to calculate the perimeter given that it is a rectangle
	 * no parameters
	 * @return perimeter of a rectangle
	 */
    @Override
    public double getPerimeter(){
        return 2 * (length + width);
    }
    
    /***
	 * Method to get the Rectangle's information
	 * @param no parameters
	 * @return formatted string containing the value of the data members
	 */
    @Override
    public String toString(){
        return String.format("%-10s\t%s\t%-15.2f\t%-15.2f\t\t%-10.2f\t%-10.2f", "Rectangle", super.toString(), length, width, getArea(), getPerimeter());// + "\t radius: " + radius + "; Area: " + computeArea();
    }
    /***
	 * Interface to scale the length and width of a Rectangle directly
	 * @param   factor to know to what extent to scale the rectangle
	 * no return
	 */
    public void scale (double factor){
        length = length * factor;
        width = width * factor;
    }
    /***
	 * Interface to make a deep copy of a certain rectangle
	 * no parameters
	 * no return
	 */
    public Object clone(){ //deep copy of this 
        return new Rectangle(getColor(), length, width);
    }
}
