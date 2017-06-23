/* 
 * Linked list based queue
 * ���� ����Ʈ ��� ť
 * rear������ ������ �߰�, front������ ������ ��ȯ
 *  
 */

package queue;

import list.Node;

public class LinkedListBasedQueue<E> implements Queue<E> {

	private Node<E> front;
	private Node<E> rear;
	
	public LinkedListBasedQueue() {
		this.front = null;
		this.rear = null;
	}
	
	@Override
	public boolean isEmpty() {
		return front == null;
	}
	
	// ������ �߰�
	@Override
	public boolean enqueue(E data) {
		if(data == null) {
			return false;
		}
		Node<E> newNode = new Node<>(data);
		
		if(isEmpty()) {
			front = newNode;
			rear = newNode;
		} else {
			rear.setNext(newNode);
			rear = newNode;
		}
		
		return true;
	}
	
	// ������ ��ȯ �� ����
	@Override
	public E dequeue() {
		if(isEmpty()) {
			return null;
		}
		
		E data = front.getData();
		front = front.getNext();
		
		return data;
	}
	
	// ������ ��ȯ(���� x)
	@Override
	public E peek() {
		return (isEmpty()) ? null : front.getData();
	}

}

