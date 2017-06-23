package queue;

public interface Queue<E> {

	public boolean isEmpty();
	
	public boolean enqueue(E data);
	
	public E dequeue();
	
	public E peek();
}
