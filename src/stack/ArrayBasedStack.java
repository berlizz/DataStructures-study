/* 
 * Array based stack
 * 배열 기반 스택
 *  
 */

package stack;

public class ArrayBasedStack {
	
	private static final int ARRAY_LENGTH = 100;

	public static void main(String[] args) {
		ArrayBasedStack.Stack stack = new ArrayBasedStack.Stack();
		
		System.out.println("isEmpty : " + stack.isEmpty());
		stack.push("1");
		stack.push("2");
		stack.push("3");
		stack.push("4");
		
		System.out.println("peek : " + stack.peek());
		
		while(true) {
			Object data = stack.pop();
			if(data == null) {
				break;
			}
			System.out.print(data + " ");
		}
		
		System.out.println();
		
		System.out.println("isEmpty : " + stack.isEmpty());
		System.out.println("peek : " + stack.peek());
	}
	
	static class Stack {
		private Object[] elements;
		private int topIndex;
		
		Stack() {
			elements = new Object[ARRAY_LENGTH];
			topIndex = -1;
		}
		
		public boolean isEmpty() {
			return topIndex == -1;
		}
		
		public boolean push(Object object) {
			if(topIndex == (ARRAY_LENGTH - 1) || object == null) {
				return false;
			}
			
			topIndex++;
			elements[topIndex] = object;
			
			return true;
		}
		
		public Object pop() {
			if(isEmpty()) {
				return null;
			}
			
			Object data = elements[topIndex];
			topIndex--;
			
			return data;
		}
		
		public Object peek() {
			if(isEmpty()) {
				return null;
			}
			
			return elements[topIndex];
		}
	}
}
