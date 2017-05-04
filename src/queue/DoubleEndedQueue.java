/* 
 * Double-ended queue
 * 덱(Deque)
 * 앞 뒤 두방향(head, tail)에서 데이터를 넣고 뺄 수 있는 구조
 * 이중 연결 리스트 기반으로 구현 
 *  
 */

package queue;

public class DoubleEndedQueue {

	public static void main(String[] args) {
		DoubleEndedQueue.Queue queue = new DoubleEndedQueue.Queue();
		
		System.out.println("isEmpty : " + queue.isEmpty());
		queue.addFirst("ㄱ");
		queue.addFirst("ㄴ");
		queue.addFirst("ㄷ");
		
		System.out.println("getFirst : " + queue.getFirst());
		System.out.println("getLast : " + queue.getLast());
		
		queue.addLast("ㄹ");
		queue.addLast("ㅁ");
		
		System.out.println("getFirst : " + queue.getFirst());
		System.out.println("getLast : " + queue.getLast());
		
		Object data;
		while(true) {
			data = queue.removeFirst();
			if(data == null) {
				break;
			}
			
			System.out.print(data + " ");
		}
		System.out.println();
		
		queue.addFirst("ㄱ");
		queue.addFirst("ㄴ");
		queue.addFirst("ㄷ");
		queue.addLast("ㄹ");
		queue.addLast("ㅁ");
		
		while(true) {
			data = queue.removeLast();
			if(data == null) {
				break;
			}
			
			System.out.print(data + " ");
		}
		System.out.println();
	}
	
	static class Queue {
		private Node head;
		private Node tail;
		
		Queue() {
			this.head = null;
			this.tail = null;
		}
		
		// head 또는 tail이 null일 경우 true
		// remove 하다가 head나 tail이 둘 중 하나라도  null이 되면 큐에 데이터 없음
		public boolean isEmpty() {
			return head == null || tail == null;
		}
		
		// head 쪽으로 데이터 추가
		public void addFirst(Object object) {
			Node newNode = new Node(object);
			
			if(isEmpty()) {
				head = newNode;
				tail = newNode;
			} else {
				newNode.setNext(head);
				head.setPrev(newNode);
				head = newNode;
			}
		}
		
		// tail 쪽으로 데이터 추가
		public void addLast(Object object) {
			Node newNode = new Node(object);
			
			if(isEmpty()) {
				head = newNode;
				tail = newNode;
			} else {
				newNode.setPrev(tail);
				tail.setNext(newNode);
				tail = newNode;
			}
		}
		
		// head쪽의 데이터 반환 및 삭제
		public Object removeFirst() {
			if(isEmpty()) {
				return null;
			}
			
			Object data = head.getData();
			
			head = head.getNext();
			if(head != null) {
				head.setPrev(null);
			}
			
			return data;
		}
		
		// tail쪽의 데이터 반환 및 삭제
		public Object removeLast() {
			if(isEmpty()) {
				return null;
			}
			
			Object data = tail.getData();
			
			tail = tail.getPrev();
			if(tail != null) {
				tail.setNext(null);
			}
			
			return data;			
		}
		
		// head쪽의 데이터 반환
		public Object getFirst() {
			return (isEmpty())? null : head.getData();
		}
		
		// tail쪽의 데이터 반환		
		public Object getLast() {
			return (isEmpty())? null : tail.getData();
		}
	}
	
	static class Node {
		private Object data;
		private Node next;
		private Node prev;
		
		Node(Object data) {
			this.data = data;
			this.next = null;
			this.prev = null;
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
