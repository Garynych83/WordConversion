package WordConversion;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WordConversion {

	public WordConversion(String dictPath) throws IOException {
		dictionary = ReadTextFile.ReadFileLines(dictPath);
	}

	private List<String> dictionary;
	private List<String> usedWords = new ArrayList<String>();

	public List<String> getUsedWords() {
		return usedWords;
	}

	public List<String> getDictionary() {
		return dictionary;
	}

	public static void main(String [] args) {
		
		try {
			List<String> path = new WordConversion(args[0]).findPath(args[1]);
			for (String word : path) {
				System.out.println(word);
			}
			
		} catch (IOException e) {
			System.out.println("Ошибка доступа к файлу: " + e.getMessage());
		}
	}
	
	public List<String> findPath(String sourceFilePath) throws IOException {
		List<String> result = null;
		List<String> sourceWords = ReadTextFile.ReadFileLines(sourceFilePath);

		Branch<String> startWord = new Branch<String>(sourceWords.get(0), null);
		String endWord = sourceWords.get(1);

		List<Branch<String>> sameLevelChildren = new ArrayList<Branch<String>>();
		sameLevelChildren.add(startWord);
		usedWords.add(startWord.getData());

		Branch<String> goalBranch = null;

		do {
			List<Branch<String>> newSameLevelChildren = new ArrayList<Branch<String>>();

			for (Branch<String> branch : sameLevelChildren) {
				List<Branch<String>> children = this.getNeighbours(branch);
				newSameLevelChildren.addAll(children);
			}

			for (Branch<String> word : newSameLevelChildren) {
				if (word.getData().equals(endWord)) {
					result = fillResult(word);
				}
			}

			sameLevelChildren = newSameLevelChildren;
		} while (goalBranch == null && sameLevelChildren.size() != 0);

		usedWords.clear();
		return result;
	}

	private List<String> fillResult(Branch<String> data) {
		List<String> result = new ArrayList<String>();

		while (data != null) {
			result.add(data.getData());
			data = data.getParent();
		}
		;
		return result;
	}

	// public для теста
	public List<Branch<String>> getNeighbours(Branch<String> word) {
		List<Branch<String>> result = new ArrayList<Branch<String>>();

		for (String curWord : dictionary) {
			if (this.dist(curWord, word.getData()) == 1) {
				if (!usedWords.contains(curWord)) {
					result.add(new Branch<String>(curWord, word));
					usedWords.add(curWord);
				}
			}
		}
		return result;
	}

	// public для теста
	public int dist(String word1, String word2) {
		int count = 0;
		if (word1.length() == word2.length()) {
			for (int i = 0; i < word1.length(); i++) {
				if ((word2.charAt(i) != word1.charAt(i))) {
					count++;
				}
			}
		}
		return count;
	}
}
