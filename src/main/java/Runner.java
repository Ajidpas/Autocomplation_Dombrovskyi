import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Runner {
	public static void main(String[] args) {
		PrefixMatches pm = new PrefixMatches();
		System.out.println(fillVocabulary(pm));
		System.out.println(pm.wordsWithPrefix("wor", 2));
		
	}
	
	private static int fillVocabulary(PrefixMatches pm) {
		int size = 0;
		BufferedReader buffReader = null;
		try {
			FileReader fr = new FileReader("src\\main\\resources\\Text.txt");
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
