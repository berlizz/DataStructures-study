/* 
 * Selection sort
 * 선택 정렬은 정렬순서에 맞게 하나씩 선택해서 옮겨 정렬
 * 정렬순서상 가장 앞서는 것을 선택해서 가장 앞으로 옮기고 원래 그 자리에 있던 데이터는 앞으로 옮겨진 데이터의 자리로 옮김
 * 데이터의 갯수가 n개일 때 비교연산의 횟수는 (n-1) + (n-2) + ... + 2 + 1 처럼 등자수열의 합이므로, (n-1) * n / 2번 
 * 빅오는 O(n^2) 
 * 
 */

package sort;

public class SelectionSort {

	public static void main(String[] args) {
		int[] arr = {3, 4, 1, 2};
		
		selectionSort(arr);
		
		for(int data : arr) {
			System.out.print(data + " ");
		}
	}
	
	public static void selectionSort(int[] arr) {
		int temp;
		int maxIndex;
		int n = arr.length;
		
		for(int i=0; i<n-1; i++) {
			maxIndex = i;
			
			for(int j=i+1; j<n; j++) {
				if(arr[j] < arr[maxIndex]) {
					maxIndex = j;
				}
			}
			
			temp = arr[i];
			arr[i] = arr[maxIndex];
			arr[maxIndex] = temp;
		}
	}

}
