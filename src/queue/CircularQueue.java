/* 
 * Circular queue
 * �迭�� ������� �� ���� ť
 * �迭�� ���̰� n�̶�� �����Ͱ� n-1�� ü������ ��, �� �� ������ ����
 * rear������ ������ �߰�, front������ ������ ��ȯ
 * front�� rear�� ������ ť�� ����ְ�, front�� rear���� ��ĭ �տ� ������ ť�� �� �� ����
 * 
 */

package queue;

public class CircularQueue {
	
	public static final int ARRAY_LENGTH = 5;

	public static void main(String[] args) {
		CircularQueue.Queue queue = new CircularQueue.Queue();
		
		System.out.println("isEmpty : " + queue.isEmpty());
		System.out.println("enqueue : " + queue.enqueue("��"));
		System.out.println("enqueue : " + queue.enqueue("��"));
		System.out.println("enqueue : " + queue.enqueue("��"));
		System.out.println("enqueue : " + queue.enqueue("��"));
		System.out.println("enqueue : " + queue.enqueue("��"));
		
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
		private Object[] queue;
		private int front;
		private int rear;
		
		Queue() {
			queue = new Object[ARRAY_LENGTH];
			front = 0;
			rear = 0;
		}
		
		public boolean isEmpty() {
			return front == rear;
		}
		
		// front�� rear�� ����, �ѹ��� ȸ�� �� �ٽ� 0
		public int nextIndex(int index) {
			return (index == (ARRAY_LENGTH - 1))? 0 : index + 1;
		}
		
		// rear + 1�� ��ġ�� ������ �߰�
		public boolean enqueue(Object object) {
			
			// ť�� ���� �� ���
			if(front == nextIndex(rear) || object == null) {
				return false;
			}
			
			rear = nextIndex(rear);
			queue[rear] = object;
			
			return true;
		}
		
		// rear + 1�� ��ġ�� ������ ��ȯ �� ����
		public Object dequeue() {
			if(isEmpty()) {
				return null;
			}
			
			front = nextIndex(front);
			Object data = queue[front];
			queue[front] = null;
			
			return data;
		}
		
		// front + 1 �� ��ȯ(������ ����x) 
		public Object peek() {
			if(isEmpty()) {
				return null;
			}
			
			return queue[nextIndex(front)];
		}
		
	}

}
