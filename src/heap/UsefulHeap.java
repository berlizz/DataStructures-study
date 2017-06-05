/* 
 * Useful heap
 * SimpleHeap과 달리 우선순위를 직접 저장하지 않고 데이터를 기반으로 우선순위에 따라 정렬
 * 아스키코드 순으로 정렬(min heap)
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
		System.out.println(heap.add('ㄱ'));
		System.out.println(heap.add('ㄴ'));
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
		
		// 부모기준 자식 중 우선순위가 높은 데이터의 인덱스 리턴
		public int getHighPriorityChildIndex(int index) {
			if(isEmpty()) {
				return -1;
			}
			
			int leftChildIndex = getLeftChildIndex(index);
			int rightChildIndex = getRightChildIndex(index);
			
			// 자식이 존재하지 않는 경우
			if(leftChildIndex > size) {
				return 0;
			}
			
			//자식이 왼쪽에 하나 있는 경우, 인덱스 값 반환
			else if(leftChildIndex == size) {
				return leftChildIndex;
			}
			
			// 자식이 왼쪽 오른쪽 모두 있는 경우, 우선순위 높은 자식의 인덱스 값 리턴
			else {
				if(comparePriority(elements[leftChildIndex], elements[rightChildIndex]) < 0) {
					return leftChildIndex;
				} else {
					return rightChildIndex;
				}
			}
		}
		
		// 데이터 추가
		public boolean add(char data) {
			if(size == ARRAY_LENGTH - 1) {
				return false;    // 배열이 가득 찬 경우
			}
			
			int childIndex = size + 1;
			int parentIndex;
			
			while(childIndex != 1) {
				parentIndex = getParentIndex(childIndex);
				
				if(comparePriority(data, elements[parentIndex]) < 0) {
					elements[childIndex] = elements[parentIndex];    // 새로운 데이터의 우선순위가 부모의 우선순위보다 높은 경우
					childIndex = parentIndex;
				} else {
					break;    // 부모의 우선순위가 새로운 데이터의 우선순위보다 높은 경우
				}
			}
			
			elements[childIndex] = data;    // 새로운 데이터 저장
			size++;
			
			return true;
		}
		
		// 가장 우선순위가 높은 루트데이터 삭제 및 반환
		public char remove() {
			
			char data = elements[1];           // 반환 데이터
			char lastData = elements[size];    // 트리의 마지막 데이터
			
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
		
		// 데이터를 기반으로 우선순위 비교
		public int comparePriority(char data1, char data2) {
			return data1 - data2;    // min heap
		}
	}

}
