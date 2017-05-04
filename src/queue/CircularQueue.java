/* 
 * Circular queue
 * 배열을 기바으로 한 원형 큐
 * 배열의 길이가 n이라면 데이터가 n-1개 체워졌을 때, 꽉 찬 것으로 간주
 * rear쪽으로 데이터 추가, front쪽으로 데이터 반환
 * front와 rear가 같으면 큐는 비어있고, front가 rear보다 한칸 앞에 있으면 큐는 꽉 찬 상태
 * 
 */

package queue;

public class CircularQueue {
	
	public static final int ARRAY_LENGTH = 5;

	public static void main(String[] args) {
		CircularQueue.Queue queue = new CircularQueue.Queue();
		
		System.out.println("isEmpty : " + queue.isEmpty());
		System.out.println("enqueue : " + queue.enqueue("ㄱ"));
		System.out.println("enqueue : " + queue.enqueue("ㄴ"));
		System.out.println("enqueue : " + queue.enqueue("ㄷ"));
		System.out.println("enqueue : " + queue.enqueue("ㄹ"));
		System.out.println("enqueue : " + queue.enqueue("ㅁ"));
		
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
		
		// front와 rear를 증가, 한바퀴 회전 시 다시 0
		public int nextIndex(int index) {
			return (index == (ARRAY_LENGTH - 1))? 0 : index + 1;
		}
		
		// rear + 1의 위치에 데이터 추가
		public boolean enqueue(Object object) {
			
			// 큐가 가득 찬 경우
			if(front == nextIndex(rear) || object == null) {
				return false;
			}
			
			rear = nextIndex(rear);
			queue[rear] = object;
			
			return true;
		}
		
		// rear + 1의 위치의 데이터 반환 및 삭제
		public Object dequeue() {
			if(isEmpty()) {
				return null;
			}
			
			front = nextIndex(front);
			Object data = queue[front];
			queue[front] = null;
			
			return data;
		}
		
		// front + 1 값 반환(데이터 삭제x) 
		public Object peek() {
			if(isEmpty()) {
				return null;
			}
			
			return queue[nextIndex(front)];
		}
		
	}

}
