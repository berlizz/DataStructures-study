package list;

public interface LinkedList<E> {

	public boolean isEmpty();
	
	public void add(E data);
	
	public E getFirstData();
	
	public E getNextData();
	
	public boolean contains(E data);
	
	public E remove(E data);
	
	public int size();
}
