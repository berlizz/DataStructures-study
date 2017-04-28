/*
 *	Linked list
 *	���� ����� ����Ʈ
 *	��������� ������ �߰�
 */

package list;

public class LinkedList {

	public static void main(String[] args) {
		LinkedList.List list = new LinkedList.List();
		
		System.out.println(list.isEmpty());
		
		list.add("a");
		list.add("b");
		list.add("c");
		list.add("d");
		list.add("a");
		list.add("b");
		
		System.out.println("���� ��� ���� : " + list.size());
		System.out.print(list.getFirst() + " ");
		while(true) {
			Object data = list.getNext();
			if(data == null) {
				break;
			}
			System.out.print(data + " ");
		}
		System.out.println();
		
		list.remove("a");
		list.remove("c");
		
		System.out.println("���� ��� ���� : " + list.size());
		System.out.print(list.getFirst() + " ");
		while(true) {
			Object data = list.getNext();
			if(data == null) {
				break;
			}
			System.out.print(data + " ");
		}
		System.out.println();
		
		System.out.println("a ��ȸ : " + list.contains("a"));
		System.out.println("c ��ȸ : " + list.contains("c"));
		
	}
	
	
	static class List {
		private Node head = null;
		private Node position = null;
		private Node before = null;
		private int size;
		
		List() {
			head = new Node("dummy");
			head.setNext(null);
			position = head;
			size = 0;
		}
		
		public boolean isEmpty() {
			if(head.getNext() == null) {
				return true;
			}
			
			return false;
		}
		
		// ��� �߰�
		public boolean add(Object object) {
			Node newNode = new Node(object);
			newNode.setNext(head.getNext());
			head.setNext(newNode);
			size++;
			
			return true;
		}
		
		// ù��° ��� ��������
		public Object getFirst() {
			before = head;
			position = head.getNext();
			
			return position.getData();
		}
		
		// ���� ��� ��������
		public Object getNext() {
			if(position.getNext() == null) {
				return null;
			}
			
			before = position;
			position = position.getNext();
			
			return position.getData();
		}
		
		// ��� ��ȸ
		public boolean contains(Object object) {
			if(object.equals(getFirst())) {
				return true;
			}
			
			while(true) {
				Object data = getNext();
				if(data == null) {
					break;
				}
				if(data.equals(object)) {
					return true;
				}
			}
			
			return false;
		}
		
		// ��� ����
		public Object remove(Object object) {
			Object data = getFirst();
			if(object.equals(data)) {
				before.setNext(position.getNext());
				position = before;
				size--;
				
				return data;
			}
			
			while(true) {
				data = getNext();
				if(data == null) {
					break;
				}
				if(object.equals(data)) {
					before.setNext(position.getNext());
					position = before;
					size--;
					
					return data;
				}
			}
			
			return null;
		}
		
		public int size() {
			return size;
		}
		
	}
	
	static class Node {
		private Object data;
		private Node next;
		
		Node(Object data) {
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
