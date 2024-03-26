/***
 * Class to create the class Triangle
 * @author Joy Kim
 * @version 0.1
 * Date of creation: February 11, 2023
 * Last Date Modified: February 15, 2023
 *
 * This program was made in order to make the subclass Triangle which defines the characteristics a
 * triangle should have which includes side1, side2, side3, perimeter, area, and a toString function.
 */
public class Triangle extends Shape{
   
    private double side1, side2, side3;
  
    /***
	 * Default constructor
	 * No parameters
     * Utilizes super constructor to initialize color to string "none"
	 * Initializes side1, side2, and side3 to the double 1.0;
	 */
    public Triangle(){
        super(); //constructor chaining
        //set fields of super class (shape class has color and name - field)
        side1 = side2 = side3 = 1.0;
    }

    /***
	 * Constructor with four parameters
	 * @param	color for the color of what each shape will be
     * @param   s1 for the value of the side1 of the triangle
     * @param   s2 for the value of the side2 of the triangle
     * @param   s3 for the value of the side3 of the triangle
     */
    public Triangle(String color, double s1, double s2, double s3) {
        super(color); //yellow is default and for this class, the default is circle
        side1 = s1;
        side2 = s2;
        side3 = s3;
    }
    
    /***
	 * Getter for the side1 of the triangle
	 * @param	no parameters
	 * @return	the value of the data member side1
	 */
    //getter setter for radius values
    public double getSide1(){
        return side1;
    }
    /***
	 * Getter for the side2 of the triangle
	 * @param	no parameters
	 * @return	the value of the data member side2
	 */
    public double getSide2(){
        return side2;
    }
    /***
	 * Getter for the side3 of the triangle
	 * @param	no parameters
	 * @return	the value of the data member side3
	 */
    public double getSide3(){
        return side3;
    }
    /***
	 * Setter for first side of the triangle
	 * @param	side1 to set the data member side1
	 * no return value
	 */
    public void setSide1(double s){
        side1 = s;
    }
    /***
	 * Setter for second side of the triangle
	 * @param	side2 to set the data member side2
	 * no return value
	 */
    public void setSide2(double s){
        side2 = s;
    }
    /***
	 * Setter for third side of the triangle
	 * @param	side3 to set the data member side3
	 * no return value
	 */
    public void setSide3(double s){
        side3 = s;
    }

    //overriding / redefining values
    /***
	 * Method in order to calculate the area given that it is a triangle
	 * no parameters
	 * @return area of a triangle
	 */
    @Override
    public double getArea(){
        double p = (side1 + side2 + side3) / 2;
        return Math.sqrt(p * (p-side1) * (p-side2) * (p-side3));
    }
    /***
	 * Method in order to calculate the perimeter given that it is a triangle
	 * no parameters
	 * @return perimeter of a triangle
	 */
    @Override
    public double getPerimeter(){
        return (side1 + side2 + side3);
    }

    /***
	 * Method to get the Triangle's information
	 * @param no parameters
	 * @return formatted string containing the value of the data members
	 */
    @Override
    public String toString(){
        return String.format("%-10s\t%s\t%-10.2f\t%-10.2f\t%-8.2f%-10.2f\t%-10.2f", "Triangle", super.toString(), side1, side2, side3, getArea(), getPerimeter());// + "\t radius: " + radius + "; Area: " + computeArea();
    }
    /***
	 * Interface to scale the sides of a triangle directly
	 * @param   factor to know to what extent to scale the triangle
	 * no return
	 */
    public void scale (double factor){
        side1 = side1 * factor;
        side2 = side2 * factor;
        side3 = side3 * factor;
    }
    /***
	 * Interface to make a deep copy of a certain triangle
	 * no parameters
	 * no return
	 */
    public Object clone(){ //deep copy of this 
        return new Triangle(getColor(), side1, side2, side3);
    }
}
