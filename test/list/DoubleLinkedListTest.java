package list;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DoubleLinkedListTest {
	
	private static final Logger log = LoggerFactory.getLogger(DoubleLinkedListTest.class);

	@Test
	public void stringTest() {
		DoubleLinkedList<String> list = new DoubleLinkedList<>();
		
		assertTrue(list.isEmpty());
		
		list.add("a");
		list.add("b");
		list.add("c");
		list.add("d");
		list.add("e");
		assertEquals(5, list.size());
		
		log.info("{}", list.getFirstData());
		String data;
		while(true) {
			data = list.getNextData();
			if(data == null) {
				break;
			}
			log.info("{}", data);			
		}
		
		while(true) {
			data = list.getPrevData();
			if(data == null) {
				break;
			}
			log.info("{}", data);
		}
		
		assertTrue(list.contains("e"));
		
		assertEquals("a", list.remove("a"));
		assertNull(list.remove("z"));
	}
}
