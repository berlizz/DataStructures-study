package list;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SortedLinkedListTest {

	private static final Logger log = LoggerFactory.getLogger(SortedLinkedList.class);
	
	@Test
	public void stringTest() {
		SortedLinkedList<String> list = new SortedLinkedList<>();
		
		assertTrue(list.isEmpty());
		
		list.add("kim");
		list.add("1");
		list.add("lee");
		list.add("park");
		list.add("12");
		list.add("3");
		list.add("choi");
		list.add("4");
		
		log.info("{}", list.getFirstData());
		String data;
		while(true) {
			data = list.getNextData();
			if(data == null) {
				break;
			}
			log.info("{}", data);
		}
		System.out.println();
		
		assertTrue(list.contains("kim"));
		assertFalse(list.contains("44"));
		
		assertEquals("1", list.remove("1"));
	}
	
	@Test
	public void integerTest() {
		SortedLinkedList<Integer> list = new SortedLinkedList<>();
		
		list.add(new Integer(1));
		list.add(new Integer(4));
		list.add(new Integer(2));
		list.add(new Integer(5));
		list.add(new Integer(3));
		list.add(6);
		
		log.info("{}", list.getFirstData());
		Integer data;
		while(true) {
			data = list.getNextData();
			if(data == null) {
				break;
			}
			log.info("{}", data);
		}
	}
	
}
