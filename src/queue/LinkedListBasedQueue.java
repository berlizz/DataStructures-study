/* 
 * Linked list based queue
 * 연결 리스트 기반 큐
 * rear쪽으로 데이터 추가, front쪽으로 데이터 반환
 *  
 */

package queue;

public class LinkedListBasedQueue {

	public static void main(String[] args) {
		LinkedListBasedQueue.Queue queue = new LinkedListBasedQueue.Queue();
		
		System.out.println("isEmpty : " + queue.isEmpty());
		
		queue.enqueue("ㄱ");
		queue.enqueue("ㄴ");
		queue.enqueue("ㄷ");
		queue.enqueue("ㄹ");
		queue.enqueue("ㅁ");
		
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
		
		// 데이터 추가
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
		
		// 데이터 반환 및 삭제
		public Object dequeue() {
			if(isEmpty()) {
				return null;
			}
			
			Object data = front.getData();
			front = front.getNext();
			
			return data;
		}
		
		// 데이터  반환
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

