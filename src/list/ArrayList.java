/*
 * 	배열을 기반으로 구현된 리스트
 * 	
 */

package list;

public class ArrayList {

	public static void main(String[] args) {
		Object data = null;
		List list = new List(10);

		list.insert("1");
		list.insert("2");
		list.insert("2");
		list.insert("3");
		
		System.out.println("현재 데이터 수 : " + list.count);
		
		System.out.print(list.first());
		data = list.next();
		while(data != null) {
			System.out.print(", " + data);
			data = list.next();
		}
		
		System.out.println();
		System.out.println();
		
	}
	
	static class List {
		int index, count;
		Object[] list;
		
		List(int size) {
			this.index = 0;
			this.count = 0;
			this.list = new Object[size];
		}
		
		void insert(Object data) {
			list[count] = data;
			count++;
		}
		
		int count() {
			return count;
		}
		
		Object first() {
			if(count == 0) {
				return null;
			}
			
			index = 0;
			return list[0];
		}
		
		Object next() {
			index++;
			if(index >= count) {
				return null;
			}
			
			return list[index];
		}
		
		Object remove() {
			Object removedValue = list[index];
			
			Object data = null;
			while(next() != null) {
				list[index] = data;
			}
			
			return removedValue;
		}
		
	}
}
