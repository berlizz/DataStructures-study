package list;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ArrayListTest {

	private static final Logger log = LoggerFactory.getLogger(ArrayListTest.class);
	
	@Test
	public void stringTest() {
		ArrayList<String> list = new ArrayList<>();
		
		assertTrue(list.isEmpty());
		
		try {
			list.add("a");
			list.add("b");
			list.add("c");
			list.add("d");
			list.add("e");
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		
		for(int i=0; i<list.count(); i++) {
			log.info("{}", list.get(i));
		}
		System.out.println();
		
		assertEquals(0, list.indexOf("a"));
		assertEquals(-1, list.indexOf("z"));
		
		assertEquals("a", list.remove(0));
		assertEquals("b", list.remove(0));
		
		for(int i=0; i<list.count(); i++) {
			log.info("{}", list.get(i));
		}
		
	}
}
