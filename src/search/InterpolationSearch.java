/* 
 * Interpolation search
 * 보간 탐색은 정렬된 대상을 기반으로 탐색
 * 탐색 대상을 찾을 때까지 동일한 패턴을 반복하므로 재귀적으로 구현
 * 보간 탐색은 데이터의 값과 그 데이터가 저장된 위치의 인덱스 값이 비례한다고 가정
 * 이진 탐색은 값에 상관없이 탐색 위치를 결정하지만, 보간 탐색은 그 값이 상대적으로 앞에 위치한다고 판단을 하면 앞쪽에서 탐색 진행
 * 찾고자 하는 데이터의 인덱스를 s라 하고, 찾고자 하는 데이터 arr[s]를 target이라 하면 s를 정하는 공식
 * s = ((target - arr[first]) / (arr[last] - arr[first]) * (last - first)) + first
 * 오차율을 최소화하기 위해 정수형 나눗셈이 아니라 실수형 나눗셈을 진행
 * 탐색대상이 존재하지 않을경우(탐색 실패) interpolationSearch메소드가 재귀적으로 됨에 따라 target에 저장된 값은
 * first와 last가 가리키는 값의 범위를 벗어남 그러므로 재귀 탈출 조건은 target이 범위를 벗어난 경우
 * 
 */

package search;

public class InterpolationSearch {

	public static void main(String[] args) {
		int[] arr = {1, 2, 3, 4, 5, 6};
		
		int targetIndex = interpolationSearch(arr, 0, arr.length - 1, 4);
		System.out.println(targetIndex);
		
		targetIndex = interpolationSearch(arr, 0, arr.length - 1, 7);
		System.out.println(targetIndex);

	}
	
	public static int interpolationSearch(int[] arr, int first, int last, int target) {
		// 탐색 실패 시 탈출 조건
		if(arr[first] > target || arr[last] < target) {
			return -1;
		}
		
		int mid = (int)((double)(target - arr[first]) / (arr[last] - arr[first]) * (last - first)) + first;
		
		if(arr[mid] == target) {
			return mid;
		} else if(arr[mid] > target) {
			return interpolationSearch(arr, first, mid - 1, target);
		}
		
		return interpolationSearch(arr, mid + 1, last, target);
	}

}
