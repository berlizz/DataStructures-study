package stack;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ArrayBasedStackTest {

	private static final Logger logger = LoggerFactory.getLogger(ArrayBasedStackTest.class);
	
	@Test
	public void arrayBasedStackTest() {
		ArrayBasedStack<String> stack = new ArrayBasedStack<>();
		
		assertTrue(stack.isEmpty());
		
		assertEquals("a", stack.push("a"));
		assertEquals("b", stack.push("b"));
		assertEquals("c", stack.push("c"));
		assertEquals("d", stack.push("d"));
		assertEquals("e", stack.push("e"));
		
		assertEquals("e", stack.peek());
		
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
