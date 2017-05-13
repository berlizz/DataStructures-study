/* 
 * Interpolation search
 * ���� Ž���� ���ĵ� ����� ������� Ž��
 * Ž�� ����� ã�� ������ ������ ������ �ݺ��ϹǷ� ��������� ����
 * ���� Ž���� �������� ���� �� �����Ͱ� ����� ��ġ�� �ε��� ���� ����Ѵٰ� ����
 * ���� Ž���� ���� ������� Ž�� ��ġ�� ����������, ���� Ž���� �� ���� ��������� �տ� ��ġ�Ѵٰ� �Ǵ��� �ϸ� ���ʿ��� Ž�� ����
 * ã���� �ϴ� �������� �ε����� s�� �ϰ�, ã���� �ϴ� ������ arr[s]�� target�̶� �ϸ� s�� ���ϴ� ����
 * s = ((target - arr[first]) / (arr[last] - arr[first]) * (last - first)) + first
 * �������� �ּ�ȭ�ϱ� ���� ������ �������� �ƴ϶� �Ǽ��� �������� ����
 * Ž������� �������� �������(Ž�� ����) interpolationSearch�޼ҵ尡 ��������� �ʿ� ���� target�� ����� ����
 * first�� last�� ����Ű�� ���� ������ ��� �׷��Ƿ� ��� Ż�� ������ target�� ������ ��� ���
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
		// Ž�� ���� �� Ż�� ����
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
