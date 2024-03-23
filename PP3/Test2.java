import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
public class Test2 {
    /* put() for hashmapLP (iterations), sortedKeys iterations for SC and LP, and hashmapLP iterations for 1-64 */
    public static void main(String[] args) {
        StringComparator sc = new StringComparator();
        HashMapLP<String, String> hmLP = new HashMapLP<>(100000);
        HashMapSC<String, String> hmSC = new HashMapSC<>(100000);
        TreeMap<String, String> treeM = new TreeMap<>(sc);
        ArrayList<MapEntry<String, String>> hashbrown = new ArrayList<>(100000);
       
        readEmails(hmLP, hmSC, treeM, "emails.txt");
        /*ArrayList<MapEntry<String, String>> AHH = hmLP.toList();
        for(int i = 0;i<50&& i < AHH.size();i++){
            System.out.println(AHH.get(i));
        }*/
       //hashbrown.clear();
        //mapbrown.clear();
        readMail(hashbrown, "mailingList.txt");

        int counter = 0;
        int totalHLP = 0;
        int totalHSC = 0; 
        int totalTM = 0;
       
        System.out.println("Testing get()");
        System.out.printf("%-30s\t%-10s\t%-10s\t%-10s\n", "Username", "Tree Map", "HashMapSC", "HashMapLP");
        
        
        for(int i =0; i < 100; i++ ){
            String lime = hashbrown.get(i).getKey();
            hmLP.get(lime);
            hmSC.get(lime);
            treeM.get(lime);
            if(i % 5 ==0){ //starts at 64
                System.out.printf("%-30s\t%-10d\t%-10d\t%-10d\n", lime, TreeMap.iterations, HashMapSC.iterations, 
                HashMapLP.iterations);
                totalHLP += HashMapLP.iterations;
                totalHSC += HashMapSC.iterations;
                totalTM += TreeMap.iterations;
            }
        }
        //AVG print
        System.out.printf("%-30s\t%-10d\t%-10d\t%-10d\n", "Average", (totalTM / 20), (totalHSC / 20), 
        (totalHLP / 20));
        /* Compare the performance of the search operations on the  three data structures (TreeMap, HashMapSC, HashMapLP).
         * For the get method, we can see that the best performance is done by the HashMaps. Out of the linear probing and separate
         * chaining for the hashmaps, we can see that the separate chaining has a slightly better performance. Hence, separate chaining
         * has the best performance, linear probing has the next best performance, and treemap has the worst performance out of the three.
         * This is most likely the case because the hashmaps search by using a certain index to start at instead of looping through
         * the entire array (like treemapo does w the while loop) in order to find the key. That is however, they still need to loop
         * through the elements when collisions happen. 
         */

        //sortedkeys
        System.out.println("\nTesting sortedKeys() - number of iterations");
        readMail(hashbrown, "emails.txt");
        treeM.sortedKeys(); //how
        System.out.printf("%-20s\t%-10d\n", "Tree Map: ", TreeMap.iterations); 
        hmSC.sortedKeys(hashbrown, sc);
        System.out.printf("%-20s\t%-10d\n", "HashMapSC: ", Sort.iterationsM); //not exact output
        hmLP.sortedKeys(hashbrown, sc);
        System.out.printf("%-20s\t%-10d\n", "HashMapLP: ", Sort.iterationsM); //not exact output
        /* Compare the performance of the sort operation on the three data structures.
         * For sorting,tree map is the most efficient with the least amount of iterations, then it is the
         * hashmap separate chaining, then the hashmap linear probing is the least efficient with the most amount 
         * of iterations. This is because the treemap is a type of binary search tree that is able to self-balance
         * which means that sorting is built into the data structure. Hashmap on the other hand, you cannot sort directly
         * in the hashmap, thus you need to extract all of its elements into another data structure like an arraylist
         * then sort it then. Thus, this extra step of extracting then sorting can be inefficient which is observed 
         * through the large number of iterations for both hashmaps.
         */

        //Collisions
        System.out.println("\nTesting put() - number of collisions");
        System.out.printf("%-15s\t%-10s\t%-10s\n", "Size", "HashMapSC", "HashMapLP");
        int add = 0; //9
        for(int i =0; i<10;i++){
            HashMapLP<String, String> hashhash = new HashMapLP<>(50000 + add);
            HashMapSC<String, String> mapmap = new HashMapSC<>(50000 + add);
            readEmails(hashhash, mapmap, treeM, "emails.txt");

            System.out.printf("%-15d\t%-10d\t%-10d\n", (add + 50000), HashMapSC.collisions , HashMapLP.collisions);
            add +=50000;

        }

        /* Compare the performance of the two hash table implementations (HashMapSC and HashMapLP)
         * For the two hashtables, we can see that as the size gets larger, the number of collisions of separate chaining 
         * is better (less) than that of linear probing. This could be due to the loading factors in the constructors of the
         * hashtables. Because separate chaining has a load factor of 0.9 whereas linear probing has a load factor of 0.5, a higher load
         * factor is more efficient as it will need to iterate through more empty cells to find the key when you have a lower loading
         * factor. However, when you have a higher load factor and the fact that it is chained through linkedlist, a lot of the 
         * values in the list have the same hash key (the new key-value is just added to the end of the linked list) which causes
         * a better performance. So for linear probing, since it will keep looking for the next empty space in the event of a collision,
         * this can result in possibly long chains of consecutively filled spaced which can lead to a worse performance when elements are
         * added to the hashtable.
         */


    }
    //public boolean searchEmail(MapEntry<String, String> m1, MapEntry<String, String> m2){

    //}
    public static void readEmails(HashMapLP<String, String> hml, HashMapSC<String, String> hsc, TreeMap<String, String> tm,
                                String filename){
        File file = new File (filename);
        try{
            Scanner read = new Scanner(file);
            while(read.hasNextLine()){
                String line = read.nextLine();
                String[] stuff = line.split(" ");
                String email = stuff[0];
                String password = stuff[1];
                hml.put(email, password);
                /*if (value == null){
                    System.out.println("WJDHIABNDHJSABDHBSWDHAKS");
                }*/
                hsc.put(email, password);
                tm.add(email, password);

                /*hmLP.put(email, password);
                //hmSC = (new HashMapEntry<String, String>(email, password));
                hmSC.put(email, password);
                treeM.add(email, password);*/
            }
            read.close();
        }catch(FileNotFoundException e){
            System.out.println("File not found");
        }
    }
    public static void readMail(ArrayList<MapEntry<String, String>> meA, String filename){
        File file = new File (filename);
        try{
            Scanner read = new Scanner(file);
            while(read.hasNextLine()){
                String line = read.nextLine();
                String[] stuff = line.split(" ");
                String email = stuff[0];
                String password = stuff[1];
                MapEntry<String, String> me = new MapEntry<> (email, password);
                meA.add(me);
            }
            read.close();
        }catch(FileNotFoundException e){
            System.out.println("File not found");
        }
    }
}
