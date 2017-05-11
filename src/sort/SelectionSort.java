/* 
 * Selection sort
 * ���� ������ ���ļ����� �°� �ϳ��� �����ؼ� �Ű� ����
 * ���ļ����� ���� �ռ��� ���� �����ؼ� ���� ������ �ű�� ���� �� �ڸ��� �ִ� �����ʹ� ������ �Ű��� �������� �ڸ��� �ű�
 * �������� ������ n���� �� �񱳿����� Ƚ���� (n-1) + (n-2) + ... + 2 + 1 ó�� ���ڼ����� ���̹Ƿ�, (n-1) * n / 2�� 
 * ����� O(n^2) 
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
