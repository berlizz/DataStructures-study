/* 
 * Simple Heap
 * ���� ���� ���� Ʈ��
 * �迭 ������� �����ϵ�, �ε����� 0�� ��Ҵ� �����
 * ���� ����Ʈ�� ������� ���� �����ϸ� ���ο� ��带 ���� ������ ��ġ�� �߰��ϴ� ���� ���� ����
 * ���� ������� �ϸ� �������� �߰��� ������ Ʈ���� ���̿� �ش��ϴ� ����ŭ �񱳿����� ����(Ʈ���� ���̰� 1���� ������ �񱳿��� Ƚ�� 1ȸ ����)
 * �������� ���� �ι� �� ������ �񱳿��� Ƚ�� 1ȸ ����
 * �� ��� ������ ������ �ð� ���⵵ O(log2n)
 * �� ��� ������ ������ �ð� ���⵵ O(log2n)
 * ���� �ڽĳ���� �ε��� �� = �θ��� �ε��� �� X 2
 * ������ �ڽĳ���� �ε��� �� = �θ��� �ε��� �� X 2 + 1
 * �θ����� �ε��� �� = �ڽĳ�� �ε��� �� / 2
 * �켱������ ���� �Է��ϴ� ���(���ڰ� �������� ���� �켱����, min heap)
 * 
 */

package heap;

public class SimpleHeap {
	
	private static final int ARRAY_LENGTH = 100;

	public static void main(String[] args) {
		SimpleHeap.Heap heap = new SimpleHeap.Heap();
		System.out.println("isEmpty : " + heap.isEmpty());
		
		System.out.println(heap.add("��", 1));
		System.out.println(heap.add("��", 2));
		System.out.println(heap.add("��", 3));
		System.out.println(heap.add("��", 1));
		System.out.println(heap.add("��", 2));
		System.out.println(heap.add("��", 3));
		
		Object data;
		while(true) {
			data = heap.remove();
			
			if(data == null) {
				break;
			}
			
			System.out.print(data + " ");
		}
		
	}
	
	static class Heap {
		private Node[] elements;
		private int size;
		
		Heap() {
			elements = new Node[ARRAY_LENGTH];
			size = 0;
		}
		
		public boolean isEmpty() {
			return size == 0;
		}
		
		// ���� ��忡�� �θ��� �ε��� ����
		public int getParentIndex(int index) {
			return index / 2;
		}
		
		// ���� ��忡�� ���� �ڽĳ�� �ε��� ����
		public int getLeftChildIndex(int index) {
			return index * 2;
		}
		
		// ���� ��忡�� ������ �ڽĳ�� �ε��� ����
		public int getRightChildIndex(int index) {
			return index * 2 + 1;
		}
		
		// �θ������ �ڽĳ�� �� �켱������ ���� ����� �ε��� ����
		public int getHighPriorityChildIndex(int index) {
			if(isEmpty()) {
				return -1;
			}
			
			int leftChildIndex = getLeftChildIndex(index);
			int rightChildIndex = getRightChildIndex(index);
			if(leftChildIndex > size) {          // �ڽĳ�尡 �������� �ʴ°��
				return 0;
			} else if(leftChildIndex == size) {  // �ڽĳ�尡 ���ʿ� �ϳ� �ִ� ���, �ε��� �� ����
				return leftChildIndex;
			} else {                             // �ڽĳ�尡 ���� ������ ��� �ִ� ���, �켱���� ���� �ڽ��� �ε��� �� ����
				if(elements[leftChildIndex].getPriority() < elements[rightChildIndex].getPriority()) {
					return leftChildIndex;
				} else {
					return rightChildIndex;
				}
			}
		}
		
		// ������ �߰�
		public boolean add(Object object, int priority) {
			// �迭�� ���� �� ���
			if(size == (ARRAY_LENGTH - 1)) {
				return false;
			}
			
			Node newNode = new Node(object, priority);
			int childIndex = size + 1;  // 
			int parentIndex;
			
			while(childIndex != 1) {
				parentIndex = getParentIndex(childIndex);
				
				// �θ����� �켭������ ���λ����� ����� �켱�������� ���� ���
				if(elements[parentIndex].getPriority() < priority) {
					break;
				}
				
				elements[childIndex] = elements[parentIndex];  // �θ��带 �ڽĳ���� ��ġ�� �̵�
				childIndex = parentIndex;  // �ڽĳ�带(���λ����� ���) ������ �ε��� ����
			}
			
			elements[childIndex] = newNode;  // ���λ����� ��� ���� ����
			size++;
			
			return true;			
		}
		
		// ���� �켱������ ����(��Ʈ���) ������ ���� �� ��ȯ
		public Object remove() {
			if(isEmpty()) {
				return null;
			}
			
			Object data = elements[1].getData();  // ��ȯ�� ��Ʈ����� ������
			Node lastNode = elements[size];       // ���� �������� �ִ� ���
			
			int parentIndex = 1;
			int childIndex;
			while(true) {
				childIndex = getHighPriorityChildIndex(parentIndex);
				if(childIndex == 0 || childIndex == -1) {
					break;
				}
				
				// �θ��尡 �ڽĳ�庸�� �켱������ ���� ���
				if(lastNode.getPriority() <= elements[childIndex].getPriority()) {
					break;
				}
				
				elements[parentIndex] = elements[childIndex];  // �ڽĳ�带 �θ����� �ڸ��� �̵�
				parentIndex = childIndex;     // ������ ��带 ������ �ε��� ���� 
			}
			
			elements[parentIndex] = lastNode;  // ������ ��� ���� ����
			size--;
			
			return data;
		}
	}
	
	static class Node {
		private Object data;
		private int priority;

		Node(Object data, int priority) {
			this.data = data;
			this.priority = priority;
		}

		public final Object getData() {
			return data;
		}

		public final void setData(Object data) {
			this.data = data;
		}
		
		public final int getPriority() {
			return priority;
		}

		public final void setPriority(int priority) {
			this.priority = priority;
		}
		
	}

}
