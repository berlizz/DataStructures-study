package queue;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CircularQueueTest {

	private static final Logger logger = LoggerFactory.getLogger(CircularQueueTest.class);
	
	private CircularQueue<String> queue;
	
	@Before
	public void init() {
		queue = new CircularQueue<>();
	}
	
	@Test
	public void circularQueueTest() {
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
	
	@Test
	public void fullTest() {
		for(int i=0; i<100; i++) {
			queue.enqueue(String.valueOf(i));
		}
		
		assertFalse(queue.enqueue("100"));
	}
}
