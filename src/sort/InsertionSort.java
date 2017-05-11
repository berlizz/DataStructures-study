/* 
 * Insertion sort
 * ���� ������ ���ĵ� �κа� ������ ���� ���� �κ����� ���� ���ĵ��� ���� �����͸� ���ĵ� �κ��� Ư�� ��ġ�� �����ϸ� �����ϴ� ��� 
 * ù��° �����Ϳ� �ι�° �����͸� ���Ͽ� ���ĵ� ���·� ����� ������ �Ϸ�� �κ��� ����
 * �� ���� ����°, �׹�° �����͸� ������ ���ĵ� �κδ� Ư��  ��ġ�� ����
 * ���ĵ� ���·� �����ϱ� ���ؼ��� Ư����ġ�� ������ϰ�, ���� ���ؼ��� �����͵��� �� ĭ�� �ڷ� �̴� ������ ����
 * �����Ͱ� n���� �� �񱳿��� Ƚ���� �־��� ��� 1 + 2 + ... + (n-2) + (n-1)�� ���������� ���̹Ƿ�, (n-1) * n / 2�� 
 * ����� O(n^2) 
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
