/***
 * Class to create the AnimalList TestClass
 * @author Joy Kim
 * @version 0.1
 * Date of creation: April, 2023
 * Last Date Modified: April 9, 2023
 *
 * This program was made in order to test our modified arraylist and linkedlist class by reading in a txtfile full of 
 * animals and running a number of operations (Add, contains, remove) and displaying the number of iterations performed
 * for each. (DISCUSSION IS AT THE VERY BOTTOM)
 */
import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;
public class AnimalList{
    public static void main (String[] args){

        ArrayList<String> animalAL = new ArrayList<>();
        LinkedList<String> animalLL = new LinkedList<>();

        readAnimals(animalAL,animalLL, "animals.txt");
        testContains(animalAL, animalLL);
        testAdd(animalAL, animalLL);
        testRemove(animalAL, animalLL);
    }
    /***
	 * Method to check if 20 random animals are present and display iterations
	 * @param al for the object to be checked that it is in the ArrayList
     * @param ll for the object to be checked that it is in the LinkedList
	 * no return value
	 */
    public static void testContains(ArrayList<String> al, LinkedList<String> ll){ 
        //select 20 random and look for them in both list and display iterations
        int totalAL = 0;
        int totalLL = 0;

        System.out.println("\nTesting contains(Object)");
        System.out.printf("%-30s\t%-10s\t%-10s\n", "Animal name", "Iterations(AL)", "Iterations(LL)");

        for(int i =0; i<20; i++){
            int randomIndex = (int)(Math.random() * al.size());
            String randomAnimal = al.get(randomIndex);
            al.contains(randomAnimal);
            ll.contains(randomAnimal);
            System.out.printf("%-30s\t%-10d\t%-10d\n", randomAnimal, ArrayList.containsIter, LinkedList.containsIter);
            totalAL += ArrayList.containsIter;
            totalLL += LinkedList.containsIter;
        }
        System.out.printf("%-30s\t%-10d\t%-10d\n", "Average: ", totalAL / 20, totalLL / 20);
        System.out.println(""); //formatting

    }
    /***
	 * Method to add 20 random animals and display iterations
	 * @param al for the object to be added that is in the ArrayList
     * @param ll for the object to be added that is in the LinkedList
	 * no return value
	 */
     public static void testAdd(ArrayList<String> al, LinkedList<String> ll){ 
        //select 20 random and look for them in both list and display iterations
        int totalAL = 0;
        int totalLL = 0;
        
        System.out.println("\nTesting add(Object)");
        System.out.printf("%-30s\t%-10s\t%-10s\n", "Animal name", "Iterations(AL)", "Iterations(LL)");

        for(int i =0; i<20; i++){
            int randomIndex = (int)(Math.random() * al.size());
            String randomAnimal = al.get(randomIndex);
            randomIndex = (int)(Math.random() * al.size());
            al.add(randomIndex, randomAnimal);
            ll.add(randomIndex, randomAnimal);
            System.out.printf("%-30s\t%-10d\t%-10d\n", randomAnimal, ArrayList.addIter, LinkedList.addIter);
            totalAL += ArrayList.addIter;
            totalLL += LinkedList.addIter;
        }
        System.out.printf("%-30s\t%-10d\t%-10d\n", "Average: ", totalAL / 20, totalLL / 20);
        System.out.println(""); //formatting

    }

    /***
	 * Method to remove 20 random animals and display iterations
	 * @param al for the object to be removed that is in the ArrayList
     * @param ll for the object to be removed that is in the LinkedList
	 * no return value
	 */
    public static void testRemove(ArrayList<String> al, LinkedList<String> ll){ 
        //select 20 random and look for them in both list and display iterations
        int totalAL = 0;
        int totalLL = 0;
        
        System.out.println("\nTesting remove(Object)");
        System.out.printf("%-30s\t%-10s\t%-10s\n", "Animal name", "Iterations(AL)", "Iterations(LL)");

        for(int i =0; i<20; i++){
            int randomIndex = (int)(Math.random() * al.size());
            String randomAnimal = al.get(randomIndex);
            al.remove(randomAnimal);
            ll.remove(randomAnimal);
            System.out.printf("%-30s\t%-10d\t%-10d\n", randomAnimal, ArrayList.removeIter, LinkedList.removeIter);
            totalAL += ArrayList.removeIter;
            totalLL += LinkedList.removeIter;
        }
        System.out.printf("%-30s\t%-10d\t%-10d\n", "Average: ", totalAL / 20, totalLL / 20);
        System.out.println(""); //formatting

    }
    /***
	 * Method to read animals from a txt file
	 * @param al for the animals to be put into an ArrayList
     * @param ll for the animals to be put into a LinkedList
     * @param filename for the filename of the list of animals
	 * no return value
	 */
    public static void readAnimals(ArrayList<String> al, LinkedList<String> ll, String filename){
        File file = new File (filename);
        try{
            Scanner read = new Scanner(file);
            while (read.hasNextLine()){
                String animal = read.nextLine();
                al.add(animal);
                ll.add(animal);
            }
            read.close();
        } catch (FileNotFoundException e){
            System.out.print("File not found");
        }
    }
}

//Discuss the results you obtained and how the average number of iterations 
//compares against the time complexity analysis of the three methods.

/*FOR CONTAINS, in an ArrayList and LinkedList, they have exactly tthe same iterations because we are adding in the same way.
We are using the add method for both. The arraylist is added one after the other, and in linkedlist, the method adds last. */
/*FOR ADD, in an arraylist, it will shift all these elements down and then it will insert the new value at the new position. 
the number of iterations will be (n-index); it will move after the index. However, for the linkedlist, it will first look 
for the object and then goes to that index and it will change the links so the number of iterations is index; it will move 
all the elements before index. The sum of these iterations will be the size of the list. */
/*FOR REMOVE, in an arraylist the number of iterations is always at the max, after removing it will start to decrease by 1.
It is equal to size of the list. Since we are removing an object, we have to find it first and look for the index. This will 
take us index iterations to find it. Then we will shift it one position down which is n-index. So the total number of iterations 
is always n. But, in linkedlist, it is different, it will be half the size of the list. Once we find it, we just change the links 
so the number of iterations would be just to find it (iterations = the index).*/
/*FOR THE AVERAGE, they are all BIG O(N) which is the worst performance. The number of iterations is around the average or at its worse the 
amount of index n. THis is the case for all three methods as they are ALL BIG O(N). We are basically comparing the actual performance
time it takes (the average) and the worst case scenario which is represented by the time complexity big o (n). */
