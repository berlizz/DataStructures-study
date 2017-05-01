/* 
 * Circular linked list
 * 원형 연결 리스트
 * 마지막의 노드가 첫번쨰 노드를 가리킨다.
 * 단순 연결 리스트와 달리 head가 아닌 tail을 사용하는 이유는 새로운 노드를 머리와 꼬리에 원하는 곳으로 추가하기 위함 
 * 꼬리에 데이터를 추가하는 것은 결국 꼬리가 이동하는 것이므로 결과적으로는 원형 연결리스트에서 머리와 꼬리의 구분이 의미가 없다.
 * 
 */

package list;

public class CircularLinkedList {

	public static void main(String[] args) {
		CircularLinkedList.List list = new CircularLinkedList.List();
		
		System.out.println(list.isEmpty());
		
		list.add("a");
		list.add("b");
		list.add("b");
		list.add("a");
		list.addFront("1");
		list.addFront("2");
		list.add("c");
		
		System.out.println("현재 노드 갯수 : " + list.size());
		System.out.print(list.getFirstData() + " ");
		while(true) {
			Object data = list.getNextData();
			if(data == null) {
				break;
			}
			System.out.print(data + " ");
		}
		System.out.println();
		System.out.println("첫번쨰 데이터 : " + list.getFirstData());
		System.out.println("마지막 데이터 : " + list.getLastData());
		
		list.remove("a");
		list.remove("c");
		
		System.out.println("현재 노드 갯수 : " + list.size());
		System.out.print(list.getFirstData() + " ");
		while(true) {
			Object data = list.getNextData();
			if(data == null) {
				break;
			}
			System.out.print(data + " ");
		}
		System.out.println();
		
		System.out.println("a 조회 : " + list.contains("a"));
		System.out.println("c 조회 : " + list.contains("c"));
		
		// 모든 노드 삭제
		list.remove("2");
		list.remove("b");
		list.remove("b");
		list.remove("1");
		list.remove("a");
		System.out.println("현재 노드 갯수 : " + list.size());
	}
	
	static class List {
		private Node tail = null;
		private Node position = null;
		private Node before = null;
		private int size;
		
		List() {
			size = 0;
		}
		
		public boolean isEmpty() {
			if(tail == null) {
				return true;
			}
			
			return false;
		}
		
		// 제일 뒤에 새로운 노드 추가
		public void add(Object object) {
			Node newNode = new Node(object);
			
			if(tail == null) {
				tail = newNode;
				newNode.setNext(newNode);
			} else {
				newNode.setNext(tail.getNext());
				tail.setNext(newNode);
				tail = newNode;
			}
			
			size++;
		}
		
		// 제일 앞에 새로운 노드 추가
		public void addFront(Object object) {
			Node newNode = new Node(object);
			
			if(tail == null) {
				tail = newNode;
				newNode.setNext(newNode);
			} else {
				newNode.setNext(tail.getNext());
				tail.setNext(newNode);
			}
			
			size++;
		}
		
		// 첫번쨰 노드 데이터 가져오기
		public Object getFirstData() {
			before = tail;
			position = tail.getNext();
			
			return position.getData();
		}
		
		// 마지막 노드 데이터 가져오기
		public Object getLastData() {
			before = tail;
			position = tail.getNext();
			
			return tail.getData();
		}
		
		// 다음 노드 데이터 가져오기
		public Object getNextData() {
			if(position == tail) {
				return null;
			}
			
			before = position;
			position = position.getNext();
			
			return position.getData();
		}
		
		// 노드 삭제
		public Object remove(Object object) {
			Object data = getFirstData();
			if(object.equals(data)) {
				
				// 삭제할 데이터가 리스트에 하나 남은 데이터인 경우
				if(size == 1) {
					tail = null;
					position = null;
					before = null;
					size--;
					
					return data;
				}
				
				before.setNext(position.getNext());
				position = before;
				size--;
				
				return data;
			}
			
			while(true) {
				data = getNextData();
				
				if(object.equals(data)) {
					
					// 삭제할 노드가 tail인 경우
					if(position == tail) {
						tail = before;
					}
					
					before.setNext(position.getNext());
					position = before;
					size--;
					
					return data;
				}
				
				// 리스트 한바퀴 조회
				if(position == tail) {
					break;
				}
			}
			
			return null;
		}
		
		// 노드 조회
		public boolean contains(Object object) {
			if(getFirstData() != null && object.equals(getFirstData())) {
				return true;
			}
			
			while(true) {
				Object data = getNextData();
				
				if(data.equals(object)) {
					return true;
				}
				
				// 리스트 한바퀴 조회
				if(position == tail) {
					break;
				}
			}
			
			return false;
		}
		
		public int size() {
			return size;
		}
	}
	
	static class Node {
		private Object data;
		private Node next;
		
		Node(Object data) {
			this.data = data;
			next = null;
		}

		public Object getData() {
			return data;
		}

		public void setData(Object data) {
			this.data = data;
		}

		public Node getNext() {
			return next;
		}

		public void setNext(Node next) {
			this.next = next;
		}
		
	}

}
