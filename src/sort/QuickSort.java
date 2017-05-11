/* 
 * Quick sort
 * 퀵 정렬은 분할정복(divide and conquer)이라는 알고리즘에 근거하여 만들어진 정렬 방식
 * 여기서는 피벗을 정렬대상에서 가장 왼쪽의 값으로 설정
 * low는 피벗보다 우선순위가 높은 데이터를 찾고, high는 피벗보다 우선순위가 낮은 데이터를 찾음
 * low와 high가 교차 하지 않았을 시 둘의 값을 교환하고, 교차 시 피벗과 high의 값을 교환
 * 그 결과 피벗기준으로 왼쪽 영역은 피벗보다 우선순위가 높은 데이터, 오른쪽 영역은 피벗보다 우선순위가 낮은 데이터로 정렬
 * 피벗을 기준으로 나누어진 양쪽을 대상으로 같은 방식으로 정렬
 * 
 * 피벗은 전체 데이터를 기준으로 중간에 해당하는 값을 피벗으로 결정할 때 좋은 성능을 보인다. 그 이유는 정렬 대상을 균등하게 나누기 때문
 * 정렬의 대상 데이터가 n개일 때 비교 연산의 횟수는 n번(엄밀히 하면 비교에서 제외이므로 n-1)
 * 데이터의 갯수가 n개 이고, 데이터가 균등하게 양쪽으로 나누어 진다는 가정 시 퀵 정렬은 log2n번 나뉨 
 * 그 결과 n번의 비교 연산이 log2n번 일어나므로 퀵 정렬의 비교연산 횟수는 최선의 경우 nlog2n번, 빅오는 O(nlog2n)
 * 최악의 경우는 비교 연산 횟수가 n^2
 * 하지만 퀵 정렬은 피벗을 중간에 가까운 값으로 선정하는 방법을 통하여 항상 최선은 아니지만 평균적으로 최선에 가까운 성능을 보임
 * O(nlog2n)으로 평가되는 다른 정렬방법에 비해 데이터의 이동횟수가 상대적으로 적고, 
 * 병합 정렬과 같이 별도의 메모리 공간을 요구하지 않는 다는 점에서 평균적으로 가장 우수한 성능을 보임
 *  
 */

package sort;

public class QuickSort {

	public static void main(String[] args) {
		int[] arr = {3, 4, 1, 2, 6, 5};
		
		quickSort(arr, 0, arr.length-1);
		
		for(int data : arr) {
			System.out.print(data + " ");
		}
		System.out.println();
		
		
		// 같은 데이터
		int[] arr2 = {4, 4, 4, 4};
		
		quickSort(arr2, 0, arr2.length-1);
		
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
		
		quickSort(arr3, 0, arr3.length-1);
		for(int data : arr3) {
			System.out.print(data + " ");
		}
	}
	
	// left는 배열의 영역 중 가장 왼쪽에 있는 인덱스
	// right는  배열의 영역 중 가장 오른쪽에 있는 인덱스
	public static void quickSort(int[] arr, int left, int right) {
		// left와 right가 교차되면 재귀 탈출
		if(left <= right) {
			int pivotIndex = partition(arr, left, right);  // 피벗 기준 양쪽으로 둘로 나눔
			
			quickSort(arr, left, pivotIndex - 1);          // 피벗 기준 왼쪽 영역 정렬
			quickSort(arr, pivotIndex + 1, right);         // 피벗 기준 오른쪽 영역 정렬
		}
	}
	
	public static int partition(int[] arr, int left, int right) {
		int pivot = arr[left];  // 피벗의 위치를 가장 왼쪽으로 잡음
		int low = left + 1;     // low는 피벗을 제외한 배열의 가장 왼쪽 인덱스
		int high = right;       // high는 피벗을 제외한 배열의 가장 오른쪽 인덱스
		
		// low와 high가 교차하지 않은 경우
		while(low <= high) {
			// 피벗보다 큰 값을 찾을 때까지 low값 증가(오른쪽으로 이동)
			while(low <= right && pivot >= arr[low]) {
				low++;
			}
			
			// 피벗보다 작은 값을 찾을 때까지 high값 감소(왼쪽으로 이동)
			while(high >= (left + 1) && pivot <= arr[high]) {
				high--;
			}
			
			// low와 high가 교차되지 않은 경우, arr[low]와 arr[high] 값 교환
			if(low <= high) {
				swap(arr, low, high);
			}
		}
		
		swap(arr, left, high);  // 피벗과 arr[high] 값 교환
		
		return high;            // 옮겨진 피벗의 인덱스 리턴
	}
	
	// 전달받은 인덱스 값이 가리키는 배열값 교환  
	public static void swap(int[] arr, int index1, int index2) {
		int temp = arr[index1];
		arr[index1] = arr[index2];
		arr[index2] = temp;
	}

}
