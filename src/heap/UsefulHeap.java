/* 
 * Useful heap
 * SimpleHeap�� �޸� �켱������ ���� �������� �ʰ� �����͸� ������� �켱������ ���� ����
 * �ƽ�Ű�ڵ� ������ ����(min heap)
 * 
 */

package heap;

public class UsefulHeap {
	
	private static final int ARRAY_LENGTH = 100;

	public static void main(String[] args) {
		UsefulHeap.Heap heap = new UsefulHeap.Heap();
		
		System.out.println("isEmpty : " + heap.isEmpty());
		System.out.println(heap.add('c'));
		System.out.println(heap.add('e'));
		System.out.println(heap.add('a'));
		System.out.println(heap.add('d'));
		System.out.println(heap.add('b'));
		System.out.println(heap.add('f'));
		System.out.println(heap.add('��'));
		System.out.println(heap.add('��'));
		System.out.println(heap.add('4'));
		System.out.println(heap.add('2'));
		System.out.println(heap.add('!'));
		System.out.println(heap.add('\"'));
		
		char data;
		while(!heap.isEmpty()) {
			data = heap.remove();
			System.out.print(data + " " );
		}
		System.out.println();
		
		System.out.println("isEmpty : " + heap.isEmpty());
		
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
