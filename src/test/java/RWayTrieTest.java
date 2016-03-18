import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

/**
 * Tests for RWayTrieTest class
 * @author Oleksandr_Dombrovsky
 *
 */
public class RWayTrieTest {

	/**
	 * Tests the add method
	 */
	@Test
	public void testAdd() {
		RWayTrie rwt = new RWayTrie();
		assert(rwt.size() == 1);
		
		// add one word
		rwt.add(new Tuple("word", 4));
		assert(rwt.size() == 1);	
		
		// input the same words
		rwt.add(new Tuple("word", 4));
		rwt.add(new Tuple("word", 4));
		rwt.add(new Tuple("word", 4));
		assert(rwt.size() == 1);	
		
		// input empty values 
		rwt.add(new Tuple("", 0));
		assert(rwt.size() == 1);	
		
		// words amount
		rwt.add(new Tuple("word2", 5));
		rwt.add(new Tuple("word3", 5));
		rwt.add(new Tuple("word4", 5));
		assert(rwt.size() == 4);
	}

	/**
	 * Tests the contains method
	 */
	@Test
	public void testContains() {
		RWayTrie rwt = new RWayTrie();
		rwt.add(new Tuple("word", 4));
		boolean expected = true;
		boolean actual = rwt.contains("word");
		assertEquals(expected, actual);
		
		// check for spaces 
		rwt.add(new Tuple("", 0));
		rwt.add(new Tuple("   ", 0));
		rwt.add(new Tuple("	", 0));
		expected = false;
		actual = rwt.contains("");
		assertEquals(expected, actual);
		
		// check with capitals 
		rwt.add(new Tuple("aaa", 3));
		rwt.add(new Tuple("aAa", 3));
		rwt.add(new Tuple("aAA", 3));
		expected = false;
		actual = rwt.contains("AAA");
		assertEquals(expected, actual);
	}

	/**
	 * Tests the delete method
	 */
	@Test
	public void testDelete() {
		RWayTrie rwt = new RWayTrie();
		rwt.add(new Tuple("word", 4));
		boolean expected = true;
		boolean actual = rwt.delete("word");
		assertEquals(expected, actual);
		
		// check for empty value
		rwt.add(new Tuple("", 0));
		expected = false;
		actual = rwt.delete("");
		assertEquals(expected, actual);
		
		// ordinary case 
		rwt.add(new Tuple("one", 3));
		rwt.add(new Tuple("two", 3));
		rwt.add(new Tuple("three", 4));
		expected = true;
		actual = rwt.delete("two");
		assertEquals(expected, actual);
	}

	/**
	 * Tests the words method
	 */
	@Test
	public void testWords() {
		RWayTrie rwt = new RWayTrie();
		rwt.add(new Tuple("one", 3));
		rwt.add(new Tuple("two", 3));
		rwt.add(new Tuple("three", 3));
		int expected = 3;
		int actual = ((List<String>) rwt.words()).size();
		assertEquals(expected, actual);
		
		// check for spaces
		rwt.add(new Tuple("", 0));
		expected = 3;
		actual = ((List<String>) rwt.words()).size();
		
		// check for wrong weight
		rwt.add(new Tuple("", 5));
		expected = 3;
		actual = ((List<String>) rwt.words()).size();
	}

	/**
	 * Tests the wordWithPrefix method
	 */
	@Test
	public void testWordWithPrefix() {
		RWayTrie rwt = new RWayTrie();
		rwt.add(new Tuple("word", 4));
		rwt.add(new Tuple("world", 5));
		rwt.add(new Tuple("work", 4));
		rwt.add(new Tuple("worry", 5));
		rwt.add(new Tuple("happy", 5));
		int expected = 4;
		int actual = ((List<String>) rwt.wordWithPrefix("wor")).size();
		assertEquals(expected, actual);
		
		String expectedString = "happy";
		String actualString = ((List<String>) rwt.wordWithPrefix("h")).get(0);
		assertEquals(expected, actual);
		
		// check for empty pattern
		expected = 5;
		actual = ((List<String>) rwt.wordWithPrefix("")).size();
		assertEquals(expected, actual);
	}

	/**
	 * Tests the size method
	 */
	@Test
	public void testSize() {
		RWayTrie rwt = new RWayTrie();
		rwt.add(new Tuple("one", 3));
		int expected = 1;
		int actual = rwt.size();
		assertEquals(expected, actual);
		
		// add and count
		rwt.add(new Tuple("two", 3));
		rwt.add(new Tuple("three", 4));
		rwt.add(new Tuple("four", 4));
		expected = 4;
		actual = rwt.size();
		assertEquals(expected, actual);
		
		// delete and count
		rwt.delete("one");
		rwt.delete("two");
		rwt.delete("three");
		rwt.delete("four");
		expected = 0;
		actual = rwt.size();
		assertEquals(expected, actual);
	}

}
