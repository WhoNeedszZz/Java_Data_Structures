/*
 * Michael J. Cusack
 */

import nhUtilities.containers2.List;
import junit.framework.TestCase;

public class LinkedBagTest extends TestCase {

	public LinkedBagTest(String name) {
		super(name);
	}
	
	// Test initial state of an empty bag
	public void testEmptyBagState() {
		Bag<String> bag = new LinkedBag<String>();
		
		assertEquals(bag.size(), 0);
		assertTrue(bag.isEmpty());
	}
	
	// Test equals()
	public void testEquals() {
		BagItem<String> apples1 = new BagItem<String>("Apple", 3);
		BagItem<String> apples2 = new BagItem<String>("Apple", 2);
		
		assertTrue(apples1.equals(apples2));
		assertTrue(apples2.equals(apples1));
	}
	
	// Test adding one element to an empty bag
	public void testAddEmptyOneElement() {
		Bag<String> bag = new LinkedBag<String>();
		BagItem<String> apples = new BagItem<String>("Apple", 5);
		
		bag.add(apples);
		
		assertEquals(bag.size(), 1);
		assertTrue(bag.isMember(apples));
		assertEquals(bag.count(apples), 5);
		assertTrue(!bag.isEmpty());
	}
	
	// Test adding multiple elements to the list
	public void testAddMulElements() {
		Bag<String> bag = new LinkedBag<String>();
		BagItem<String> apples = new BagItem<String>("Apple", 5);
		BagItem<String> bananas = new BagItem<String>("Banana", 3);
		
		bag.add(apples);
		bag.add(bananas);
		
		assertEquals(bag.size(), 2);
		assertTrue(bag.isMember(apples));
		assertTrue(bag.isMember(bananas));
		assertEquals(bag.count(apples), 5);
		assertEquals(bag.count(bananas), 3);
		assertTrue(!bag.isEmpty());
	}
	
	// Test adding to an element already in the bag 1 element
	public void testAddExistsOneElement() {
		Bag<String> bag = new LinkedBag<String>();
		BagItem<String> apples1 = new BagItem<String>("Apple", 3);
		BagItem<String> apples2 = new BagItem<String>("Apple", 2);
		
		bag.add(apples1);
		bag.add(apples2);
		
		assertEquals(bag.size(), 1);
		assertTrue(bag.isMember(apples1));
		assertTrue(bag.isMember(apples2));
		assertEquals(bag.count(apples1), 5);
		assertTrue(!bag.isEmpty());
	}
	
	// Test adding an element that already exists to a multiple item bag
	public void testAddExistsMulElements() {
		Bag<String> bag = new LinkedBag<String>();
		BagItem<String> apples1 = new BagItem<String>("Apple", 3);
		BagItem<String> bananas = new BagItem<String>("Banana", 3);
		BagItem<String> apples2 = new BagItem<String>("Apple", 2);
		
		bag.add(apples1);
		bag.add(bananas);
		bag.add(apples2);
		
		assertEquals(bag.size(), 2);
		assertTrue(bag.isMember(apples1));
		assertTrue(bag.isMember(bananas));
		assertTrue(bag.isMember(apples2));
		assertEquals(bag.count(apples1), 5);
		assertEquals(bag.count(bananas), 3);
		assertTrue(!bag.isEmpty());
	}
	
	// Test remove all 1 element from a 1 element bag
	public void testOneRemoveAllOneElement() {
		Bag<String> bag = new LinkedBag<String>();
		BagItem<String> apples = new BagItem<String>("Apple", 5);
		
		bag.add(apples);
		bag.remove(apples);
		
		assertEquals(bag.size(), 0);
		assertTrue(bag.isEmpty());
		assertTrue(!bag.isMember(apples));
		assertEquals(bag.count(apples), 0);
	}
	
