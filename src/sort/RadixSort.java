/* 
 * Radix sort
 * ��� ������ ���ļ����� �ռ��� �ڼ��� �Ǵ��� ���� �񱳿����� ���� ����
 * ��� ������ O(nlog2n)���� ���� ������ �������� ������ �� �ִ� ������ ������
 * ����� �̿��� ���Ĺڽ��̹Ƿ�, ��Ŷ�� ����� ������ŭ �ʿ�
 * LSD(Least Significant Digit)��� ������ ������ ũ�⸦ ���ϴµ� �־� �� �߿��� �ڸ���(���� �ڸ���)���� ������ ����
 * ���� �ڸ������� �����ؼ� ū �ڸ������� ��� �񱳸� �ؾ� �������� ��Ҹ� �Ǵ�, �� �߰����� ��� �Ǵ� �Ұ���
 * MSD(Most Significant Digit)��� ������ ���� ū �ڸ����������� ������ ����Ǵ� ��� 
 * ������ �ڸ��� ���� ������ �������� �ʰ� �߰��� ������ �Ϸ�� �� ������, �̸� Ȯ���Ϸ��� �߰��� �����͸� �����ؾ� �ϹǷ� ������ ������ �ݰ�
 * LSD�� MSD�� ����� ����, MSD�� ��� ���İ����� ����� �� �ִ��� �̸� ���� �߰����� ������ �޸��� �䱸�ǹǷ� �Ϲ����� ��� LSD ���
 * ��Ŷ�� �� ������ ť�� �ش��ϹǷ� ������Ʈ�� queue.LinkedListBasedQueue�� �̿��Ͽ� ����
 * ��� ������ �񱳿����� �ٽ��� �ƴϰ� ��Ŷ������ ������ ���԰� ������ �ٽ��̹Ƿ�, ���԰� ������ �󵵼��� �ð� ���⵵ ���
 * �����ͼ��� n���� �� �������� ���԰� ������ n�� �Ͼ��, ����� ������ ����(l) ��ŭ �ݺ��ǹǷ� n*l
 * ����� O(n)  
 *  
 */

package sort;

import java.util.Arrays;

public class RadixSort {
	
	public static final int BUCKET_SIZE = 10;

	public static void main(String[] args) {
		int[] arr = {58491, 11, 444, 36, 23, 44, 19, 4444, 4, 1, 2, 3, 0};
		
		// ���� 58491�� ���̰� 5
		radixSort(arr, 5);
		
		System.out.println(Arrays.toString(arr));
	}
	
	// maxLength�� ���Ĵ���� ���� �� ���� ū ������ ��������
	public static void radixSort(int[] arr, int maxLength) {
		int radix;        // ���(�迭�� �� �������� �ڸ���)
		int divisor = 1;  // n��° �ڸ� ���� �������� ���� ����(n��°�� �ڸ� ���� = NUM / 10^(n-1) % 10)
		
		Queue[] bucket = new Queue[BUCKET_SIZE];
		for(int i=0; i<BUCKET_SIZE; i++) {
			bucket[i] = new Queue();
		}
		
		// ���� ū ������ ���̸�ŭ �ݺ�
		for(int i=0; i<maxLength; i++) {
			
			// ��Ŷ�� ������ �߰�
			for(int j=0; j<arr.length; j++) {
				radix = arr[j] / divisor % 10;
				bucket[radix].enqueue(arr[j]);
			}
			
			// ��Ŷ�� ����� �����͸� ���ʷ� ��������
			for(int j=0, k=0; j<BUCKET_SIZE; j++) {
				while(!bucket[j].isEmpty()) {
					arr[k] = (int) bucket[j].dequeue();
					k++;
				}
			}
			
			divisor *= 10;
		}
	}
	
	
	static class Queue {
		private Node front;
		private Node rear;
		
		Queue() {
			front = null;
			rear = null;
		}
		
		public boolean isEmpty() {
			return front == null;
		}
		
		// ������ �߰�
		public void enqueue(Object object) {
			Node newNode = new Node(object);
			
			if(isEmpty()) {
				front = newNode;
				rear = newNode;
			} else {
				rear.setNext(newNode);
				rear = newNode;
			}
		}
		
		// ������ ��ȯ �� ����
		public Object dequeue() {
			if(isEmpty()) {
				return null;
			}
			
			Object data = front.getData();
			front = front.getNext();
			
			return data;
		}
		
		// ������  ��ȯ
		public Object peek() {
			return front.getData();
		}
		
	}
	
	static class Node {
		private Object data;
		private Node next;
		
		Node(Object data) {
			this.data = data;
			this.next = null;
		}

		public Object getData() {
			return data;
		}

		public void setData(Object data) {
			this.data = data;
		}

		public Node getNext() {
			return next;
		}

		public void setNext(Node next) {
			this.next = next;
		}
		
	}

}
