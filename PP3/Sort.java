import java.util.ArrayList;
import java.util.Comparator;
public class Sort{
    
    public static int iterationsM;
    
    //time complexity: o(n)
    public static <K,V> ArrayList<MapEntry<K,V>> subList(ArrayList<MapEntry<K,V>> list, int start, int end){
        //also count iterations here for making copies
        ArrayList<MapEntry<K,V>> newList = new ArrayList<MapEntry<K,V>>(list.size());
        for(int i = start; i < end; i++){
            iterationsM++;
            newList.add(list.get(i));
        }
        return newList;
        //make more robust by checking the indices and throwing indexoutofBounds etc
    }
    /**
        mergeSort method 
        @param list where the merged elements will be stored
        @param c for the comparator
        time complexity: o(n log n)
    */
    public static <K,V> void mergeSort(ArrayList<MapEntry<K,V>> list, Comparator<K> c){
        //take arraylist of e and then comparator
        //recursive so incremement directly and initialize variable before calling mergesort
        iterationsM++; //counts the recursive calls
        if (list.size() > 1) { // ==1: base case
            ArrayList<MapEntry<K,V>> firstHalf = subList(list, 0, list.size() / 2);
            ArrayList<MapEntry<K,V>> secondHalf = subList(list, list.size() / 2, list.size());

            /*System.arraycopy(list, 0, firstHalf, 0, list.size()/2);
            System.arraycopy(list,list.size()/2, secondHalf, 0, list.size()-list.size()/2);*/

            mergeSort(firstHalf, c);
            mergeSort(secondHalf, c);
            merge(firstHalf, secondHalf, list, c);
        }
    } 
    /**
        merge method used by mergeSort
        @param list where the merged elements will be stored
        @param list1 the first sorted list to be merged
        @param list2 the second sorted list to be merged
        time complexity: O(n)
        space complexity: O(1)
     */  
    public static <K,V> void merge(ArrayList<MapEntry<K,V>> list1, ArrayList<MapEntry<K,V>> list2, ArrayList<MapEntry<K,V>> list, Comparator<K> c) {
        int list1Index = 0;
        int list2Index = 0;
        int listIndex = 0;
        while(list1Index < list1.size() && list2Index < list2.size()) {
            iterationsM++;
            if (c.compare(list1.get(list1Index).getKey(), list2.get(list2Index).getKey()) < 0)
                list.set(listIndex++, list1.get(list1Index++));
            else
                list.set(listIndex++, list2.get(list2Index++));
        }
        while(list1Index < list1.size()){
            iterationsM++;
            list.set(listIndex++, list1.get(list1Index++));
        }
        while(list2Index < list2.size()){
            iterationsM++;
            list.set(listIndex++, list2.get(list2Index++));
        }
    }
}