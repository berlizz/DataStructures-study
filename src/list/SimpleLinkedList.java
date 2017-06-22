/*
 *	Simple linked list
 *	연결 기반의 리스트
 *	헤드쪽으로 데이터 추가
 */

package list;

public class SimpleLinkedList<E> implements LinkedList<E> {

	private Node<E> head;
	private Node<E> position;
	private Node<E> before;
	private int size;
	
	public SimpleLinkedList() {
		this.head = new Node<E>();  // 더미노드 생성
		this.position = head;
		this.before = null;
		this.size = 0;
		
		head.setNext(null);
	}
	
	@Override
	public boolean isEmpty() {
		return size == 0;
	}
	
	// 노드 추가
	@Override
	public void add(E data) {
		Node<E> newNode = new Node<>(data);
		newNode.setNext(head.getNext());
		head.setNext(newNode);
		size++;
	}
	
	// 첫번째 노드 가져오기
	@Override
	public E getFirstData() {
		if(isEmpty()) {
			return null;
		}
		
		before = head;
		position = head.getNext();
		
		return position.getData();
	}
	
	// 다음 노드 가져오기
	@Override
	public E getNextData() {
		if(position.getNext() == null) {
			return null;
		}
		
		before = position;
		position = position.getNext();
		
		return position.getData();
	}
	
	// 노드 조회
	@Override
	public boolean contains(E data) {
		E check = getFirstData();
		
		if(check != null && check.equals(data)) {
			return true;
		}
		
		while(true) {
			check = getNextData();
			
			if(check == null) {
				break;
			}
			
			if(check.equals(data)) {
				return true;
			}			
		}
		
		return false;
	}
	
	// 노드 삭제
	@Override
	public E remove(E data) {
		E check = getFirstData();
		if(check.equals(data)) {
			before.setNext(position.getNext());
			position = before;
			size--;
			
			return check;
		}
		
		while(true) {
			check = getNextData();
			if(check == null) {
				break;
			}
			
			if(check.equals(data)) {
				before.setNext(position.getNext());
				position = before;
				size--;
				
				return check;
			}
		}
		
		return null;
	}
	
	@Override
	public int size() {
		return size;
	}
}
