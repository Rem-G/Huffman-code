import java.util.*;

public class Alphabet{
	private ArrayList<String> data;
	private boolean compression;

	public Alphabet(ArrayList<String> data, boolean compression){
		this.data = data;
		this.compression = compression;
	}

	public Map frequence(){
		Map<Character, Integer> occurences = new TreeMap<Character, Integer>();

		for (String word : this.data){
			char[] list_char = word.toCharArray();
			for (char c : list_char){
				if ((occurences.keySet()).contains(c)){
					int value = (int)occurences.get(c);
					value++;
					occurences.replace(c, value);
				}
				else{
					occurences.put(c, 1);
				}
			}
		}
		if (compression == true){
			occurences.put((System.getProperty("line.separator")).charAt(0), this.data.size());
		}
		return occurences;
	}

	public LinkedHashMap sorted_frequence(){
		Map occurences = this.frequence();

		ArrayList<Integer> values = new ArrayList<Integer>(occurences.values());
		ArrayList<Character> keys = new ArrayList<Character>(occurences.keySet());

		Collections.sort(values);

		LinkedHashMap<Character, Integer> sorted_occurences = new LinkedHashMap<Character, Integer>();

		for (int value : values){
			for (char key : keys){
				if ((int)(occurences.get(key)) == value){
					sorted_occurences.put(key, value);
				}
			}
		}
		return sorted_occurences;
	}
}