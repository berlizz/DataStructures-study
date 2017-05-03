/* 
 * Linked list based queue
 * ���� ����Ʈ ��� ť
 * rear������ ������ �߰�, front������ ������ ��ȯ
 *  
 */

package queue;

public class LinkedListBasedQueue {

	public static void main(String[] args) {
		LinkedListBasedQueue.Queue queue = new LinkedListBasedQueue.Queue();
		
		System.out.println("isEmpty : " + queue.isEmpty());
		
		queue.enqueue("��");
		queue.enqueue("��");
		queue.enqueue("��");
		queue.enqueue("��");
		queue.enqueue("��");
		
		System.out.println("peek : " + queue.peek());
		
		Object data;
		while(true) {
			data = queue.dequeue();
			if(data == null) {
				break;
			}
			
			System.out.print(data + " ");
		}
		System.out.println();
		
		System.out.println("isEmpty : " + queue.isEmpty());

	}
	
	static class Queue {
		private Node front;
		private Node rear;
		
		Queue() {
			front = null;
			rear = null;
		}
		
		public boolean isEmpty() {
			return front == null;
		}
		
		// ������ �߰�
		public void enqueue(Object object) {
			Node newNode = new Node(object);
			
			if(isEmpty()) {
				front = newNode;
				rear = newNode;
			} else {
				rear.setNext(newNode);
				rear = newNode;
			}
		}
		
		// ������ ��ȯ �� ����
		public Object dequeue() {
			if(isEmpty()) {
				return null;
			}
			
			Object data = front.getData();
			front = front.getNext();
			
			return data;
		}
		
		// ������  ��ȯ
		public Object peek() {
			return front.getData();
		}
		
	}
	
	static class Node {
		private Object data;
		private Node next;
		
		Node(Object data) {
			this.data = data;
			this.next = null;
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

