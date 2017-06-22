/*
 *	Array list 	
 * 	�迭�� ������� ������ ����Ʈ
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
	
	// ������ �߰�
	public void add(E data) throws Exception {
		if(position == (ARRAY_SIZE -1)) {
			throw new Exception("array full");
		}
		
		position++;
		elements[position] = data;
	}
	
	// ������ ��������
	public E get(int index) {
		return elements[index];
	}
	
	// ������ ����
	public E remove(int index) {
		E returnValue = elements[index];
		
		for(int i=index + 1; i<elements.length; i++) {
			elements[i - 1] = elements[i];
		}
		
		elements[position] = null;
		position--;
		
		return returnValue;
	}
	
	// ������ ��ȸ
	public int indexOf(E data) {
		for(int i=0; i<position + 1; i++) {
			if(elements[i].equals(data)) {
				return i;
			}
		}
		
		return -1;  // ������ ��ȸ ����
	}
	
	// ������ ����
	public int count() {
		return position + 1;
	}
}
