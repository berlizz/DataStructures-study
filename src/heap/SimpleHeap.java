/* 
 * Simple Heap
 * 힙은 완전 이진 트리
 * 배열 기반으로 구현하되, 인덱스가 0인 요소는 비워둠
 * 연결 리스트를 기반으로 힙을 구현하면 새로운 노드를 힙의 마지막 위치에 추가하는 것이 쉽지 않음
 * 힙을 기반으로 하면 데이터의 추가와 삭제는 트리의 높이에 해당하는 수만큼 비교연산을 진행(트리의 높이가 1증가 때마다 비교연산 횟수 1회 증가)
 * 데이터의 수가 두배 늘 때마다 비교연산 횟수 1회 증가
 * 힙 기반 데이터 저장의 시간 복잡도 O(log2n)
 * 힙 기반 데이터 삭제의 시간 복잡도 O(log2n)
 * 왼쪽 자식노드의 인덱스 값 = 부모노드 인덱스 값 X 2
 * 오른쪽 자식노드의 인덱스 값 = 부모노드 인덱스 값 X 2 + 1
 * 부모노드의 인덱스 값 = 자식노드 인덱스 값 / 2
 * 우선순위를 직접 입력하는 방식(숫자가 낮을수록 높은 우선순위, min heap)
 * 
 */

package heap;

public class SimpleHeap {
	
	private static final int ARRAY_LENGTH = 100;

	public static void main(String[] args) {
		SimpleHeap.Heap heap = new SimpleHeap.Heap();
		System.out.println("isEmpty : " + heap.isEmpty());
		
		System.out.println(heap.add("ㄱ", 1));
		System.out.println(heap.add("ㄴ", 2));
		System.out.println(heap.add("ㄷ", 3));
		System.out.println(heap.add("ㄹ", 1));
		System.out.println(heap.add("ㅁ", 2));
		System.out.println(heap.add("ㅂ", 3));
		
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
		
		// 현재 노드에서 부모노드 인덱스 리턴
		public int getParentIndex(int index) {
			return index / 2;
		}
		
		// 현재 노드에서 왼쪽 자식노드 인덱스 리턴
		public int getLeftChildIndex(int index) {
			return index * 2;
		}
		
		// 현재 노드에서 오른쪽 자식노드 인덱스 리턴
		public int getRightChildIndex(int index) {
			return index * 2 + 1;
		}
		
		// 부모노드기준 자식노드 중 우선순위가 높은 노드의 인덱스 리턴
		public int getHighPriorityChildIndex(int index) {
			if(isEmpty()) {
				return -1;
			}
			
			int leftChildIndex = getLeftChildIndex(index);
			int rightChildIndex = getRightChildIndex(index);
			if(leftChildIndex > size) {          // 자식노드가 존재하지 않는경우
				return 0;
			} else if(leftChildIndex == size) {  // 자식노드가 왼쪽에 하나 있는 경우, 인덱스 값 리턴
				return leftChildIndex;
			} else {                             // 자식노드가 왼쪽 오른쪽 모두 있는 경우, 우선순위 높은 자식의 인덱스 값 리턴
				if(elements[leftChildIndex].getPriority() < elements[rightChildIndex].getPriority()) {
					return leftChildIndex;
				} else {
					return rightChildIndex;
				}
			}
		}
		
		// 데이터 추가
		public boolean add(Object object, int priority) {
			// 배열이 가득 찬 경우
			if(size == (ARRAY_LENGTH - 1)) {
				return false;
			}
			
			Node newNode = new Node(object, priority);
			int childIndex = size + 1;  // 
			int parentIndex;
			
			while(childIndex != 1) {
				parentIndex = getParentIndex(childIndex);
				
				// 부모노드의 우서순위가 새로생성한 노드의 우선순위보다 높은 경우
				if(elements[parentIndex].getPriority() < priority) {
					break;
				}
				
				elements[childIndex] = elements[parentIndex];  // 부모노드를 자식노드의 위치로 이동
				childIndex = parentIndex;  // 자식노드를(새로생성한 노드) 저장할 인덱스 변경
			}
			
			elements[childIndex] = newNode;  // 새로생성한 노드 최종 저장
			size++;
			
			return true;			
		}
		
		// 가장 우선순위가 높은(루트노드) 데이터 삭제 및 반환
		public Object remove() {
			if(isEmpty()) {
				return null;
			}
			
			Object data = elements[1].getData();  // 반환할 루트노드의 데이터
			Node lastNode = elements[size];       // 가장 마지막에 있는 노드
			
			int parentIndex = 1;
			int childIndex;
			while(true) {
				childIndex = getHighPriorityChildIndex(parentIndex);
				if(childIndex == 0 || childIndex == -1) {
					break;
				}
				
				// 부모노드가 자식노드보다 우선순위가 높은 경우
				if(lastNode.getPriority() <= elements[childIndex].getPriority()) {
					break;
				}
				
				elements[parentIndex] = elements[childIndex];  // 자식노드를 부모노드의 자리로 이동
				parentIndex = childIndex;     // 마지막 노드를 저장할 인덱스 변경 
			}
			
			elements[parentIndex] = lastNode;  // 마지막 노드 최종 저장
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
