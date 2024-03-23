import java.util.ArrayList;
import java.util.Comparator;
/**
	Class that implements a hashtable of Hashmap entries with two generic types
	K for key
	V for value
 */
public class HashMapLP <K, V> {
	// data member: number of elements added to the hashmap
	private int size;
	// data member: load factor at which rehashing is required
	private double loadFactor;
	// data member: Array of linked lists
	private MapEntry<K,V>[] hashTable;
    public static int collisions;
    public static int iterations;
	/**
		Default constructor
		Creates a hashmap with capacity 100 and load factor 0.9
		time complexity: O(1)
	 */
	public HashMapLP() {
		this(100, 0.5);
	}
	/**
		Constructor with one parameter
		Creates a hashmap with capacity c and default load factor 0.9
		@param c the capacity of the hashtable
		time complexity: O(logn)
	 */
	public HashMapLP(int c) {
		this(c, 0.5);
	}
	/**
		Constructor with two parameters
		@param c the capacity of the hashtable
		@param lf the load factor for the hashtable
		time complexity: O(logn)
	 */
	public HashMapLP(int c, double lf) {
		hashTable = new MapEntry[trimToPowerOf2(c)];
		loadFactor = lf;
		size = 0;
        collisions = 0;
	}
    /**
		Method trimToPowerOf2 to create a hashtable with a size that is
		the closest power of two to c
		@param c the capacity intended for the hashtable
		@return the closet power of 2 to c 
		time complexity: O(logn)
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
		time complexity: O(1)
	 */
    private int hash(int hashCode) {
		return hashCode & (hashTable.length-1);
	}
    /**
		Method to get the size of the hashtable
		@return the number of elements in the hashtable
		time complexity: O(1)
	 */
	public int size() { 
		return size;
	}
	/**
		Method to clear the hashtable
		time complexity: O(n)
	 */
	public void clear() { 
		size = 0;
		for(int i=0; i<hashTable.length; i++)
			if(hashTable[i] != null)
				//hashTable[i].clear();
                hashTable[i] = null;
	}
	/**
		Method to check if the hashtable is empty
		@return true if the hashtable is empty, false otherwise
		time complexity: O(1)
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
		time complexity: O(1)
	 */
	public V get(K key) {
		/*int HTIndex = hash(key.hashCode());
		if(hashTable[HTIndex] != null) {
			LinkedList<HashMapEntry<K,V>> ll = hashTable[HTIndex];
			for(HashMapEntry<K,V> entry: ll) {
				if(entry.getKey().equals(key))
					return entry.getValue();
			}
		}
		return null;
        */
        iterations = 0;
        int HTIndex = hash(key.hashCode());
        while(hashTable[HTIndex] != null){ //hashTable[HTIndex] = null
			//System.out.println("here");
			iterations++;
			//System.out.println("wow");
           //iterations++;
            if(hashTable[HTIndex].getKey().equals(key)){
                return hashTable[HTIndex].getValue();
            }
            HTIndex = (HTIndex +1) & (hashTable.length - 1);
        }
        return null;
	}
    /**
		Method to add a pair (key,value) to the hashtable
		@param key to be added
		@param value of the key to be added
		@return old value if the key was found or the new value if key was not found
		time complexity: O(1) without rehash, O(n) with rehash
	 */
    public V put(K key, V value) {
		if (get(key) != null) { // The key is in the hash map
		    int HTIndex = hash(key.hashCode());
		    
		    while(hashTable[HTIndex] != null){
			    if(hashTable[HTIndex].getKey().equals(key)) {
                    V old = hashTable[HTIndex].getValue();
                    hashTable[HTIndex].setValue(value); 
                    return old;
			    }
				HTIndex = (HTIndex +1) & (hashTable.length - 1);
		    }
        }
        // key not in the hash map - check load factor
        if(size >= hashTable.length * loadFactor)
		        rehash();
        int HTIndex = hash(key.hashCode());
        //create a new LL if empty
        if(hashTable[HTIndex] == null){
		    hashTable[HTIndex] = new MapEntry<>(key, value); 
        } else {
			collisions++; //replacinig is technically collision
			while(hashTable[HTIndex] != null){
				HTIndex = (HTIndex +1) & (hashTable.length - 1);
			}
			hashTable[HTIndex] = new MapEntry<>(key, value); 
		}
        size++; 
		//System.out.println(size);
        return value;
    }
       /*  int HTIndex = hash(key.hashCode());
	    if (hashTable[HTIndex] != null) { // The key is in the hash map
			//collisions++;
            for(int i =0; i < hashTable.length; i++){
				//iterations++;
                //LinkedList<HashMapEntry<K,V>> ll;
                //ll = hashTable[HTIndex];
                if(hashTable[HTIndex].getKey().equals(key)) {
					collisions++;
                    V old = hashTable[HTIndex].getValue();
                    hashTable[HTIndex].setValue(value); 
                    return old;
                }
            }
        }
        int counter = 0;
        while(counter < (hashTable.length)){
            if (hashTable[HTIndex] == null){ //if empty insert there
               // hashTable[HTIndex].setValue(value);
                hashTable[HTIndex] = new MapEntry<>(key, value);
				break;
            } else if (counter == 0){
                collisions ++; //how do collisions work here
            }
            HTIndex = (HTIndex + 1) & (hashTable.length - 1); //creating new hash
            //exactly what is it doing
            counter ++;
        }
        // key not in the hash map - check load factor
        if(size >= hashTable.length * loadFactor){
		    rehash();
        }
        /*int HTIndex = hash(key.hashCode());
        //create a new LL if empty
        if(hashTable[HTIndex] == null){
		    hashTable[HTIndex] = new MapEntry<>();
        }
        hashTable[HTIndex].add(new MapEntry<>(key, value));
        size++; 
        return value; //returning new value (assuming NOT FOUND)*/
   	/**
		Method to rehash the hashtable
		time complexity: (n)
    */
	private void rehash() {
		ArrayList<MapEntry<K,V>> list = toList();
		hashTable = new MapEntry[hashTable.length << 1]; // double the length of hashtable
		size = 0;
		for(MapEntry<K,V> entry: list) {
			put(entry.getKey(), entry.getValue());
        }
	}
   	/**
		Method to return the pairs (key,value) stored in the hashtable
		@return an array list with all the pairs (key,value)
		time complexity: O(n)
    */
	public ArrayList<MapEntry<K,V>> toList(){
		/*ArrayList<MapEntry<K,V>> list = new ArrayList<>();
		for(int i=0; i< hashTable.length; i++) {
			if(hashTable[i] != null) {
				MapEntry<K,V>[] ll = hashTable[i];
				for(MapEntry<K,V> entry: ll)
					list.add(entry);
			}
		} 
        return list;
        */
        ArrayList<MapEntry<K,V>> list = new ArrayList<>();
        
		for(int i =0; i< hashTable.length; i++){
			Sort.iterationsM++;
			if(hashTable[i] != null){
				list.add(hashTable[i]);
			}
        }
        return list;
    }
    /**
		toString method
		@return formatted string with all the pairs (key,value) in the hashtable
		time complexity: O(n)
	 */
	public String toString() {
		String out = "[";
		for(int i=0; i<hashTable.length; i++) {
			if(hashTable[i]!=null) {
                out += hashTable[i].toString();
				/*for(HashMapEntry<K,V> entry: hashTable[i])
					out += entry.toString();
				out += "\n";*/
			}
		}
		out += "]"; 
        return out;
	}
	//time complexity: o(n log n)
    public ArrayList<MapEntry<K,V>> sortedKeys(ArrayList<MapEntry<K,V>> arr, Comparator<K> c){
		Sort.iterationsM = 0;
        arr = toList();
       // iterations = 0;
        Sort.mergeSort(arr, c);
        return (arr);

    }
}