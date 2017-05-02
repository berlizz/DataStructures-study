/* 
 * Linked list based stack
 * ���� ����Ʈ ��� ����
 * ��� ������ ��� �߰�(push)
 * ��� �ʿ��� ��� ����(pop)
 *  
 */

package stack;

public class LinkedListBasedStack {

	public static void main(String[] args) {
		LinkedListBasedStack.Stack stack = new LinkedListBasedStack.Stack();
		
		System.out.println("isEmpty : " + stack.isEmpty());
		stack.push("��");
		stack.push("��");
		stack.push("��");
		stack.push("��");
		
		System.out.println("peek : " + stack.peek());
		
		while(true) {
			Object data = stack.pop();
			if(data == null) {
				break;
			}
			System.out.print(data + " ");
		}
		
		System.out.println();
		
		System.out.println("isEmpty : " + stack.isEmpty());
		System.out.println("peek : " + stack.peek());
	}
	
	static class Stack {
		private Node head;
		
		Stack() {
			head = new Node("dummy");
		}
		
		public boolean isEmpty() {
			return head.getNext() == null;
		}
		
		public void push(Object object) {
			Node newNode = new Node(object);
			newNode.setNext(head.getNext());
			head.setNext(newNode);
		}
		
		public Object pop() {
			if(isEmpty()) {
				return null;
			}
			
			Object data = head.getNext().getData();
			head.setNext(head.getNext().getNext());
			
			return data;
		}
		
		public Object peek() {
			if(isEmpty()) {
				return null;
			}
			
			return head.getNext().getData();
		}
	}
	
	static class Node {
		private Object data;
		private Node next;
		
		public Node(Object data) {
			this.data = data;
			next = null;
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
