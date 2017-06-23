package stack;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LinkedListBasedStackTest {

	private static final Logger logger = LoggerFactory.getLogger(LinkedListBasedStackTest.class);
	
	@Test
	public void linkedListBasedStackTest() {
		LinkedListBasedStack<String> stack = new LinkedListBasedStack<>();
		
		assertTrue(stack.isEmpty());
		
		assertEquals("a", stack.push("a"));
		assertEquals("1", stack.push("1"));
		assertEquals("2", stack.push("2"));
		assertEquals("b", stack.push("b"));
		assertEquals("4", stack.push("4"));
		
		assertEquals("4", stack.peek());
		
		String data;
		while(true) {
			data = stack.pop();
			if(data == null) {
				break;
			}
			logger.info("{}", data);
		}
		
		assertTrue(stack.isEmpty());
	}
	
}
