package list;

public class Node<E> {
	
	public E data;
	public Node<E> next;
	
	Node() {
	}
	
	Node(E data) {
		this.data = data;
		this.next = null;
	}

	@Override
	public String toString() {
		return "Node [data=" + data + ", next=" + next + "]";
	}

	public final E getData() {
		return data;
	}

	public final void setData(E data) {
		this.data = data;
	}

	public final Node<E> getNext() {
		return next;
	}

	public final void setNext(Node<E> next) {
		this.next = next;
	}
	
}
