/**
 * Michael J. Cusack
 */

import junit.framework.TestCase;

/*
 * A simple test for using a hash table
 */
public class HashTableTest extends TestCase {

	// Set up the test
	public HashTableTest(String name) {
		super(name);
	}
	
	// Test getting the size of an empty table
	public void testSizeEmpty() {
		HashTable<String> table = new HashTable<String>(5);
		assertEquals(0, table.size());
		assertTrue(table.isEmpty());
	}
	
	// Test hashes of different and similar words are different
	public void testHash() {
		HashTable<String> table = new HashTable<String>(25);
		int hash1 = table.hash("Foo");
		int hash2 = table.hash("Bar");
		int hash3 = table.hash("foo");
		int hash4 = table.hash("Foo1");
		assertTrue(hash1 != hash2);
		assertTrue(hash1 != hash3);
		assertTrue(hash2 != hash3);
		assertTrue(hash1 != hash4);
	}
	
	// Test adding one entry to a hash table
	public void testAddOneEntry() {
		HashTable<String> table = new HashTable<String>(3);
		table.put("Foo");
		assertEquals(1, table.size());
		assertTrue(!table.isEmpty());
		assertTrue(table.contains("Foo"));
	}
	
	// Test getting one entry from a hash table
	public void testGetOneEntry() {
		HashTable<String> table = new HashTable<String>(3);
		table.put("Foo");
		int hash = table.hash("Foo");
		String result = table.get(hash);
		assertEquals(1, table.size());
		assertTrue(!table.isEmpty());
		assertTrue(table.contains("Foo"));
		assertEquals("Foo", result);
	}
	
	// Test adding multiple entries to a hash table
	public void testAddMulEntries() {
		HashTable<String> table = new HashTable<String>(5);
		table.put("Foo");
		table.put("Foo");
		table.put("Bar");
		table.put("FooBar");
		table.put("Douglas Adams");
		table.put("42");
		table.put("42");
		assertEquals(5, table.size());
		assertTrue(!table.isEmpty());
		assertTrue(table.contains("Foo"));
		assertTrue(table.contains("Bar"));
		assertTrue(table.contains("FooBar"));
		assertTrue(table.contains("Douglas Adams"));
		assertTrue(table.contains("42"));
	}

}
