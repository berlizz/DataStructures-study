/* 
 * Quick sort
 * �� ������ ��������(divide and conquer)�̶�� �˰��� �ٰ��Ͽ� ������� ���� ���
 * ���⼭�� �ǹ��� ���Ĵ�󿡼� ���� ������ ������ ����
 * low�� �ǹ����� �켱������ ���� �����͸� ã��, high�� �ǹ����� �켱������ ���� �����͸� ã��
 * low�� high�� ���� ���� �ʾ��� �� ���� ���� ��ȯ�ϰ�, ���� �� �ǹ��� high�� ���� ��ȯ
 * �� ��� �ǹ��������� ���� ������ �ǹ����� �켱������ ���� ������, ������ ������ �ǹ����� �켱������ ���� �����ͷ� ����
 * �ǹ��� �������� �������� ������ ������� ���� ������� ����
 * 
 * �ǹ��� ��ü �����͸� �������� �߰��� �ش��ϴ� ���� �ǹ����� ������ �� ���� ������ ���δ�. �� ������ ���� ����� �յ��ϰ� ������ ����
 * ������ ��� �����Ͱ� n���� �� �� ������ Ƚ���� n��(������ �ϸ� �񱳿��� �����̹Ƿ� n-1)
 * �������� ������ n�� �̰�, �����Ͱ� �յ��ϰ� �������� ������ ���ٴ� ���� �� �� ������ log2n�� ���� 
 * �� ��� n���� �� ������ log2n�� �Ͼ�Ƿ� �� ������ �񱳿��� Ƚ���� �ּ��� ��� nlog2n��, ����� O(nlog2n)
 * �־��� ���� �� ���� Ƚ���� n^2
 * ������ �� ������ �ǹ��� �߰��� ����� ������ �����ϴ� ����� ���Ͽ� �׻� �ּ��� �ƴ����� ��������� �ּ��� ����� ������ ����
 * O(nlog2n)���� �򰡵Ǵ� �ٸ� ���Ĺ���� ���� �������� �̵�Ƚ���� ��������� ����, 
 * ���� ���İ� ���� ������ �޸� ������ �䱸���� �ʴ� �ٴ� ������ ��������� ���� ����� ������ ����
 *  
 */

package sort;

public class QuickSort {

	public static void main(String[] args) {
		int[] arr = {3, 4, 1, 2, 6, 5};
		
		quickSort(arr, 0, arr.length-1);
		
		for(int data : arr) {
			System.out.print(data + " ");
		}
		System.out.println();
		
		
		// ���� ������
		int[] arr2 = {4, 4, 4, 4};
		
		quickSort(arr2, 0, arr2.length-1);
		
		for(int data : arr2) {
			System.out.print(data + " ");
		}
		System.out.println();
		
		
		// 100��
		int[] arr3 = new int[100];
		int value = 100;
		
		for(int i=0; i<arr3.length; i++) {
			arr3[i] = value;
			value--;
		}
		
		quickSort(arr3, 0, arr3.length-1);
		for(int data : arr3) {
			System.out.print(data + " ");
		}
	}
	
	// left�� �迭�� ���� �� ���� ���ʿ� �ִ� �ε���
	// right��  �迭�� ���� �� ���� �����ʿ� �ִ� �ε���
	public static void quickSort(int[] arr, int left, int right) {
		// left�� right�� �����Ǹ� ��� Ż��
		if(left <= right) {
			int pivotIndex = partition(arr, left, right);  // �ǹ� ���� �������� �ѷ� ����
			
			quickSort(arr, left, pivotIndex - 1);          // �ǹ� ���� ���� ���� ����
			quickSort(arr, pivotIndex + 1, right);         // �ǹ� ���� ������ ���� ����
		}
	}
	
	public static int partition(int[] arr, int left, int right) {
		int pivot = arr[left];  // �ǹ��� ��ġ�� ���� �������� ����
		int low = left + 1;     // low�� �ǹ��� ������ �迭�� ���� ���� �ε���
		int high = right;       // high�� �ǹ��� ������ �迭�� ���� ������ �ε���
		
		// low�� high�� �������� ���� ���
		while(low <= high) {
			// �ǹ����� ū ���� ã�� ������ low�� ����(���������� �̵�)
			while(low <= right && pivot >= arr[low]) {
				low++;
			}
			
			// �ǹ����� ���� ���� ã�� ������ high�� ����(�������� �̵�)
			while(high >= (left + 1) && pivot <= arr[high]) {
				high--;
			}
			
			// low�� high�� �������� ���� ���, arr[low]�� arr[high] �� ��ȯ
			if(low <= high) {
				swap(arr, low, high);
			}
		}
		
		swap(arr, left, high);  // �ǹ��� arr[high] �� ��ȯ
		
		return high;            // �Ű��� �ǹ��� �ε��� ����
	}
	
	// ���޹��� �ε��� ���� ����Ű�� �迭�� ��ȯ  
	public static void swap(int[] arr, int index1, int index2) {
		int temp = arr[index1];
		arr[index1] = arr[index2];
		arr[index2] = temp;
	}

}
