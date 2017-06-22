/* 
 * Circular linked list
 * 원형 연결 리스트
 * 마지막의 노드가 첫번쨰 노드를 가리킨다.
 * 단순 연결 리스트와 달리 head가 아닌 tail을 사용하는 이유는 새로운 노드를 머리와 꼬리에 원하는 곳으로 추가하기 위함 
 * 꼬리에 데이터를 추가하는 것은 결국 꼬리가 이동하는 것이므로 결과적으로는 원형 연결리스트에서 머리와 꼬리의 구분이 의미가 없다.
 * a b c d e 순으로 넣으면(add())
 *                    tail
 * a -> b -> c -> d -> e -> a
 * 
 * 
 */

package list;

public class CircularLinkedList<E> implements LinkedList<E> {
	
	private Node<E> tail;
	private Node<E> position;
	private Node<E> before;
	private int size;
	
	public CircularLinkedList() {
		this.tail = null;  // 순환되어야 하기 때문에 더미노드 생성하지 않음
		this.position = tail;
		this.before = null;
		this.size = 0;
	}
	
	@Override
	public boolean isEmpty() {
		return size == 0;
	}
	
	// 노드 추가
	@Override
	public void add(E data) {
		Node<E> newNode = new Node<>(data);
		
		// 더미노드가 없으므로 생성된 노드가 첫번째 노드인지 확인
		if(tail == null) {
			tail = newNode;
			newNode.setNext(newNode);  // 순환하기 위해 처음노드의 next는 자신을 가리킴
		} else {
			newNode.setNext(tail.getNext());
			tail.setNext(newNode);
			tail = newNode;
		}
		
		size++;
	}
	
	// 마지막 위치에 노드 추가
	public void addLast(E data) {
		add(data);
	}
	
	// 가장 앞의 위치에 노드 추가
	public void addFront(E data) {
		Node<E> newNode = new Node<>(data);
		
		// 더미노드가 없으므로 생성된 노드가 첫번째 노드인지 확인
		if(tail == null) {
			tail = newNode;
			newNode.setNext(newNode);  // 순환하기 위해 처음노드의 next는 자신을 가리킴
		} else {
			newNode.setNext(tail.getNext());
			tail.setNext(newNode);
		}
		
		size++;
	}
	
	// 첫번째 노드 데이터 가져오기
	@Override
	public E getFirstData() {
		if(isEmpty()) {
			return null;
		}
		
		before = tail;
		position = tail.getNext();
		
		return position.getData();
	}
	
	public E getLastData() {
		if(isEmpty()) {
			return null;
		}
		
		before = tail;
		position = tail.getNext();
		
		return tail.getData();
	}
	
	// 다음 노드 데이터 가져오기
	@Override
	public E getNextData() {
		// 한바퀴 순회 한 경우
		if(position == tail) {
			return null;
		}
		
		before = position;
		position = position.getNext();
		
		return position.getData();
	}
	
	// 노드 데이터 조회
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
		
		return false;  // 조회 실패 
	}
	
	// 노드 삭제
	@Override
	public E remove(E data) {
		E check = getFirstData();
		if(check != null && check.equals(data)) {
			// 삭제할 노드가 리트에 하나 남은 데이터인 경우
			if(size == 1) {
				tail = null;
				position = null;
				before = null;
				size--;
				
				return check; 
			}
			
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
				// 삭제할 노드가 tail인 경우
				if(position == tail) {
					tail = before;
				}
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
