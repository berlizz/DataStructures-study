/* 
 * Array based stack
 * 배열 기반 스택
 *  
 */

package stack;

public class ArrayBasedStack<E> implements Stack<E> {
	
	private static final int ARRAY_LENGTH = 100;
	private E[] elements;
	private int topIndex;
	
	public ArrayBasedStack() {
		this.elements = (E[]) new Object[ARRAY_LENGTH];
		this.topIndex = -1;
	}
	
	@Override
	public boolean isEmpty() {
		return topIndex == -1;
	}
	
	@Override
	public E push(E data) {
		if(topIndex >= ARRAY_LENGTH -1) {
			return null;
		}
		
		topIndex++;
		elements[topIndex] = data;
		
		return data;
	}
	
	@Override
	public E pop() {
		if(isEmpty()) {
			return null;
		}
		
		E data = elements[topIndex];
		topIndex--;
		
		return data;
	}
	
	@Override
	public E peek() {
		return (isEmpty())? null : elements[topIndex];
	}

	
}
