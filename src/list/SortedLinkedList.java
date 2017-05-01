/*
 *	Sorted linked list
 *	연결 기반의 리스트
 *	노드의 data값을 비교하여 데이터 추가
 *	
 */

package list;

public class SortedLinkedList {

	public static void main(String[] args) {
		SortedLinkedList.List list = new SortedLinkedList.List();
		
		System.out.println(list.isEmpty());
		
		list.add("1");
		list.add("4");
		list.add("b");
		list.add("a");
		list.add("3");
		list.add("2");
		list.add("1");
		list.add("2");
		
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
		
		list.remove("1");
		list.remove("3");
		
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
		
		System.out.println("1 조회 : " + list.contains("1"));
		System.out.println("3 조회 : " + list.contains("3"));
		
	}
	
	
	static class List {
		private Node head = null;
		private Node position = null;
		private Node before = null;
		private Node comparator = null;
		private int size;
		
		List() {
			head = new Node("dummy");
			head.setNext(null);
			position = head;
			size = 0;
		}
		
		public boolean isEmpty() {
			if(head.getNext() == null) {
				return true;
			}
			
			return false;
		}
		
		// 노드 추가
		public void add(Object object) {
			Node newNode = new Node(object);
			comparator = head;
			
			// 다음 노드가 null이 아니고, 생성된 노드보다 우선순위가 높은경우(헤더쪽에 가까운 경우)
			while(comparator.getNext() != null && compare(comparator.getNext().getData(), object) > 0) {
				comparator = comparator.getNext();
			}
			
			newNode.setNext(comparator.getNext());
			comparator.setNext(newNode);
			size++;
		}
		
		// 첫번째 노드 가져오기
		public Object getFirstData() {
			before = head;
			position = head.getNext();
			
			return position.getData();
		}
		
		// 다음 노드 가져오기
		public Object getNextData() {
			if(position.getNext() == null) {
				return null;
			}
			
			before = position;
			position = position.getNext();
			
			return position.getData();
		}
		
		// 노드 조회
		public boolean contains(Object object) {
			if(getFirstData() != null && object.equals(getFirstData())) {
				return true;
			}
			
			while(true) {
				Object data = getNextData();
				if(data == null) {
					break;
				}
				if(data.equals(object)) {
					return true;
				}
			}
			
			return false;
		}
		
		// 노드 삭제
		public Object remove(Object object) {
			Object data = getFirstData();
			if(object.equals(data)) {
				before.setNext(position.getNext());
				position = before;
				size--;
				
				return data;
			}
			
			while(true) {
				data = getNextData();
				if(data == null) {
					break;
				}
				if(object.equals(data)) {
					before.setNext(position.getNext());
					position = before;
					size--;
					
					return data;
				}
			}
			
			return null;
		}
		
		public int size() {
			return size;
		}
		
		// data값 비교
		public int compare(Object o1, Object o2) {
			String data1 = (String)o1;
			String data2 = (String)o2;
			
			return data1.toLowerCase().compareTo(data2.toLowerCase());	// 내림차순
			//return data2.toLowerCase().compareTo(data1.toLowerCase());	// 오름차순
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
