/*
 *	Array list 	
 * 	배열을 기반으로 구현된 리스트
 */

package list;

public class ArrayList<E> {
	
	private static final int ARRAY_SIZE = 100;

	private E[] elements;
	private int position;
	
	public ArrayList() {
		elements = (E[]) new Object[ARRAY_SIZE];
		this.position = -1;
	}
	
	public boolean isEmpty() {
		return position == -1;
	}
	
	// 데이터 추가
	public void add(E data) throws Exception {
		if(position == (ARRAY_SIZE -1)) {
			throw new Exception("array full");
		}
		
		position++;
		elements[position] = data;
	}
	
	// 데이터 가져오기
	public E get(int index) {
		return elements[index];
	}
	
	// 데이터 삭제
	public E remove(int index) {
		E returnValue = elements[index];
		
		for(int i=index + 1; i<elements.length; i++) {
			elements[i - 1] = elements[i];
		}
		
		elements[position] = null;
		position--;
		
		return returnValue;
	}
	
	// 데이터 조회
	public int indexOf(E data) {
		for(int i=0; i<position + 1; i++) {
			if(elements[i].equals(data)) {
				return i;
			}
		}
		
		return -1;  // 데이터 조회 실패
	}
	
	// 데이터 갯수
	public int count() {
		return position + 1;
	}
}