	// Test remove some 1 element from a 1 element bag
	public void testOneRemoveSomeOneElement() {
		Bag<String> bag = new LinkedBag<String>();
		BagItem<String> apples1 = new BagItem<String>("Apple", 5);
		BagItem<String> apples2 = new BagItem<String>("Apple", 3);
		
		bag.add(apples1);
		bag.remove(apples2);
		
		assertEquals(bag.size(), 1);
		assertTrue(!bag.isEmpty());
		assertTrue(bag.isMember(apples1));
		assertEquals(bag.count(apples1), 2);
	}
	
	// Test remove all 1 element from a multiple element bag
	public void testOneRemoveAllMulElement() {
		Bag<String> bag = new LinkedBag<String>();
		BagItem<String> apples = new BagItem<String>("Apple", 5);
		BagItem<String> bananas = new BagItem<String>("Banana", 3);
		
		bag.add(apples);
		bag.add(bananas);
		bag.remove(apples);
		
		assertEquals(bag.size(), 1);
		assertTrue(!bag.isEmpty());
		assertTrue(!bag.isMember(apples));
		assertTrue(bag.isMember(bananas));
		assertEquals(bag.count(apples), 0);
		assertEquals(bag.count(bananas), 3);
	}
	
	// Test remove some 1 element from a multiple element bag
	public void testOneRemoveSomeMulElement() {
		Bag<String> bag = new LinkedBag<String>();
		BagItem<String> apples1 = new BagItem<String>("Apple", 5);
		BagItem<String> bananas = new BagItem<String>("Banana", 3);
		BagItem<String> apples2 = new BagItem<String>("Apple", 3);
		
		bag.add(apples1);
		bag.add(bananas);
		bag.remove(apples2);
		
		assertEquals(bag.size(), 2);
		assertTrue(!bag.isEmpty());
		assertTrue(bag.isMember(apples1));
		assertTrue(bag.isMember(bananas));
		assertEquals(bag.count(apples1), 2);
		assertEquals(bag.count(bananas), 3);
	}
	
	// Test union of two bags no repeated elements
	public void testUnionNoRepeat() {
		Bag<String> bag = new LinkedBag<String>();
		Bag<String> bag2 = new LinkedBag<String>();
		BagItem<String> apples = new BagItem<String>("Apple", 5);
		BagItem<String> bananas = new BagItem<String>("Banana", 3);
		BagItem<String> plums = new BagItem<String>("Plum", 10);
		
		bag.add(apples);
		bag.add(plums);
		bag2.add(bananas);
		
		Bag<String> unionBag1 = bag.union(bag2);
		Bag<String> unionBag2 = bag2.union(bag);
		
		assertEquals(unionBag1.size(), 3);
		assertEquals(unionBag2.size(), 3);
		assertTrue(!unionBag1.isEmpty());
		assertTrue(!unionBag2.isEmpty());
		assertTrue(unionBag1.isMember(apples));
		assertTrue(unionBag2.isMember(apples));
		assertTrue(unionBag1.isMember(bananas));
		assertTrue(unionBag2.isMember(bananas));
		assertTrue(unionBag1.isMember(plums));
		assertTrue(unionBag2.isMember(plums));
		assertEquals(unionBag1.count(apples), 5);
		assertEquals(unionBag2.count(apples), 5);
		assertEquals(unionBag1.count(bananas), 3);
		assertEquals(unionBag2.count(bananas), 3);
		assertEquals(unionBag1.count(plums), 10);
		assertEquals(unionBag2.count(plums), 10);
	}
	
