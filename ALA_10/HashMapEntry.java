/***
 * Class for HashMapEntry 
 * @author Joy Kim
 * @version 0.1
 * Date of creation: April, 2023
 * Last Date Modified: April 25, 2023
 *
 * This program was made in order to format the way that the HashMap elements will be inserted into 
 * the Hash Table.
 * 
 */
public class HashMapEntry<K, V> {
	private K key;
	private V value;

	/***
	 * Constructor  with 2 parameters
	 * @param k for the key
	 * @param v for the value
	 * Initializes key and value to k and v respectively
	 */
	public HashMapEntry(K k, V v) {
		key = k;
		value = v;
	}
	/***
	 * Getter for the key
	 * @param	no parameters
	 * @return	the value of the data member key
	 */
	public K getKey() { 
        return key; 
    }
	/***
	 * Getter for the value
	 * @param	no parameters
	 * @return	the value of the data member value
	 */
	public V getValue() { 
        return value; 
    }
	/***
	 * Setter for the key
	 * @param	k to set the data member key
	 * no return value
	 */
	public void setKey(K k) {
		key = k;
	}
	/***
	 * Setter for the value
	 * @param	v to set the data member value
	 * no return value
	 */
	public void setValue(V v) {
		value=v;
	}
	/**
		toString method
		@return formatted string with key and value
        time complexity: O(1) 
	 */
	public String toStaring() {
		return "(" + key + ", " + value + ")";
	}
}