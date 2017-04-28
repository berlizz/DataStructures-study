/*
 *	Array list 	
 * 	�迭�� ������� ������ ����Ʈ
 */

package list;

public class ArrayList {

	public static final int SIZE = 100;
	
	public static void main(String[] args) {
		ArrayList.List list = new ArrayList.List(SIZE);
		
		System.out.println(list.isEmpty());
		
		list.add("a");
		list.add("b");
		list.add("c");
		list.add("d");
		list.add("a");
		list.add("b");
		System.out.println("���� ������ �� : " + list.count());
		for(int i=0; i<list.count(); i++) {
			System.out.print(list.get(i) + " ");
		}
		System.out.println();
		
		System.out.println(list.indexOf("a"));
		System.out.println(list.indexOf("b"));
		System.out.println(list.indexOf("c"));
		
		System.out.println("������ ������ : " + list.remove(0));
		System.out.println("���� ������ �� : " + list.count());
		for(int i=0; i<list.count(); i++) {
			System.out.print(list.get(i) + " ");
		}
		System.out.println();
		
		System.out.println(list.indexOf("a"));
		
		
	}
	
	static class List {
		private Object[] elements;
		private int position;
		
		List(int size) {
			elements = new Object[size];
			position = -1;
		}
		
		public boolean isEmpty() {
			if(position == -1) {
				return true;
			}
			
			return false;
		}
		
		// ������ �߰�
		public boolean add(Object element) {
			position++;
			
			if((position + 1) > SIZE) {
				position--;
				return false;
			}
			
			elements[position] = element;
			return true;
		}
		
		// ������ ��������
		public Object get(int index) {
			return elements[index];
		}
		
		// ������ ����
		public Object remove(int index) {
			Object returnValue = elements[index];
			
			for(int i=index + 1; i<=elements.length - 1; i++) {
				elements[i-1] = elements[i];
			}
			
			elements[position] = null;
			position--;
			
			return returnValue;
		}
		
		// ������ Ž��
		public int indexOf(Object data) {
			if(data == null) {
				for(int i=0; i<count(); i++) {
					if(elements[i] == null) {
						return i;
					}
				}
			} else {
				for(int i=0; i<count(); i++) {
					if(data.equals(elements[i])) {
						return i;
					}
				}
			}
			
			
			return -1;
		}
		
		// ������ ����
		public int count() {
			return position + 1;
		}
		
	}
}
