package trie.treetraversal;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Queue;

public class TrieIterator implements Iterator<String> {
	
	/** queue of nodes */
	private Queue<RWayTrie.Node> nodes = new LinkedList<RWayTrie.Node>();
	
	/** queue of prefixes */
	private Queue<String> prefixes = new LinkedList<String>();
	
	private RWayTrie.Node currentNode;
	
	private String currentPrefix;
	
	/**
	 * Constructor
	 */
	public TrieIterator(RWayTrie.Node root) {
		this(root, new String());
	}
	
	public TrieIterator(RWayTrie.Node currentNode, String currentPrefix) {
		this.currentNode = currentNode;
		this.currentPrefix = currentPrefix;
		nodes.add(currentNode);
		prefixes.add(currentPrefix);
	}

	public boolean hasNext() {
		if (nodes.isEmpty()) {
			return false;
		}
		return true;
	}

	public String next() {
		if (!hasNext()) {
			throw new NoSuchElementException();
		}
		String word = getNextWord();
		if (word == null) {
			throw new NoSuchElementException();
		}
		return word;
	}
	
	/**
	 * Get all words from existing tree, breadth-first search
	 * 
	 * @param node node from which search starts 
	 * @param word word 
	 * @param result collection of words 
	 */
	private String getNextWord() {
		while (!nodes.isEmpty()) {
			currentNode = nodes.poll();
			if (currentNode == null) {
				throw new NoSuchElementException();
			}
			String prefix = prefixes.poll();
			currentPrefix = prefix != null ? prefix : "";
			RWayTrie.Node[] children = currentNode.getChildren();
			for (int i = 0; i < children.length; i++) {
				RWayTrie.Node currentChild = children[i];
				if (currentChild != null) {
					nodes.add(currentChild);
					prefixes.add(currentPrefix + (char) i);
				}
			}
			if (currentNode.getWeight() != 0) {
				return currentPrefix;
			}
		}
		return null;
	}

}