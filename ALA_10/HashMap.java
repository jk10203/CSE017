/***
 * Class to modify the HashMap Class
 * @author Joy Kim
 * @version 0.1
 * Date of creation: April, 2023
 * Last Date Modified: April 25, 2023
 *
 * This program was made in order to modify the HashMap class so that it can count the number of iterations
 * it performs when it does its operations.
 */
import java.util.ArrayList;
import java.util.LinkedList;

import EXAMPREP3.HashMapEntry;

/**
	Class that implements a hashtable of Hashmap entries with two generic types
	K for key
	V for value
 */
public class HashMap <K, V> {
	// data member: number of elements added to the hashmap
	private int size;
	// data member: load factor at which rehashing is required
	private double loadFactor; //how full it should be until rehash
	// data member: Array of linked lists and elements in linked list are type HashMapEntry
	private LinkedList<HashMapEntry<K,V>>[] hashTable;
    //3 layers, array then elements of array are linkedlist then elements of that are type hashMapEntry
	public static int iterations;

    /* separate chaining for linked list - implementing hashMap */
	/**
		Default constructor
		Creates a hashmap with capacity 100 and load factor 0.9
        time complexity: O (1)
	 */
	public HashMap() {
		this(100, 0.9); //(0.9) good factor for separate chaining
        //half full then rehash
	}
	/**
		Constructor with one parameter
		Creates a hashmap with capacity c and default load factor 0.9
		@param c the capacity of the hashtable
        time complexity: O (log N)
	 */
	public HashMap(int c) {
		this(c, 0.9);
        
	}
	/**
		Constructor with two parameters
		@param c the capacity of the hashtable
		@param lf the load factor for the hashtable
        time complexity: O (log N)
	 */
	public HashMap(int c, double lf) {
		hashTable = new LinkedList[trimToPowerOf2(c)]; //creating array 
        //trimToPowerOf2: the closest power of 2 to the value of c
		loadFactor = lf;
		size = 0;
        //new HashMap<>(100) --> returns trimToPOwerOf2(100) capacity = 1 and 
        //while capacity<100 --> capacity = capacity<<1 (*2)(shifting in binary 1101 --> 11010 (like multiplying by 2))
        //100 --> 128 (closest power of 2 to 100) WHY? replace modulus w & operator if length of hashTable is a power of 2
        //has to do with how fast 
	}
    /**
		Method trimToPowerOf2 to create a hashtable with a size that is
		the closest power of two to c
		@param c the capacity intended for the hashtable
		@return the closet power of 2 to c 
        time complexity: O (log N)
	 */
	private int trimToPowerOf2(int c) {
		int capacity = 1;
		while (capacity < c)
			capacity  = capacity << 1; // * 2
		return capacity;
	}
	/**
		The hash function
		@param the hash code of the key
		@return a valid index in the hashtable
        time complexity: O (1)
	 */
    private int hash(int hashCode) {
		return hashCode & (hashTable.length-1);
        //& - bitwise operator  and (returns value)
        //&& - logical and (looks at two values true and false)
	}
    /**
		Method to get the size of the hashtable
		@return the number of elements in the hashtable
	 */
	public int size() { 
		return size;
	}
	/**
		Method to clear the hashtable
        time complexity: O (N)
	 */
	public void clear() { 
		size = 0;
		for(int i=0; i<hashTable.length; i++)
			if(hashTable[i] != null)
				hashTable[i].clear(); //this is O(1)
                //clear all linkedlist in hashtable
	}
	/**
		Method to check if the hashtable is empty
		@return true if the hashtable is empty, false otherwise
	 */
	public boolean isEmpty() { 
		return (size == 0);
	}

