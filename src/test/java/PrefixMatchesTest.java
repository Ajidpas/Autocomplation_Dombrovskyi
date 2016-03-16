import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

public class PrefixMatchesTest {

	@Test
	public void testAdd() {
		PrefixMatches pm = new PrefixMatches();
		int expected = 1;
		int actual = pm.add("word");
		assertEquals(expected, actual);	
		
		expected = 0;
		actual = pm.add("word", "word", "word");
		assertEquals(expected, actual);	
		
		expected = 0;
		actual = pm.add("", "", "");
		assertEquals(expected, actual);	
		
		expected = 3;
		actual = pm.add("wordA", "wordB", "wordC");
		assertEquals(expected, actual);
		
		expected = 3;
		actual = pm.add(new String[]{"textA", "textB", "textC"});
		assertEquals(expected, actual);
	}

	@Test
	public void testContains() {
		PrefixMatches pm = new PrefixMatches();
		pm.add("word");
		boolean expected = true;
		boolean actual = pm.contains("word");
		assertEquals(expected, actual);
		
		pm.add("", "", "");
		expected = false;
		actual = pm.contains("");
		assertEquals(expected, actual);
		
		pm.add(new String[]{"aaa", "aAa", "aAA"});
		expected = false;
		actual = pm.contains("AAA");
		assertEquals(expected, actual);
	}

	@Test
	public void testDelete() {
		PrefixMatches pm = new PrefixMatches();
		pm.add("word");
		boolean expected = true;
		boolean actual = pm.delete("word");
		assertEquals(expected, actual);
		
		pm.add("");
		expected = false;
		actual = pm.delete("");
		assertEquals(expected, actual);
		
		pm.add(new String[]{"one", "two", "three"});
		expected = true;
		actual = pm.delete("two");
		assertEquals(expected, actual);
		
		pm.add("        space        ");
		expected = true;
		actual = pm.delete("space");
		assertEquals(expected, actual);
	}

	@Test
	public void testSize() {
		PrefixMatches pm = new PrefixMatches();
		pm.add("one");
		int expected = 1;
		int actual = pm.size();
		assertEquals(expected, actual);
		
		pm.add("");
		expected = 1;
		actual = pm.size();
		assertEquals(expected, actual);
		
		pm.add(new String[]{"two", "three", "four"});
		expected = 4;
		actual = pm.size();
		assertEquals(expected, actual);
		
		pm.delete("one");
		pm.delete("two");
		pm.delete("three");
		pm.delete("four");
		expected = 0;
		actual = pm.size();
		assertEquals(expected, actual);
	}

	@Test
	public void testWordsWithPrefixStringInt() {
		PrefixMatches pm = new PrefixMatches();
		pm.add(new String[]{"word1", "word12", "word123", "word1234"});
		int expected = 2;
		int actual = ((List<String>) pm.wordsWithPrefix("word")).size();
		assertEquals(expected, actual);
		
		pm.add(new String[]{"word3", "word4"});
		expected = 4;
		actual = ((List<String>) pm.wordsWithPrefix("word")).size();
		assertEquals(expected, actual);
	}

	@Test
	public void testWordsWithPrefixString() {
		PrefixMatches pm = new PrefixMatches();
		pm.add(new String[]{"word", "word2", "word33", "word444"});
		int expected = 1;
		int actual = ((List<String>) pm.wordsWithPrefix("word", 1)).size();
		assertEquals(expected, actual);
		
		expected = 2;
		actual = ((List<String>) pm.wordsWithPrefix("word", 2)).size();
		assertEquals(expected, actual);
		
		expected = 3;
		actual = ((List<String>) pm.wordsWithPrefix("word", 3)).size();
		assertEquals(expected, actual);
		
		expected = 4;
		actual = ((List<String>) pm.wordsWithPrefix("word", 4)).size();
		assertEquals(expected, actual);
	}

}
