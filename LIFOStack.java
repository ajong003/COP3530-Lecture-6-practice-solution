
public interface LIFOStack<E> {

	// Interface to specify operations for a simple unbounded LIFO stack
	
	public void push(E element);
	// Insert element at top of stack
	
	public boolean isEmpty();
	// Is the stack empty?
	
	public E pop();
	// Remove element from top of stack

	public E peek();
	// Examine element at top of stack

}
