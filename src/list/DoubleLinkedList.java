/* 
 * Double linked list
 * ���� ���� ����Ʈ
 * ���� ��尡 ������ ��带 ����Ŵ�� ���ÿ� ������ ��嵵 ���� ��׸� ����Ű�� ����
 * ���� ���� ����Ʈ�� ��������� ��ȸ�� ����
 * 	
 */

package list;

public class DoubleLinkedList {

	public static void main(String[] args) {
		DoubleLinkedList.List list = new DoubleLinkedList.List();
		
System.out.println(list.isEmpty());
		
		list.add("a");
		list.add("b");
		list.add("c");
		list.add("d");
		list.add("a");
		list.add("b");
		
		System.out.println("���� ��� ���� : " + list.size());
		System.out.print(list.getFirstData() + " ");
		while(true) {
			Object data = list.getNextData();
			if(data == null) {
				break;
			}
			System.out.print(data + " ");
		}
		System.out.println();
		while(true) {
			Object data = list.getPrevData();
			if(data == null) {
				break;
			}
			System.out.print(data + " ");
		}
		System.out.println();
		
		list.remove("a");
		list.remove("c");
		
		System.out.println("���� ��� ���� : " + list.size());
		System.out.print(list.getFirstData() + " ");
		while(true) {
			Object data = list.getNextData();
			if(data == null) {
				break;
			}
			System.out.print(data + " ");
		}
		System.out.println();
		
		System.out.println("a ��ȸ : " + list.contains("a"));
		System.out.println("c ��ȸ : " + list.contains("c"));
		
		
		list.remove("a");
		list.remove("b");
		list.remove("b");
		list.remove("d");
		System.out.println("���� ��� ���� : " + list.size());
	}
	
	static class List {
		private Node head = null;
		private Node position = null;
		private int size;
		
		List() {
			head = new Node("dummy");
			head.setNext(null);
			head.setPrev(null);
			size = 0;
		}
		
		public boolean isEmpty() {
			return head.getNext() == null;
		}
		
		// ��� �߰�
		public void add(Object data) {
			Node newNode = new Node(data);
			newNode.setNext(head.getNext());
			newNode.setPrev(null);
			
			if(head.getNext() != null) {
				head.getNext().setPrev(newNode);
			}
			head.setNext(newNode);
			
			size++;
		}
		
		// ù���� ��� ��������
		public Object getFirstData() {
			if(head.getNext() == null) {
				return null;
			}
			
			position = head.getNext();
			
			return position.getData();
			
		}
		
		// ���� ��� ��������
		public Object getNextData() {
			if(position.getNext() == null) {
				return null;
			}
			
			position = position.getNext();
			
			return position.getData();
		}
		
		// ���� ��� ��������
		public Object getPrevData() {
			if(position.getPrev() == null) {
				return null;
			}
			
			position = position.getPrev();
			
			return position.getData();
		}
		
		// ��� ��ȸ
		public boolean contains(Object object) {
			Object data = getFirstData();
			if(data != null && data.equals(object)) {
				return true;
			}
			
			while(true) {
				data = getNextData();
				
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
			Object data = getFirstData();
			
			if(object.equals(data)) {
				head.setNext(position.getNext());
				size--;
				
				return data;
			}
			
			while(true) {
				data = getNextData();
				
				if(data == null) {
					break;
				}
				
				if(object.equals(data)) {
					
					// ���� ������ ��尡 ���� ����� ���
					if(position.getNext() == null) {
						position.getPrev().setNext(null);
					} else {
						position.getPrev().setNext(position.getNext());
						position.getNext().setPrev(position.getPrev());
					}
					position = position.getPrev();
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
		private Node prev;
		
		public Node(Object data) {
			this.data = data;
			next = null;
			prev = null;
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

		public Node getPrev() {
			return prev;
		}

		public void setPrev(Node prev) {
			this.prev = prev;
		}
		
	}

}
