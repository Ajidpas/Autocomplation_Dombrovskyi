package trie.treetraversal;

public class Tuple {
	
	/** word string value */
	private String term;
	
	/** words' weight */
	private int weight;
	
	/**
	 * Constructor
	 * 
	 * @param term word string value
	 * @param weight words' weight
	 */
	public Tuple(String term, int weight) {
		this.term = term;
		this.weight = weight;
	}

	/**
	 * Terms' getter
	 * @return term
	 */
	public String getTerm() {
		return term;
	}

	/**
	 * Terms' setter
	 * @param term term
	 */
	public void setTerm(String term) {
		this.term = term;
	}

	/**
	 * Weight getter
	 * @return weight
	 */
	public int getWeight() {
		return weight;
	}

	/**
	 * Weight setter
	 * @param weight words' weight
	 */
	public void setWeight(int weight) {
		this.weight = weight;
	}
	
}
