package list;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SimpleLinkedListTest {
	
	private static final Logger log = LoggerFactory.getLogger(SimpleLinkedListTest.class);
	
	@Test
	public void stringTest() {
		SimpleLinkedList<String> list = new SimpleLinkedList<>();
		
		assertTrue(list.isEmpty());
		
		list.add("a");
		list.add("b");
		list.add("c");
		list.add("d");
		list.add("e");
		
		assertTrue(list.contains("a"));
		assertEquals(5, list.size());
		
		log.info("{} ", list.getFirstData());
		String data;
		while(true) {
			data = list.getNextData();
			if(data == null) {
				break;
			}
			log.info("{} ", data);
		}
		System.out.println();
		
		assertEquals("a", list.remove("a"));
		assertEquals("c", list.remove("c"));
		
		assertEquals(3, list.size());
	}
	
	@Test
	public void integerTest() {
		SimpleLinkedList<Integer> list = new SimpleLinkedList<>();
		
		assertTrue(list.isEmpty());
		
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(4);
		list.add(5);
		
		assertTrue(list.contains(1));
		assertEquals(5, list.size());
		
		log.info("{} ", list.getFirstData());
		Integer data;
		while(true) {
			data = list.getNextData();
			if(data == null) {
				break;
			}
			log.info("{} ", data);
		}
		System.out.println();
		
		assertEquals(new Integer(1), list.remove(1));
		assertEquals(new Integer(3), list.remove(3));
		
		assertEquals(3, list.size());
	}
}
