import java.util.*;

public class Alphabet{
	private ArrayList<String[]> data;

	public Alphabet(ArrayList<String[]> data){
		this.data = data;
	}

	public Map frequence(){
		HashMap occurences = new HashMap();

		for(String[] line : this.data){
			for (String word : line){
				for (int i=0; i < word.length()-1; i++){

					if ((occurences.keySet()).contains(word.charAt(i))){

						int value = (int)occurences.get(word.charAt(i));
						value++;
						occurences.replace(word.charAt(i), value);
					}

					else{
						occurences.put((int)word.charAt(i), 1);
					}

				}
			}
		}

   		Map treeMap_occurences = new TreeMap(occurences);

		return treeMap_occurences;
	}
}