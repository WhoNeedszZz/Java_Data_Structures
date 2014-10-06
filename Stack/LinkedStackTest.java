/**
 * Michael J. Cusack
 */

import junit.framework.TestCase;

/*
 * A simple tester for LinkedStack<T>
 */
public class LinkedStackTest extends TestCase {

	public LinkedStackTest(String name) {
		super(name);
	}
	
	// Test the size of an empty stack
	public void testSizeEmpty() {
		Stack<Integer> intStack = new LinkedStack<Integer>();
		assertTrue(intStack.isEmpty());
	}
	
	// Test pushing one element onto the stack
	public void testPushOneElement() {
		Stack<Integer> intStack = new LinkedStack<Integer>();
		Integer num = 42;
		intStack.push(num);
		assertTrue(!intStack.isEmpty());
		assertEquals(num, intStack.top());
	}
	
	// Test pushing multiple elements onto the stack
	public void testPushMulElements() {
		Stack<Integer> intStack = new LinkedStack<Integer>();
		Integer num1 = 3;
		Integer num2 = 17;
		Integer num3 = 42;
		intStack.push(num1);
		intStack.push(num2);
		intStack.push(num3);
		assertTrue(!intStack.isEmpty());
		assertEquals(num3, intStack.top());
	}
	
	// Test popping a one element stack
	public void testPopOneElement() {
		Stack<Integer> intStack = new LinkedStack<Integer>();
		Integer num = 42;
		intStack.push(num);
		intStack.pop();
		assertTrue(intStack.isEmpty());
	}
	
	// Test popping multiple elements from a three element stack
	public void testPopMulElements() {
		Stack<Integer> intStack = new LinkedStack<Integer>();
		Integer num1 = 3;
		Integer num2 = 17;
		Integer num3 = 42;
		intStack.push(num1);
		intStack.push(num2);
		intStack.push(num3);
		intStack.pop();
		assertTrue(!intStack.isEmpty());
		assertEquals(num2, intStack.top());
		intStack.pop();
		intStack.pop();
		assertTrue(intStack.isEmpty());
	}

}
