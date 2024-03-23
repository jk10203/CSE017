/***
 * Class to test BST and Heap
 * @author Joy Kim
 * @version 0.1
 * Date of creation: April, 2023
 * Last Date Modified: April 24, 2023
 *
 * This program was made in order to compare the number of iterations for various 
 * methods in Heap and BST which includes add(), contains(), and remove() on a list of animals. 
 * Their respective height and whether they are balanced before and after sorting is also compared.
 */
import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Test{
    public static void main(String[] args){
        BST<String> animalBST = new BST<>();
        Heap<String> animalHeap = new Heap<>();
        ArrayList<String> animalAL = new ArrayList<>();

        System.out.println("Testing add()");
        readAnimals(animalBST, animalHeap, animalAL, "animals.txt");
        System.out.println("\nTesting contains()");
        testContains(animalBST,animalHeap, animalAL);
        System.out.println("\nTesting remove()");
        testRemove(animalBST, animalHeap, animalAL);
        
        System.out.println("\n\nBST height: " + animalBST.height());
        System.out.println("BST is balanced? " + animalBST.isBalanced());

        System.out.println("\nHeap height: " + animalHeap.height());
        System.out.println("Heap is balanced? " + animalHeap.isBalanced() + "\n");

        /*Compare and discuss the results obtained with the big-O time complexity 
        of the methods contains, add, and remove.
        For add(), BST and Heap are both smaller than the iterations used for linked or arraylist.
        The Heap is always log(n) and there are 500 animals, and the log(500) is 9 so it is always below 9.
        For BST, because it is not properly balanced, the maximum is 14, but it is still much better (usually below).
        It is log(n) on average, but can become o(n) if the data is inserted in order. The BST has a height to 17, but 
        it is close to log n but not linear. For the Heap, the height is 9 as the log(500) is 9. The Heap is always balanced.

        For contains(), search in BST is efficient on logarithmic scale as it utilizes binary search. Heap is similar to linkedlist 
        or arraylist as it is not a data structure that is used for searching, but moreso for sorting data. It does not have a 
        efficient search as the average is easily in the 200s range compared to BST which averages around 10 iterations.

        For remove(), both remove methods have logarithmic performances as Heap never exceeds the log(500) which is 9 and for BST the 
        maximum would be 16. This is because of their time complexity of log(n) (can be o(n) for BST depending on whether data is ordered or not).
        */

        animalBST.clear();
        animalHeap.clear();
        
        java.util.Collections.sort(animalAL); //add sorted list to 2 data structures
        for(String animal: animalAL){
            animalBST.add(animal);
            animalHeap.add(animal); //stays the same (always keeps balanced)
        }
        System.out.println("__________________________________________");
        System.out.println("Sorted animal arraylist: ");
        System.out.println("\nBST height: " + animalBST.height()); //equals to size 
        System.out.println("BST is balanced? " + animalBST.isBalanced());

        System.out.println("\nHeap height: " + animalHeap.height()); //still 9 and balanced --> doesn't matter for the heap 
        //(will reconstruct itself aka ALWAYS balanced)
        System.out.println("Heap is balanced? " + animalHeap.isBalanced() + "\n");

        /* Discuss the meaning of the values you obtained for height and isBalanced for the two binary 
        trees in steps f and g.
        For step f, the height of the heap is 9 as the log(500) is 9. The Heap is always balanced. The height
        of a heap is basically the height of its root. BST has a height of around 16 which is close to log, but not linear.
        It is kind of balanced as this is what happens when the data is inserted in random order.
        
        For step g, if the data is inserted in order, the height of the BST is now equal to the number of animals which 
        now looks like linkedlist. For the heap, it is still 9 and balanced. Thus, for the heap, it does not matter 
        whether the data is entered in order or not in order as it will always reconstruct itself to keep the 2 properties of the 
        tree are complete(always balanced).
        */

    }

    /***
	 * Method to read animals from a txt file (which also tests add())
	 * @param bst for the animals to be put into a BST
     * @param heap for the animals to be put into a Heap
     * @param al for the animals to be put into an ArrayList
     * @param filename for the filename of the list of animals
	 * no return value
	 */
    public static void readAnimals(BST<String> bst, Heap<String> heap, ArrayList<String> al, String filename){
        File file = new File(filename);
        try{
            Scanner read = new Scanner(file);
            int index = 0;
            int totalBST = 0; 
            int totalHeap = 0;
            System.out.printf("%-30s\t%-15s\t%-15s\n", "Animal", "BST(iterations)", "Heap(iterations");
            while(read.hasNextLine()){
                String animal = read.nextLine();
                bst.add(animal);
                heap.add(animal);
                al.add(animal);
                totalBST += BST.iterations;
                totalHeap += Heap.iterations;
                if(index%24 ==0){
                    System.out.printf("%-30s\t%-15d\t%-15d\n", animal, BST.iterations, Heap.iterations);
                }
                index++;
            }
            System.out.printf("%-30s\t%-15d\t%-15d\n", "Average", totalBST/al.size(), totalHeap/al.size());
            read.close();
        }
        catch(FileNotFoundException e){
            System.out.println("File not found");
        }
    }
    /***
	 * Method to test the method contains
	 * @param bst for the 20 random animals in a BST to check contains
     * @param heap for the 20 random animals in a Heap to check contains
     * @param al to get an animal to search for in BST or Heap
	 * no return value
	 */
    //time complexity: O(N^2)
    public static void testContains(BST<String> bst, Heap<String> heap, ArrayList<String> al){
        int totalBST = 0;
        int totalHeap = 0;
        System.out.printf("%-30s\t%-15s\t%-15s\n", "Animal", "BST(iterations)", "Heap(iterations)");
        for(int i=0; i<20; i++){
            int randomIndex = (int) (Math.random() * al.size());
            String animal = al.get(randomIndex);
            bst.contains(animal);
            heap.contains(animal);
            totalBST += BST.iterations;
            totalHeap += Heap.iterations;
            System.out.printf("%-30s\t%-15d\t%-15d\n", animal, BST.iterations, Heap.iterations);
        }
         System.out.printf("%-30s\t%-15d\t%-15d\n", "Average", totalBST/20, totalHeap/20);
    }
    /***
	 * Method to test the method remove
	 * @param bst for the 20 random animals in a BST to remove
     * @param heap for the 20 random animals in a Heap to remove
     * @param al to get an animal to search for in BST or Heap to remove
	 * no return value
	 */
    //time complexity: O(NlogN)
    public static void testRemove(BST<String> bst, Heap<String> heap, ArrayList<String> al){
        int totalBST = 0;
        int totalHeap = 0;
        System.out.printf("%-30s\t%-15s\t%-15s\n", "Animal", "BST(iterations)", "Heap(iterations)");
        for(int i=0; i<20;i++){
            int randomIndex = (int) (Math.random() * al.size());
            String animal = al.get(randomIndex);
            bst.remove(animal); 
            heap.remove(); //o(logn)
            //implicitly remove root 20 times?
            //how does it know to remove the specific animal
            totalBST += BST.iterations;
            totalHeap += Heap.iterations;
            System.out.printf("%-30s\t%-15d\t%-15d\n", animal, BST.iterations, Heap.iterations);
        }
         System.out.printf("%-30s\t%-15d\t%-15d\n", "Average", totalBST/20, totalHeap/20);
    }
}