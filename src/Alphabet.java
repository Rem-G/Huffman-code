import java.util.*;

public class Alphabet{
	private ArrayList<String[]> data;

	public Alphabet(ArrayList<String[]> data){
		this.data = data;
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