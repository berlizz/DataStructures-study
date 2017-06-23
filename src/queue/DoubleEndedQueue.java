/* 
 * Double-ended queue
 * ��(Deque)
 * �� �� �ι���(head, tail)���� �����͸� �ְ� �� �� �ִ� ����
 * ���� ���� ����Ʈ ������� ���� 
 *  
 */

package queue;

import list.BidirectionalNode;

public class DoubleEndedQueue<E> implements Queue<E> {

	private BidirectionalNode<E> front;
	private BidirectionalNode<E> rear;
	
	public DoubleEndedQueue() {
		this.front = null;
		this.rear = null;
	}
	
	@Override
	public boolean isEmpty() {
		// head �Ǵ� tail�� null�� ��� true
		// remove �ϴٰ� head�� tail�� �� �� �ϳ���  null�� �Ǹ� ť�� ������ ����
		return (front == null || rear == null);
	}
	
	// rear�ʿ� ������ �߰�
	@Override
	public boolean enqueue(E data) {
		if(data == null) {
			return false;
		}
		
		BidirectionalNode<E> newNode = new BidirectionalNode<>(data);
		if(isEmpty()) {
			front = newNode;
			rear = newNode;
		} else {
			rear.setNext(newNode);
			newNode.setPrev(rear);
			rear = newNode;
		}
		
		return true;
	}
	
	// front�� ������ ��ȯ �� ����
	@Override
	public E dequeue() {
		if(isEmpty()) {
			return null;
		}
		
		E data = front.getData();
		front = (BidirectionalNode<E>) front.getNext();
		if(front != null) {
			front.setPrev(null);
		}
		
		return data;
	}
	
	// front�� ������ ��ȯ �� ����
	@Override
	public E peek() {
		return (isEmpty())? null : front.getData();
	}
	
	// front�ʿ� ������ �߰�
	public boolean addFirst(E data) {
		if(data == null) {
			return false;
		}
		
		BidirectionalNode<E> newNode = new BidirectionalNode<>(data);
		if(isEmpty()) {
			front = newNode;
			rear = newNode;
		} else {
			newNode.setNext(front);
			front.setPrev(newNode);
			front = newNode;
		}
		
		return true;
	}
	
	// rear�ʿ� ������ �߰�
	public boolean addLast(E data) {
		return enqueue(data);
	}
	
	// front�� ������ ��ȯ �� ����
	public E removeFirst() {
		return dequeue();
	}
	
	// rear�� ��ġ�� ��ȯ �� ����
	public E removeLast() {
		if(isEmpty()) {
			return null;
		}
		
		E data = rear.getData();
		rear = rear.getPrev();
		if(rear != null) {
			rear.setNext(null);
		}
		
		return data;
	}
	
	// front�� ������ ��ȯ
	public E getFirst() {
		return peek();
	}
	
	// rear�� ������ ��ȯ
	public E getLast() {
		return (isEmpty())? null : rear.getData();
	}

}
