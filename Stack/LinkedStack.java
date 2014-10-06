/**
 * Michael J. Cusack
 */

import nhUtilities.containers2.List;

/**
 * An implementation of the Stack<T> interface using a circular linked list
 */
public class LinkedStack<T> implements Stack<T> {

	private int size;
	private List<T> elements;
	
	/*
	 * Creates a new circular linked based stack of type T
	 * @require
	 * 		N/A
	 * @ensure
	 * 		size >= 0 && size == number of elements in stack
	 * 		elements = new CircularLinkedList<T>()
	 */
	public LinkedStack() {
		size = 0;
		elements = new CircularLinkedList<T>();
	}
	
	/*
	 * @{inheritDoc}
	 */
	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	/*
	 * @{inheritDoc}
	 */
	@Override
	public T top() {
		return elements.get(size-1);
	}

	/*
	 * @{inheritDoc}
	 * @ensure
	 * 		elements.get(size-1) == element
	 * 		this.size = old.size + 1
	 */
	@Override
	public void push(T element) {
		elements.add(element);
		size = size + 1;
	}

	/*
	 * @{inheritDoc}
	 * @ensure
	 * 		elements.get(size-1) == old.elements.get(size-2)
	 * 		this.size = old.size - 1
	 */
	@Override
	public void pop() {
		elements.remove(size-1);
		size = size - 1;
	}	
	
}
