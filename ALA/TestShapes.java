/***
 * Class to create the test class TestShapes
 * @author Joy Kim
 * @version 0.1
 * Date of creation: February 11, 2023
 * Last Date Modified: February 15, 2023
 *
 * This program is intended to test the classes that area made from the abstract class shape and its sub classes.
 * It will 
 */
public class TestShapes {
    //abstract  - no objects from abstract class --> use other classes to define characteristics from abstract
    public static void main(String[]args){
        Shape[] shapes = new Shape[8]; //creating shapes array
        
        shapes[0] = new Circle("Black", 2.5);
        shapes[1] = new Triangle("Green", 6.0, 6.0, 6.0);
        shapes[2] = new Rectangle("Red", 5.0, 3.0);
        shapes[3] = new Pentagon ("Yellow", 7.0);
        //cloning
        shapes[4] = (Shape) shapes[0].clone(); //clone is type object so DOWNCASTING by using (Shape)
        shapes[5] = (Shape) shapes[1].clone(); 
        shapes[6] = (Shape) shapes[2].clone(); 
        shapes[7] = (Shape) shapes[3].clone(); 

        shapes[4].scale(2.0); //scaling to factor of 2.0
        shapes[5].setColor("Orange"); //set color is inherited and shared between all classes
        ((Rectangle)shapes[6]).setLength(10.0); //setLength for class rectangle but we are in shape --> DOWNCAST to rectangle
        ((Pentagon)shapes[7]).setSide(4.0); //setSide for class pentagon but we are in shape --> DOWNCAST to pentagon

        System.out.println("\nOriginal List: ");
        System.out.println(String.format("%-10s\t%-10s\t%-30s\t\t%-10s\t%-10s", "Shape", "Color", "Dimensions", "Area", "Perimeter"));
        printArray(shapes);
       
        System.out.println("\nSorted List: ");
        System.out.println(String.format("%-10s\t%-10s\t%-30s\t\t%-10s\t%-10s", "Shape", "Color", "Dimensions", "Area", "Perimeter"));
        java.util.Arrays.sort(shapes);
        printArray(shapes);
        
        System.out.printf("\nAverage Perimeter = %.2f\n", getAvgPerimeter(shapes));
        System.out.println("");
    
    }
    /***
	* Method to display list of shapes initialized above
	* @param   list for the shape array above to be inputted and printed
	* no return value
	*/
    public static void printArray(Shape[] list){
        for(Shape s: list){
            System.out.println(s);
        }
    }
    /***
	 * Method to get the average perimeter of all the shapes in an array
	 * @param   list for the shapes array above to be used to find and add all the perimeters
	 * @return the value for the average perimeter
	 */
    public static double getAvgPerimeter(Shape[] list){
        double p = 0;
        for(Shape s: list){
            p += s.getPerimeter();
        }
        return p / list.length;
    }
}