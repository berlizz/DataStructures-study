/* 
 * Bubble sort
 * 버블 정렬은 인접한 두 개의 데이터를 비교하면서 정렬을 진행하는 방식
 * 정렬순서상 위치가 바뀌어야 하는 경우 두 데이터의 위치를 바꿈
 * 정렬의 우선순위가 가장 낮은 데이터를 맨 뒤로 보내는 방식 
 * 데이터의 수가 n개일 때 비교연산의 횟수는 (n-1) + (n-2) + ... + 2 + 1 처럼 등자수열의 합이므로, O(n^2) 
 *  
 */

package sort;

public class BubbleSort {

	public static void main(String[] args) {
		int[] arr = {3, 4, 1, 2};
		
		bubbleSort(arr);
		
		for(int data : arr) {
			System.out.print(data + " ");
		}

	}
	
	public static void bubbleSort(int[] arr) {
		int temp;
		int n = arr.length;
		
		for(int i=0; i<n-1; i++) {
			for(int j=0; j<(n-i)-1; j++) {
				if(arr[j] > arr[j+1]) {
					temp = arr[j+1];
					arr[j+1] = arr[j];
					arr[j] = temp;
				}
			}
		}
		
		
	}

}
