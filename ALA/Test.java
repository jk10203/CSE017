/***
 * Test Class for Pair, ComparatorBySecond, ComparatorByFirst
 * @author Joy Kim
 * @version 0.1
 * Date of creation: February 28, 2023
 * Last Date Modified: March 8, 2023
 *
 * This program was made in order to display and run menu operations based if the argument is trees or states. For either,
 * it can display the array, search a tree/state by name, sort based on name and capitol/height, and exits program
 * when needed. We are able to utilize generic types to make this more efficient and adaptable. 
 */
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Test{
    public static void main (String[] args){
        //String[] args --> list of parameters you are passing through main method
        //so you can pass command line arguments (pass list of states or list of trees)
        if(args.length == 0){//no arguments were passed
            System.out.println("Invalid argument. It should be states or trees.");
            System.exit(0); //ends program
        }
        if (args.length>1){
            System.out.println("Too many arguments (one argument trees or states)");
            System.exit(0);
        }
        String type = args[0]; //get the command-line argument
        if (!type.equals("trees")&& !type.equals("states")){
            System.out.println("Invalid data type. states and trees are the only types available.");
            System.exit(0);
        }

        switch(type){
            case "states":
                //default ArrayList --> defaults to 10 and grows as you run program
                //resizing during running the program is unpredictable
                stateOperations();
                break;
            case "trees":
                //readTrees(trees, "trees.txt");
                //treesOperations(trees);
                treeOperations();
                break;

        }
    }
    /***
	 * Method to search an event given the array and element you want to search
     * @param list for the array of information you want to look through
     * @param   key for the input of an element from the user to search for in an array 
	 * @return the index in which the element was found in the array
	 */
     public static <E1,E2> int search(ArrayList<Pair<E1,E2>> list, E1 key){
        for(int i=0; i<list.size();i++){
            Pair<E1,E2> pair = list.get(i); //get pair at index i
            //Pair pair --> causes one parameter to be possible
            E1 pairFirst = pair.getFirst(); // get the first element of pair
            if(pairFirst.equals(key)){
                return i; //found pair that matches search
            }
        }
        return -1;
    }
    /***
	* Method to operate states menu 
	* no parameters
	* no return value
	*/
    public static void stateOperations(){
        ArrayList<Pair<String, String>> states = new ArrayList<>(50); //create list with capacity 10
        //10, 10x1.5 = 15, 15x1.5 = 22, 22x1.5 = 33, 33x1.5 = 49, 49x1.5=73
        readStates(states, "states.txt");
        Scanner keyboard = new Scanner(System.in);
        int operation = 0;
        do{ 
            printMenu("states");
            operation = Integer.parseInt(keyboard.nextLine());
            switch(operation){
                case 1: //view
                    print(states);
                    break;
                case 2: //search
                    System.out.println("Enter a state: ");
                    String name = keyboard.nextLine();
                    int index = search(states, name);
                    if(index ==-1){
                        System.out.println("State not found");
                    } else{
                        System.out.println("State found: " + states.get(index));
                    }
                    break;
                case 3: //sort by name
                    states.sort(new ComparatorByFirst());
                    print(states);
                    break;
                case 4: //sort by capital
                    states.sort(new ComparatorBySecond());
                    print(states);
                    break;
                case 5: // exit
                    break;
                default:
                System.out.println("Invalid operation (1 to 5 only)");
            }
        }while (operation !=5);
        
    }
    /***
	 * Method to get multiple 2 String elements in an array by reading through a textfile
	 * @param   list for the states array to have info inputted based on textfile
     * @param   filename for the name of the textfile that is to be read
	 * no return value
	 */
    public static void readStates(ArrayList<Pair<String, String>> list, String filename){
        File file = new File(filename);
        try{
            Scanner read = new Scanner (file);
            while(read.hasNextLine()){
                String line = read.nextLine();
                String[] items = line.split("\\|"); // "(ab|cd)"" --> looking for ab OR cd
                String name = items[0];
                String capital = items[1];
                Pair<String, String> state = new Pair<>(name, capital); //create pair object
                list.add(state);
            }
            read.close();

        }
        catch(FileNotFoundException e){
                System.out.println("File not found.");

        }
    }
    /***
	* Method to display array (trees or states) 
	* @param   list for the array elements to be displayed
	* no return value
	*/
    public static<E> void print(ArrayList<E> list){ //FIX
        for(E element: list){
            System.out.println(element);
        }
    }
    /***
	* Method to display menu options based on trees or states
	* @param   type to distinguish which specific menu to print
	* no return value
	*/
    public static void printMenu(String type){
        System.out.println("Select an operation:");
        System.out.println("1: View the list of " + type);
        System.out.println("2: Search " + type +  " by name");
        System.out.println("3: Sort " + type + " by name");
        if(type.equals("trees") == true){
            System.out.println("4: Sort " + type + " by height");
        }
        if(type.equals("states") == true){
            System.out.println("4: Sort " + type + " by capital");
        }
        System.out.println("5: Exit");
    }
    /***
	* Method to operate trees menu 
	* no parameters
	* no return value
	*/
    public static void treeOperations(){
        ArrayList<Pair<String, Integer>> trees = new ArrayList<>(50); 
        //10, 10x1.5 = 15, 15x1.5 = 22, 22x1.5 = 33, 33x1.5 = 49, 49x1.5=73
        readTrees(trees, "trees.txt");
        Scanner keyboard = new Scanner(System.in);
        int operation = 0;
        do{ 
            printMenu("trees");
            operation = Integer.parseInt(keyboard.nextLine());
            switch(operation){
                case 1: //view
                    print(trees);
                    break;
                case 2: //search
                    System.out.println("Enter a tree: ");
                    String name = keyboard.nextLine();
                    int index = search(trees, name);
                    if(index ==-1){
                        System.out.println("Tree not found");
                    } else{
                        System.out.println("Tree found: " + trees.get(index));
                    }
                    break;
                case 3: //sort by name
                    trees.sort(new ComparatorByFirst());
                    print(trees);
                    break;
                case 4: //sort by capital
                    trees.sort(new ComparatorBySecond());
                    print(trees);
                    break;
                case 5: // exit
                    break;
                default:
                System.out.println("Invalid operation (1 to 5 only)");
            }
        }while (operation !=5);
        
    }
    /***
	 * Method to get multiple String, Integer elements in an array by reading through a textfile
	 * @param   list for the trees array to have info inputted based on textfile
     * @param   filename for the name of the textfile that is to be read
	 * no return value
	 */
    public static void readTrees(ArrayList<Pair<String, Integer>> list, String filename){
        File file = new File(filename);
        try{
            Scanner read = new Scanner (file);
            while(read.hasNextLine()){
                String line = read.nextLine();
                String[] items = line.split("\\|"); // "(ab|cd)"" --> looking for ab OR cd
                String name = items[0];
                Integer height = Integer.parseInt(items[1]);
                Pair<String, Integer> tree = new Pair<>(name, height); //create pair object
                list.add(tree);
            }
            read.close();

        }
        catch(FileNotFoundException e){
                System.out.println("File not found.");

        }
    }

}