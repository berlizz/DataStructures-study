/*
 *	Array list 	
 * 	배열을 기반으로 구현된 리스트
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
		System.out.println("현재 데이터 수 : " + list.count());
		for(int i=0; i<list.count(); i++) {
			System.out.print(list.get(i) + " ");
		}
		System.out.println();
		
		System.out.println(list.indexOf("a"));
		System.out.println(list.indexOf("b"));
		System.out.println(list.indexOf("c"));
		
		System.out.println("삭제된 데이터 : " + list.remove(0));
		System.out.println("현재 데이터 수 : " + list.count());
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
		
		// 데이터 추가
		public boolean add(Object element) {
			position++;
			
			if((position + 1) > SIZE) {
				position--;
				return false;
			}
			
			elements[position] = element;
			return true;
		}
		
		// 데이터 가져오기
		public Object get(int index) {
			return elements[index];
		}
		
		// 데이터 삭제
		public Object remove(int index) {
			Object returnValue = elements[index];
			
			for(int i=index + 1; i<=elements.length - 1; i++) {
				elements[i-1] = elements[i];
			}
			
			elements[position] = null;
			position--;
			
			return returnValue;
		}
		
		// 데이터 탐색
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
		
		// 데이터 갯수
		public int count() {
			return position + 1;
		}
		
	}
}
