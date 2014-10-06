/**
 * Michael J. Cusack
 */

import junit.framework.TestCase;

public class HeapSortTest extends TestCase {

	// Set up test
	public HeapSortTest(String name) {
		super(name);
	}
	
	// Test sorting a table with only one element
	public void testTableOneElement() {
		int[] table = {42};
		int[] sortTable = table.clone();
		assertEquals(HeapSort.tableToString(table), HeapSort.tableToString(sortTable));
		boolean ordered = HeapSort.inOrder(sortTable);
		HeapSort.heapSort(sortTable);
		assertTrue(ordered);
		assertEquals(table.length, sortTable.length);
		assertEquals(HeapSort.tableToString(table), HeapSort.tableToString(sortTable));
	}
	
	// Test sorting a table that is already in order with no repeated elements
	public void testTableIncreasingOrder() {
		int[] table = {1,2,4,6,8,17,42,76};
		int[] sortTable = table.clone();
		assertEquals(HeapSort.tableToString(table), HeapSort.tableToString(sortTable));
		boolean ordered = HeapSort.inOrder(sortTable);
		HeapSort.heapSort(sortTable);
		assertTrue(ordered);
		assertEquals(table.length, sortTable.length);
		assertEquals(HeapSort.tableToString(table), HeapSort.tableToString(sortTable));
	}
	
	// Test sorting a table that is already in order with repeated elements
	public void testTableIncreasingOrderRep() {
		int[] table = {1,1,2,4,6,8,8,17,42,42,76};
		int[] sortTable = table.clone();
		assertEquals(HeapSort.tableToString(table), HeapSort.tableToString(sortTable));
		boolean ordered = HeapSort.inOrder(sortTable);
		HeapSort.heapSort(sortTable);
		assertTrue(ordered);
		assertEquals(table.length, sortTable.length);
		assertEquals(HeapSort.tableToString(table), HeapSort.tableToString(sortTable));
	}
	
	// Test sorting a table that is almost in increasing order with no repeated elements
	public void testTableAlmostIncOrder() {
		int[] table = {1,2,4,6,8,17,76,42};
		int[] sortTable = table.clone();
		assertEquals(HeapSort.tableToString(table), HeapSort.tableToString(sortTable));
		boolean tableOrdered = HeapSort.inOrder(table);
		HeapSort.heapSort(sortTable);
		boolean sortTableOrdered = HeapSort.inOrder(sortTable);
		assertTrue(!tableOrdered);
		assertTrue(sortTableOrdered);
		assertEquals(table.length, sortTable.length);
	}
	
	// Test sorting a table that is almost in increasing order with repeated elements
	public void testTableAlmostIncOrderRep() {
		int[] table = {1,2,2,4,6,6,8,17,76,42,42};
		int[] sortTable = table.clone();
		assertEquals(HeapSort.tableToString(table), HeapSort.tableToString(sortTable));
		boolean tableOrdered = HeapSort.inOrder(table);
		HeapSort.heapSort(sortTable);
		boolean sortTableOrdered = HeapSort.inOrder(sortTable);
		assertTrue(!tableOrdered);
		assertTrue(sortTableOrdered);
		assertEquals(table.length, sortTable.length);
	}
	
	// Test sorting a table that is in decreasing order with no repeated elements
	public void testTableDecreasingOrder() {
		int[] table = {76,42,17,8,6,4,2,1};
		int[] sortTable = table.clone();
		assertEquals(HeapSort.tableToString(table), HeapSort.tableToString(sortTable));
		boolean tableOrdered = HeapSort.inOrder(table);
		HeapSort.heapSort(sortTable);
		boolean sortTableOrdered = HeapSort.inOrder(sortTable);
		assertTrue(!tableOrdered);
		assertTrue(sortTableOrdered);
		assertEquals(table.length, sortTable.length);
	}
	
	// Test sorting a table that is in decreasing order with repeated elements
	public void testTableDecreasingOrderRep() {
		int[] table = {76,42,42,17,8,6,4,4,2,2,1};
		int[] sortTable = table.clone();
		assertEquals(HeapSort.tableToString(table), HeapSort.tableToString(sortTable));
		boolean tableOrdered = HeapSort.inOrder(table);
		HeapSort.heapSort(sortTable);
		boolean sortTableOrdered = HeapSort.inOrder(sortTable);
		assertTrue(!tableOrdered);
		assertTrue(sortTableOrdered);
		assertEquals(table.length, sortTable.length);
	}
	
	// Test sorting a table that is almost in decreasing order with no repeated elements
	public void testTableAlmostDecOrder() {
		int[] table = {76,42,17,8,4,6,2,1};
		int[] sortTable = table.clone();
		assertEquals(HeapSort.tableToString(table), HeapSort.tableToString(sortTable));
		boolean tableOrdered = HeapSort.inOrder(table);
		HeapSort.heapSort(sortTable);
		boolean sortTableOrdered = HeapSort.inOrder(sortTable);
		assertTrue(!tableOrdered);
		assertTrue(sortTableOrdered);
		assertEquals(table.length, sortTable.length);
	}
	
	// Test sorting a table that is almost in decreasing order with repeated elements
	public void testTableAlmostDecOrderRep() {
		int[] table = {76,76,42,42,17,17,8,4,6,2,1};
		int[] sortTable = table.clone();
		assertEquals(HeapSort.tableToString(table), HeapSort.tableToString(sortTable));
		boolean tableOrdered = HeapSort.inOrder(table);
		HeapSort.heapSort(sortTable);
		boolean sortTableOrdered = HeapSort.inOrder(sortTable);
		assertTrue(!tableOrdered);
		assertTrue(sortTableOrdered);
		assertEquals(table.length, sortTable.length);
	}
	
	// Test sorting a table that has no order with no repeated elements
	public void testTableNoOrder() {
		int[] table = {8,10,5,9,2,16,11,7,14,12,3};
		int[] sortTable = table.clone();
		assertEquals(HeapSort.tableToString(table), HeapSort.tableToString(sortTable));
		boolean tableOrdered = HeapSort.inOrder(table);
		HeapSort.heapSort(sortTable);
		boolean sortTableOrdered = HeapSort.inOrder(sortTable);
		assertTrue(!tableOrdered);
		assertTrue(sortTableOrdered);
		assertEquals(table.length, sortTable.length);
	}
	
	// Test sorting a table that has no order with repeated elements
	public void testTableNoOrderRep() {
		int[] table = {8,10,7,9,9,2,16,11,7,11,12,3};
		int[] sortTable = table.clone();
		assertEquals(HeapSort.tableToString(table), HeapSort.tableToString(sortTable));
		boolean tableOrdered = HeapSort.inOrder(table);
		HeapSort.heapSort(sortTable);
		boolean sortTableOrdered = HeapSort.inOrder(sortTable);
		assertTrue(!tableOrdered);
		assertTrue(sortTableOrdered);
		assertEquals(table.length, sortTable.length);
	}
}
