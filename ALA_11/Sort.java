/**
    Class Sort contains five sorting algorithms as static methods
    @author Houria Oudghiri
    Date of the creation: April 24, 2023
 */
 import java.util.ArrayList;

public class Sort{
    //next week add Heap, radix, etc
    //comparing iterations with different data sets
    public static int[] iterations = new int[10];
    /**
        Selection sort method
        @param list to be sorted
        time complexity: O(n^2)
        space complexity: O(1)
     */

    public static <E extends Comparable<E>> void selectionSort(ArrayList<E> list) {
        //needs to extend because they are all comparison based (need to use compareTo)
        //genetic type should extend comparable
        iterations[0] = 0;
        int minIndex;
        for (int i=0; i<list.size() - 1; i++) {
            iterations[0]++;
            E min = list.get(i); 
            minIndex = i;
            for (int j=i+1; j<list.size(); j++){
                iterations[0]++;
                if (list.get(j).compareTo(min) < 0){
                    min = list.get(j);
                    minIndex = j;
                }
            }
            swap(list, i, minIndex);
        }      
    }
    /**
        Insertion sort method
        @param list to be sorted
        time complexity: O(n^2)
        space complexity: O(1)
     */
    public static <E extends Comparable<E>> void insertionSort(ArrayList<E> list) {
        iterations[1] = 0; //might be wrong
        for (int i=1; i<list.size(); i++) {
            iterations[1]++;
            //Insert element i in the sorted sub-list
            E currentVal = list.get(i);
            int j = i;
            while (j > 0 && currentVal.compareTo(list.get(j - 1)) < 0){
                iterations[1]++;
                // Shift element (j-1) into element (j)
                list.set(j, list.get(j - 1));
                j--;
            }
            // Insert currentVal at index j 
            list.set(j, currentVal);
        }
    } 
    /**
        Bubble sort method
        @param list to be sorted
        time complexity: O(n^2)
        space complexity: O(1)
     */
    public static <E extends Comparable<E>> void bubbleSort(ArrayList<E> list) { 
        iterations[2] = 0;
        boolean sorted = false; 
        for (int k=1; k < list.size() && !sorted; k++) { 
            iterations[2]++;
            sorted = true; 
            for (int i=0; i<list.size()-k; i++) { 
                iterations[2]++;
                //if (list[i] > list[i+1]) { 
                if (list.get(i).compareTo(list.get(i+1))>0){
                    // swap 
                    swap(list, i, i+1);
                    sorted = false; 
                } 
            } 
        }     
    }  

