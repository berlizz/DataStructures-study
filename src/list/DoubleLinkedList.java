/* 
 * Double linked list
 * 이중 연결 리스트
 * 왼족 노드가 오른쪽 노드를 가리킴과 동시에 오른쪽 노드도 왼쪽 노그를 가리키는 구조
 * 이중 연결 리스트는 양방향으로 조회가 가능
 * 	
 */

package list;

public class DoubleLinkedList<E> implements LinkedList<E> {
	
	private BidirectionalNode<E> head;
	private BidirectionalNode<E> position;
	private int size;
	
	public DoubleLinkedList() {
		head = new BidirectionalNode<E>();  // 더미노드 생성
		position = head;
		size = 0;
		
		head.setPrev(null);
		head.setNext(null);
	}
	
	@Override
	public boolean isEmpty() {
		return size == 0;
	}
	
	// 노드 추가
	@Override
	public void add(E data) {
		BidirectionalNode<E> newNode = new BidirectionalNode<>(data);
		
		if(head.getNext() != null) {
			((BidirectionalNode<E>) head.getNext()).setPrev(newNode);
		}
		
		newNode.setNext(head.getNext());
		head.setNext(newNode);
		size++;
	}
	
	// 첫번째 노드 데이터 가져오기 
	@Override
	public E getFirstData() {
		if(isEmpty()) {
			return null;
		}
		
		position = (BidirectionalNode<E>) head.getNext();
		
		return position.getData();
	}
	
	// 오른쪽 노드 데이터 가져오기
	@Override
	public E getNextData() {
		if(position.getNext() == null) {
			return null;
		}
		
		position = (BidirectionalNode<E>) position.getNext();
		
		return position.getData();
	}
	
	// 왼쪽 노드 데이터 가져오기
	public E getPrevData() {
		if(position.getPrev() == null) {
			return null;
		}
		
		position = position.getPrev();
		
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
			head.setNext(position.getNext());
			size--;
			
			return check;
		}
		
		while(true) {
			check = getNextData();
			if(check == null) {
				break;
			}
			
			if(check.equals(data)) {
				if(position.getNext() == null) {    // 마지막 노드가 삭제 대상인 경우
					position.setNext(null);
				} else {                            // 그 외
					((BidirectionalNode<E>) position.getNext()).setPrev(position.getPrev());
					position.getPrev().setNext(position.getNext());
				}
				
				size--;
				
				return check;
			}
		}
		
		return null;  // 삭제 실패
	}
	
	@Override
	public int size() {
		return size;
	}

}
