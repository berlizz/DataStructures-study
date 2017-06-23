package queue;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DoubleEndedQueueTest {
	
	private static final Logger logger = LoggerFactory.getLogger(DoubleEndedQueueTest.class);
	
	@Test
	public void doubleEndeddequeTest() {
		DoubleEndedQueue<String> deque = new DoubleEndedQueue<>();
		
		assertTrue(deque.isEmpty());
		
		assertTrue(deque.enqueue("1"));
		assertTrue(deque.enqueue("2"));
		assertTrue(deque.enqueue("3"));
		assertTrue(deque.enqueue("4"));
		
		assertEquals("1", deque.peek());
		
		String data;
		while(true) {
			data = deque.dequeue();
			if(data == null) {
				break;
			}
			logger.info("{}", data);
		}
		
		assertTrue(deque.addFirst("a"));
		assertTrue(deque.addLast("b"));
		assertEquals("a", deque.getFirst());
		assertEquals("b", deque.getLast());
		
		logger.info("{}", deque.removeLast());
		logger.info("{}", deque.removeLast());
		logger.info("{}", deque.removeLast());
	}

}
