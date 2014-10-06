/*
 * Michael J. Cusack
 */

import nhUtilities.containers2.Iterator;
import nhUtilities.containers2.List;

/*
 * A circular linked-based implementation of Bag<T>
 * size >= 0 && size == total number of elements in bag
 */
public class LinkedBag<T> extends AbstractBag<T> {
	
	private List<BagItem<T>> elements;
	private int size;
	
	/*
	 * Create a new instance of LinkedBag<T>
	 * @require
	 * 		N/A
	 * @ensure
	 * 		elements = new CircularLinkedList<BagItem<T>>()
	 * 		size = 0
	 */
	public LinkedBag() {
		elements = new CircularLinkedList<BagItem<T>>();
		size = 0;
	}
	
	/*
	 * Returns a new instance of LinkedBag<T>
	 * {@inheritDoc}
	 */
	public Bag<T> makeBag() {
		return new LinkedBag<T>();
	}
	
	/*
	 * Returns the size of the bag
	 * {@inheritDoc}
	 */
	public int size() {
		return size;
	}
	
	/*
	 * Returns the count of the given element
	 * {@inheritDoc}
	 */
	public int count(BagItem<T> item) {
		Iterator<BagItem<T>> iter = this.iterator();
		int count = 0;
		
		// Iterate through the bag and stop if element is found or iter is done
		while(!iter.done() && !iter.get().equals(item)) {
			iter.advance();
		}
		
		// Item is found
		if (!iter.done())
			count = iter.get().count();
		
		return count;
	}
	
	/*
	 * Adds the given element to the bag with count given
	 * {@inheritDoc}
	 */
	public void add(BagItem<T> item) {
		if (item.element() != null) {
			Iterator<BagItem<T>> iter = this.iterator();
			
			// Iterate through the elements and stop if done or element is found
			while(!iter.done() && !iter.get().element().equals(item.element())) {
				iter.advance();
			}
			
			// Element already exists, add new count to previous count
			if (!iter.done()) {
				iter.get().add(item.count());
			}
			// Element doesn't exist, create a new element
			else {
				elements.add(item);
				size = size + 1;
			}
		}
	}
	
	/*
	 * Removes the given element from the bag with given count
	 * {@inheritDoc}
	 */
	public void remove(BagItem<T> item) {
		Iterator<BagItem<T>> iter = this.iterator();
		
		// Iterate through the elements and stop if done or element is found
		while(!iter.done() && !iter.get().equals(item)) {
			iter.advance();
		}
		
		// Element is found, remove all or some
		// Do nothing if element isn't in the bag
		if (!iter.done()) {
			// Remove all
			if (item.count() == iter.get().count()) {
				elements.remove(iter.get());
				size = size - 1;
			}
			else
				iter.get().remove(item.count());
		}
	}
	
	/*
	 * Returns an iterator traversing this bag
	 * {@inheritDoc}
	 */
	public Iterator<BagItem<T>> iterator() {
		return elements.iterator();
	}
	
	/*
	 * Returns the bag as a list
	 * {@inheritDoc}
	 */
	public List<BagItem<T>> toList() {
		return elements.copy();
	}
}
