/* 
 * Linked list based queue
 * 연결 리스트 기반 큐
 * rear쪽으로 데이터 추가, front쪽으로 데이터 반환
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
	
	// 데이터 추가
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
	
	// 데이터 반환 및 삭제
	@Override
	public E dequeue() {
		if(isEmpty()) {
			return null;
		}
		
		E data = front.getData();
		front = front.getNext();
		
		return data;
	}
	
	// 데이터 반환(삭제 x)
	@Override
	public E peek() {
		return (isEmpty()) ? null : front.getData();
	}

}

