/* 
 * Insertion sort
 * 삽입 정렬은 정렬된 부분과 정렬이 되지 않은 부분으로 나눠 정렬되지 않은 데이터를 정렬된 부분의 특정 위치에 삽입하며 정렬하는 방식 
 * 첫번째 데이터와 두번째 데이터를 비교하여 정렬된 상태로 만들어 정렬이 완료된 부분을 만듬
 * 그 이후 세번째, 네번째 데이터를 가져와 정렬된 부부늬 특정  위치에 삽입
 * 정렬된 상태로 삽입하기 위해서는 특정위치를 비워야하고, 비우기 위해서는 데이터들을 한 칸씩 뒤로 미는 연산을 수행
 * 데이터가 n개일 때 비교연산 횟수는 최악의 경우 1 + 2 + ... + (n-2) + (n-1)로 등차수열의 합이므로, (n-1) * n / 2번 
 * 빅오는 O(n^2) 
 *  
 */

package sort;

public class InsertionSort {

	public static void main(String[] args) {
		int[] arr = {3, 4, 1, 2, 6, 5};
		
		insertionSort(arr);
		
		for(int data : arr) {
			System.out.print(data + " ");
		}
	}
	
	public static void insertionSort(int[] arr) {
		int i, j;
		int temp;
		int n = arr.length;
		
		for(i=1; i<n; i++) {
			temp = arr[i];
			
			for(j=i-1; j>=0; j--) {
				if(arr[j] > temp) {
					arr[j+1] = arr[j];
				} else {
					break;
				}
			}
			
			arr[j+1] = temp;
		}
	}

}
