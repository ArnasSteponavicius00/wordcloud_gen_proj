package ie.gmit.sw;

/* 
 * Arnas Steponavicius - G00361891
 * Time Complexity Table:
 * https://gist.github.com/psayre23/c30a821239f4818b0709
 * 
 */

import java.io.*;
import java.util.*;
import java.util.concurrent.PriorityBlockingQueue;
import java.net.*;

public class Parser {

	private static final String IGNORE = "./ignorewords.txt";

	private Set<String> hashSet = new HashSet<String>();
	private Map<String, Integer> wordMap = new HashMap<String, Integer>();
	private Queue<WordFreq> queue = new PriorityBlockingQueue<>();
	
	public Queue<WordFreq>getQueue(){
		return queue;
	}
		
	/*
	 * Add ignorewords.txt to a Set (no need for duplicates)
	 * Inserting to a Set = O(1)
	 */
	public void ignore() throws IOException {
		
		BufferedReader ignoreBr = new BufferedReader(new InputStreamReader(new FileInputStream(new File(IGNORE))));
		String word = null;

		// T.C = O(n^2)
		while((word = ignoreBr.readLine()) != null) {
			hashSet.add(word); //Add to HashSet = O(1)
		}
		ignoreBr.close();
		
	}//ignore()

	/*
	 * Takes in the file name user entered in Menu class, reads in that file
	 * Loops over the file with a while loop (skips empty spaces)
	 * And populates the HashMap and increments the frequency of the words
	 */
	public void parseFile(String file, int maxWords) throws IOException {	

		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(new File(file))));
		String next  = null;	
		int count = 0;

		ignore();
		
		// T.C = O(n^2)
		while((next = br.readLine()) != null) {
			String[] ignoreWords = next.replaceAll("[^A-Za-z0-9 ]", "").split(" ");

			for(String words: ignoreWords) {
				if(!hashSet.contains(words)) {
					wordMap.put(words, ++count); //O(1) if the word is in the Map, get the word, frequency and increment
				}
			}
		}
		//add populated Map into the Queue
		addToQueue();
		
		/* DEBUG || Print out to see Map and the Queue are populated
		System.out.println("\nPopulated Map:\n" + wordMap);
		System.out.println("\n========================================================\n");
		System.out.println("\nPopulated Queue:\n");		
		System.out.println(queue.toString());
		*/
		
		new ImageGen().createImage(queue, maxWords);
		 	
		br.close();				
	}//parseFile();

	/*
	 * Uses 'import java.net.*' for URL
	 * User inputs url link in Menu class and is sent over to this method
	 * Webpage gets parsed and stored in the HashMap and word frequency gets incremented
	 * 
	 * Reference: https://docs.oracle.com/javase/tutorial/networking/urls/readingURL.html
	 */
	public void parseUrl(String url, int maxWords) throws IOException {

		URL userUrl = new URL(url);

		BufferedReader br = new BufferedReader(new InputStreamReader(userUrl.openStream()));
		int count = 0;
		String next;

		// T.C = O(n^2)
		while((next = br.readLine()) != null) {

			String[] ignoreWords = next.replaceAll("[^A-Za-z0-9 ]", "").split(" ");

			for(String words: ignoreWords) {
				if(!hashSet.contains(words)) {
					wordMap.put(words, ++count); //O(1)	if the word is in the Map, get frequency and increment	
				}
			}
		}
		//add populated Map into the Queue
		addToQueue();
		
		new ImageGen().createImage(queue, maxWords);
		
		/* DEBUG || Print out to see Map and the Queue are populated
		System.out.println(wordMap);
		System.out.println(queue.toString());
		*/
	}

	/*
	 * Add words in HashMap to PriorityQueue, using a Set removes duplicates
	 * Uses compareTo() method in WordFreq class
	 */
	public void addToQueue() {
		//Add key in Map to the Set
		Set<String> mapKey = wordMap.keySet();

		//Loop over each mapKey in the set and then add WordFreq to the Queue		
		 for(String key : mapKey) {
			 queue.offer(new WordFreq(key, wordMap.get(key)));
		 }
		 // offer = O(log n)	 
	}
	
	
}


