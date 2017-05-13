/* 
 * Binary search
 * ���� Ž���� ���ĵ� ����� ������� �ϴ� Ž��
 * �߾ӿ� ��ġ�� �����͸� Ž���� ��, �̸� �������� Ž������� �ݾ� �ٿ� ���鼭 Ž���� ���� 
 * Ž�� ����� ã�� ������ ������ ������ �ݺ��ϹǷ� ��������� ����
 * Ž�� ������ ������ġ�� �ǹ��ϴ� first�� Ž�� ������ ���� �ǹ��ϴ� last���� Ŀ���� ��� ��� Ż��(Ž�� ����)
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
		// Ž���� ������ ���
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
