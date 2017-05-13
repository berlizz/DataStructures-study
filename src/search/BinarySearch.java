/* 
 * Binary search
 * 이진 탐색은 정렬된 대상을 기반으로 하는 탐색
 * 중앙에 위치한 데이터를 탐색한 후, 이를 기준으로 탐색대상을 반씩 줄여 가면서 탐색을 진행 
 * 탐색 대상을 찾을 때까지 동일한 패턴을 반복하므로 재귀적으로 구현
 * 탐색 범위의 시작위치를 의미하는 first가 탐색 범위의 끝을 의미하는 last보다 커지는 경우 재귀 탈출(탐색 실패)
 *  
 */

package search;

public class BinarySearch {

	public static void main(String[] args) {
		int[] arr = {1, 2, 3, 4, 5, 6};
		
		int targetIndex = binarySearch(arr, 0, arr.length - 1, 4);
		System.out.println(targetIndex);
		
		targetIndex = binarySearch(arr, 0, arr.length - 1, 7);
		System.out.println(targetIndex);
	}
	
	public static int binarySearch(int[] arr, int first, int last, int target) {
		// 탐색이 실패한 경우
		if(first > last) {
			return -1;
		}
		
		int mid = (first + last) / 2;
		
		if(arr[mid] == target) {
			return mid;
		} else if(arr[mid] > target) {
			return binarySearch(arr, first, mid - 1, target);
		}
		
		return binarySearch(arr, mid + 1, last, target);
	}

}
