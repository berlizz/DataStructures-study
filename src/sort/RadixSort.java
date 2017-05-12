/* 
 * Radix sort
 * 기수 정렬은 정렬순서상 앞서고 뒤섬의 판단을 위한 비교연산을 하지 않음
 * 기수 정렬은 O(nlog2n)보다 좋은 성능을 보이지만 적용할 수 있는 범위가 제한적
 * 기수를 이용한 정렬박식이므로, 버킷은 기수의 개수만큼 필요
 * LSD(Least Significant Digit)기수 정렬은 정수의 크기를 정하는데 있어 덜 중요한 자리수(낮은 자리수)부터 정렬을 진행
 * 작은 자릿수에서 시작해서 큰 자릿수까지 모두 비교를 해야 정수들의 대소를 판단, 비교 중간에는 대소 판단 불가능
 * MSD(Most Significant Digit)기수 정렬은 가장 큰 자릿수에서부터 정렬이 진행되는 방식 
 * 마지막 자릿수 까지 정렬을 진행하지 않고 중간에 정렬이 완료될 수 있으나, 이를 확인하려면 중간에 데이터를 점검해야 하므로 성능의 이점이 반감
 * LSD와 MSD의 빅오는 동일, MSD의 경우 정렬과정이 단축될 수 있느나 이를 위해 추가적인 검증과 메모라기 요구되므로 일반적인 경우 LSD 사용
 * 버킷은 그 구조가 큐에 해당하므로 프로젝트의 queue.LinkedListBasedQueue를 이용하여 구현
 * 기수 정렬은 비교연산이 핵심이 아니고 버킷으로의 데이터 삽입과 추출이 핵심이므로, 삽입과 추출의 빈도수로 시간 복잡도 계산
 * 데이터수가 n개일 때 데이터의 삽입과 추출이 n번 일어나고, 가장긴 정수의 길이(l) 만큼 반복되므로 n*l
 * 빅오는 O(n)  
 *  
 */

package sort;

import java.util.Arrays;

public class RadixSort {
	
	public static final int BUCKET_SIZE = 10;

	public static void main(String[] args) {
		int[] arr = {58491, 11, 444, 36, 23, 44, 19, 4444, 4, 1, 2, 3, 0};
		
		// 정수 58491의 길이가 5
		radixSort(arr, 5);
		
		System.out.println(Arrays.toString(arr));
	}
	
	// maxLength는 정렬대상의 정수 중 가장 큰 정수의 길이정보
	public static void radixSort(int[] arr, int maxLength) {
		int radix;        // 기수(배열의 각 제이터의 자리수)
		int divisor = 1;  // n번째 자리 숫자 가져오기 위한 제수(n번째의 자리 숫자 = NUM / 10^(n-1) % 10)
		
		Queue[] bucket = new Queue[BUCKET_SIZE];
		for(int i=0; i<BUCKET_SIZE; i++) {
			bucket[i] = new Queue();
		}
		
		// 가장 큰 정수의 길이만큼 반복
		for(int i=0; i<maxLength; i++) {
			
			// 버킷에 데이터 추가
			for(int j=0; j<arr.length; j++) {
				radix = arr[j] / divisor % 10;
				bucket[radix].enqueue(arr[j]);
			}
			
			// 버킷에 저장된 데이터를 차례로 가져오기
			for(int j=0, k=0; j<BUCKET_SIZE; j++) {
				while(!bucket[j].isEmpty()) {
					arr[k] = (int) bucket[j].dequeue();
					k++;
				}
			}
			
			divisor *= 10;
		}
	}
	
	
	static class Queue {
		private Node front;
		private Node rear;
		
		Queue() {
			front = null;
			rear = null;
		}
		
		public boolean isEmpty() {
			return front == null;
		}
		
		// 데이터 추가
		public void enqueue(Object object) {
			Node newNode = new Node(object);
			
			if(isEmpty()) {
				front = newNode;
				rear = newNode;
			} else {
				rear.setNext(newNode);
				rear = newNode;
			}
		}
		
		// 데이터 반환 및 삭제
		public Object dequeue() {
			if(isEmpty()) {
				return null;
			}
			
			Object data = front.getData();
			front = front.getNext();
			
			return data;
		}
		
		// 데이터  반환
		public Object peek() {
			return front.getData();
		}
		
	}
	
	static class Node {
		private Object data;
		private Node next;
		
		Node(Object data) {
			this.data = data;
			this.next = null;
		}

		public Object getData() {
			return data;
		}

		public void setData(Object data) {
			this.data = data;
		}

		public Node getNext() {
			return next;
		}

		public void setNext(Node next) {
			this.next = next;
		}
		
	}

}
