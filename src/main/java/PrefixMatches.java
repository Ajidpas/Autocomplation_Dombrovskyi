import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Queue;

public class PrefixMatches {
	
	/** trie object */
	private Trie trie;
	
	/**
	 * Constructor
	 */
	public PrefixMatches() {
		trie = new RWayTrie();
	}
	
	/**
	 * In-memory vocabulary creation
	 * 
	 * @param strings word, string or array of words/strings. 
	 * If string is coming, she should be brake into the words with space delimiter.
	 * @return words count
	 */
	public int add(String... strings) {
		int size = 0;
		List<String> allWords = new ArrayList<String>();
		for (String string : strings) {
			String[] words = string.split(" ");
			allWords.addAll(Arrays.asList(words));
		}
		for (String word : allWords) {
			if (!word.isEmpty() && !trie.contains(word)) {
				Tuple tuple = new Tuple(word, word.length());
				trie.add(tuple);
				size++;
			}
		}
		return size;
	}
	
	/**
	 * Check does word exist in the vocabulary 
	 * @param word checked word
	 * @return true and false in the corresponding case
	 */
	public boolean contains(String word) {
		return trie.contains(word);
	}
	
	/**
	 * Deletes the word from the vocabulary
	 * @param word word might be deleted from the vocabulary
	 * @return true and false in corresponding case off success or failure 
	 */
	public boolean delete(String word) {
		return trie.delete(word);
	}
	
	/**
	 * Number of words in the vocabulary
	 * 
	 * @return integer value of the number of the words
	 */
	public int size() {
		return trie.size();
	}
	
	/**
	 * If inputed pref is longer or equals 2, returns array of words with k different lengths 
	 * begin from minimum and start from given pref. 
	 * 
	 * @param pref prefix
	 * @param k length distinguish 
	 * @return all matched words
	 */
	public Iterable<String> wordsWithPrefix(String pref, int k) {
		Queue<String> words = (Queue<String>) trie.wordWithPrefix(pref);
		Iterator<String> it = words.iterator();
		while (it.hasNext()) {
			String word = (String) it.next();
			if (word.length() > pref.length() + k - 1) {
				it.remove();
			}
		}
		return words;
	}
	
	/**
	 * If inputed pref is longer then 2 symbols, returns set of words with different lengths k=3 
	 * start from minimum that begin from pref
	 * 
	 * @param perf prefix for matching
	 * @return queue of matched words 
	 */
	public Iterable<String> wordsWithPrefix(String pref) {
		return wordsWithPrefix(pref, 3);
	}

}
