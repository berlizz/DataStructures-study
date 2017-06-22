/*
 *	Sorted linked list
 *	연결 기반의 리스트
 *	노드의 data값을 비교하여 데이터 추가
 *	
 */

package list;

public class SortedLinkedList<E> implements LinkedList<E> {

	private Node<E> head;
	private Node<E> position;
	private Node<E> before;
	private Node<E> comparator;
	private int size;
	
	public SortedLinkedList() {
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
		comparator = head;
		
		// 다음 노드가 null이 아니고, 생성된 노드보다 우선순위가 높은경우(헤더쪽에 가까운 경우)
		while(comparator.getNext() != null && compare(comparator.getNext().getData(), data) > 0) {
			comparator = comparator.getNext();
		}
		
		newNode.setNext(comparator.getNext());
		comparator.setNext(newNode);
		size++;
	}
	
	// 첫번째 데이터 가져오기
	@Override
	public E getFirstData() {
		if(isEmpty() ) {
			return null;
		}
		
		position = head.getNext();
		before = head;
		
		return position.getData();
	}
	
	// 다음 데이터 가져오기
	@Override
	public E getNextData() {
		if(position.getNext() == null) {
			return null;
		}
		
		before = position;
		position = position.getNext();
		
		return position.getData();
	}
	
	// 데이터 조회
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
	
	// 데이터 삭제
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
	
	// data값 비교
	private int compare(E data1, E data2) {
		String s1 = String.valueOf(data1);
		String s2 = String.valueOf(data2);
		
		//return s1.toLowerCase().compareTo(s2.toLowerCase());  // 내림차순
		return s2.toLowerCase().compareTo(s1.toLowerCase());  // 오름차순
	}
}
