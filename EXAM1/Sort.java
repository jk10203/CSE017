import java.util.ArrayList;
public class Sort{
    /**
        Bubble sort method
        @param list to be sorted
        time complexity: O(n^2)
        space complexity: O(1)
     */
    public static <E extends Comparable<E>> void bubbleSort(ArrayList<HashMapEntry<Integer,String>> list) { 
        boolean sorted = false; 
        for (int k=1; k < list.size() && !sorted; k++) {
            sorted = true; 
            for (int i=0; i<list.size()-k; i++) {
                if (list.get(i).getValue().compareTo(list.get(i+1).getValue()) > 0) { 
                    HashMapEntry<Integer,String> temp = list.get(i);
                    list.set(i, list.get(i+1));
                    list.set(i+1, temp); 
                    sorted = false; 
                } 
            } 
        }     
    }  
}