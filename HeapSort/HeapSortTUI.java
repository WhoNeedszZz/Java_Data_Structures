/**
 * Michael J. Cusack
 */

/*
 * A simple TUI for getting an array of integers from the user and sorting the data
 * using the heap sort algorithm
 */
public class HeapSortTUI {
	
	private java.util.Scanner input;
	
	/*
	 * @require
	 * 		N/A
	 * @ensure
	 * 		Initializes input for getting input from the user
	 */
	public HeapSortTUI() {
		input = new java.util.Scanner(System.in);
	}
	
	/*
	 * @require
	 * 		N/A
	 * @ensure
	 * 		Returns a user given size for the table
	 */
	public int getTableSize() {
		int size = input.nextInt();
		return size;
	}
	
	/*
	 * @require
	 * 		size > 0
	 * @ensure
	 * 		Returns an array of integers obtained from the user
	 */
	public int[] getTableElements(int size) {
		int[] table = new int[size];
		
		// Set each element in table to the value obtained from the user
		for (int i = 0; i < size; i++) {
			System.out.println("Element " + i + ":");
			table[i] = input.nextInt();
		}
		
		return table;
	}
	
	/*
	 * @require
	 * 		table.length > 0
	 * @ensure
	 * 		Sorts the table using the heap sort algorithm
	 */
	public void sortTable(int[] table) {
		HeapSort.heapSort(table);
	}
	
	/*
	 * @require
	 * 		N/A
	 * @ensure
	 * 		Sets up the tui and obtains the size and elements of the table to be sorted
	 */
	public static void main(String[] args) {
		HeapSortTUI tui = new HeapSortTUI();
		System.out.println("Size of array? (>0): ");
		int size = tui.getTableSize();
		int[] table = tui.getTableElements(size);
		System.out.println("Unsorted table: " + HeapSort.tableToString(table));
		tui.sortTable(table);
		System.out.println("Sorted table: " + HeapSort.tableToString(table));
	}
}
