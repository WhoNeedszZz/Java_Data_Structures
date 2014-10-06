/*
 * Michael J. Cusack
 */

import junit.framework.TestCase;
import nhUtilities.containers2.Iterator;
import nhUtilities.containers2.List;

/**
 *  A tester for the CircularLinkedList class
 */
public class CircularLinkedListTest extends TestCase {
    
    public CircularLinkedListTest(String name) {
        super(name);
    }

    /**
     * Test of size method of empty list
     */
    public void testSizeEmpty() {
        CircularLinkedList<Integer> list = new CircularLinkedList<Integer>();
        int expResult = 0;
        int result = list.size();
        assertEquals(expResult, result);
    }
    
    /**
     * Test of size method non-empty list
     */
    public void testSizeNonEmpty() {
        CircularLinkedList<Integer> list = new CircularLinkedList<Integer>();
        list.add(1);
        list.add(3);
        list.add(5);
        int expResult = 3;
        int result = list.size();
        assertEquals(expResult, result);
    }

    /**
     * Test of iterator method
     */
    public void testIterator() {
        CircularLinkedList<Integer> list = new CircularLinkedList<Integer>();
        Iterator<Integer> iter = list.iterator();
        assertTrue(iter.traverses(list));
    }
    
    /**
     * Test of indexOf method
     */
    public void testIndexOf() {
        CircularLinkedList<Integer> list = new CircularLinkedList<Integer>();
        list.add(1);
        list.add(5);
        list.add(17);
        list.add(36);
        list.add(42);
        assertEquals(list.indexOf(1), 0); // First element
        assertEquals(list.indexOf(17), 2); // Middle element
        assertEquals(list.indexOf(42), 4); // Last element
    }
    
    /**
     * Test of get method
     */
    public void testGet() {
        CircularLinkedList<Integer> list = new CircularLinkedList<Integer>();
        list.add(3);
        list.add(42);
        list.add(69);
        int expResult = 42;
        int result = list.get(1);
        assertEquals(expResult, result);
    }

    /**
     * Test of add method to an empty list at end
     */
    public void testAddEmpty() {
        CircularLinkedList<Integer> list = new CircularLinkedList<Integer>();
        list.add(42);
        boolean expResult = true;
        boolean result = list.contains(42);
        assertEquals(expResult, result);
        assertEquals(list.size(), 1);
    }

    /**
     * Test of add method to a 1 element list at end
     */
    public void testAddOneElementEnd() {
        CircularLinkedList<Integer> list = new CircularLinkedList<Integer>();
        list.add(3);
        list.add(42);
        int expResult = 42;
        int result = list.get(1);
        assertEquals(expResult, result);
        assertEquals(list.size(), 2);
    }
    
    /**
     * Test of add method to a 1 element list at beginning
     */
    public void testAddOneElementBeg() {
        CircularLinkedList<Integer> list = new CircularLinkedList<Integer>();
        list.add(3);
        list.add(0, 42);
        int expResult = 42;
        int result = list.get(0);
        assertEquals(expResult, result);
        assertEquals(list.size(), 2);
    }
    
    /**
     * Test of add method to a multiple element list at end
     */
    public void testAddMulElementEnd() {
        CircularLinkedList<Integer> list = new CircularLinkedList<Integer>();
        list.add(3);
        list.add(87);
        list.add(104);
        list.add(42);
        int expResult = 42;
        int result = list.get(3);
        assertEquals(expResult, result);
        assertEquals(list.size(), 4);
    }
    
    /**
     * Test of add method to a multiple element list at beginning
     */
    public void testAddMulElementBeg() {
        CircularLinkedList<Integer> list = new CircularLinkedList<Integer>();
        list.add(3);
        list.add(87);
        list.add(104);
        list.add(0, 42);
        int expResult = 42;
        int result = list.get(0);
        assertEquals(expResult, result);
        assertEquals(list.size(), 4);
    }
    
    /**
     * Test of add method with iterator
     */
    public void testAddIterator() {
        CircularLinkedList<Integer> list1 = new CircularLinkedList<Integer>();
        list1.add(1);
        list1.add(33);
        list1.add(42);
        Iterator<Integer> iter = list1.iterator();
        CircularLinkedList<Integer> list2 = new CircularLinkedList<Integer>();
        
        // Iterate through list1, adding each element to list2 
        while (!iter.done()) {
            list2.add(iter, iter.get());
            iter.advance();
        }
        
        assertTrue(list2.get(0) == 1);
        assertTrue(list2.get(1) == 33);
        assertTrue(list2.get(2) == 42);
        assertEquals(list2.size(), 3);
    }

    /**
     * Test of remove method with 1 element in list
     */
    public void testRemoveOneElement() {
        CircularLinkedList<Integer> list = new CircularLinkedList<Integer>();
        list.add(42);
        list.remove(0);
        assertEquals(list.size(), 0);
        assertTrue(!list.contains(42));
    }

    /**
     * Test of remove method with multiple elements from the beginning
     */
    public void testRemoveMulElementBeg() {
        CircularLinkedList<Integer> list = new CircularLinkedList<Integer>();
        list.add(1);
        list.add(17);
        list.add(36);
        list.add(42);
        list.remove(0);
        assertTrue(!list.contains(1));
        assertEquals(list.size(), 3);
    }
    
    /**
     * Test of remove method with multiple elements from the middle
     */
    public void testRemoveMulElementMid() {
        CircularLinkedList<Integer> list = new CircularLinkedList<Integer>();
        list.add(1);
        list.add(17);
        list.add(36);
        list.add(42);
        list.remove(2);
        assertTrue(!list.contains(36));
        assertEquals(list.size(), 3);
    }
    
    /**
     * Test of remove method with multiple elements from the end
     */
    public void testRemoveMulElementEnd() {
        CircularLinkedList<Integer> list = new CircularLinkedList<Integer>();
        list.add(1);
        list.add(17);
        list.add(36);
        list.add(42);
        list.remove(3);
        assertTrue(!list.contains(42));
        assertEquals(list.size(), 3);
    }
    
    /**
     * Test of remove method with iterator
     */
    public void testRemoveIterator() {
        CircularLinkedList<Integer> list = new CircularLinkedList<Integer>();
        list.add(1);
        list.add(17);
        list.add(42);
        Iterator<Integer> iter = list.iterator();
        iter.advance();
        list.remove(iter);
        assertTrue(!list.contains(17));
        assertEquals(list.size(), 2);
    }
    
    /**
     * Test of equals method
     */
    public void testEquals() {
        CircularLinkedList<Integer> list1 = new CircularLinkedList<Integer>();
        list1.add(1);
        list1.add(17);
        list1.add(42);
        List<Integer> list2 = (CircularLinkedList<Integer>)list1.copy();
        assertTrue(list1.equals(list2));
        assertTrue(list2.equals(list1));
    }
    
    /**
     * Test of copy method
     */
    public void testCopy() {
        CircularLinkedList<Integer> list = new CircularLinkedList<Integer>();
        list.add(1);
        list.add(5);
        list.add(42);
        List<Integer> copyList = list.copy();
        assertTrue(list.equals(copyList));
    }
}
