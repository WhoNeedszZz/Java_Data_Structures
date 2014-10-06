/*
 * Michael J. Cusack
 */

import nhUtilities.containers2.Iterator;

/*
 * Common methods for all implementations of Bag<T>
 */
public abstract class AbstractBag<T> implements Bag<T> {
	
	/*
	 * Returns whether or not the bag is empty
	 * {@inheritDoc}
	 */
	public boolean isEmpty() {
		return this.size() == 0;
	}
	
	/*
	 * Returns whether or not the item is in the bag
	 * {@inheritDoc}
	 */
	public boolean isMember(BagItem<T> item) {
		Iterator<BagItem<T>> iter = this.iterator();
		
		// Iterate through the elements and advance if item is not equal to
		// iter's current reference
		while(!iter.done() && !iter.get().equals(item))
			iter.advance();
		
		return !iter.done();
	}
	
	/*
	 * Returns a bag containing elements from both bags
	 * {@inheritDoc}
	 */
	public Bag<T> union(Bag<T> other) {
		Bag<T> unionBag = this.copy();
		Iterator<BagItem<T>> iter = other.iterator();
		
		// Iterate through each element in other and add to unionBag
		while (!iter.done()) {
			unionBag.add(iter.get());
			iter.advance();
		}
		
		return unionBag;
	}
	
	/*
	 * Returns a bag containing the similar elements of both bags
	 * {@inheritDoc}
	 */
	public Bag<T> intersection(Bag<T> other) {
		Bag<T> intersectBag = this.makeBag();
		Iterator<BagItem<T>> iter = other.iterator();
		int count = 0;
		
		// Iterate through each element in other and add to intersectBag
		// if count > 0
		while(!iter.done()) {
			count = Math.min(this.count(iter.get()), 
					iter.get().count());
			if (count > 0)				
				intersectBag.add(iter.get());
			iter.advance();
		}
		
		return intersectBag;
	}
	
	/*
	 * Returns a bag containing elements in this and not in other
	 * {@inheritDoc}
	 */
	public Bag<T> difference(Bag<T> other) {
		Bag<T> diffBag = this.makeBag();
		Iterator<BagItem<T>> iter = this.iterator();
		int count = 0;
		
		// Iterate through each element in other and add to diffBag
		// if count > 0
		while(!iter.done()) {
			count = this.count(iter.get()) - other.count(iter.get());
			if (count > 0)
				diffBag.add(iter.get());
			iter.advance();
		}
		
		return diffBag;
	}
	
	/*
	 * Returns a bag containing the elements not in both bags
	 * {@inheritDoc}
	 */
	public Bag<T> xor(Bag<T> other) {
		Bag<T> xorBag = this.union(other);
		Bag<T> intersectBag = this.intersection(other);
		Iterator<BagItem<T>> iter = intersectBag.iterator();
		
		// Iterate through each element in intersectBag and remove the element
		// from xorBag (xor == union - intersection)
		while(!iter.done()) {
			if (xorBag.isMember(iter.get())) {
				int count = Math.min(this.count(iter.get()), 
					other.count(iter.get()));
				BagItem<T> item = new BagItem<T>(iter.get().element(), count);
				xorBag.remove(item);
				item = new BagItem<T>(iter.get().element(), iter.get().count());
				xorBag.remove(item);
			}
			iter.advance();
		}
		
		return xorBag;
	}
	
	/*
	 * Returns true if each element is found in both bags with at least
	 * the same count; false if not
	 * {@inheritDoc}
	 */
	public boolean isContainedIn(Bag<T> other) {
		Bag<T> intersectBag = this.intersection(other);
		Iterator<BagItem<T>> iter = intersectBag.iterator();
	
		// Iterate through each element in intersectBag and compare the count
		// of the element to this and other. Stop if the count in this <= other
		// or the iterator is done
		while(!iter.done() && 
			this.count(iter.get()) >= iter.get().count()) {
				iter.advance();
		}
		
		return iter.done();
	}
	
	/*
	 * Returns a copy of this bag
	 * {@inheritDoc}
	 */
	public Bag<T> copy() {
		Bag<T> copyBag = this.makeBag();
		Iterator<BagItem<T>> iter = this.iterator();
		
		// Iterate through each element in this and add to copyBag
		while(!iter.done()) {
			copyBag.add(iter.get());
			iter.advance();
		}
		
		return copyBag;
	}
	
	/*
	 * @require
	 * 		N/A
	 * @ensure
	 * 		Returns the string representation of the contents of the bag
	 */
	public String toString() {
		StringBuilder repr = new StringBuilder("{");
		Iterator<BagItem<T>> iter = this.iterator();
		
		// Iterate through the bag adding each element and it's count to the
		// string representation of the bag
		while(!iter.done()) {
			repr.append(iter.get().element() + ":" + iter.get().count() + ",");
			iter.advance();
		}
		
		// Delete the last ","
		repr.deleteCharAt(repr.length() - 1);
		repr.append("}");		
		
		return repr.toString();
	}
}
