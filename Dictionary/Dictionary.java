/**
 * Michael J. Cusack
 */


import nhUtilities.containers2.List;

/*
 * An interface for the dictionary container using a key, value pair
 */
public interface Dictionary<K,V> {

	/*
	 * @require
	 * 		N/A
	 * @ensure
	 * 		Returns the number of dictionary entries
	 */
	public int size();
	
	/*
	 * @require
	 * 		N/A
	 * @ensure
	 * 		Returns true if size() == 0; false if size() > 0
	 */
	public boolean isEmpty();
	
	/*
	 * @require
	 * 		value != null
	 * @ensure
	 * 		Returns true if value is found in dictionary; false otherwise
	 */
	public boolean contains(V value);
	
	/*
	 * @require
	 * 		key != null
	 * @ensure
	 * 		Returns the first element given by key; returns null if no entries found
	 */
	public V get(K key);
	
	/*
	 * @require
	 * 		key != null
	 * @ensure
	 * 		Returns a list of values given by key; returns null if no entries found
	 */
	public List<V> getAll(K key);
	
	/*
	 * @require
	 * 		key != null
	 * 		value != null
	 * @ensure
	 * 		Adds the given key value pair to the dictionary
	 * 		this.getAll(key).contains(value)
	 */
	public void put(K key, V value);
	
	/*
	 * @require
	 * 		value != null
	 * @ensure
	 * 		Removes the given value from the dictionary
	 * 		!this.getAll(key).contains(value)
	 */
	public void remove(V value);
	
	/*
	 * @require
	 * 		N/A
	 * @ensure
	 * 		Returns a set containing the entries of the dictionary
	 */
	public java.util.Set<V> entrySet();
}
