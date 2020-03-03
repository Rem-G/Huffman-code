import java.util.*;

public class Alphabet{
	private ArrayList<String[]> data;

	public Alphabet(ArrayList<String[]> data){
		this.data = data;
	}

	public LinkedHashMap sorted_frequence(){
		Map occurences = this.frequence();

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

	public Map frequence(){
		HashMap<Integer, Integer> occurences = new HashMap<Integer, Integer>();

		for(String[] line : this.data){
			for (String word : line){
				for (int i=0; i < word.length()-1; i++){

					int ascii_value = (int)word.charAt(i);

					if ((occurences.keySet()).contains(ascii_value)){

						int value = (int)occurences.get(ascii_value);
						value++;
						occurences.replace(ascii_value, value);
					}

					else{
						occurences.put(ascii_value, 1);
					}

				}
			}
		}

   		Map treeMap_occurences = new TreeMap(occurences);

		return treeMap_occurences;
	}

}