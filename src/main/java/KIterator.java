import java.util.Iterator;
import java.util.NoSuchElementException;

public class KIterator implements Iterator<String> {
	
	private String cache;
	
	private Iterator<String> iterator;
	
	private int k;
	
	private final String prefix;
	
	public KIterator(Iterable<String> origin, String prefix, int k) {
		iterator = origin.iterator();
		this.prefix = prefix;
		this.k = k;
		if (iterator.hasNext()) {
			cache = iterator.next();
		} else {
			cache = null;
		}
	}

	public boolean hasNext() {
		if (cache != null && cache.length() <= prefix.length() + k) {
			return true;
		}
		return false;
	}

	public String next() {
		if (!hasNext()) {
			throw new NoSuchElementException();
		}
		String word = cache;
		if (iterator.hasNext()) {
			cache = iterator.next();
		} else {
			cache = null;
		}
		return word;
	}

}
