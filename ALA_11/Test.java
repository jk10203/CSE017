import java.util.ArrayList;
public class Test{
    public static void main(String[] args){
        final int SIZE = 10000;
        ArrayList<Integer> randomList = new ArrayList<>(SIZE);
        System.out.println("\nDataset Size: " + SIZE);

        System.out.printf("\n%-20s\t%-10s\t%-10s\t%-10s\t", "Sorting Algorithm", "Random", "Sorted", "Reversed");

        
        for(int i =0; i< SIZE; i++){
            randomList.add((int)(Math.random()*SIZE));
        }
        ArrayList<Integer> sortedList = (ArrayList<Integer>)(randomList.clone());
        //clone gives object so you cast to arraylist
        java.util.Collections.sort(sortedList);

        ArrayList<Integer> reversedList = (ArrayList<Integer>)(sortedList.clone());
        java.util.Collections.reverse(reversedList);
       
        //selection sort
        Sort.selectionSort(randomList);
        System.out.printf("\n%-20s\t%-10d\t", "Selection Sort", Sort.iterations[0]);
        Sort.selectionSort(sortedList);
        System.out.printf("%-10d\t", Sort.iterations[0]);
        Sort.selectionSort(reversedList);
        System.out.printf("%-10d\t", Sort.iterations[0]);
        //now they are all sorted, so you need to shuffle the randomlist and reverse the reverse list
        //keep sortedlist as is

        //insertion sort
        java.util.Collections.shuffle(randomList);
        java.util.Collections.reverse(reversedList);
        Sort.insertionSort(randomList);
        System.out.printf("\n%-20s\t%-10d\t", "Insertion Sort", Sort.iterations[1]);
        Sort.insertionSort(sortedList);
        System.out.printf("%-10d\t", Sort.iterations[1]);
        Sort.insertionSort(reversedList);
        System.out.printf("%-10d\t", Sort.iterations[1]);

        //Bubble Sort
        java.util.Collections.shuffle(randomList);
        java.util.Collections.reverse(reversedList);
        Sort.bubbleSort(randomList);
        System.out.printf("\n%-20s\t%-10d\t", "Bubble Sort", Sort.iterations[2]);
        Sort.bubbleSort(sortedList);
        System.out.printf("%-10d\t", Sort.iterations[2]);
        Sort.bubbleSort(reversedList);
        System.out.printf("%-10d\t", Sort.iterations[2]);

        //Merge Sort
        java.util.Collections.shuffle(randomList);
        java.util.Collections.reverse(reversedList);
        Sort.iterations[3] = 0;
        Sort.mergeSort(randomList);
        System.out.printf("\n%-20s\t%-10d\t", "Merge Sort", Sort.iterations[3]);
        Sort.iterations[3] = 0;
        Sort.mergeSort(sortedList);
        System.out.printf("%-10d\t", Sort.iterations[3]);
        Sort.iterations[3] = 0;
        Sort.mergeSort(reversedList);
        System.out.printf("%-10d\t", Sort.iterations[3]);

        //Quick Sort
        java.util.Collections.shuffle(randomList);
        java.util.Collections.reverse(reversedList);
        Sort.quickSort(randomList);
        System.out.printf("\n%-20s\t%-10d\t", "Quick Sort", Sort.iterations[4]);
        Sort.quickSort(sortedList);
        System.out.printf("%-10d\t", Sort.iterations[4]);
        Sort.quickSort(reversedList);
        System.out.printf("%-10d\t\n", Sort.iterations[4]);

        //Heap Sort
        java.util.Collections.shuffle(randomList);
        java.util.Collections.reverse(reversedList);
        Sort.heapSort(randomList);
        System.out.printf("%-20s\t%-10d\t", "Heap Sort", Sort.iterations[5]);
        Sort.heapSort(sortedList);
        System.out.printf("%-10d\t", Sort.iterations[5]);
        Sort.heapSort(reversedList);
        System.out.printf("%-10d\t\n", Sort.iterations[5]);

        //Bucket Sort
        java.util.Collections.shuffle(randomList);
        java.util.Collections.reverse(reversedList);
        Sort.bucketSort(randomList);
        System.out.printf("%-20s\t%-10d\t", "Bucket Sort", Sort.iterations[6]);
        Sort.bucketSort(sortedList);
        System.out.printf("%-10d\t", Sort.iterations[6]);
        Sort.bucketSort(reversedList);
        System.out.printf("%-10d\t\n", Sort.iterations[6]);

        //Radix Sort
        java.util.Collections.shuffle(randomList);
        java.util.Collections.reverse(reversedList);
        Sort.radixSort(randomList);
        System.out.printf("%-20s\t%-10d\t", "Radix Sort", Sort.iterations[7]);
        Sort.radixSort(sortedList);
        System.out.printf("%-10d\t", Sort.iterations[7]);
        Sort.radixSort(reversedList);
        System.out.printf("%-10d\t\n", Sort.iterations[7]);
        
    }
}
/* compare the eight sorting algorithms for random, sorted, and reversed datasets.
 * For the randomized datasets, the best performing sorting algorithm is Bucket Sort followed by
 * Radix Sort, Heap Sort, Quick Sort, Merge Sort, Insertion Sort, Bubble Sort, then Selection Sort.
 * Because the randomized dataset is most likely unformly distributed across 10000 (the size), bucket sort
 * is able to make use of this fact by using relatively equal sized buckets and distributing the numbers 
 * throughout in linear time. And because the sorting is done one each bucket then concatenated back into
 * to form the final list, bucket sort is the most efficient algorithm for this dataset. Selection sort is the
 * worst sorting algorithm for a randomized dataset with a size of 10000 because of its time complexity
 * of o(n^2). It also makes multiple traverses through the list in order to find the minimum element and assign
 * it accordingly.
 * For the sorted datasets, the best performing sorting algorithm is Insertion Sort, then bubble sort, 
 * bucket, radix, heap merge, selection, quick. Inserttion sort is the best performing algorithm in an
 * already sorted list because each element only needs to be compared with the previous element once in order to be
 * inserted into the correct position in constant time (o(n) for sorted datasets). The worst performing algorithm
 * is quicksort because it utilizes pivots to divide into subgroups recursively. Especially since pivots are made with
 * one subgroup with elements greater and the other less than the pivot, if a dataset is sorted, it results in
 * many unnecessary comparisons/swaps as one subgroup will be larger in size than the other. This leads to a 
 * poor performance for a sorted dataset.
 * For the reversed datasets, the best performing sorting algorithm is Bucket Sort then radix, heap,
 * merge, quick, insertion then selection and bubble. Bucket Sort is once again, the best performing
 * sorting algorithm for a reversed dataset because even though the dataset is reversed, it is still distributed
 * uniformly, and thus will be put into relatively equal-size buckets. And because it sorts the data depending on their
 * value into each bucket and then sorts. Bubble sort and selection sort would be the worst performing
 * sorting algorithms because they both have a time complexity of o(n^2) in the worst case. Because both have to make
 * a large number of swaps/comparisons which leads to the worst case scenario. Because bubble sort works by swapping with the 
 * adjacent value if they are in the wrong order, especially for a reversed list,this will take a significant amount of time.
 * And because selection sort functions by repeatedly finding the minimum element in the unsorted portion of the list, it will 
 * take many swaps especially for a reversed list of numbers.
 * Thus overall, radix sort and bucket sort had the most consistent, well performing sorting performance  no matter the form the dataset
 * was in which can be due to the fact that they are non-comparative sorting algorithms--they do not compare the values of the
 * elements themselves with each other to sort. Bucket sort uses equally sized buckets and distributing over these buckets and sorting
 * , whereas radix sort sorts the data digit-by-digit. Both have a time complexity of o(n+d) where n is the amount of elements to sort, and d
 * is the range of the inputted values of the elements.
 */