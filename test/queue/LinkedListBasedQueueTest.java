package queue;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LinkedListBasedQueueTest {

	private static final Logger logger = LoggerFactory.getLogger(LinkedListBasedQueueTest.class);
	
	@Test
	public void linkedListBasedQueueTest() {
		LinkedListBasedQueue<String> queue = new LinkedListBasedQueue<>();
		assertTrue(queue.isEmpty());
		
		assertTrue(queue.enqueue("1"));
		assertTrue(queue.enqueue("2"));
		assertTrue(queue.enqueue("3"));
		assertTrue(queue.enqueue("4"));
		
		assertEquals("1", queue.peek());
		
		String data;
		while(true) {
			data = queue.dequeue();
			if(data == null) {
				break;
			}
			logger.info("{}", data);
		}
	}
}
