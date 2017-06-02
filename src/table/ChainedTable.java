/* 
 * Chained table
 * 테이블은 key와 value로 구성
 * 충돌(collision)을 해결하기 위한 방법으로 체이닝(chaining)사용
 * 체이닝은 닫힌 어드레싱 방법(closed addressing method)로 충돌이 발생해도 그자리의 키값으로 저장 
 * 저장하기 위해 linked list 사용, linked list로 slot을 연결해 나가는 방식으로 충돌문제 해결
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
	
	// hash 함수
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
		
		// 테이블에 데이터 추가
		public void insert(int key, String value) {
			int i = hashFunction(key);
			
			// key가 중복인 경우
			if(search(key) != null) {
				return;
			} else {
				// key가 중복되지 않은 경우 Slot을 생성하여 노드 추가
				Slot slot = new Slot(key, value);
				table[i].add(slot);
			}
			
		}
		
		// 일치하는 key가 존재하는지 탐색 , 일치치하는 key가 있을 경우 key에 해닫하는 value 리턴, 없을경우 null 리턴
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
			
			return null;    // key값이 존재하지 않은 경우
		}
		
		// key에 해당하는 값 삭제, 일치치하는 key가 있을 경우 해당 slot 삭제 후 key에 해닫하는 value 리턴, 없을경우 null 리턴
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
		
		// 노드 추가
		public void add(Object object) {
			Node newNode = new Node(object);
			newNode.setNext(head.getNext());
			head.setNext(newNode);
			size++;
		}
		
		// 첫번째 노드 가져오기
		public Object getFirstData() {
			before = head;
			position = head.getNext();
			
			if(position == null) {
				return null;
			}
			
			return position.getData();
		}
		
		// 다음 노드 가져오기
		public Object getNextData() {
			if(position == null || position.getNext() == null) {
				return null;
			}
			
			before = position;
			position = position.getNext();
			
			return position.getData();
		}
		
		// 노드 조회
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
		
		// 노드 삭제
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
