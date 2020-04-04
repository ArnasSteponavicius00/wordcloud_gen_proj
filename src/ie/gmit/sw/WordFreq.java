package ie.gmit.sw;

public class WordFreq implements Comparable<WordFreq> {
	
    private String word;
    private int freq;
    
    public WordFreq(String key, int freq) {
        setWord(key);
        setFreq(freq);
    }

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	public int getFreq() {
		return freq;
	}

	public void setFreq(int freq) {
		this.freq = freq;
	}
	
	@Override
	public String toString() {
		return "Word: " + word +" | Freq: "+freq+ "\n";
	}
	
	@Override
	public int compareTo(WordFreq key) {
		return 0;
	}
}