	// Test union of two bags with repeated elements
	public void testUnionRepeated() {
		Bag<String> bag = new LinkedBag<String>();
		Bag<String> bag2 = new LinkedBag<String>();
		BagItem<String> apples1 = new BagItem<String>("Apple", 3);
		BagItem<String> bananas1 = new BagItem<String>("Banana", 3);
		BagItem<String> plums = new BagItem<String>("Plum", 10);
		BagItem<String> apples2 = new BagItem<String>("Apple", 2);
		BagItem<String> bananas2 = new BagItem<String>("Banana", 2);
		
		bag.add(apples1);
		bag.add(bananas1);
		bag.add(plums);
		bag2.add(apples2);
		bag2.add(bananas2);
		
		Bag<String> unionBag = bag.union(bag2);
		
		assertEquals(unionBag.size(), 3);
		assertTrue(!unionBag.isEmpty());
		assertTrue(unionBag.isMember(apples1));
		assertTrue(unionBag.isMember(bananas1));
		assertTrue(unionBag.isMember(plums));
		assertEquals(unionBag.count(apples1), 5);
		assertEquals(unionBag.count(bananas1), 5);
		assertEquals(unionBag.count(plums), 10);
	}
	
	// Test intersection of two bags with similar elements
	public void testIntersectionSimilar() {
		Bag<String> bag = new LinkedBag<String>();
		Bag<String> bag2 = new LinkedBag<String>();
		BagItem<String> apples = new BagItem<String>("Apple", 5);
		BagItem<String> bananas = new BagItem<String>("Banana", 3);
		BagItem<String> plums = new BagItem<String>("Plum", 10);
		
		bag.add(apples);
		bag.add(bananas);
		bag.add(plums);
		bag2.add(apples);
		bag2.add(plums);
		
		Bag<String> intersectBag1 = bag.intersection(bag2);
		Bag<String> intersectBag2 = bag2.intersection(bag);
		
		assertEquals(intersectBag1.size(), 2);
		assertEquals(intersectBag2.size(), 2);
		assertTrue(!intersectBag1.isEmpty());
		assertTrue(!intersectBag2.isEmpty());
		assertTrue(intersectBag1.isMember(apples));
		assertTrue(intersectBag2.isMember(apples));
		assertTrue(!intersectBag1.isMember(bananas));
		assertTrue(!intersectBag2.isMember(bananas));
		assertTrue(intersectBag1.isMember(plums));
		assertTrue(intersectBag2.isMember(plums));
		assertEquals(intersectBag1.count(apples), 5);
		assertEquals(intersectBag2.count(apples), 5);
		assertEquals(intersectBag1.count(plums), 10);
		assertEquals(intersectBag2.count(plums), 10);
		assertEquals(intersectBag1.count(bananas), 0);
		assertEquals(intersectBag2.count(bananas), 0);
		assertTrue(intersectBag1.isContainedIn(intersectBag2));
		assertTrue(intersectBag2.isContainedIn(intersectBag1));
	}
	
	// Test intersection with dissimilar bags
	public void testIntersectionDissimilar() {
		Bag<String> bag = new LinkedBag<String>();
		Bag<String> bag2 = new LinkedBag<String>();
		BagItem<String> apples = new BagItem<String>("Apple", 5);
		BagItem<String> bananas = new BagItem<String>("Banana", 3);
		BagItem<String> plums = new BagItem<String>("Plum", 10);
		
		bag.add(apples);
		bag.add(bananas);
		bag2.add(plums);
		
		Bag<String> intersectBag = bag.intersection(bag2);
		
		assertEquals(intersectBag.size(), 0);
		assertTrue(intersectBag.isEmpty());
	}
	
	// Test difference with similar bags
	public void testDiffSimilar() {
		Bag<String> bag = new LinkedBag<String>();
		Bag<String> bag2 = new LinkedBag<String>();
		BagItem<String> apples = new BagItem<String>("Apple", 5);
		BagItem<String> bananas = new BagItem<String>("Banana", 3);
		
		bag.add(apples);
		bag.add(bananas);
		bag2.add(apples);
		bag2.add(bananas);
		
		Bag<String> diffBag = bag.difference(bag2);
		
		assertEquals(diffBag.size(), 0);
		assertTrue(diffBag.isEmpty());
	}
	
