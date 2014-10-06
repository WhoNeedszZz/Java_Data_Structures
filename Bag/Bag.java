/*
 * Michael J. Cusack
 */

import nhUtilities.containers2.Iterator;
import nhUtilities.containers2.List;

/*
 * A set-like collection of elements in no particular order with a reference to
 * how many of each element occurs in the bag.
 */
public interface Bag<T> {
	
	/*
	 * Returns the number of elements in the bag
	 * @require
	 * 		N/A
	 * @ensure
	 * 		size >= 0
	 */
	public int size();
	
	/*
	 * @require
	 * 		N/A
	 * @ensure
	 * 		Returns true if the bag contains 0 elements; false if > 0
	 */
	public boolean isEmpty();
	
	/*
	 * @require
	 * 		element != null
	 * @ensure
	 * 		Returns true if the bag contains the item; false if not
	 */
	public boolean isMember(BagItem<T> item);
	
	/*
	 * @require
	 * 		item != null
	 * @ensure
	 * 		Returns the count of the item
	 */
	public int count(BagItem<T> item);
	
	/*
	 * Adds the given item to the bag
	 * @require
	 * 		item != null
	 * 		count > 0
	 * @ensure
	 * 		this.size() == old.size() + 1 if !this.isMember(item)
	 * 		if this.isMember(item) then this.count(item) = 
	 * 			old.count(item) + count
	 * 		this.isMember(item)
	 */
	public void add(BagItem<T> item);
	
	/*
	 * Removes the given item from the bag
	 * @require
	 * 		item != null
	 * @ensure
	 * 		if this.count(item) == item.count() then
	 * 			this.size() == old.size() - 1
	 * 			!this.isMember(item)
	 * 		this.count(item) == old.count(item) - count
	 */
	public void remove(BagItem<T> item);
	
	/*
	 * Returns a bag containing all elements of both bags
	 * @require
	 * 		other != null
	 * @ensure
	 * 		for each element in this and other
	 * 			unionBag.isMember(element)
	 * 			unionBag.count(element) == 
	 * 				this.count(element) + other.count(element)
	 */
	public Bag<T> union(Bag<T> other);
	
	/*
	 * Returns a bag containing the similar elements in each bag
	 * @require
	 * 		other != null
	 * @ensure
	 * 		for each element in this and other
	 * 			intersectBag.isMember(element)
	 * 			intersectBag.count(element) == 
	 * 				min(this.count(element),other.count(element))
	 */
	public Bag<T> intersection(Bag<T> other);
	
	/*
	 * Returns a bag containing the elements in this bag that are not in other
	 * @require
	 * 		other != null
	 * @ensure
	 * 		for each element in this not other
	 * 			diffBag.isMember(element)
	 * 			diffBag.count(element) ==
	 * 				this.count(element) - other.count(element)
	 */
	public Bag<T> difference(Bag<T> other);
	
	/*
	 * Returns a bag containing the elements not in both bags
	 * @require
	 * 		other != null
	 * @ensure
	 * 		for each element in this xor other
	 * 			xorBag.isMember(element)
	 * 			xorBag.count(element) ==  
	 * 				max(this.count(element),other.count(element))
	 * 				- min(this.count(element),other.count(element))
	 */
	public Bag<T> xor(Bag<T> other);
	
	/*
	 * @require
	 * 		other != null
	 * @ensure
	 * 		Returns true if each element is found in both bags with at least
	 * 		the same count; false if not
	 */
	public boolean isContainedIn(Bag<T> other);
	
	/*
	 * Returns a list containing the elements in the bag
	 * @require
	 * 		N/A
	 * @ensure
	 * 		list.size() == count of all elements in bag
	 * 		for each element in bag
	 * 			list.contains(element)
	 */
	public List<BagItem<T>> toList();
	
	/*
	 * Returns a copy of this bag
	 * @require
	 * 		N/A
	 * @ensure
	 * 		copy != this
	 * 		copy.size() == this.size()
	 * 		for each element in this
	 * 			copy.isMember(element)
	 */
	public Bag<T> copy();
	
	/*
	 * @require
	 * 		N/A
	 * @ensure
	 * 		Returns an iterator traversing this bag
	 */
	public Iterator<BagItem<T>> iterator();
	
	/*
	 * Make an empty bag
	 * @require
	 * 		N/A
	 * @ensure
	 * 		Returns a new instance of Bag
	 */
	public Bag<T> makeBag();
}
