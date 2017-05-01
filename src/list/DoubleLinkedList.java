/* 
 * Double linked list
 * 이중 연결 리스트
 * 왼족 노드가 오른쪽 노드를 가리킴과 동시에 오른쪽 노드도 왼쪽 노그를 가리키는 구조
 * 이중 연결 리스트는 양방향으로 조회가 가능
 * 	
 */

package list;

public class DoubleLinkedList {

	public static void main(String[] args) {
		DoubleLinkedList.List list = new DoubleLinkedList.List();
		
System.out.println(list.isEmpty());
		
		list.add("a");
		list.add("b");
		list.add("c");
		list.add("d");
		list.add("a");
		list.add("b");
		
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
		while(true) {
			Object data = list.getPrevData();
			if(data == null) {
				break;
			}
			System.out.print(data + " ");
		}
		System.out.println();
		
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
		
		
		list.remove("a");
		list.remove("b");
		list.remove("b");
		list.remove("d");
		System.out.println("현재 노드 갯수 : " + list.size());
	}
	
	static class List {
		private Node head = null;
		private Node position = null;
		private int size;
		
		List() {
			head = new Node("dummy");
			head.setNext(null);
			head.setPrev(null);
			size = 0;
		}
		
		public boolean isEmpty() {
			return head.getNext() == null;
		}
		
		// 노드 추가
		public void add(Object data) {
			Node newNode = new Node(data);
			newNode.setNext(head.getNext());
			newNode.setPrev(null);
			
			if(head.getNext() != null) {
				head.getNext().setPrev(newNode);
			}
			head.setNext(newNode);
			
			size++;
		}
		
		// 첫번쨰 노드 가져오기
		public Object getFirstData() {
			if(head.getNext() == null) {
				return null;
			}
			
			position = head.getNext();
			
			return position.getData();
			
		}
		
		// 다음 노드 가져오기
		public Object getNextData() {
			if(position.getNext() == null) {
				return null;
			}
			
			position = position.getNext();
			
			return position.getData();
		}
		
		// 이전 노드 가져오기
		public Object getPrevData() {
			if(position.getPrev() == null) {
				return null;
			}
			
			position = position.getPrev();
			
			return position.getData();
		}
		
		// 노드 조회
		public boolean contains(Object object) {
			Object data = getFirstData();
			if(data != null && data.equals(object)) {
				return true;
			}
			
			while(true) {
				data = getNextData();
				
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
				head.setNext(position.getNext());
				size--;
				
				return data;
			}
			
			while(true) {
				data = getNextData();
				
				if(data == null) {
					break;
				}
				
				if(object.equals(data)) {
					
					// 제일 마지막 노드가 삭제 대상일 경우
					if(position.getNext() == null) {
						position.getPrev().setNext(null);
					} else {
						position.getPrev().setNext(position.getNext());
						position.getNext().setPrev(position.getPrev());
					}
					position = position.getPrev();
					size--;
					
					return data;
				}
			}
			
			return null;
		}
		
		public int size() {
			return size;
		}
		
		
	}
	
	static class Node {
		private Object data;
		private Node next;
		private Node prev;
		
		public Node(Object data) {
			this.data = data;
			next = null;
			prev = null;
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

		public Node getPrev() {
			return prev;
		}

		public void setPrev(Node prev) {
			this.prev = prev;
		}
		
	}

}
