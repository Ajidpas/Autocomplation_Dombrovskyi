
public class PrefixMatches {
	@SuppressWarnings("unused")
	private Trie trie;
	
	/**
	 * In-memory vocabulary creation
	 * 
	 * @param strings word, string or array of words/strings. 
	 * If string is coming, she should be brake into the words with space delimiter.
	 * @return words count
	 */
	public int add(String... strings) {
		throw new UnsupportedOperationException();
	}
	
	/**
	 * Check does word exist in the vocabulary 
	 * @param word checked word
	 * @return true and false in the corresponding case
	 */
	public boolean contains(String word) {
		throw new UnsupportedOperationException();
	}
	
	/**
	 * Deletes the word from the vocabulary
	 * @param word word might be deleted from the vocabulary
	 * @return true and false in corresponding case off success or failure 
	 */
	public boolean delete(String word) {
		throw new UnsupportedOperationException();
	}
	
	/**
	 * Number of words in the vocabulary
	 * 
	 * @return integer value of the number of the words
	 */
	public int size() {
		throw new UnsupportedOperationException();
	}
	
	/**
	 * If inputed pref is longer or equals 2, returns array of words with k different lengths 
	 * begin from minimum and start from given pref. 
	 * 
	 * @param pref
	 * @param k
	 * @return
	 */
	public Iterable<String> wordsWithPrefix(String pref, int k) {
		throw new UnsupportedOperationException();
	}
	
	/**
	 * If inputed pref is longer then 2 symbols, returns set of words with different lengths k=3 
	 * start from minimum that begin from pref
	 * 
	 * @param perf
	 * @return
	 */
	public Iterable<String> wordsWithPrefix(String perf) {
		throw new UnsupportedOperationException();
	}

}
