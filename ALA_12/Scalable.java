/***
 * Class to create the interface Scalable
 * @author Joy Kim
 * @version 0.1
 * Date of creation: February 11, 2023
 * Last Date Modified: February 15, 2023
 *
 * This program was made in order to make the interface Scalable, so it could be used
 * to scale properties of different shapes by certain variable / numbers. 
 */
public interface Scalable{
    /***
	 * Interface to scale the properties of a shape directly
	 * @param   factor to know to what extent to scale the shape
	 * no return
	 */
    public void scale (double factor);
}
//cloneable and comparable already exist in java class