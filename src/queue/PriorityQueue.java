/* 
 * Priority Queue
 * 우선순위 큐 
 * 들어간 순서에 상관 없이 우선순위가 높은 데이터가 먼저 나옴
 * heap을 사용하여 우선순위 큐 구현
 * 배열 또는 연결리스트로 우선순위 큐를 구현할 경우 데이터의 삽입과 삭제가 비효율적
 * 큐의 enqueue, dequeue의 결화는 힙의 add, remove와 일치
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
		System.out.println(queue.enqueue('ㄱ'));
		System.out.println(queue.enqueue('ㄴ'));
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
