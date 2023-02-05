
import java.util.*;

public class ArrayStack<E> implements LIFOStack<E> {

	private E[] dataArray;
	private int top;

	/**
	 * Creates an empty stack.
	 */
	public ArrayStack() {
		dataArray = (E[])new Object[1];
		top = -1;
	}

	/**
	 * Pushes the given element on this stack
	 * @param element The element of type E to push on the stack.
	 */
	public void push(E element) {
		if (top == dataArray.length-1)
			reallocate();
		top++;
		dataArray[top] = element;
	}
	
	/**
	 * Determines if the stack is empty or not.
	 * @return true if the stack is empty or false otherwise
	 */
	public boolean isEmpty() {
		return top == -1;
	}

	/**
	 * Returns but does not remove the top element of the stack if the stack is not empty.
	 * @return The top element of the stack
	 * @throws NoSuchElementException if the stack is empty
	 */
	public E peek() {
		if (isEmpty())
			throw new NoSuchElementException();
		return dataArray[top];
	}

	/**
	 * Returns and removes the top element of the stack if the stack is not empty.
	 * @return The top element of the stack
	 * @throws NoSuchElementException if the stack is empty
	 */
	public E pop() {
		if (isEmpty())
			throw new NoSuchElementException();
		E result = dataArray[top];
		top--;
		return result;		
	}
		
	private void reallocate() {
		E[] newArray = (E[]) new Object[dataArray.length*2];
		System.arraycopy(dataArray, 0, newArray,0,dataArray.length);
		dataArray = newArray;
	}
	
	public String toString() {
		String result = "";
		for (int i = 0; i <= top; i++)
			result += dataArray[i] + " ";
		return result;
	}
	
	public static void main(String[] args) {
		
		// Using the stacks
		
		ArrayStack<Integer> s = new ArrayStack<Integer>();
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
