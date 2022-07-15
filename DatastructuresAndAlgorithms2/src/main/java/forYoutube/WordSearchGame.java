package forYoutube;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class WordSearchGame {
	char[][] gameMatrix = new char[17][24];
	String[] wordList = null;
	
	
	void buildGameMatrix(List<String> gameBoardLines) {
		for (int i=0; i<gameBoardLines.size(); i++) {
			String line = gameBoardLines.get(i);
			for (int j=0; j<line.length(); j++) {
				gameMatrix[i][j] = line.charAt(j);
			}
		}
	}
	
	void buildWordList(String allWordsStr) {
		wordList = allWordsStr.split(" ");
	}	
	
	void solvePuzzle() {
		new WordSearchGameSolver().findWords(gameMatrix, wordList);
	}
	
	
	public static void main(String[] args) {
		Stream<String> stream = null;
		List<String> gameBoardLines = new ArrayList<>();
		try {
			String gameBoardFilePath = "/Users/shirish/Documents/PersonalProjects/AlgosAndDataStructures2/DatastructuresAndAlgorithms2/src/main/java/forYoutube/Game_Matrix.txt";
			stream = Files.lines(Paths.get(gameBoardFilePath));
	        stream.forEach((line)->{
	        	String noSpaceLine = line.replaceAll("\\s+","");
	        	if (!noSpaceLine.isEmpty()) {
	        		gameBoardLines.add(noSpaceLine);
	        	}	        	
	        });
	        WordSearchGame wsg = new WordSearchGame();
	        wsg.buildGameMatrix(gameBoardLines);
	        
	        String wordListFilePath = "/Users/shirish/Documents/PersonalProjects/AlgosAndDataStructures2/DatastructuresAndAlgorithms2/src/main/java/forYoutube/Word_List.txt";
	        stream = Files.lines(Paths.get(wordListFilePath));
	        StringBuilder wordListBuilder = new StringBuilder();
	        stream.forEach((line -> {
	        	wordListBuilder.append(line);
	        }));
	        
	        wsg.buildWordList(wordListBuilder.toString());
	        
	        wsg.solvePuzzle();
	        
	        stream.close();
		} catch (Exception e) {
			System.out.println(e.toString());
			if (stream!=null)
				stream.close();
		}
		
	}
}
