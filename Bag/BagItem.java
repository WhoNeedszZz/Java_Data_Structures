/*
 * Michael J. Cusack
 */

/*
 * An object used in a Bag containing an element and the count of that element
 */
public class BagItem<T> {
	
	private T element;
	private int count;
	
	/*
	 * Create a new BagItem
	 * @require
	 * 		theElement != null
	 * 		amount > 0
	 * @ensure
	 * 		element = theElement
	 * 		count = amount
	 */
	public BagItem(T theElement, int amount) {
		element = theElement;
		count = amount;
	}
	
	/*
	 * @require
	 * 		N/A
	 * @ensure
	 * 		Returns the element
	 */
	public T element() {
		return element;
	}
	
	/*
	 * @require
	 * 		N/A
	 * @ensure
	 * 		Returns the count of the element
	 */
	public int count() {
		return count;
	}
	
	/*
	 * Add the specified amount to the element's count
	 * @require
	 * 		amount > 0
	 * @ensure
	 * 		count == old.count + amount
	 */
	public void add(int amount) {
		count = count + amount;
	}
	
	/*
	 * Remove the specified amount from the element's count
	 * @require
	 * 		amount > 0 && amount < count
	 * @ensure
	 * 		count == old.count - amount
	 */
	public void remove(int amount) {
		count = count - amount;
	}
	
	/*
	 * Returns whether or not the object is equal to this
	 * @require
	 * 		obj != null
	 * @ensure
	 * 		Returns true if the object is an instance of BagItem and has the
	 * 			same element
	 */
	public boolean equals(Object obj) {
		boolean isEqual = false;
		if (this == obj) return true;
		if (obj instanceof BagItem) {
			// Unchecked cast warning, but acceptable as the object is checked
			// if it is an instance of BagItem before casting
			BagItem<T> other = (BagItem<T>)(obj);
			if (other.element().equals(this.element()))
				isEqual = true;
		}		
		return isEqual;
	}
}
