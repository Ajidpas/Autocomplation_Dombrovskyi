package trie;

import trie.treetraversal.Tuple;

public interface Trie {
	
	/**
	 * Add a tuple: word - term, and it's weight. Word length should be taken as weight.
	 * 
	 * @param tuple term-weight tuple
	 */
	public void add(Tuple tuple);
	
	/**
	 * Does word exist in trie
	 * 
	 * @param word term
	 * @return true or false in the corresponding case 
	 */
	public boolean contains(String word);
	
	/**
	 * Deletes the word from trie
	 * 
	 * @param word term
	 * @return true or false in the corresponding case
	 */
	public boolean delete(String word);
	
	/**
	 * Iterator through all words, breadth-first search
	 * 
	 * @return list of words
	 */
	public Iterable<String> words();
	
	/**
	 * Iterator through all words, that start from pref, breath-first search
	 * 
	 * @param pref prefix
	 * @return list of words
	 */
	public Iterable<String> wordWithPrefix(String pref);
	
	/**
	 * Words number in the Trie
	 * 
	 * @return integer size 
	 */
	public int size();
	
}
