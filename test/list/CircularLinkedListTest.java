package list;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CircularLinkedListTest {

	private static final Logger log = LoggerFactory.getLogger(CircularLinkedListTest.class);
	
	@Test
	public void stringTest() {
		CircularLinkedList<String> list = new CircularLinkedList<>();
		
		assertTrue(list.isEmpty());
		
		list.addLast("a");
		list.addLast("b");
		list.addLast("c");
		list.addLast("d");
		list.addLast("e");
		
		log.info("first data : {}", list.getFirstData());
		log.info("last data : {} ", list.getLastData());
		
		list.addFront("1");
		list.addFront("2");
		list.addFront("3");
		
		log.info("{}", list.getFirstData());
		String data;
		while(true) {
			data = list.getNextData();
			if(data == null) {
				break;
			}
			log.info("{}", data);
		}
		log.info("");
		
		assertTrue(list.contains("a"));
		assertFalse(list.contains("4"));
		
		assertEquals("3", list.remove("3"));
		assertEquals("e", list.remove("e"));
		
		log.info("{}", list.getFirstData());
		while(true) {
			data = list.getNextData();
			if(data == null) {
				break;
			}
			log.info("{}", data);
		}
		
	}
}
