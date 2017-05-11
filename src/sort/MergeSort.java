/* 
 * Merge sort
 * ���� ������ ��������(divide and conquer)�̶�� �˰��� �ٰ��Ͽ� ������� ���� ���
 * ����, ����, ���� 3�ܰ踦 ���� ����
 * �����Ͱ� �� �Ѱ��� ���е� ������ ���� ���� 
 * ������ �Ϸ�� �� ���ļ����� ����Ͽ� ����
 * ������ �������� �ϳ��� ���е� ������ �ѷ� ������ ������ �ݺ��ϴ� ������ ����� ������ ����
 * ������ ����� �������� ���� n�� �� ��, �� ������ �ܰ踶�� �ִ� n���� �񱳿����� ����
 * �������� ���� n�� �� ��, ���� ������ Ƚ���� log2n�� (2�� �α��� ��)
 * n���� �񱳿����� log2n�� �Ͼ�� �ǹǷ� �ִ� �񱳿��� Ƚ���� n * log2n = nlog2n
 * �����  O(nlog2n) 
 *  
 */

package sort;

public class MergeSort {

	public static void main(String[] args) {
		int[] arr = {3, 4, 1, 2, 6, 5};
		
		mergeSort(arr, 0, arr.length-1);
		
		for(int data : arr) {
			System.out.print(data + " ");
		}
		System.out.println();
		
		
		// ���� ������
		int[] arr2 = {4, 4, 4, 4};
		
		mergeSort(arr2, 0, arr2.length-1);
		
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
		
		mergeSort(arr3, 0, arr3.length-1);
		for(int data : arr3) {
			System.out.print(data + " ");
		}
	}
	
	public static void mergeSort(int[] arr, int left, int right) {
		int mid;
		
		// �ѷ� ������ �� �ִ� ���, �� �̻� ������ �� ���� ��� ��� Ż��
		if(left < right) {
			mid = (left + right) / 2;
			
			mergeSort(arr, left, mid);           // left���� mid���� ��ġ�� ������ ����
			mergeSort(arr, mid + 1, right);      // mid+1���� right���� ��ġ�� ������ ����
			
			combination(arr, left, mid, right);  // ���ĵ� �� �迭�� ����
		}
	}
	
	public static void combination(int[] arr, int left, int mid, int right) {
		int front = left;
		int rear = mid + 1;
		int[] sortedArr = new int[right + 1];
		
		int sortedIndex = left;
		// mid ���� �¿��, ���� �����  ���� ��� ���� �����Ͱ� �����ϴ� ���
		while(front <= mid && rear <= right) {
			// �� ���� ���Ͽ� �켱������ ���� �����͸� ���� �� ���� �ε��� �ϳ� ����
			if(arr[front] <= arr[rear]) {
				sortedArr[sortedIndex] = arr[front];
				front++;
			} else {
				sortedArr[sortedIndex] = arr[rear];
				rear++;
			}
			
			sortedIndex++;
		}
		
		// �ݺ����� ����ǰ� ���� ���� �� �����ִ� �����͸� �迭�� ������� ����
		if(front > mid) {
			for(int i=rear; i<=right; i++) {
				sortedArr[sortedIndex] = arr[i];
				sortedIndex++;
			}
		} else {
			for(int i=front; i<=mid; i++) {
				sortedArr[sortedIndex] = arr[i];
				sortedIndex++;
			}
		}
		
		for(int i=left; i<=right; i++) {
			arr[i] = sortedArr[i];
		}
	}

}
