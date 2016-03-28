package trie.treetraversal.matcher;
import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import trie.Trie;
import trie.treetraversal.Tuple;

/**
 * Tests for PrefixMatches class
 * @author Oleksandr_Dombrovsky
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class PrefixMatchesTest {
	
	/** mock object for Trie object */
	@Mock
	private Trie mockedTrie;
	
	/** inject mock object for PrefixMatches object */
	@InjectMocks
	private PrefixMatches mockedPrefixMatches;	

	/**
	 * Tests the add method
	 */
	@Test
	public void testAdd() {
		PrefixMatches pm = new PrefixMatches();
		
		// word's length should be more then 2
		int expected = 0;
		int actual = pm.add("wo");
		assertEquals(expected, actual);
		
		// add one word
		expected = 1;
		actual = pm.add("word");
		assertEquals(expected, actual);	
		
		// input the same words
		expected = 0;
		actual = pm.add("word", "word", "word");
		assertEquals(expected, actual);	
		
		// input empty values 
		expected = 0;
		actual = pm.add("", "  ", "  ");
		assertEquals(expected, actual);	
		
		// words amount
		expected = 3;
		actual = pm.add("wordA", "wordB", "wordC");
		assertEquals(expected, actual);
		
		// words amount in array
		expected = 3;
		actual = pm.add(new String[]{"textA", "textB", "textC"});
		assertEquals(expected, actual);
	}

	/**
	 * Tests the contains method
	 */
	@Test
	public void testContains() {
		PrefixMatches pm = new PrefixMatches();
		pm.add("word");
		boolean expected = true;
		boolean actual = pm.contains("word");
		assertEquals(expected, actual);
		
		// check for spaces 
		pm.add("", "   ", "	");
		expected = false;
		actual = pm.contains("");
		assertEquals(expected, actual);
		
		// check with capitals 
		pm.add(new String[]{"aaa", "aAa", "aAA"});
		expected = false;
		actual = pm.contains("AAA");
		assertEquals(expected, actual);
	}

	/**
	 * Tests the delete method
	 */
	@Test
	public void testDelete() {
		PrefixMatches pm = new PrefixMatches();
		pm.add("word");
		boolean expected = true;
		boolean actual = pm.delete("word");
		assertEquals(expected, actual);
		
		// check for empty value
		pm.add("");
		expected = false;
		actual = pm.delete("");
		assertEquals(expected, actual);
		
		// ordinary case 
		pm.add(new String[]{"one", "two", "three"});
		expected = true;
		actual = pm.delete("two");
		assertEquals(expected, actual);
		
		// word with spaces
		pm.add("        space        ");
		expected = true;
		actual = pm.delete("space");
		assertEquals(expected, actual);
	}

	/**
	 * Tests the size method
	 */
	@Test
	public void testSize() {
		PrefixMatches pm = new PrefixMatches();
		pm.add("one");
		int expected = 1;
		int actual = pm.size();
		assertEquals(expected, actual);
		
		// empty value
		pm.add("");
		expected = 1;
		actual = pm.size();
		assertEquals(expected, actual);
		
		// add and count
		pm.add(new String[]{"two", "three", "four"});
		expected = 4;
		actual = pm.size();
		assertEquals(expected, actual);
		
		// delete and count
		pm.delete("one");
		pm.delete("two");
		pm.delete("three");
		pm.delete("four");
		expected = 0;
		actual = pm.size();
		assertEquals(expected, actual);
	}

	/**
	 * Tests the wordsWithPrefix method
	 */
	@Test
	public void testWordsWithPrefixStringInt() {
		PrefixMatches pm = new PrefixMatches();
		pm.add(new String[]{"word1", "word12", "word123", "word1234"});
		int expected = 4;
		int actual = pm.size();
		assertEquals(expected, actual);
		
		pm.add(new String[]{"word3", "word4"});
		expected = 6;
		actual = pm.size();
		assertEquals(expected, actual);
	}

	/**
	 * Tests the wordWithPrefix method
	 */
	@Test
	public void testWordsWithPrefixString() {
		PrefixMatches pm = new PrefixMatches();
		pm.add(new String[]{"word123"});
		boolean expected = false;
		boolean actual = pm.wordsWithPrefix("word", 1).iterator().hasNext();
		assertEquals(expected, actual);
		expected = true;
		actual = pm.wordsWithPrefix("word", 3).iterator().hasNext();
	}
	
	@Test
	public void wordsShouldNotBeCalledWithSize() {
		// add method calling count should be the same as size
		int size = fillVocabulary(mockedPrefixMatches);
		verify(mockedTrie, times(size)).add(any(Tuple.class));
		
		// verify that calling size doesn't means tree traversal
		mockedPrefixMatches.size();
		verify(mockedTrie, never()).words();
		
		
	}
	
	/**
	 * For filling vocabulary tree only
	 * 
	 * @param pm PrefixMatches object
	 * @return count of words was filled into the tree
	 */
	private static int fillVocabulary(PrefixMatches pm) {
		int size = 0;
		BufferedReader buffReader = null;
		try {
			FileReader fr = new FileReader("src\\main\\resources\\TextDemo.txt");
			buffReader = new BufferedReader(fr);
			String line = null;
			while ((line = buffReader.readLine()) != null) {
				String[] words = line.split(" ");
				for (String word : words) {
					if (!word.isEmpty()) {
						word = trimWord(word);
						pm.add(word);
						size++;
					}
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (buffReader != null) {
				try {
					buffReader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return size;
	}
	
	/**
	 * Trims word
	 * 
	 * @param word word might be trimmed 
	 * @return clear word
	 */
	private static String trimWord(String word) {
		StringBuilder trimedWord = new StringBuilder(word);
		for (int i = 0; i < word.length(); i++) {
			char c = word.charAt(i);
			if (c < 64) {
				trimedWord.deleteCharAt(0);
			} else {
				break;
			}
		}
		return trimedWord.toString();
	}

}
