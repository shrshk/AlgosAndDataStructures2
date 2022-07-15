package forYoutube;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

class TrieNode {
	Map<Character, TrieNode> children;
	boolean isLeaf;

	TrieNode() {
		children = new HashMap<>();
		isLeaf = false;
	}

	void insert(String word) {
		TrieNode current = this;
		for (char c : word.toCharArray()) {
			if (!current.children.containsKey(c)) {
				current.children.put(c, new TrieNode());
			}
			current = current.children.get(c);
		}

		current.isLeaf = true;
	}

	boolean search(String word) {
		TrieNode current = this;
		for (char c : word.toCharArray()) {
			if (!current.children.containsKey(c))
				return false;
			current = current.children.get(c);
		}
		return current.isLeaf;
	}

	boolean startsWith(String prefix) {
		TrieNode current = this;
		for (char c : prefix.toCharArray()) {
			if (!current.children.containsKey(c))
				return false;
			current = current.children.get(c);
		}
		return true;
	}

}

public class WordSearchGameSolver {
	Set<String> set = new HashSet<>();
    List<String> currPath = new ArrayList<>();
    Map<String, List<List<String>>> finalPathMap = new HashMap<>();
	TrieNode trieNode = new TrieNode();
	int[][] dirs = new int[][] { { 1, 0 }, { 0, 1 }, { -1, 0 }, { 0, -1 }, {1,1}, {-1,-1}, {1,-1}, {-1,1} };

	public List<String> findWords(char[][] board, String[] words) {

		for (String word : words) {
			trieNode.insert(word);
		}

		int m = board.length;
		int n = board[0].length;
        
        boolean[][] used = new boolean[m][n];

		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {	
				dfs("", i, j, board, m, n, used, currPath);
			}
		}
		
		finalPathMap.entrySet().forEach(entry -> {
		    System.out.println(entry.getKey() + " " + entry.getValue());
		});
		
		Set<String> wordsFound = finalPathMap.keySet();
		
		for (String word: words) {
			if (!wordsFound.contains(word)) {
				System.out.println(word);
			}
		}

		return new ArrayList<>(set);
	}

	private void dfs(String tmp, int i, int j, char[][] board, int m, int n, boolean[][] used, List<String> currPath) {
		
		tmp+=board[i][j];
        
        currPath.add(i+"_"+j);

		if (!trieNode.startsWith(tmp)) {
            currPath.remove(currPath.size()-1);
            return;
        }

		if (trieNode.search(tmp)) {
			addPathToResult(tmp, currPath);
			set.add(tmp);
		}

		used[i][j] = true;

		for (int[] dir : dirs) {
			int x = i + dir[0];
			int y = j + dir[1];
            if (x >= m || y >= n || x < 0 || y < 0 || used[x][y])
                continue;
			dfs(tmp, x, y, board, m, n, used, currPath);
		}

		used[i][j] = false;
	}
	
	private void addPathToResult(String tmp, List<String> currPath) {
		int len = tmp.length();
		List<List<String>> allPathsForWord = finalPathMap.getOrDefault(tmp, new ArrayList<>());
		List<String> wordPath = new ArrayList<>(currPath.subList(currPath.size()-len, currPath.size()-1));
		allPathsForWord.add(wordPath);
        finalPathMap.put(tmp, allPathsForWord);
	}
}