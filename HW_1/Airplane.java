/***
 * Class to create the class Airplane
 * @author Joy Kim
 * @version 0.1
 * Date of creation: January 31, 2023
 * Last Date Modified: February 9, 2023
 *
 * This program was made in order to make the Airplane class that includes methods to reserve a seat,
 * free a seat, read a map from a file, and write a map in a file. It also includes the formatting 
 * necessary to simulate an organized plane seating chart. It is also able to initialize the seatMap array. 
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.PrintWriter;
//need these three to read from file

public class Airplane{
    private char[][] seatMap; //declare array

    /***
	 * Default constructor
	 * No parameters
     * Utilizes nested for loops to initialize seatMap array for the airplane
	 * Initializes seatMap elements to '.' to mean that they are free
	 */
    public Airplane(){
        seatMap = new char[9][8]; //creates the array
        for (int i =0; i<seatMap.length;i++){ //row
            for (int j = 0; j<seatMap[i].length; j++){ //column
                seatMap[i][j] = '.'; //single quotes for CHARACTERS!!!
            }
        }
    }
     /***
	 * Constructor with one parameter
	 * @param	filename for the filename to be read and a seatMap to be created
	 */
    public Airplane(String filename){
        seatMap = new char[9][8];
        readMap(filename); //if file not found, '.'
    }
    /***
	 * Method to read a file given its name and contents and adjust seatMap array accordingly
	 * @param filename for a filename to be inputted and read and adjust seatMap array accordingly
	 * no return value
	 */
    private void readMap(String filename){//only inside class only in this constructor
        //import the java.io/util (above)
        //create a file object
        File file = new File(filename);
        try{
            //create Scanner object
            Scanner readFile = new Scanner(file); //throws CHECKED (file not found)exception
            //while(readFile.hasNext()){ --> dont need bc you know exactly how much
            for (int i =0; i<seatMap.length;i++){
                for (int j = 0; j<seatMap[i].length; j++){
                    seatMap[i][j] = readFile.next().charAt(0); //read one char
                    //read as string and extract one character
                }
            }
            readFile.close();
        }
        catch(FileNotFoundException e){
            //if file not found '.' used instead
            for (int i =0; i<seatMap.length;i++){ //rows
                for (int j = 0; j<seatMap[i].length; j++){ //columns
                    seatMap[i][j] = '.'; //single quotes for CHARACTERS!!!
                }
            }
        }
    }
    /***
	 * Method to check the input from the user about the expected format for a seat number
	 * @param seatNumber a string that holds a seat number (number then letter within the bounds of the seatmap)
	 * @return a true or false based on if userinput matches expected input format
	 */
    private boolean checkSeatNumber(String seatNumber) throws InvalidSeatException{
        if(seatNumber.matches("[1-9][A-H]")){
        //can also do if (!seatNumber.matches("[1-9][A-H]")){}
             return true;
        }
       throw new InvalidSeatException("Invalid seat number (row[1-9]column[A-H]). Please try again.");
    }
    /***
	 * Method to reserve a seat using user input
	 * @param seatNumber a string that holds a seat number (number then letter within the bounds of the seatmap)
	 * @return a true or false based on if userinput of a seat number is already reserved or not (if not, it returns true and reserves)
	 */
    public boolean reserveSeat(String seatNumber) throws InvalidSeatException{
        if(checkSeatNumber(seatNumber)){ //valid seat number
         //seatNumber = "9G"
            int row = seatNumber.charAt(0) - '1'; //'9' 
            int col = seatNumber.charAt(1) - 'A'; //'G'
            //A is 41 ascii (6 is range A-G)
            //(8 is range 1-9)
            if(seatMap[row][col] == '.'){ //free
                seatMap[row][col] = 'X';
                return true;
            } else { //already reserved
                return false;
            }
        }
        return false; //quiet the compiler
    }
    /***
	 * Method to free a seat from a reservation using user input
	 * @param seatNumber a string that holds a seat number (number then letter within the bounds of the seatmap)
	 * @return a true or false based on if userinput of a seat number is already reserved or not (if yes, it returns true and frees the seat)
	 */
    public boolean freeSeat(String seatNumber) throws InvalidSeatException{
        //opposite of what reserve seat does
        if(checkSeatNumber(seatNumber)){ //valid seat number
         //seatNumber = "9G"
            int row = seatNumber.charAt(0) - '1'; //'9' 
            int col = seatNumber.charAt(1) - 'A'; //'G'
            //A is 41 ascii (6 is range A-G)
            //(8 is range 1-9)
            if(seatMap[row][col] == 'X'){ //already reserved
                seatMap[row][col] = '.';
                return true;
            } else { //free
                return false; 
            }
        }
        return false;
    }
    /***
	 * Method to write a file given a already initialized seat map array 
	 * @param filename for a filename to be inputted and read and adjust seatMap array accordingly
	 * no return value
	 */
    public void saveMap(String filename){
        //open file for writing
        File file = new File(filename);
        try{
            PrintWriter writeFile = new PrintWriter(file);
            for (int i =0; i<seatMap.length;i++){ //row
                for (int j = 0; j<seatMap[i].length; j++){ //column
                    writeFile.print(seatMap[i][j] + " "); //single quotes for CHARACTERS!!!
                }
                writeFile.println(); //prints new line after one row
            }
            writeFile.close();
        }
        catch(FileNotFoundException e){
            System.out.println("Cannot write to file " + filename);
        }
    }
    /***
	 * Method to get the seatMap array information in a formatted way
	 * @param no parameters
	 * @return formatted string containing the value of the seatMap array
	 */
    public String toString(){
        String out = "\tA\tB\tC\tD\tE\tF\tG\tH\n";
        for (int i =0; i<seatMap.length;i++){ //row
            out +=(i+1)+"\t";
            for (int j = 0; j<seatMap[i].length; j++){ //column
                out += seatMap[i][j] + "\t";
            }
                out += "\n";
        }
        return out;
    }
}