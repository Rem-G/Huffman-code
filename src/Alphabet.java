import java.util.*;

public class Alphabet{
	private ArrayList<String[]> data;

	public Alphabet(ArrayList<String[]> data){
		this.data = data;
	}

	public Map frequence(){
		Map<Character, Integer> occurences = new TreeMap<Character, Integer>();

		for (String[] line : this.data){
			for (String word : line){
				for (int i=0; i < word.length(); i++){

					char c = (char)word.charAt(i);

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
		}
		occurences.put((char)13, this.data.size());
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