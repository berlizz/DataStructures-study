package list;

public class BidirectionalNode<E> extends Node<E> {

	private BidirectionalNode<E> prev;
	
	public BidirectionalNode() {
	}
	
	public BidirectionalNode(E data) {
		super.data = data;
		super.next = null;
		this.prev = null;
	}

	@Override
	public String toString() {
		return "BidirectionalNode [prev=" + prev + ", data=" + data + ", next=" + next + "]";
	}

	public final BidirectionalNode<E> getPrev() {
		return prev;
	}

	public final void setPrev(BidirectionalNode<E> prev) {
		this.prev = prev;
	}
	
}