	// Test difference with dissimilar bags
	public void testDiffDissimilar() {
		Bag<String> bag = new LinkedBag<String>();
		Bag<String> bag2 = new LinkedBag<String>();
		BagItem<String> apples = new BagItem<String>("Apple", 5);
		BagItem<String> bananas = new BagItem<String>("Banana", 3);
		BagItem<String> plums = new BagItem<String>("Plum", 10);
		
		bag.add(apples);
		bag.add(bananas);
		bag2.add(plums);
		
		Bag<String> diffBag1 = bag.difference(bag2);
		Bag<String> diffBag2 = bag2.difference(bag);
		
		assertEquals(diffBag1.size(), 2);
		assertEquals(diffBag2.size(), 1);
		assertTrue(!diffBag1.isEmpty());
		assertTrue(!diffBag2.isEmpty());
		assertTrue(diffBag1.isMember(apples));
		assertTrue(!diffBag2.isMember(apples));
		assertTrue(diffBag1.isMember(bananas));
		assertTrue(!diffBag2.isMember(bananas));
		assertTrue(!diffBag1.isMember(plums));
		assertTrue(diffBag2.isMember(plums));
	}
	
	// Test xor with similar bags
	public void testXorSimilar() {
		Bag<String> bag = new LinkedBag<String>();
		Bag<String> bag2 = new LinkedBag<String>();
		BagItem<String> apples = new BagItem<String>("Apple", 5);
		BagItem<String> bananas = new BagItem<String>("Banana", 3);
		
		bag.add(apples);
		bag.add(bananas);
		bag2.add(apples);
		bag2.add(bananas);
		
		Bag<String> xorBag = bag.xor(bag2);
		
		assertEquals(xorBag.size(), 0);
		assertTrue(xorBag.isEmpty());
	}
	
	// Test xor with dissimilar bags
	public void testXorDissimilar() {
		Bag<String> bag = new LinkedBag<String>();
		Bag<String> bag2 = new LinkedBag<String>();
		BagItem<String> apples1 = new BagItem<String>("Apple", 5);
		BagItem<String> apples2 = new BagItem<String>("Apple", 3);
		BagItem<String> bananas = new BagItem<String>("Banana", 3);
		BagItem<String> plums = new BagItem<String>("Plum", 10);
		
		bag.add(apples1);
		bag.add(bananas);
		bag2.add(apples2);
		bag2.add(bananas);
		bag2.add(plums);
		
		Bag<String> xorBag = bag.xor(bag2);
		
		assertEquals(xorBag.size(), 2);
		assertTrue(!xorBag.isEmpty());
		assertTrue(xorBag.isMember(apples1));
		assertTrue(!xorBag.isMember(bananas));
		assertTrue(xorBag.isMember(plums));
		assertEquals(xorBag.count(apples1), 2);
		assertEquals(xorBag.count(bananas), 0);
		assertEquals(xorBag.count(plums), 10);
	}
	
	// Test toList()
	public void testToList() {
		Bag<String> bag = new LinkedBag<String>();
		BagItem<String> apples = new BagItem<String>("Apple", 5);
		BagItem<String> bananas = new BagItem<String>("Banana", 3);
		
		bag.add(apples);
		bag.add(bananas);
		
		List<BagItem<String>> expList = new CircularLinkedList<BagItem<String>>();
		expList.add(apples);
		expList.add(bananas);
		
		assertEquals(bag.toList(), expList);
	}
	
	// Test copy()
	public void testCopy() {
		Bag<String> bag = new LinkedBag<String>();
		BagItem<String> apples = new BagItem<String>("Apple", 5);
		BagItem<String> bananas = new BagItem<String>("Banana", 3);
		BagItem<String> plums = new BagItem<String>("Plum", 10);
		
		bag.add(apples);
		bag.add(bananas);
		bag.add(plums);
		
		Bag<String> copyBag = bag.copy();
		
		assertEquals(copyBag.size(), bag.size());
		assertTrue(bag.isContainedIn(copyBag));
		assertTrue(copyBag.isContainedIn(bag));
	}

}
