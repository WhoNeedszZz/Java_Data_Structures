/**
 * Michael J. Cusack
 */

/*
 * A sorting utility using the heap sort algorithm used for integer arrays.
 * Expected efficiency is nlog(n) at worst.
 */
public class HeapSort {
	
	/*
	 * @require
	 * 		table.length > 0
	 * @ensure
	 * 		Returns true if the elements in table are in increasing order; false otherwise
	 */
	public static boolean inOrder(int[] table) {
		boolean ordered = false;
		int count = 0;
		
		// Compare each successive element starting at index 1 with the previous
		// element and increment count if greater than the previous element
		for (int i = 1; i < table.length; i++) {
			if (table[i] >= table[i-1])
				count = count + 1;
		}
		
		// All elements are in order if count is one less than the size of the array
		// (There are length-1 pairs of elements to compare)
		if (count == table.length - 1)
			ordered = true;
		
		return ordered;
	}
	
	/*
	 * @require
	 * 		table.length > 0
	 * @ensure
	 * 		Sorts the given table in-place using a heap
	 */
	public static void heapSort(int[] table) {
		
		if (!inOrder(table)) {
			
			// Put the elements in heap order before sorting
			buildHeap(table);
			
			int end = table.length - 1;
			// Swap the first and last elements and then put elements back in heap
			// order until there is only one element left to swap
			while (end > 0) {
				swap(table, 0, end);
				end = end - 1;
				heapify(table, 0, end);
			}
			
			// Elements will be in decreasing order, flip them back
			reverse(table);
		}
	}
	
	/*
	 * @require
	 * 		table.length > 0
	 * @ensure
	 * 		Creates a heap from the elements in table
	 */
	private static void buildHeap(int[] table) {
		int parent = (table.length - 2) / 2;
		
		// Put elements in heap order from last parent to the root parent
		while (parent >= 0) {
			heapify(table, parent, table.length - 1);
			parent = parent - 1;
		}
	}
	
	/*
	 * @require
	 * 		table.length > 0
	 * 		0 <= root < table.length
	 * 		0 > size < table.length
	 * @ensure
	 * 		Moves elements in table into heap order
	 */
	private static void heapify(int[] table, int root, int size) {
		int leftChild = root*2 + 1;
		int rightChild = -1;
		int smallest = leftChild;
		
		// Only perform if there is at least one child
		if (leftChild <= size) {
		
			// If right child exists, set it and get the smallest of the children
			if (leftChild + 1 <= size) {
				rightChild = leftChild + 1;
				smallest = getSmallestChild(table, leftChild, rightChild);
			} 
			// If right child doesn't exist set equal to leftChild so the lookup
			// doesn't go out of bounds
			else
				rightChild = leftChild;
			
			// If the root is greater than either child swap the elements and sift
			// down the remaining elements to maintain heap order
			if (table[root] > table[leftChild] || table[root] > table[rightChild]) {
				swap(table, root, smallest);
				heapify(table, smallest, size);
			}
		}
	}
	
	/*
	 * @require
	 * 		table.length > 0
	 * 		0 <= high < table.length
	 * 		0 <= low < table.length
	 * @ensure
	 * 		Swaps the two given elements' locations in the table
	 */
	private static void swap(int[] table, int high, int low) {
		int temp = table[high];
		table[high] = table[low];
		table[low] = temp;
	}
	
	/*
	 * @require
	 * 		table.length > 0
	 * 		1 < leftChild < table.length
	 * 		2 < rightChild < table.length
	 * @ensure
	 * 		Returns leftChild if table[leftChild] <= table[rightChild] or
	 * 		rightChild if table[rightChild] < table[leftChild]
	 */
	private static int getSmallestChild(int[] table, int leftChild, int rightChild) {
		int smallest = leftChild;
		
		if (table[rightChild] < table[leftChild])
			smallest = rightChild;
		
		return smallest;
	}
	
	/*
	 * Reverse the elements in the given table
	 * @require
	 * 		table.length > 0
	 * @ensure
	 * 		for all i in table: table[i] = table[table.length - (i+1)]
	 */
	private static void reverse(int[] table) {
		
		// Reverse each pair of elements about the midpoint of the array, otherwise
		// the array would be reversed twice putting it back into original order
		for(int i = 0; i < Math.floor(table.length / 2); i++) {
			swap(table, i, table.length - (i+1));
		}
	}
	
	/*
	 *  @require
	 *  	table.length > 0
	 *  @ensure
	 *  	Returns the string representation of the given table
	 */
	public static String tableToString(int[] table) {
		StringBuilder str = new StringBuilder("[");
		
		// Add each element of the table to the string
		for (int i = 0; i < table.length; i++) {
			str.append(table[i]);
			// Add a comma after every element except the last
			if (i < table.length - 1)
				str.append(",");
		}
		
		str.append("]");
		
		return str.toString();
	}

}
