import java.util.LinkedList;
import java.util.Queue;

public class RWayTrie implements Trie {
	
	/** ascii length */
	private static final int EXTENDED_ASCII = 256;
	
	/** root element */
	private Node root;
	
	/**
	 * Constructor
	 */
	public RWayTrie() {
		root = new Node();
	}

	/**
	 * Add a tuple: word - term, and it's weight. Word length should be taken as weight.
	 * 
	 * @param tuple term-weight tuple
	 */
	public void add(Tuple tuple) {
		int weight = tuple.getWeight();
		String term = tuple.getTerm();
		root = put(root, term, weight, 0);
	}
	
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
	
	private int get(String term) {
		Node node = get(root, term, 0);
		if (node == null) {
			return 0;
		}
		return (int) node.weight;
	}
	
	private Node get(Node node, String term, int k) {
		if (node == null) {
			return null;
		}
		if (k == node.weight) {
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
		// TODO wrong boolean value will be get
		if (!contains(word)) {
			return false;
		}
		root = delete(root, word, 0);
		return true;
	}
	
//	private boolean isEmpty(Node node, String term, int k) {
//		if (node == null) {
//			return false;
//		}
//		if (k == node.weight) {
//			node.weight = 0;
//			return areAllChildrenEmpty(node);
//		}
//		char c = term.charAt(k);
//		if (isEmpty(node.children[c], term, k++)) {
//			node.children[c] = null;
//			return node.weight == 0 || areAllChildrenEmpty(node);
//		}
//		return false;
//	}
	
	private Node delete(Node node, String term, int k) {
		if (node == null) {
			return null;
		}
		if (k == node.weight) {
			node.weight = 0;
			if (areAllChildrenEmpty(node)) {
				return null;
			} else {
				return node;
			}
		}
		char c = term.charAt(k);
		node.children[c] = delete(node.children[c], term, k++);
		if (node.weight != 0) {
			return node;
		}
		if (areAllChildrenEmpty(node)) {
			return null;
		}
		return node;
	}
	
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
		// TODO Auto-generated method stub
		Queue<String> result = new LinkedList<String>();
		collect(root, new StringBuilder(), result);
		return null;
	}
	
	private void collect(Node node, StringBuilder stringBuilder, Queue<String> result) {
		if (node == null) {
			return;
		}
		if (node.weight != 0) {
			result.add(stringBuilder.toString());
		}
		for (int i = 0; i < EXTENDED_ASCII; i++) {
			Node n = node.children[i];
			if (n != null) {
				stringBuilder.append((char) i);
				collect(n, stringBuilder, result);
			}
		}
	}

	/**
	 * Iterator through all words, that start from pref, breath-first search
	 * 
	 * @param pref prefix
	 * @return list of words
	 */
	public Iterable<String> wordWithPrefix(String pref) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Words number in the Trie
	 * 
	 * @return integer size 
	 */
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	/**
	 * Node class
	 * @author Oleksandr_Dombrovsky
	 *
	 */
	public static class Node {
		
		private int weight;
		
		private Node[] children;
		
		public Node() {
			children = new Node[EXTENDED_ASCII];
		}
		
	}

}
