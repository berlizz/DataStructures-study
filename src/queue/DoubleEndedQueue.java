/* 
 * Double-ended queue
 * ��(Deque)
 * �� �� �ι���(head, tail)���� �����͸� �ְ� �� �� �ִ� ����
 * ���� ���� ����Ʈ ������� ���� 
 *  
 */

package queue;

public class DoubleEndedQueue {

	public static void main(String[] args) {
		DoubleEndedQueue.Queue queue = new DoubleEndedQueue.Queue();
		
		System.out.println("isEmpty : " + queue.isEmpty());
		queue.addFirst("��");
		queue.addFirst("��");
		queue.addFirst("��");
		
		System.out.println("getFirst : " + queue.getFirst());
		System.out.println("getLast : " + queue.getLast());
		
		queue.addLast("��");
		queue.addLast("��");
		
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
		
		queue.addFirst("��");
		queue.addFirst("��");
		queue.addFirst("��");
		queue.addLast("��");
		queue.addLast("��");
		
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
		
		// head �Ǵ� tail�� null�� ��� true
		// remove �ϴٰ� head�� tail�� �� �� �ϳ���  null�� �Ǹ� ť�� ������ ����
		public boolean isEmpty() {
			return head == null || tail == null;
		}
		
		// head ������ ������ �߰�
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
		
		// tail ������ ������ �߰�
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
		
		// head���� ������ ��ȯ �� ����
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
		
		// tail���� ������ ��ȯ �� ����
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
		
		// head���� ������ ��ȯ
		public Object getFirst() {
			return (isEmpty())? null : head.getData();
		}
		
		// tail���� ������ ��ȯ		
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
