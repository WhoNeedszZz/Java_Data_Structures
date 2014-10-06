/**
 * Michael J. Cusack
 */


import nhUtilities.containers2.Iterator;
import nhUtilities.containers2.List;
import nhUtilities.utilities.HashUtil;

/*
 * An implementation of Dictionary<K, V> using a hash table to contain the entries
 */
public class HashTable<V> implements Dictionary<Integer, V> {
	
	private List<V>[] table;
	private int size;
	private int capacity;
	
	/*
	 * @require
	 * 		totalSize > 0
	 * @ensure
	 * 		Creates a hash table with a capacity given by totalSize
	 * 		Creates and initializes an array of Lists for storing entries
	 */
	public HashTable(int totalSize) {
		capacity = totalSize;
		// Throws an unchecked cast warning, but is safe because the data will
		// always be a list of type V
		table = (List<V>[])(new CircularLinkedList[capacity]);
		initLists();
	}

	/*
	 * @require
	 * 		N/A
	 * @ensure
	 * 		Initializes each list in the table used for solving collisions of key hashes
	 */
	private void initLists() {
		// Iterate through each list and initialize with a CircularLinkedList
		for(int i=0; i < capacity; i++) {
			table[i] = new CircularLinkedList<V>();
		}
	}
	
	/*
	 * @require
	 * 		value != null
	 * @ensure
	 * 		Returns a hash of the given value <= capacity for storing entries
	 */
	public int hash(V value) {
		return (int)HashUtil.hash(value.toString()) % capacity;
	}
	
	/*
	 * @{inheritDoc}
	 */
	@Override
	public int size() {
		return size;
	}
	
	/*
	 * @{inheritDoc}
	 */
	public boolean isEmpty() {
		return size == 0;
	}
	
	/*
	 * @{inheritDoc}
	 */
	public boolean contains(V value) {
		int key = hash(value);
		List<V> values = getAll(key);
		Iterator<V> iter = values.iterator();
		
		// Iterate through the collisions and stop if value is found or iterator is done
		while (!iter.done() && !iter.get().equals(value)) {
			iter.advance();
		}
		
		return !iter.done();
	}
	
	/*
	 * @{inheritDoc}
	 */
	@Override
	public V get(Integer key) {
		List<V> values = table[key];
		return values.get(0);
	}

	/*
	 * @{inheritDoc}
	 */
	@Override
	public List<V> getAll(Integer key) {
		List<V> values = table[key];
		return values;
	}

	/*
	 * @require
	 * 		value != null
	 * @ensure
	 * 		Hashes the value to a key used for storing the value and passes
	 * 			the pair to the put() from the Dictionary interface
	 */
	public void put(V value) {
		int keyValue = hash(value);
		put(keyValue, value);
	}
	
	/*
	 * @{inheritDoc}
	 */
	@Override
	public void put(Integer key, V value) {
		List<V> values = getAll(key);
		// Value doesn't exist, add it; do nothing otherwise
		if (!values.contains(value)) {
			table[key].add(value);
			size = size + 1;
		}
	}

	/*
	 * @{inheritDoc}
	 */
	@Override
	public void remove(V value) {
		throw new UnsupportedOperationException("Unsupported operation.");
		
	}

	/*
	 * @{inheritDoc}
	 */
	@Override
	public java.util.Set<V> entrySet() {
		throw new UnsupportedOperationException("Unsupported operation.");
	}	
	
}
