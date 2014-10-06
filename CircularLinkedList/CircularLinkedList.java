/*
 * Michael J. Cusack
 */

import nhUtilities.containers2.Iterator;
import nhUtilities.containers2.List;

/**
 * An implementation of List using circularly linked nodes
 */
public class CircularLinkedList<Element> 
        extends nhUtilities.containers2.AbstractList<Element> {
    
    private Node header;
    private Node last;
    private int size;
    
    /**
     * @require
     *      N/A
     * @ensure
     *      Creates an empty circularly linked list with a header node with 
     *          element null
     */
    public CircularLinkedList() {
        size = 0;
        header = new Node(null);
        last = header;
    }
    
    /**
     * @require
     *      N/A
     * @ensure
     *      Returns the size of the list
     */
    @Override
    public int size() {
        return size;
    }
    
    /**
     * @require
     *      this != null
     *      index != null && index < size
     * @ensure
     *      Returns the element from the node at given index
     */
    @Override
    public Element get(int index) {
        Node theNode = getNode(index);
        return theNode.element;
    }
    
    /**
     * @require
     *      this != null
     *      index != null && index < size
     * @ensure
     *      Helper method that returns the node at given index
     */
    private Node getNode(int index) {
        Node current = header;
        int position = -1;
        
        // Move from node to node until the index-th position
        while (position != index) {
            current = current.next;
            position = position + 1;
        }
        
        return current;
    }
    
    /**
     * @require
     *      element != null
     * @ensure
     *      Add the given element to the end of the list
     */
    @Override
    public void add(Element element) {
        Node newNode = new Node(element);
        
        last.next = newNode;
        last = newNode;
        size = size + 1;
    }
    
    /**
     * @require
     *      element != null
     *      index != null && index <= size
     * @ensure
     *      Adds the given element at position index in the list
     */
    @Override
    public void add(int index, Element element) {
        Node newNode = new Node(element);
        Node previous = getNode(index-1);
        
        previous.next = newNode;
        size = size + 1;
    }
    
    /**
     * @require
     *      addIter != null
     *      element != null
     * @ensure
     *      Adds the element from addIter's current reference to the list
     * 
     * Usage: add(addIter, addIter.get());
     */
    @Override
    public void add(Iterator<Element> addIter, Element element) {
        Node newNode = new Node(element);
        Node previous = getNode(size-1);
        
        previous.next = newNode;
        size = size + 1;
    }
    
    /**
     * @require
     *      index != null && index < size
     * @ensure
     *      Removes the node at given index from the list
     */
    @Override
    public void remove(int index) {
        Node previous = getNode(index-1);
        
        previous.next = previous.next.next;
        size = size - 1;
    }
    
    /**
     * @require
     *      remIter != null
     *      remIter.traverses(this)
     * @ensure
     *      Removes the node from remIter's current reference from the list
     */
    @Override
    public void remove(Iterator<Element> remIter) {
        LinkedIterator iter = (LinkedIterator)remIter;
        
        iter.previous.next = iter.previous.next.next;
        size = size - 1;
    }
    
    /**
     * @require
     *      N/A
     * @ensure
     *      Returns an iterator which traverses the list
     */
    @Override
    public Iterator<Element> iterator() {
        return new LinkedIterator();
    }
    
    /**
     * @require
     *      N/A
     * @ensure
     *      this.size() == listCopy.size()
     *      Returns a copy of the list
     */
    @Override
    public List<Element> copy() {
        CircularLinkedList<Element> listCopy = new CircularLinkedList<Element>();
        Node previous = header;
        Node copyPrev = listCopy.header;
        
        while (previous.next != header) {
            copyPrev.next = new Node(previous.next.element);
            previous = previous.next;
            copyPrev = copyPrev.next;
        }
        
        listCopy.size = this.size;
        
        return listCopy;
    }
    
    /**
     * @require
     *      this != null
     * @ensure
     *      Computes a hash code from the size and each element of the list
     */
    @Override
    public int hashCode() {
        int hash = 3;
        Iterator<Element> iter = this.iterator();
        
        hash = 61 * hash + this.size;
        while (!iter.done()) {
            hash = 61 * hash + iter.get().hashCode();
            iter.advance();
        }
        
        return hash;
    }
    
    /**
     * @require
     *      obj != null
     * @ensure
     *      Returns true if obj is a CircularLinkedList of the same size and 
     */
    @Override
    public boolean equals(Object obj) {
        boolean isEquals = false;
        if (obj instanceof CircularLinkedList<?>) {
            CircularLinkedList<Element> other = (CircularLinkedList<Element>)obj;
            if (this.hashCode() == other.hashCode())
                isEquals = true;
        }
        
        return isEquals;
    }
    
    /**
     * @require
     *      N/A
     * @ensure
     *      Throws an UnsupportedOperationException as set is not supported
     */
    @Override
    public void set(int index, Element element) {
        throw new UnsupportedOperationException("CircularLinkedList.set()");
    }
    
    /**
     * @require
     *      N/A
     * @ensure
     *      Throws an UnsupportedOperationException as set is not supported
     */
    @Override
    public void set(Iterator<Element> iter, Element element) {
        throw new UnsupportedOperationException("CircularLinkedList.set()");
    }
    
    /**
     * A helper class which creates a single node containing an element and a
     * reference to the next node
     */
    private class Node {
        
        private Element element;
        private Node next;
        
        /**
         * @require
         *      N/A
         * @ensure
         *      Creates a new node with given element and a reference back to
         *      the header so that the list is circular
         */
        public Node (Element newElement) {
            element = newElement;
            next = header;
        }
    }
    
    /**
     * A helper class to iterate through a linked-based list
     */
    private class LinkedIterator 
            extends nhUtilities.containers2.AbstractIterator<Element> {
    
        private Node previous;

        /**
         * @require
         *      N/A
         * @ensure
         *      Creates a new iterator traversing this list
         */
        public LinkedIterator() {
            reset(); 
        }

        /**
         * @require
         *      N/A
         * @ensure
         *      Returns previous reference to the header of the list
         */
        @Override
        public void reset() {
            previous = CircularLinkedList.this.header;
        }

        /**
         * @require
         *      N/A
         * @ensure
         *      Returns the current element in the list
         */
        @Override
        public Element get() {
            return previous.next.element;
        }

        /**
         * @require
         *      N/A
         * @ensure
         *      Advances the iterator to the next node
         */
        @Override
        public void advance() {
            previous = previous.next;
        }

        /**
         * @require
         *      N/A
         * @ensure
         *      Returns true if the next element is the header(null), 
         *      false if not
         */
        @Override
        public boolean done() {
            return previous.next.element == null;
        }

        /**
         * @require
         *      list != null
         * @ensure
         *      Returns true if the iterator references this list,
         *      false if not
         */
        @Override
        public boolean traverses(Object list) {
            return list == CircularLinkedList.this;
        }
        
        /**
         * @require
         *      other != null
         * @ensure
         *      Points this iterator's reference to the other iterator
         */
        @Override
        public void setEqualTo(Iterator<Element> other) {
            this.previous = ((LinkedIterator)other).previous;
        }
    }
}
