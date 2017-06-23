/* 
 * Circular queue
 * 배열을 기바으로 한 원형 큐
 * 배열의 길이가 n이라면 데이터가 n-1개 체워졌을 때, 꽉 찬 것으로 간주
 * rear쪽으로 데이터 추가, front쪽으로 데이터 반환
 * front와 rear가 같으면 큐는 비어있고, front가 rear보다 한칸 앞에 있으면 큐는 꽉 찬 상태
 * front 또는 rear의 인덱스를 하나 증가한 후 그 위치에서 데이터 추가 또는 반환
 * 
 */

package queue;

public class CircularQueue<E> implements Queue<E> {
	
	private static final int ARRAY_LENGTH = 100;
	
	private E[] elements;
	private int front;
	private int rear;
	
	public CircularQueue() {
		this.elements = (E[]) new Object[ARRAY_LENGTH];
		this.front = 0;
		this.rear = 0;
	}
	
	@Override
	public boolean isEmpty() {
		return front == rear;
	}
	
	// rear + 1의 위치에 데이터 추가
	@Override
	public boolean enqueue(E data) {
		// 배열이 가득 찬 경우 또는 매개변수가 null인 경우
		if(front == nextIndex(rear) || data == null) {
			return false;
		}
		
		rear = nextIndex(rear);
		elements[rear] = data;
		
		return true;
	}
	
	// front + 1의 위치에 있는 데이터 반환 및 삭제
	@Override
	public E dequeue() {
		if(isEmpty()) {
			return null;
		}
		
		front = nextIndex(front);
		E data = elements[front];
		elements[front] = null;
		
		return data;
	}
	
	// front + 1의 위치에 있는 데이터 반환 (삭제 x)
	@Override
	public E peek() {
		return (isEmpty())? null : elements[nextIndex(front)];
	}
	
	// front와 rear를 증가, 한바퀴 회전 시 다시 0
	private int nextIndex(int index) {
		return (index == (ARRAY_LENGTH - 1))? 0 : index + 1;
	}

}