    /**
        Merge sort method
        @param list to be sorted
        time complexity: O(nlogn)
        space complexity: O(n)
     */
    public static <E> ArrayList<E> subList(ArrayList<E> list, int start, int end){
        //also count iterations here for making copies
        ArrayList<E> newList = new ArrayList<E>(list.size());
        for(int i = start; i < end; i++){
            iterations[3]++;
            newList.add(list.get(i));
        }
        return newList;
        //make more robust by checking the indices and throwing indexoutofBounds etc
    }
    public static <E extends Comparable<E>> void mergeSort(ArrayList<E> list) {
        //recursive so incremement directly and initialize variable before calling mergesort
        iterations[3]++; //counts the recursive calls
        if (list.size() > 1) { // ==1: base case
            ArrayList<E> firstHalf = subList(list, 0, list.size() / 2);
            ArrayList<E> secondHalf = subList(list, list.size() / 2, list.size());

            /*System.arraycopy(list, 0, firstHalf, 0, list.size()/2);
            System.arraycopy(list,list.size()/2, secondHalf, 0, list.size()-list.size()/2);*/

            mergeSort(firstHalf);
            mergeSort(secondHalf);
            merge(firstHalf, secondHalf, list);
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
    public static <E extends Comparable<E>> void merge(ArrayList<E> list1, ArrayList<E> list2, ArrayList<E> list) {
        int list1Index = 0;
        int list2Index = 0;
        int listIndex = 0;
        while(list1Index < list1.size() && list2Index < list2.size()) {
            iterations[3]++;
            if (list1.get(list1Index).compareTo(list2.get(list2Index)) < 0)
                list.set(listIndex++, list1.get(list1Index++));
            else
                list.set(listIndex++, list2.get(list2Index++));
        }
        while(list1Index < list1.size()){
            iterations[3]++;
            list.set(listIndex++, list1.get(list1Index++));
        }
        while(list2Index < list2.size()){
            iterations[3]++;
            list.set(listIndex++, list2.get(list2Index++));
        }
    }

    /**
        Quick sort method
        @param list to be sorted
        time complexity: O(nlogn) on average or O(n^2) in the worst case
        space complexity: O(1)
     */
    public static <E extends Comparable<E>> void quickSort(ArrayList<E> list) {
        iterations[4] = 0;
        quickSort(list, 0, list.size()-1);
    }
    /**
        Recursive helper method used by quicksort
        @param list to be sorted
        @param first index of the first element in the part being sorted
        @param last index of the last element in the part being sorted
        time complexity: O(nlogn) on average ot O(n^2) in the worst case
        space complexity: O(1)
     */
    public static <E extends Comparable<E>> void quickSort(ArrayList<E> list, int first, int last) {
        iterations[4]++; //counts recursive calls
        if (last > first) {
            int pivotIndex = partition(list, first, last);
            quickSort(list, first, pivotIndex-1);
            quickSort(list, pivotIndex+1, last);
        }
    }
    /**
        partition method used by quicksort
        @param list to be partitioned
        @param first index of the first element in the part being partitioned
        @param last index of the last element in the part being partitioned
        @return index of the pivot
        time complexity: O(n)
        space complexity: O(1)
     */
    public static <E extends Comparable<E>> int partition(ArrayList<E> list, int first, int last){
        int index, pivotIndex;
        E pivot = list.get(first);
        pivotIndex = first;
        for (index = first + 1; index <= last; index++){
            iterations[4]++;
            if (list.get(index).compareTo(pivot) < 0){
                pivotIndex++;
                swap(list, pivotIndex, index);
            }
        }
        swap(list, first, pivotIndex);
        return pivotIndex;
    }
    /**
        swap method
        @param list where the two elements will be swapped
        @param i1 index of the first element to be swapped
        @param i2 index of the second element to be swapped
        time complexity: O(1)
        space complexity: O(1)
     */
    public static <E> void swap(ArrayList<E> list, int i1, int i2){
        E temp = list.get(i1);
        list.set(i1,list.get(i2));
        list.set(i2, temp);
        //no extends comparab le<E> bc we are not comparing
        //no iterations
    }
    
    //Heap Sort
    //time complexity: nlogn
    //space complexity: n additional space (big o(n))
    //need heap that holds whole list
    public static <E extends Comparable<E>> void heapSort(ArrayList<Integer> list) {
        iterations[5]= 0;
        Heap<Integer> heap = new Heap<>();
        for(int i=0; i<list.size(); i++){ //O(nlogn)
            iterations[5]++;
            heap.add(list.get(i)); //o(log(n))
            //iterations[5] += Heap.iterations; //use to count in add and remove method
        }
        for (int i=list.size()-1; i>=0; i--) { //O(nlogn)
            iterations[5] ++;
            list.set(i, heap.remove()); //o(log(n))
            //iterations[5] += Heap.iterations;
        }
    }

    //bucket sort and radix sort ONLY INTEGERS

    //Bucket sort
    //Time Complexity: o(n+t)
    //Space Complexity:big o(n+t)
    //each bucket stores elements from list

     public static int max(ArrayList<Integer> list){
        Integer maximum = list.get(0);
        for(int i =1; i< list.size();i++){
            iterations[6]++;
            iterations[7]++;
            if(list.get(i)> maximum){
                maximum = list.get(i);
            }
        }
        return maximum;
        /*int maximum = list.get(0);
        for(int value: list){
            if (value> maximum){
                maximum = value;
            }
        }
        return maximum;*/
       /* for(int i = 0; i<list.length; i++){
            if(list[i] > maximum){
                return maximum;  //wrong?
            }
        }
        return maximum;*/
    }
    //not practical until small range of data you want to sort
    //n^2 + n --> n squared is nominating, but when n+t, don't know which is nominating
    public static void bucketSort(ArrayList<Integer> list) {
        iterations[6] = 0;
        int t = max(list); //o(n)
        ArrayList<ArrayList<Integer>> buckets;
        buckets = new ArrayList<>(t+1); 
        // create t+1 buckets
        for(int i=0; i<t+1; i++){ //o(t)
            iterations[6] ++;
            buckets.add(new ArrayList<>());
        }

        //Distribute the data on the buckets
        for(int i=0; i<list.size(); i++) { //o(n)
            iterations[6] ++;
            ArrayList<Integer> bucket = buckets.get(list.get(i)); //??????????
            bucket.add(list.get(i));
        }
        // Move the data from the buckets back to the list
        int k = 0;
        for(int i=0; i<buckets.size(); i++) { //o(n+t)
            iterations[6] ++;
        //if t is larger than  than o(t)
        //100 elements (n = 100) - range of values is from 0 to 10,000,000,000
        //t = 10,000,000,001
            //size is t so o(t) --> t times executed
            ArrayList<Integer> bucket = buckets.get(i);
            for(int j=0; j<bucket.size(); j++){// n values
                iterations[6] ++;
                list.set(k++,bucket.get(j));
            }
        }
    }
    //radix sort
    //Time Complexity: o(n d) --> scales linearly with number of digits
    //digits(10+n+n) --> (2dn) --> (dn)
    //Space Complexity: 
    //buckets is always n (10 buckets) --> n + 10 but 10 is constant
    public static void radixSort(ArrayList<Integer> list) {
        iterations[7] = 0;
        ArrayList<ArrayList<Integer>> buckets;
        buckets = new ArrayList<>();
        Integer maxValue = max(list);
        int digits = maxValue.toString().length();
        for(int d=0; d<digits; d++) { //big o(digits)
            iterations[7]++;
            // create 10 buckets
            for(int j=0; j<10; j++) { //o(1)
                iterations[7]++;
                buckets.add(new ArrayList<>());
            }
        //Distribute the data on the buckets
        for(int j=0; j<list.size(); j++){ //o(n))
            iterations[7]++;
            // find the index of the bucket where list[j] should be placed
            int bucketIndex = (list.get(j) % (int)(Math.pow(10, d+1))) /
            (int)(Math.pow(10,d));
            ArrayList<Integer> bucket = buckets.get(bucketIndex);
            bucket.add(list.get(j));
        }
        // Move the data from the buckets back to the list
        int k=0;
        for(int j=0; j<10; j++) { //o(n)
            iterations[7] ++;
            ArrayList<Integer> bucket = buckets.get(j);
                for(int l=0; l<bucket.size(); l++){
                    iterations[7]++;
                    list.set(k++, bucket.get(l));
                }
        }
        // clear all the buckets for the next iteration
        buckets.clear();
        }
    }
}