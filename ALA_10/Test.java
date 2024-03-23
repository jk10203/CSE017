/***
 * Class to test HashMap, BST, and LinkedList
 * @author Joy Kim
 * @version 0.1
 * Date of creation: April, 2023
 * Last Date Modified: April 25, 2023
 *
 * This program was made in order to compare the number of iterations for searching
 * of a dictionary word for various classes including HashMap, BST, and LinkedList. 
 * The maximum number of collisions are also obtained for the HashMap.
 ** The discussion for the 3 methods are at the end of this file
 */
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
public class Test{
    public static void main(String[] args){
        HashMap<String, String> hashWords = new HashMap<>(50000);
        BST<String> bstWords = new BST<>();
        LinkedList<String> llWords = new LinkedList<>();
        ArrayList<HashMapEntry<String,String>> words = new ArrayList<>();
        
        readFile(words, "dictionary.txt");
        java.util.Collections.shuffle(words);

        for(HashMapEntry<String, String> entry: words){ //store word and its definition
            hashWords.put(entry.getKey(), entry.getValue()); //store the word only
            bstWords.add(entry.getKey()); //store word only
            llWords.add(entry.getKey());
        }
        int totalHash = 0;
        int totalBST = 0; 
        int totalLL = 0;
        System.out.printf("%-20s\t%-15s\t%-15s\t%-15s\n", "Word", "LL search", "BST search", "HashMap search");

        for(int i=0; i< 1000; i++){
            int randomIndex = (int) (Math.random() * words.size());
            String word = words.get(randomIndex).getKey();
            hashWords.get(word);
            bstWords.contains(word);
            llWords.contains(word);
            totalHash += HashMap.iterations;
            totalBST += BST.iterations;
            totalLL += LinkedList.containsIter;
            if(i % 50 == 0){
                System.out.printf("%-20s\t%-15d\t%-15d\t%-15d\n", word, LinkedList.containsIter, BST.iterations, HashMap.iterations);
               

            }
        }
        System.out.printf("%-20s\t%-15d\t%-15d\t%-15d\n", "Average", totalLL / 1000, totalBST / 1000, totalHash / 1000);
        System.out.println("\nMaximum number of collisions: " + hashWords.collisions());
        
        /* What does maximum amount of collisions mean?
        The max amount of collision is referring to the largest amount of elements being inserted into
        the same bucket. 
         */
    }
    /***
	 * Method to read dictionary words/definitions from a txt file
     * @param al for the words/definitions to be put into an ArrayList
     * @param filename for the filename of the list of words/definitions
	 * no return value
	 */
    public static void readFile(ArrayList<HashMapEntry<String, String>> al, String filename){
        File file = new File(filename);
        try{
            Scanner read = new Scanner(file);
            while(read.hasNextLine()){
                String line = read.nextLine();
                String[] items = line.split("\\|");
                String words = items[0];
                String definition = items[1];
                HashMapEntry<String, String> pair = new HashMapEntry<> (words, definition);
                al.add(pair);
            }
            read.close();
        }catch(FileNotFoundException e){
            System.out.println("File not found");
        }
        
    }
}

/*Use the results obtained in the step above to discuss and compare the 
three data structures. Add your discussion as a multiline comment in the test class. 
Because LinkedList utilizes a linear search in the method contains(), the number of iterations
will be relatively high as it is always searching for the word starting from index 0 to the index of 
where the word it is searching for is at. 
For BST, it searches on a logarithmic scale using binary search which will utilize a middle index
/ will split keep splitting the list in half to find the specific index. Due to its logarithmic nature,
the average is relatively low with an average of 18.
For HashMap, the number of iterations has an average of 1 and at max 3. This is because HashMap
uses Hash tables that uses the item's keys as the value used to map nto an index. This means that
in Hash Tables, every element has a unique key that can be found by searching for that specific key.
This is HashMap searching is the most efficient and it has an average of 1 iteration. 

*/