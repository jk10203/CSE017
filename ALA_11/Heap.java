/***
 * Class to modify the Heap Class
 * @author Joy Kim
 * @version 0.1
 * Date of creation: April, 2023
 * Last Date Modified: April 24, 2023
 *
 * This program was made in order to modify the Heap class so that it can count the number of iterations
 * it performs when it does its operations. We also add methods for the height and if it is balanced.
 */
import java.util.ArrayList;
/**
    Generic class that implements the data structure Heap
*/
public class Heap<E extends Comparable<E>> {
    // data member:a list of the heap nodes
    private ArrayList<E> list;
    /**
        Default constructor to create an empty heap
        Time complexity: O(1)
    */
    public Heap(){
        list = new ArrayList<>();
    }
    /**
        Method to get the size of the heap
        @return the number of nodes in the heap
        Time complexity: O(1)
    */
    public int size(){ 
        return list.size(); 
    }
    /**
        Method to check if the heap is empty
        @return true if the heap is empty, false otherwise
        Time complexity: O(1)
    */
    public boolean isEmpty(){
        return list.isEmpty();
    }
    /**
        Method to clear the heap
        Time complexity: O(1)
    */
    public void clear(){
        list.clear(); 
    }
    /**
        Method to get the values stored in the heap as a string
        @return formatted string with the values of all the nodes in the heap
        Time complexity: O(n)
    */
    public String toString(){
        return list.toString();
    }
    /**
        Method to search for a value in the heap
        @param value to be searched in the heap
        @return true if value was found in the heap, false otherwise
        Time complexity: O(n)
    */
    public boolean contains(E value) {
        for(int i=0; i<list.size(); i++) {
            if(list.get(i).equals(value))
                return true;
        }
        return false;
    }
    /**
        Method to add a new value in the heap
        @param value to be added to the heap
        Time complexity: O(logn)
    */
    public void add(E value) {
        list.add(value);// add value at the end of the list
        int currentIndex = list.size()-1; // index of the added node
        //Sort.iterations[5]++; //used by heap sort algorithm
        while(currentIndex > 0) {
            Sort.iterations[5]++;
            int parentIndex = (currentIndex-1)/2;
            E current = list.get(currentIndex);
            E parent = list.get(parentIndex);
            if(current.compareTo(parent) > 0) { // need to swap
                list.set(currentIndex, parent);
                list.set(parentIndex, current);
            }
            else
                break;
            currentIndex = parentIndex;
        }
    }
    /**
        Method to remove the root node from the heap
        @return the value of the root (the largest value in the heap)
        Time complexity: O(logn)
    */
    public E remove() {
        if(list.size() == 0) 
            return null;
        E removedItem = list.get(0);// value of the root
        list.set(0, list.get(list.size()-1));
        list.remove(list.size()-1);
        int currentIndex = 0;
        while (currentIndex < list.size()) {
            Sort.iterations[5]++;
            int left = 2 * currentIndex + 1;
            int right = 2 * currentIndex + 2;
            if (left >= list.size()) 
                break;
            int maxIndex = left;
            E max = list.get(maxIndex);
            if (right < list.size())
            if(max.compareTo(list.get(right)) < 0)
                maxIndex = right;
            E current = list.get(currentIndex);
            max = list.get(maxIndex);            
            if(current.compareTo(max) < 0){
                list.set(maxIndex, current);
                list.set(currentIndex, max);
                currentIndex = maxIndex;
            }
            else
                break;
        }
        return removedItem;
    }

    /**
        Method to find the height of the Heap
        Time complexity: O(n)
    */
    //Does height visit every node in tree --> a linear
    //looking for levels not nodes
    //O(N)
    public int height(){ //call it outside class from BST --> make helper method that takes node as parameter
        return height(0);
    }
    /**
        Recursive Helper Method to find the max height of the left or right side to the node 
        @param nodeIndex root index of the subtree 
        Time complexity: O(n)
    */
    //O(N)
    private int height(int nodeIndex){ //can be private bc only using this in this class
        if(nodeIndex >= list.size()){ //base case
            return 0;
        }
        int lHeight = height(nodeIndex * 2 + 1);//recursive call on the left subtree
        int rHeight = height(nodeIndex * 2 + 2);
        return Math.max(lHeight, rHeight) + 1; // the max of the two
    }

    //if subtree is not balanced (3 v 1 in notes) --> the tree is not balanced??
    
    /**
        Method to find whether the Heap is balanced or not
        Time complexity: O(n^2)
    */
    //O(N^2) / quadratic
    //isbalanced one every node to check property
    public boolean isBalanced(){
        return isBalanced(0);
    }
    /**
        Recursive Helper Method to find whether the Heap is balanced
        @param nodeIndex root index of the subtree 
        Time complexity: O(n^2)
    */
     //O(N^2) / quadratic
    private boolean isBalanced(int nodeIndex){
        //check if node is NOT null
        if(nodeIndex >= list.size()){ //base case 1
            //empty tree IS balanced by definition
            return true;
        }
        int lHeight = height(nodeIndex * 2 + 1);
        int rHeight = height(nodeIndex * 2 + 1);
        int diff = Math.abs(lHeight - rHeight);
        if (diff > 1){ //base case 2
            return false;
        }
        return isBalanced(nodeIndex * 2 + 1) && isBalanced(nodeIndex * 2 + 2); //only true if both are balanced
    }
}