package project3;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * <p>Title: Linked List Class</p>
 * <p>Description: A generic Linked List class that utilizes recursion for insertion and deletion</p>
 * N#: N00918949
 * @author Gaetano Re
 * @param <E>
 */
public class LinkedList<E> implements Iterable<E>{
	
	private Node<E> head; // The head of the list
	private int length; // The length of the list
	
	/**
	 * <p>Title: Node Class</p>
	 * <p>Description: Creates a node object that will be utilized in the list</p>
	 * @param <E>
	 */
	private class Node<E>{
		private E data; // Data contained in the node
		private Node<E> next; // The next Node in the list
		
		/**
		 * parameterized constructor - takes a generic datatype as a parameter and creates a node object
		 * @param data to contain within the node
		 */
		private Node(E data) {
			this.data = data;
			this.next = null;
		}
	}
	
	@Override
	public Iterator<E> iterator() {
		return new LinkedListIterator();
	}
	
	private class LinkedListIterator implements Iterator<E>{
		private Node<E> current = head;

		@Override
		public boolean hasNext() {
			return current != null;
		}

		@Override
		public E next() {
			if(current == null) {
				throw new NoSuchElementException();
			}
			
			E value = current.data;
			current = current.next;
			return value;
		}
		
		
	}
	
	/**
	 * default constructor - initializes the linked list by setting the head to null and length to 0
	 */
	public LinkedList(){
		head = null;
		length = 0;
	}
	/**
	 * 
	 * @param data
	 */
	public void insert(E data) {
		insert(head, new Node<E>(data));
		++length;
	}
	
	public E search(E data) {
		return search(head, data);
	}
	
	public void delete(E data) {
		head = delete(head, data);
		--length;
	}
	
	public int length() {
		return length;
	}
	
	public String toString() {
		StringBuilder strbld = new StringBuilder();
		Node<E> current = head;
		while(current != null) {
			strbld.append(current.data);
			strbld.append(", ");
			current = current.next;
		}
		
		return strbld.toString();
	}
	
	
	private void insert(Node<E> current, Node<E> data) {
		if(isEmpty()) {
			head = data;
		}
		else if(current.next == null) {
			current.next = data;;
		}
		else {
			insert(current.next, data);
		}
	}
	
	public boolean isEmpty() {
		return head == null;
	}
	
	private E search(Node<E> current, E data) {
		if(current == null) {
			return null;
		}
		if(current.data.equals(data)) {
			return current.data;
		}
		
		return search(current.next, data);
	}
	
	private Node<E> delete(Node<E> current, E data) {
		if(current == null) {
			return null;
		}
		if(current.data.equals(data)) {
			return current.next;
		}
		current.next = delete(current.next, data);
		return current;
	}
	

}
