/**
 * Class MapEntry
 * @author Houria Oudghiri
 * Date of creation: may 10, 2022
 * @param <K> type of the key
 * @param <V> type of the value
 */
public class MapEntry<K, V> {
	private K key;
	private V value;
	/**
	 * constructor
	 * @param k value of key
	 * @param v value of value
	 */
	public MapEntry(K k, V v) {
		key = k;
		value = v;
	}
	/**
	 * Accessor for key
	 * @return value of key
	 */
	public K getKey() { 
		return key; 
	}
	/**
	 * Accessor for value
	 * @return value of value
	 */
	public V getValue() { 
		return value;
	}
	/**
	 * Mutator for key
	 * @param k new value of key
	 */
	public void setKey(K k) {
		key = k;
	}
	/**
	 * Mutator for value
	 * @param v new value of value
	 */
	public void setValue(V v) {
		value=v;
	}
	/**
	 * returns the key and value between parentheses
	 */
	public String toString() {
		return "(" + key + ", " + value + ")";
	}
	
}

