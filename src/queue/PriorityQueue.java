/* 
 * Priority Queue
 * �켱���� ť 
 * �� ������ ��� ���� �켱������ ���� �����Ͱ� ���� ����
 * heap�� ����Ͽ� �켱���� ť ����
 * �迭 �Ǵ� ���Ḯ��Ʈ�� �켱���� ť�� ������ ��� �������� ���԰� ������ ��ȿ����
 * ť�� enqueue, dequeue�� ��ȭ�� ���� add, remove�� ��ġ
 */

package queue;

public class PriorityQueue {
	
	private static final int ARRAY_LENGTH = 100;

	public static void main(String[] args) {
		Queue queue = new Queue();
		System.out.println("isEmpty : " + queue.isEmpty());
		System.out.println(queue.enqueue('c'));
		System.out.println(queue.enqueue('e'));
		System.out.println(queue.enqueue('a'));
		System.out.println(queue.enqueue('d'));
		System.out.println(queue.enqueue('b'));
		System.out.println(queue.enqueue('f'));
		System.out.println(queue.enqueue('��'));
		System.out.println(queue.enqueue('��'));
		System.out.println(queue.enqueue('4'));
		System.out.println(queue.enqueue('2'));
		System.out.println(queue.enqueue('!'));
		System.out.println(queue.enqueue('\"'));
		
		char data;
		while(!queue.isEmpty()) {
			data = queue.dequeue();
			System.out.print(data + " " );
		}
		System.out.println();
		
		System.out.println("isEmpty : " + queue.isEmpty());
	}
	
	static class Queue {
		Heap heap;
		
		Queue() {
			heap = new Heap();
		}
		
		public boolean isEmpty() {
			return heap.isEmpty();
		}
		
		public boolean enqueue(char data) {
			return heap.add(data);
		}
		
		public char dequeue() {
			return heap.remove();
		}
	}
	
	static class Heap {
		char [] elements;
		int size;
		
		Heap() {
			elements = new char[ARRAY_LENGTH];
			size = 0;
		}
		
		public boolean isEmpty() {
			return size == 0;
		}
		
		public int getParentIndex(int index) {
			return index / 2;
		}
		
		public int getLeftChildIndex(int index) {
			return index * 2;
		}
		
		public int getRightChildIndex(int index) {
			return index * 2 + 1;
		}
		
		// �θ���� �ڽ� �� �켱������ ���� �������� �ε��� ����
		public int getHighPriorityChildIndex(int index) {
			if(isEmpty()) {
				return -1;
			}
			
			int leftChildIndex = getLeftChildIndex(index);
			int rightChildIndex = getRightChildIndex(index);
			
			// �ڽ��� �������� �ʴ� ���
			if(leftChildIndex > size) {
				return 0;
			}
			
			//�ڽ��� ���ʿ� �ϳ� �ִ� ���, �ε��� �� ��ȯ
			else if(leftChildIndex == size) {
				return leftChildIndex;
			}
			
			// �ڽ��� ���� ������ ��� �ִ� ���, �켱���� ���� �ڽ��� �ε��� �� ����
			else {
				if(comparePriority(elements[leftChildIndex], elements[rightChildIndex]) < 0) {
					return leftChildIndex;
				} else {
					return rightChildIndex;
				}
			}
		}
		
		// ������ �߰�
		public boolean add(char data) {
			if(size == ARRAY_LENGTH - 1) {
				return false;    // �迭�� ���� �� ���
			}
			
			int childIndex = size + 1;
			int parentIndex;
			
			while(childIndex != 1) {
				parentIndex = getParentIndex(childIndex);
				
				if(comparePriority(data, elements[parentIndex]) < 0) {
					elements[childIndex] = elements[parentIndex];    // ���ο� �������� �켱������ �θ��� �켱�������� ���� ���
					childIndex = parentIndex;
				} else {
					break;    // �θ��� �켱������ ���ο� �������� �켱�������� ���� ���
				}
			}
			
			elements[childIndex] = data;    // ���ο� ������ ����
			size++;
			
			return true;
		}
		
		// ���� �켱������ ���� ��Ʈ������ ���� �� ��ȯ
		public char remove() {
			
			char data = elements[1];           // ��ȯ ������
			char lastData = elements[size];    // Ʈ���� ������ ������
			
			int parentIndex = 1;
			int childIndex;
			
			while(true) {
				childIndex = getHighPriorityChildIndex(parentIndex);
				if(childIndex == -1 || childIndex == 0) {
					break;
				}
				
				if(comparePriority(elements[childIndex], lastData) < 0) {
					elements[parentIndex] = elements[childIndex];
					parentIndex = childIndex;
				} else {
					break;
				}
			}
			
			elements[parentIndex] = lastData;
			size--;
			
			return data;
		}
		
		// �����͸� ������� �켱���� ��
		public int comparePriority(char data1, char data2) {
			return data1 - data2;    // min heap
		}
	}

}
