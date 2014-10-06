/**
 * Michael J. Cusack
 */

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

import nhUtilities.containers2.Iterator;
import nhUtilities.containers2.List;

/*
 * A game where a word is randomly selected from a given dictionary list (text file)
 * and the user must enter words that can be created from the characters in the word.
 * The player receives 10 points per correct answer.
 */
public class WordScramble {
	
	private java.util.Scanner input;
	private HashTable<String> wordTable;
	private String gameWord;
	private List<String> validWords;
	private List<String> playerAnswers;
	private List<String> playerCorrectAnswers;
	private int score;
	
	/*
	 * @require
	 * 		capacity > 0
	 * @ensure
	 * 		Sets up the game
	 */
	public WordScramble(int capacity) {
		input = new java.util.Scanner(System.in);
		wordTable = new HashTable<String>(capacity);
		gameWord = "";
		validWords = new CircularLinkedList<String>();
		playerAnswers = new CircularLinkedList<String>();
		playerCorrectAnswers = new CircularLinkedList<String>();
		score = 0;
	}
	
	/*
	 * @require
	 * 		filename != null
	 * @ensure
	 * 		Reads each word from the given file and adds it to a hash table
	 * 		containing the words to select the game word from
	 */
	public void buildHashTable(String filename) 
			throws FileNotFoundException, IOException {
		
		FileInputStream inStream = new FileInputStream(filename);
		DataInputStream dataStream = new DataInputStream(inStream);
		BufferedReader reader = new BufferedReader(new InputStreamReader(dataStream));
		
		// Read each word from input file and add to hash table
		while (reader.ready()) {
			String line = reader.readLine();
			wordTable.put(line);
		}
	}
	
	/*
	 * @require
	 * 		capacity > 0
	 * @ensure
	 * 		Sets the game word to a word randomly selected from wordTable
	 */
	public void pickRandomWord(int capacity) {
		java.util.Random generator = new java.util.Random();
		
		int rndKey = generator.nextInt(capacity);
		List<String> rndList = wordTable.getAll(rndKey);
		
		int rndIndex = generator.nextInt(rndList.size());
		String word = rndList.get(rndIndex);
		
		gameWord = word;
	}
	
	/*
	 * @require
	 * 		prefix != null
	 * 		word != null
	 * 		list != null
	 * @ensure
	 * 		Recursively finds all permutations of the given word and adds the words
	 * 		to the given list
	 */
	private void getWordPerms(String prefix, String word, List<String> list) {
		int len = word.length();
		// Generated all possible permutations, add the words to the list
		if (len == 0)
			list.add(prefix);
		// Generate more permutations
		else {
			for (int i=0; i < len; i++)
				getWordPerms(prefix + word.charAt(i), word.substring(0, i) + 
						word.substring(i+1, len), list);
		}
	}
	
	/*
	 * @require
	 * 		word != null
	 * @ensure
	 * 		Returns the permutations of the given substring
	 */
	private List<String> getWordPermsSub(String substr) {
		List<String> perms = new CircularLinkedList<String>();
		getWordPerms("", substr, perms);
		return perms;
	}
	
	/*
	 * @require
	 * 		word != null
	 * 		sublength > 0
	 * @ensure
	 * 		Creates substrings of length sublength, creates a list of permutations
	 * 		of the substring, and checks if the generated words are valid
	 */
	private void getSubString(String word, int sublength) {
		// Generate all substrings of the word, generate all permutations, and check
		// if each word is valid
		for (int i=0; i < (word.length() - sublength + 1); i++) {
			String substr = word.substring(i, i+sublength);
			List<String> subperms = getWordPermsSub(substr);
			checkListValid(subperms);
		}
	}
	
	/*
	 * @require
	 * 		N/A
	 * @ensure
	 * 		Generate permutations from each substring length 3 to length of gameWord
	 */
	public void getWordPerms() {
		// Get each substring from length 3 to length of gameWord
		for (int i=3; i <= gameWord.length(); i++) {
			getSubString(gameWord, i);
		}
	}
	
	/*
	 * @require
	 * 		sublist != null
	 * @ensure
	 * 		Iterates through each word in the sublist and checks if word is valid
	 */
	private void checkListValid(List<String> sublist) {
		Iterator<String> iter = sublist.iterator();
		
		// Iterate through each word in the sublist and check if word is in validWords
		while (!iter.done()) {
			int key = wordTable.hash(iter.get());
			List<String> chain = wordTable.getAll(key);
			// Check if current reference is in collision list
			if (chain.contains(iter.get())) {
				if (!validWords.contains(iter.get()) && !iter.get().equals(gameWord))
					validWords.add(iter.get());
			}
			iter.advance();
		}
	}
	
	/*
	 * @require
	 * 		N/A
	 * @ensure
	 * 		Returns the name of the file containing the list of words to use
	 */
	public String getDictionaryFile() {
		System.out.println("Enter name of dictionary to use: ");
		String filename = input.next();
		return filename;
	}
	
	/*
	 * @require
	 * 		N/A
	 * @ensure
	 * 		Gets player's answers, stores in a list of answers, and checks each
	 * 		answer if it is valid
	 */
	public void getPlayerAnswers() {
		String answer = "";
		String altAnswer = "";
		
		System.out.println("Create words from the characters of: " + gameWord);
		
		// Ask user to enter a word until "0" is entered, add answer to playerAnswers,
		// and check if word (or word - "s" or "ed") is in validWords
		while (!answer.equals("0")) {
			System.out.println("Enter word (0 to stop): ");
			answer = input.next();
			
			if (!answer.equals("0")) {
				playerAnswers.add(answer);
				
				if (answer.endsWith("s"))
					altAnswer = answer.substring(0, answer.length());
				
				if (answer.endsWith("ed"))
					altAnswer = answer.substring(0, answer.length() - 1);
				
				if (validWords.contains(answer) || validWords.contains(altAnswer)); {
					score = score + 10;
					playerCorrectAnswers.add(answer);
				}
			}
		}
	}
	
	/*
	 * @require
	 * 		N/A
	 * @ensure
	 * 		Outputs the results of the game
	 */
	public void getResults() {
		System.out.println("Possible correct answers: " + validWords);
		System.out.println("Your correct answers: " + playerCorrectAnswers);
		System.out.println("Your score: " + score);
	}
	
	/*
	 * @require
	 * 		N/A
	 * @ensure
	 * 		Plays the game!
	 */
	public static void main(String[] args) {
		WordScramble game = new WordScramble(20000);
		String filename = game.getDictionaryFile();
		
		try {
			game.buildHashTable(filename);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
				
		game.pickRandomWord(20000);
		game.getWordPerms();
		game.getPlayerAnswers();
		game.getResults();
	}
}
