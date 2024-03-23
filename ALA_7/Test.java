/***
 * Test class for ArrayList, PriorityQueue, Stack, StringComparator
 * @author Joy Kim
 * @version 0.1
 * Date of creation: March, 2023
 * Last Date Modified: March 30, 2023
 *
 * This program was made in order to test the established properties of the ArrayList,
 * PriorityQueue, Stack, and String Comparator for generics. We read animals off a file, generate random animals
 * in order to test their various functions from each class.
 */

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Test{
    public static void main(String[] args){
        ArrayList<String> animalAL = new ArrayList<>();
        Stack<String> animalStack = new Stack<>();
        PriorityQueue<String> animalPQ = new PriorityQueue<>();
        readAnimals(animalAL, animalStack, animalPQ, "animals.txt");
        int alTotal = 0, sTotal= 0, pqTotal = 0;
        //testing contains
        System.out.println("Testing the contains() method");
        for(int i=0;i<20;i++){
            int randomIndex = (int)(Math.random() * animalAL.size());
            String randomAnimal = animalAL.get(randomIndex);
            
            animalAL.contains(randomAnimal); //measuring performance of contains so we dont care if it retunrs true false
            int alIter = ArrayList.containsIter; //static so we can access through name of the class
            alTotal += alIter;
            
            animalStack.contains(randomAnimal);
            int stackIter = ArrayList.containsIter;
            sTotal +=stackIter;
            
            animalPQ.contains(randomAnimal);
            int pqIter = ArrayList.containsIter;
            pqTotal += pqIter;
            
            //animalAl, animalStack, animalPQ are each instances to ArrayList which has static method containsIter
            System.out.printf("%-30s\t%-10d\t%-10d\t%-10d\n", randomAnimal, alIter, stackIter, pqIter);
        }
        System.out.printf("\n%-30s\t%-10d\t%-10d\t%-10d\n", "Average", alTotal/20, sTotal/20, pqTotal/20);
        //testing sorting
        System.out.println("\nContents of the three data structures");
        System.out.println("\nArrayList: " + animalAL);
        System.out.println("\n" + animalStack);
        System.out.println ("\n" + animalPQ);
        
        animalAL.sort();
        int arrSort = ArrayList.sortIter;
        animalStack.sort();
        int stackSort = ArrayList.sortIter;
        animalPQ.sort(); //need to sort
        int pqSort = ArrayList.sortIter;
        
        System.out.println("\nContents of the three data structures after sorting using the natural ordering");
        System.out.println("\nArrayList: " + animalAL);
        System.out.println("\n" + animalStack);
        System.out.println ("\n" + animalPQ);

        StringComparator sc = new StringComparator();
        animalAL.sort(sc);
        int arrSortComp = ArrayList.comparatorSortIter;
        animalStack.sort(sc);
        int stackSortComp = ArrayList.comparatorSortIter;
        animalPQ.sort(sc); //need to sort
        int pqSortComp = ArrayList.comparatorSortIter;

        System.out.println("\nContents of the three data structures after sorting using a string comparator");
        System.out.println("\nArrayList: " + animalAL);
        System.out.println("\n" + animalStack);
        System.out.println ("\n" + animalPQ);

        System.out.println("\nTesting sort() methods");
        System.out.printf("%-20s\t%-10s\t%-10s\t%-10s\n", "Sorting method", "ArrayList", "Stack", "PriorityQueue");
        System.out.printf("%-20s\t%-10d\t%-10d\t%-10d\n", "sort()", arrSort, stackSort, pqSort);
        System.out.printf("%-20s\t%-10d\t%-10d\t%-10d\n", "sort(comparator)", arrSortComp, stackSortComp, pqSortComp);
    }

    /***
	 * Method to read through a given textfile and insert into arraylist/stack/printqueue
	 * @param al for the arraylist to have info inputted into
     * @param s for the stack to have the info inputted into
     * @param pq for the printQueue to have the info inputted into
     * @param filename for the file to be read
	 * no return value
	 */
    public static void readAnimals(ArrayList<String> al, Stack<String> s, PriorityQueue<String> pq, String filename){
        File file = new File (filename);
        try{
            Scanner read = new Scanner(file);
            while (read.hasNextLine()){
                String animal = read.nextLine();
                al.add(animal);
                s.push(animal);
                pq.offer(animal);
            }
            read.close();
        } catch (FileNotFoundException e){
            System.out.print("File not found");
        }
    }
}