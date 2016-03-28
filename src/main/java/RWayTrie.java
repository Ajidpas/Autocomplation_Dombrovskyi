import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

/**
 * RWayTrie class 
 * @author Oleksandr_Dombrovsky
 *
 */
public class RWayTrie implements Trie {
	
	/** ascii length */
	private static final int EXTENDED_ASCII = 128;
	
	/** trie size - amount of words */
	private int size;
	
	/** root element */
	private Node root;
	
	/**
	 * Constructor
	 */
	public RWayTrie() {
		root = new Node();
		size = 0;
	}

	/**
	 * Add a tuple: word - term, and it's weight. Word length should be taken as weight.
	 * 
	 * @param tuple term-weight tuple
	 */
	public void add(Tuple tuple) {
		int weight = tuple.getWeight();
		String term = tuple.getTerm();
		if (!contains(term)) {
			root = put(root, term, weight, 0);
			size++;
		}
	}
	
	/**
	 * Put word in the tree 
	 * 
	 * @param node current node 
	 * @param term string word which might be added to the tree 
	 * @param weight weight (length) of the word 
	 * @param k increment
	 * @return last node of the word
	 */
	private Node put(Node node, String term, int weight, int k) {
		if (node == null) {
			node = new Node();
		}
		if (k == term.length()) {
			node.weight = weight;
			return node;
		}
		char c = term.charAt(k);
		node.children[c] = put(node.children[c], term, weight, k + 1);
		return node;
	}

	/**
	 * Does word exist in trie
	 * 
	 * @param word term
	 * @return true or false in the corresponding case 
	 */
	public boolean contains(String word) {
		return get(word) != 0;
	}
	
	/**
	 * Get weight of the word 
	 * 
	 * @param term string word 
	 * @return length of the word if such word exists in the tree 
	 */
	private int get(String term) {
		Node node = get(root, term, 0);
		if (node == null) {
			return 0;
		}
		return (int) node.weight;
	}
	
	/**
	 * Get end node of the word in the tree 
	 * 
	 * @param node current node 
	 * @param term string word 
	 * @param k increment
	 * @return true or false if such node exists corresponding to word
	 */
	private Node get(Node node, String term, int k) {
		if (node == null) {
			return null;
		}
		if (k == term.length()) {
			return node;
		}
		char c = term.charAt(k);
		return get(node.children[c], term, k + 1);
	}

	/**
	 * Deletes the word from trie
	 * 
	 * @param word term
	 * @return true or false in the corresponding case
	 */
	public boolean delete(String word) {
		if (!contains(word)) {
			return false;
		}
		root = delete(root, word, 0);
		size--;
		return true;
	}
	
	/**
	 * Delete word from the tree 
	 * 
	 * @param node current node 
	 * @param term string word
	 * @param k increment 
	 * @return next node
	 */
	private Node delete(Node node, String term, int k) {
		if (node == null) {
			return null;
		}
		if (k == term.length()) {
			node.weight = 0;
			if (areAllChildrenEmpty(node)) {
				return null;
			} else {
				return node;
			}
		}
		char c = term.charAt(k);
		node.children[c] = delete(node.children[c], term, k + 1);
		if (node.weight != 0) {
			return node;
		}
		if (areAllChildrenEmpty(node)) {
			return null;
		}
		return node;
	}
	
	/**
	 * Check is current node's node array empty or not
	 * 
	 * @param node current node
	 * @return true or false if node array is empty or not corresponding
	 */
	private boolean areAllChildrenEmpty(Node node) {
		for (Node n : node.children) {
			if (n != null) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Iterator through all words, breadth-first search
	 * 
	 * @return list of words
	 */
	public Iterable<String> words() {
		return new Iterable<String>() {

			public Iterator iterator() {
				return new TrieIterator(root);
			}
			
		};
	}

	/**
	 * Iterator through all words, that start from pref, breath-first search
	 * 
	 * @param pref prefix
	 * @return list of words
	 */
	public Iterable<String> wordWithPrefix(String pref) {
		Node currNode = root;
		final StringBuilder word = new StringBuilder();
		for (int i = 0; i < pref.length(); i++) {
			char c = pref.charAt(i);
			Node node = currNode.children[c];
			if (node == null) {
				return null;
			}
			currNode = node;
			word.append(c);
		}
		final Node node = currNode;
		return new Iterable<String>() {

			public Iterator<String> iterator() {
				// TODO Auto-generated method stub
				return new TrieIterator(node, word.toString());
			}
			
		};
	}

	/**
	 * Words number in the Trie
	 * 
	 * @return integer size 
	 */
	public int size() {
		return size;
	}
	
	/**
	 * Node class
	 * @author Oleksandr_Dombrovsky
	 *
	 */
	public static class Node {
		
		/** weight of word */
		int weight;
		
		/** node children nods */
		Node[] children;
		
		/** 
		 * Constructor
		 */
		public Node() {
			children = new Node[EXTENDED_ASCII];
		}
		
		public int getWeight() {
			return weight;
		}
		
		public Node[] getChildren() {
			return children;
		}
		
	}

}