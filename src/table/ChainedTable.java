/* 
 * Chained table
 * ���̺��� key�� value�� ����
 * �浹(collision)�� �ذ��ϱ� ���� ������� ü�̴�(chaining)���
 * ü�̴��� ���� ��巹�� ���(closed addressing method)�� �浹�� �߻��ص� ���ڸ��� Ű������ ���� 
 * �����ϱ� ���� linked list ���, linked list�� slot�� ������ ������ ������� �浹���� �ذ�
 *  
 */

package table;

public class ChainedTable {
	
	private static final int ARRAY_LENGTH = 100;

	public static void main(String[] args) {
		ChainedTable.Table table = new ChainedTable.Table();
		
		table.insert(1, "value1");
		table.insert(2, "value2");
		table.insert(3, "value3");
		table.insert(101, "value4");
		table.insert(201, "value5");
		
		System.out.println(table.search(1));
		System.out.println(table.search(2));
		System.out.println(table.search(3));
		System.out.println(table.search(101));
		System.out.println(table.search(201));
		
		table.delete(1);
		System.out.println(table.search(1));
		
		table.delete(101);
		System.out.println(table.search(101));

	}
	
	// hash �Լ�
	public static int hashFunction(int key) {
		return key % 100;
	}
	
	static class Table {
		private List[] table;
		
		Table() {
			table = new List[ARRAY_LENGTH];
			
			for(int i=0; i<table.length; i++) {
				table[i] = new List();
			}
		}
		
		// ���̺� ������ �߰�
		public void insert(int key, String value) {
			int i = hashFunction(key);
			
			// key�� �ߺ��� ���
			if(search(key) != null) {
				return;
			} else {
				// key�� �ߺ����� ���� ��� Slot�� �����Ͽ� ��� �߰�
				Slot slot = new Slot(key, value);
				table[i].add(slot);
			}
			
		}
		
		// ��ġ�ϴ� key�� �����ϴ��� Ž�� , ��ġġ�ϴ� key�� ���� ��� key�� �ش��ϴ� value ����, ������� null ����
		public String search(int key) {
			int i = hashFunction(key);
			
			Slot slot = (Slot) table[i].getFirstData();
			if(slot != null && slot.getKey() == key) {
				return slot.value;
			} else {
				
				while(true) {
					slot = (Slot) table[i].getNextData();
					
					if(slot == null) {
						break;
					}
					
					if(slot.getKey() == key) {
						return slot.getValue();
					}
				}
			}
			
			return null;    // key���� �������� ���� ���
		}
		
		// key�� �ش��ϴ� �� ����, ��ġġ�ϴ� key�� ���� ��� �ش� slot ���� �� key�� �ش��ϴ� value ����, ������� null ����
		public String delete(int key) {
			int i = hashFunction(key);
			String deletedValue = "";
			
			Slot slot = (Slot) table[i].getFirstData();
			if(slot != null && slot.getKey() == key) {
				deletedValue = slot.getValue();
				table[i].remove(slot);
				
				return deletedValue;
			} else {
				
				while(true) {
					slot = (Slot) table[i].getNextData();
					if(slot == null) {
						break;
					}
					
					if(slot.getKey() == key) {
						deletedValue = slot.getValue();
						table[i].remove(slot);
						
						return deletedValue;
					}
				}
			}
			
			return null;
		}
		
	}
	
	static class Slot {
		private int key;
		private String value;
		
		Slot(int key, String value) {
			this.key = key;
			this.value = value;
		}

		public int getKey() {
			return key;
		}

		public void setKey(int key) {
			this.key = key;
		}

		public String getValue() {
			return value;
		}

		public void setValue(String value) {
			this.value = value;
		}
		
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
		public void add(Object object) {
			Node newNode = new Node(object);
			newNode.setNext(head.getNext());
			head.setNext(newNode);
			size++;
		}
		
		// ù��° ��� ��������
		public Object getFirstData() {
			before = head;
			position = head.getNext();
			
			if(position == null) {
				return null;
			}
			
			return position.getData();
		}
		
		// ���� ��� ��������
		public Object getNextData() {
			if(position == null || position.getNext() == null) {
				return null;
			}
			
			before = position;
			position = position.getNext();
			
			return position.getData();
		}
		
		// ��� ��ȸ
		public boolean contains(Object object) {
			if(getFirstData() != null && object.equals(getFirstData())) {
				return true;
			}
			
			while(true) {
				Object data = getNextData();
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
				before.setNext(position.getNext());
				position = before;
				size--;
				
				return data;
			}
			
			while(true) {
				data = getNextData();
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
