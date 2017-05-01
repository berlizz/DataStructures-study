/* 
 * Circular linked list
 * ���� ���� ����Ʈ
 * �������� ��尡 ù���� ��带 ����Ų��.
 * �ܼ� ���� ����Ʈ�� �޸� head�� �ƴ� tail�� ����ϴ� ������ ���ο� ��带 �Ӹ��� ������ ���ϴ� ������ �߰��ϱ� ���� 
 * ������ �����͸� �߰��ϴ� ���� �ᱹ ������ �̵��ϴ� ���̹Ƿ� ��������δ� ���� ���Ḯ��Ʈ���� �Ӹ��� ������ ������ �ǹ̰� ����.
 * 
 */

package list;

public class CircularLinkedList {

	public static void main(String[] args) {
		CircularLinkedList.List list = new CircularLinkedList.List();
		
		System.out.println(list.isEmpty());
		
		list.add("a");
		list.add("b");
		list.add("b");
		list.add("a");
		list.addFront("1");
		list.addFront("2");
		list.add("c");
		
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
		System.out.println("ù���� ������ : " + list.getFirstData());
		System.out.println("������ ������ : " + list.getLastData());
		
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
		
		// ��� ��� ����
		list.remove("2");
		list.remove("b");
		list.remove("b");
		list.remove("1");
		list.remove("a");
		System.out.println("���� ��� ���� : " + list.size());
	}
	
	static class List {
		private Node tail = null;
		private Node position = null;
		private Node before = null;
		private int size;
		
		List() {
			size = 0;
		}
		
		public boolean isEmpty() {
			if(tail == null) {
				return true;
			}
			
			return false;
		}
		
		// ���� �ڿ� ���ο� ��� �߰�
		public void add(Object object) {
			Node newNode = new Node(object);
			
			if(tail == null) {
				tail = newNode;
				newNode.setNext(newNode);
			} else {
				newNode.setNext(tail.getNext());
				tail.setNext(newNode);
				tail = newNode;
			}
			
			size++;
		}
		
		// ���� �տ� ���ο� ��� �߰�
		public void addFront(Object object) {
			Node newNode = new Node(object);
			
			if(tail == null) {
				tail = newNode;
				newNode.setNext(newNode);
			} else {
				newNode.setNext(tail.getNext());
				tail.setNext(newNode);
			}
			
			size++;
		}
		
		// ù���� ��� ������ ��������
		public Object getFirstData() {
			before = tail;
			position = tail.getNext();
			
			return position.getData();
		}
		
		// ������ ��� ������ ��������
		public Object getLastData() {
			before = tail;
			position = tail.getNext();
			
			return tail.getData();
		}
		
		// ���� ��� ������ ��������
		public Object getNextData() {
			if(position == tail) {
				return null;
			}
			
			before = position;
			position = position.getNext();
			
			return position.getData();
		}
		
		// ��� ����
		public Object remove(Object object) {
			Object data = getFirstData();
			if(object.equals(data)) {
				
				// ������ �����Ͱ� ����Ʈ�� �ϳ� ���� �������� ���
				if(size == 1) {
					tail = null;
					position = null;
					before = null;
					size--;
					
					return data;
				}
				
				before.setNext(position.getNext());
				position = before;
				size--;
				
				return data;
			}
			
			while(true) {
				data = getNextData();
				
				if(object.equals(data)) {
					
					// ������ ��尡 tail�� ���
					if(position == tail) {
						tail = before;
					}
					
					before.setNext(position.getNext());
					position = before;
					size--;
					
					return data;
				}
				
				// ����Ʈ �ѹ��� ��ȸ
				if(position == tail) {
					break;
				}
			}
			
			return null;
		}
		
		// ��� ��ȸ
		public boolean contains(Object object) {
			if(getFirstData() != null && object.equals(getFirstData())) {
				return true;
			}
			
			while(true) {
				Object data = getNextData();
				
				if(data.equals(object)) {
					return true;
				}
				
				// ����Ʈ �ѹ��� ��ȸ
				if(position == tail) {
					break;
				}
			}
			
			return false;
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
