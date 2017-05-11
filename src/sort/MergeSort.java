/* 
 * Merge sort
 * 병합 정렬은 분할정복(divide and conquer)이라는 알고리즘에 근거하여 만들어진 정렬 방식
 * 분할, 정복, 결합 3단계를 통해 정렬
 * 데이터가 각 한개씩 구분될 때까지 분할 진행 
 * 분할이 완료된 후 정렬순서를 고려하여 결합
 * 분할의 과정에서 하나씩 구분될 때까지 둘로 나누는 과정을 반복하는 이유는 재귀적 구현을 위함
 * 정렬의 대상인 데이터의 수가 n개 일 때, 각 결합의 단계마다 최대 n번의 비교연산이 진행
 * 데이터의 수가 n개 일 때, 결합 과정의 횟수는 log2n번 (2는 로그의 밑)
 * n번의 비교연산이 log2n번 일어나게 되므로 최대 비교연산 횟수는 n * log2n = nlog2n
 * 빅오는  O(nlog2n) 
 *  
 */

package sort;

public class MergeSort {

	public static void main(String[] args) {
		int[] arr = {3, 4, 1, 2, 6, 5};
		
		mergeSort(arr, 0, arr.length-1);
		
		for(int data : arr) {
			System.out.print(data + " ");
		}
		System.out.println();
		
		
		// 같은 데이터
		int[] arr2 = {4, 4, 4, 4};
		
		mergeSort(arr2, 0, arr2.length-1);
		
		for(int data : arr2) {
			System.out.print(data + " ");
		}
		System.out.println();
		
		// 100개
		int[] arr3 = new int[100];
		int value = 100;
		
		for(int i=0; i<arr3.length; i++) {
			arr3[i] = value;
			value--;
		}
		
		mergeSort(arr3, 0, arr3.length-1);
		for(int data : arr3) {
			System.out.print(data + " ");
		}
	}
	
	public static void mergeSort(int[] arr, int left, int right) {
		int mid;
		
		// 둘로 분할할 수 있는 경우, 더 이상 분할할 수 없는 경우 재귀 탈출
		if(left < right) {
			mid = (left + right) / 2;
			
			mergeSort(arr, left, mid);           // left부터 mid까지 위치한 데이터 정렬
			mergeSort(arr, mid + 1, right);      // mid+1부터 right까지 위치한 데이터 정렬
			
			combination(arr, left, mid, right);  // 정렬된 두 배열을 결합
		}
	}
	
	public static void combination(int[] arr, int left, int mid, int right) {
		int front = left;
		int rear = mid + 1;
		int[] sortedArr = new int[right + 1];
		
		int sortedIndex = left;
		// mid 기준 좌우로, 병합 대상인  영역 모두 비교할 데이터가 존재하는 경우
		while(front <= mid && rear <= right) {
			// 두 값을 비교하여 우선순위가 높은 데이터를 삽입 후 비교할 인덱스 하나 증가
			if(arr[front] <= arr[rear]) {
				sortedArr[sortedIndex] = arr[front];
				front++;
			} else {
				sortedArr[sortedIndex] = arr[rear];
				rear++;
			}
			
			sortedIndex++;
		}
		
		// 반복문이 종료되고 양쪽 영역 중 남아있는 데이터를 배열에 순서대로 삽입
		if(front > mid) {
			for(int i=rear; i<=right; i++) {
				sortedArr[sortedIndex] = arr[i];
				sortedIndex++;
			}
		} else {
			for(int i=front; i<=mid; i++) {
				sortedArr[sortedIndex] = arr[i];
				sortedIndex++;
			}
		}
		
		for(int i=left; i<=right; i++) {
			arr[i] = sortedArr[i];
		}
	}

}
