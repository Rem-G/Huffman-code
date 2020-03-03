import java.util.*;

public class Alphabet{
	private ArrayList<String[]> data;

	public Alphabet(ArrayList<String[]> data){
		this.data = data;
	}

	public HashMap frequence(){
		HashMap<Integer, Integer> occurences = new HashMap<Integer, Integer>();

		for(String[] line : this.data){
			for (String word : line){
				for (int i=0; i < word.length()-1; i++){

					int ascii_code = (int)word.charAt(i);

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
		}

		return occurences;
	}

	public LinkedHashMap sorted_frequence(){
		HashMap occurences = this.frequence();

		ArrayList<Integer> values = new ArrayList<Integer>(occurences.values());
		ArrayList<Integer> keys = new ArrayList<Integer>(occurences.keySet());

		Collections.sort(values);

		LinkedHashMap<Integer, Integer> sorted_occurences = new LinkedHashMap<Integer, Integer>();

		for (int value : values){
			for (int key : keys){
				if ((int)(occurences.get(key)) == value){
					sorted_occurences.put(key, value);
				}
			}
		}
		return sorted_occurences;
	}

}