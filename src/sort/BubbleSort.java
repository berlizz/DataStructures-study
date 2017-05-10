/* 
 * Bubble sort
 * ���� ������ ������ �� ���� �����͸� ���ϸ鼭 ������ �����ϴ� ���
 * ���ļ����� ��ġ�� �ٲ��� �ϴ� ��� �� �������� ��ġ�� �ٲ�
 * ������ �켱������ ���� ���� �����͸� �� �ڷ� ������ ��� 
 * �������� ���� n���� �� �񱳿����� Ƚ���� (n-1) + (n-2) + ... + 2 + 1 ó�� ���ڼ����� ���̹Ƿ�, O(n^2) 
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