	/**
		Search method
		@param key to be serached
		@return true if key was found, false otherwise
        time complexity: O(1)
	 */
	public boolean containsKey(K key) {
		if(get(key) != null)
			return true;
		return false;
	}
    /**
		Method to get the value of a key
		@param key to be serached
		@return the value of the key if found, null otherwise
        time complexity: O(1) (does search in big O(1) - attractive)
	 */
	public V get(K key) { 
		iterations = 0;
		int HTIndex = hash(key.hashCode()); //hash the hashCode of the key --> returns integer for index to access HashTable
		if(hashTable[HTIndex] != null) {
			LinkedList<HashMapEntry<K,V>> ll = hashTable[HTIndex];
			for(HashMapEntry<K,V> entry: ll) { //going through all elements in linked list at an index
            //size of linkedlist is very small in comparison to size of hash
            //constant time most of the time
			iterations++;
				if(entry.getKey().equals(key))
					return entry.getValue();
			}
		}
		return null;
        /*String s = "Bye"
          s.hashCode() = 40*b^0 + 60*b^1 + 45*b^2 */
	}
    /**
		Method to remove a pair from the hashtable
		@param key to be searched and removed
		if the key is not found, the hashtable is unchanged
        time complexity: O(1)
	 */
	public void remove(K key) {
		int HTIndex = hash(key.hashCode());
		if (hashTable[HTIndex]!=null) { //key is in the hash map
			LinkedList<HashMapEntry<K,V>> ll = hashTable[HTIndex];
			for(HashMapEntry<K,V> entry: ll) {
				if(entry.getKey().equals(key)) {
					ll.remove(entry); //utilizing from linkedList
					size--;
					break;
				}
			}
		}		
	}
    /**
		Method to add a pair (key,value) to the hashtable
		@param key to be added
		@param value of the key to be added
		@return old value if the key was found or the new value if key was not found
        time complexity: O(1) (if no rehash needed)
        time complexity: O(N) (if rehash needed)
	 */
    public V put(K key, V value) {
	    if (get(key) != null) { // The key is in the hash map
		    int HTIndex = hash(key.hashCode());
		    LinkedList<HashMapEntry<K,V>> ll;
            ll = hashTable[HTIndex];
		    for(HashMapEntry<K,V> entry: ll) {
			    if(entry.getKey().equals(key)) {
                    V old = entry.getValue();
                    entry.setValue(value); 
                    return old;
			    }
		    }
        }
        // key not in the hash map - check load factor
        if(size >= hashTable.length * loadFactor)
		        rehash(); // after reaching 90 (100*0.9)
        int HTIndex = hash(key.hashCode());
        //create a new LL if empty
        if(hashTable[HTIndex] == null){
		    hashTable[HTIndex] = new LinkedList<>(); //first entry to that index
        }
        hashTable[HTIndex].add(new HashMapEntry<>(key, value)); //add new pair to the exising linkedList at that index
        size++; 
        return value;
    }
   	/**
		Method to rehash the hashtable
        time complexity: O(N) - worst method in hashmap class
    */
	private void rehash() {
		ArrayList<HashMapEntry<K,V>> list = toList(); //O(N)
		hashTable = new LinkedList[hashTable.length << 1];
		size = 0;
		for(HashMapEntry<K,V> entry: list) { //O(N)
			put(entry.getKey(), entry.getValue()); //
        }
		
	}
   	/**
		Method to return the pairs (key,value) stored in teh hashtable
		@return an array list with all the pairs (key,value)
        time complexity: O(N)
    */
	public ArrayList<HashMapEntry<K,V>> toList(){
		ArrayList<HashMapEntry<K,V>> list = new ArrayList<>();
		for(int i=0; i< hashTable.length; i++) {
			if(hashTable[i]!= null) {
				LinkedList<HashMapEntry<K,V>> ll = hashTable[i];
				for(HashMapEntry<K,V> entry: ll)
					list.add(entry);
			}
		} return list;
	}
    /**
		toString method
		@return formatted string with all the pairs (key,value) in the hashtable
        time complexity: O(N) - every element in hashtable visited
	 */
	public String toString() {
		String out = "[";
		for(int i=0; i<hashTable.length; i++) {
			if(hashTable[i]!=null) {
				for(HashMapEntry<K,V> entry: hashTable[i])
					out += entry.toString();
				out += "\n";
			}
		}
		out += "]"; 
        return out;
	}
    /*public int collisions(){
        //size of largest linkedlist - max number of collisions
        //which linkedlist has largest size
        int max = 0;
        for(int i =0; i< hashTable.length; i++){
            if (hashTable[i] != null){
                LinkedList<HashMapEntry<K,V>> ll = hashTable[i]; //some elements null
                if(ll.size()> max){
                    max = ll.size();
                }
            }
        }
        return max-1;
    }*/
	/**
		Method to find maximum number of collisions
		@return the max amount of collisions at any index in the hashtable
        time complexity: O(N)
    */
	public int collisions(){
		int max = 0;
		for(int i = 0; i < hashTable.length; i++){
			if(hashTable[i] != null){
				int llSize = hashTable[i].size(); //size of linkedlist
				if (llSize > max){
					max = llSize;
				}

			}
		}
		return max;
	}
}
