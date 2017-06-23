package stack;

public interface Stack<E> {

	public boolean isEmpty();
	
	public E push(E data);
	
	public E pop();
	
	public E peek();
	
}
