/***
 * Class to create the class Shape
 * @author Joy Kim
 * @version 0.1
 * Date of creation: February 11, 2023
 * Last Date Modified: February 15, 2023
 *
 * This program was made in order to create the abstract class shape in order to set up a
 * list of characteristics that its subclasses should have which includes color, area, 
 * perimeter, and a toString function.
 */
public abstract class Shape implements Comparable<Shape>, Cloneable, Scalable{
    private String color;

    /***
	 * Default constructor
	 * No parameters
     * Intitializes color to the string "none"
	 */
    protected Shape(){ //only available to related classes
        color = "none";
    }
    
    /***
	 * Constructor with one parameter
	 * @param	color for the color of what each shape will be
     */
    protected Shape (String color){
        this.color = color;
    }
    
    /***
	 * Getter for the color of the shape
	 * @param	no parameters
	 * @return	the value of the data member color
	 */
    public String getColor(){
        return color;
    }
    
    /***
	 * Setter for the color of the shape
	 * @param	color to set the data member color
	 * no return value
	 */
    public void setColor(String color){
        this.color = color;
    }
    
    /***
	 * Method to get the Shape's information
	 * @param no parameters
	 * @return formatted string containing the value of the data members
	 */
    public String toString(){
        return String.format("%-10s", color); //complete with spaces on left of color
        //%10s --> "Red       " but - is left
    }
    /***
	 * Abstract method in order to calculate the area given the shape type
	 * no parameters
	 * no return value
	 */
    public abstract double getArea(); //definition provided in derived classdes
    /***
	 * Abstract method in order to calculate the perimeter given the shape type
	 * no parameters
	 * no return value
	 */
    public abstract double getPerimeter();

    /***
	 * Method to compare the area of 2 shapes
	 * @param no parameters
	 * @return a value based on if the are of the first shape equals, bigger than, 
     * or smaller than the second shape
	 */
    public int compareTo(Shape shape){
        if (this.getArea() == shape.getArea()){
            return 0; //if equal
        } else if (this.getArea() > shape.getArea()){
            return 1;
        } else {
            return -1;
        }
    }
    /***
	 * Abstract interface to make a deep copy of a certain pentagon
	 * no parameters
	 * no return
	 */
    public abstract Object clone();
    
    /***
	 * Abstract interface to scale the properties of a shape directly
	 * @param   factor to know to what extent to scale the shape
	 * no return
	 */
    public abstract void scale(double factor);

}
