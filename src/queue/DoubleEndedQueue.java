/* 
 * Double-ended queue
 * 덱(Deque)
 * 앞 뒤 두방향(head, tail)에서 데이터를 넣고 뺄 수 있는 구조
 * 이중 연결 리스트 기반으로 구현 
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
		// head 또는 tail이 null일 경우 true
		// remove 하다가 head나 tail이 둘 중 하나라도  null이 되면 큐에 데이터 없음
		return (front == null || rear == null);
	}
	
	// rear쪽에 데이터 추가
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
	
	// front의 데이터 반환 및 삭제
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
	
	// front의 데이터 반환 및 삭제
	@Override
	public E peek() {
		return (isEmpty())? null : front.getData();
	}
	
	// front쪽에 데이터 추가
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
	
	// rear쪽에 데이터 추가
	public boolean addLast(E data) {
		return enqueue(data);
	}
	
	// front의 데이터 반환 및 제거
	public E removeFirst() {
		return dequeue();
	}
	
	// rear의 데치터 반환 및 제거
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
	
	// front의 데이터 반환
	public E getFirst() {
		return peek();
	}
	
	// rear의 데이터 반환
	public E getLast() {
		return (isEmpty())? null : rear.getData();
	}

}
