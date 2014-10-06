/**
 * Michael J. Cusack
 */


/**
 * A generic interface for implementing a stack of elements following the
 *	LIFO (Last in, first out) procedure
 */
public interface Stack<T> {

	/*
	 * @require
	 * 		N/A
	 * @ensure
	 * 		Returns true if there are no elements in the stack; false if
	 * 		elements exist
	 */
	public boolean isEmpty();
	
	/*
	 * @require
	 * 		this.!isEmpty()
	 * @ensure
	 * 		Returns the top element of the stack
	 */
	public T top();
	
	/*
	 * Pushes the given element on top of the stack
	 * @require
	 * 		element != null
	 * @ensure
	 * 		this.top() == element
	 * 		!this.isEmpty()
	 */
	public void push(T element);
	
	/*
	 * @require
	 * 		!this.isEmpty()
	 * @ensure
	 * 		Pops the top element from the stack
	 */
	public void pop();
}
