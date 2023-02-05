import java.util.*;

public class ListStack<E> implements LIFOStack<E> {

	private Node<E> top;

	/**
	 * Creates an empty stack.
	 */
	public ListStack() {
		top = null;
	}

	/**
	 * Pushes the given element on this stack
	 * @param element The element of type E to push on the stack.
	 */
	public void push(E element) {
		Node<E> newNode = new Node<E>(element);
		newNode.next = top;
		top = newNode;		
	}
	
	/**
	 * Determines if the stack is empty or not.
	 * @return true if the stack is empty or false otherwise
	 */
	public boolean isEmpty() {
		return top == null;
	}

	/**
	 * Returns but does not remove the top element of the stack if the stack is not empty.
	 * @return The top element of the stack
	 * @throws NoSuchElementException if the stack is empty
	 */
	public E peek() {
		if (isEmpty())
			throw new NoSuchElementException();
		return top.data;
	}

	/**
	 * Returns and removes the top element of the stack if the stack is not empty.
	 * @return The top element of the stack
	 * @throws NoSuchElementException if the stack is empty
	 */
	public E pop() {
		if (isEmpty())
			throw new NoSuchElementException();
		E result = top.data;
		top = top.next;
		return result;
	}
	
	public String toString() {
		String result = "";
		Node<E> nodePtr = top;
		while (nodePtr != null) {
			result += nodePtr.data + " ";
			nodePtr = nodePtr.next;
		}
		return result;
	}
	
	private static class Node<E> {
		private E data;
		private Node<E> next;
		public Node(E dataEntry) {
			data = dataEntry;
			next = null;
		}
		public Node(E dataEntry, Node<E> nodeRef) {
			data = dataEntry;
			next = nodeRef;
		}
	}

	public static void main(String[] args) {
		
		// Using the stacks
		
		ListStack<Integer> s = new ListStack<Integer>();
		s.push(1);
		s.push(2);
		s.push(3);
		System.out.println(s.peek());
		System.out.println(s.pop());
		System.out.println(s.pop());
		System.out.println(s.isEmpty());
		System.out.println(s.pop());
		System.out.println(s.isEmpty());		
		System.out.println(s.pop());
	}
	
}
