import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
/**
 * Class HashMap
 * @author Houria Oudghiri
 * Date of creation: May 10, 2022
 * @param <K> type of the key of the map entry
 * @param <V> type of the value of the map entry
 */
public class HashMap <K, V> {
	private int size;
	private double loadFactor;
	private LinkedList<MapEntry<K,V>>[] hashTable;
	/**
	 * Default constructor
	 * sets the capacity to 100 and the load factor to 0.9
	 */
	public HashMap() {
		this(100, 0.9);
	}
	/**
	 * Constructor with one parameter
	 * sets the capacity to c and the load factor to 0.9
	 * @param c
	 */
	public HashMap(int c) {
		this(c, 0.9);
	}
	/**
	 * Constructor with two parameters
	 * sets the capacity to the closest power of 2 to c and the load factor to lf
	 * @param c capacity of the hash table
	 * @param lf load factor of the hash table
	 */
	public HashMap(int c, double lf) {
		hashTable = new LinkedList[trimToPowerOf2(c)];
		loadFactor = lf;
		size = 0;
	}
	/**
	 * Method to find the closest power of 2 to the value c (>=)
	 * @param c capacity
	 * @return the closest power of 2 to c
	 */
	private int trimToPowerOf2(int c) {
		int capacity = 1;
		while (capacity < c)
			capacity  = capacity << 1;
		return capacity;
	}
	/**
	 * Method hash to convert the hashCode into an index in the hash table
	 * @param hashCode
	 * @return a valid index in the hash table 
	 */
	private int hash(int hashCode) {
		return hashCode & (hashTable.length-1);
	}
	/**
	 * Method rehash to increase the capacity of the hash table and 
	 * rehash all the data in the old hash table to the new hash table
	 */
	private void rehash() {
		ArrayList<MapEntry<K,V>> list = toList();
		hashTable = new LinkedList[hashTable.length << 1];
		size = 0;
		for(MapEntry<K,V> entry: list) 
			put(entry.getKey(), entry.getValue());

	}
	
	/**
	 * Method size
	 * @return the number of non-null map entries in the hash table
	 */
	public int size() { 
		return size;
	}
	/**
	 * Method clear to reset all the hash table entries to null
	 */
	public void clear() { 
		size = 0;
		for(int i=0; i<hashTable.length; i++)
			if(hashTable[i] != null)
				hashTable[i].clear();
	}
	/**
	 * Method isEmpty
	 * @return true if there no valid entries in the hash table
	 */
	public boolean isEmpty() { 
		return (size == 0);
	}
	/**
	 * Method containsKey
	 * @param key being searched
	 * @return true if there is a map entry that matches key
	 */
	public boolean containsKey(K key) {
		if(get(key) != null)
			return true;
		return false;
	}
	
	/**
	 * Method get to search for a map entry
	 * @param key being searched
	 * @return the value of the map entry that matches key
	 *         null if no map entry was found
	 */
	public V get(K key) {
		int HTIndex = hash(key.hashCode());
		if(hashTable[HTIndex] != null) {
			LinkedList<MapEntry<K,V>> ll = hashTable[HTIndex];
			Iterator<MapEntry<K,V>> iter = ll.iterator();
			while(iter.hasNext()) {
				MapEntry<K,V> entry = iter.next();
				if(entry.getKey().equals(key))
					return entry.getValue();
			}
		}
		return null;
	}
	
	/**
	 * Method put to add a new map entry
	 * @param key of the map entry being added
	 * @param value of the map entry being added
	 * @return the value of the new map entry added or
	 *         the old value of the map entry if a map entry was found
	 */
	public V put(K key, V value) {
		if(get(key) != null) { 
			// The key is in the hash map
			int HTIndex = hash(key.hashCode());
			LinkedList<MapEntry<K,V>> ll;
			ll = hashTable[HTIndex];
			Iterator<MapEntry<K,V>> iter = ll.iterator();
			while(iter.hasNext()) {
				MapEntry<K,V> entry = iter.next();
				if(entry.getKey().equals(key)) {
					V old = entry.getValue();
					entry.setValue(value); 
					return old;
				}
			}
		}
		// key is not found in the hash map
		// check load factor for rehashing
		if(size >= hashTable.length * loadFactor)
			rehash();
		int HTIndex = hash(key.hashCode());
		if(hashTable[HTIndex] == null){
			hashTable[HTIndex] = new LinkedList<>();
		}
		hashTable[HTIndex].add(new MapEntry<>(key, value));
		size++; 
		return value;
	}
	/**
	 * Method toList used by the rehash method
	 * @return the valid map entries in an array list
	 */
	public ArrayList<MapEntry<K,V>> toList(){
		ArrayList<MapEntry<K,V>> list = new ArrayList<>();
		for(int i=0; i< hashTable.length; i++) {
			if(hashTable[i]!= null) {
				LinkedList<MapEntry<K,V>> ll = hashTable[i];
				Iterator<MapEntry<K,V>> iter = ll.iterator();
				while(iter.hasNext()) {
					MapEntry<K,V> entry = iter.next();
					list.add(entry);
				}
			}
		} 
		return list;
	}
	/**
	 * Method toString()
	 * returns the valid map entries in a string
	 */
	public String toString() {
		String out = "[";
		for(int i=0; i<hashTable.length; i++) {
			if(hashTable[i]!=null) {
				LinkedList<MapEntry<K,V>> ll = hashTable[i];
				Iterator<MapEntry<K,V>> iter = ll.iterator();
				while(iter.hasNext()) {
					MapEntry<K,V> entry = iter.next();
					out += entry.toString();
				}
				out += "\n";
			}
		}
		out += "]"; return out;
	}
}
