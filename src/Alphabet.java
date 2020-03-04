import java.util.*;

public class Alphabet{
	private String[] data;

	public Alphabet(String[] data){
		this.data = data;
	}

	public HashMap frequence(){
		HashMap<Character, Integer> occurences = new HashMap<Character, Integer>();

		for (String word : this.data){
			for (int i=0; i < word.length()-1; i++){

				char ascii_code = (char)word.charAt(i);

				if ((occurences.keySet()).contains(ascii_code)){

					int value = (int)occurences.get(ascii_code);
					value++;
					occurences.replace(ascii_code, value);
				}

				else{
					occurences.put(ascii_code, 1);
				}
			}
		}
		return occurences;
	}

	public LinkedHashMap sorted_frequence(){
		HashMap occurences = this.frequence();

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