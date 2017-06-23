/* 
 * Circular queue
 * �迭�� ������� �� ���� ť
 * �迭�� ���̰� n�̶�� �����Ͱ� n-1�� ü������ ��, �� �� ������ ����
 * rear������ ������ �߰�, front������ ������ ��ȯ
 * front�� rear�� ������ ť�� ����ְ�, front�� rear���� ��ĭ �տ� ������ ť�� �� �� ����
 * front �Ǵ� rear�� �ε����� �ϳ� ������ �� �� ��ġ���� ������ �߰� �Ǵ� ��ȯ
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
	
	// rear + 1�� ��ġ�� ������ �߰�
	@Override
	public boolean enqueue(E data) {
		// �迭�� ���� �� ��� �Ǵ� �Ű������� null�� ���
		if(front == nextIndex(rear) || data == null) {
			return false;
		}
		
		rear = nextIndex(rear);
		elements[rear] = data;
		
		return true;
	}
	
	// front + 1�� ��ġ�� �ִ� ������ ��ȯ �� ����
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
	
	// front + 1�� ��ġ�� �ִ� ������ ��ȯ (���� x)
	@Override
	public E peek() {
		return (isEmpty())? null : elements[nextIndex(front)];
	}
	
	// front�� rear�� ����, �ѹ��� ȸ�� �� �ٽ� 0
	private int nextIndex(int index) {
		return (index == (ARRAY_LENGTH - 1))? 0 : index + 1;
	}

}
