import java.io.*;
import java.util.*;

import java.nio.file.Path; 
import java.nio.file.Paths; 


public class Main {

	public static void main(String[] args) throws FileNotFoundException{
		Path path_file = Paths.get(System.getProperty("user.dir")).getParent();
		ReadData data = new ReadData(path_file + "/data/textesimple.txt");
		ArrayList<String[]> data_text = data.read();

		String path = "";

		for(String[] line : data_text){
			Alphabet a = new Alphabet(line);

			LinkedHashMap alphabet = a.sorted_frequence();

			Tree t = new Tree(alphabet);
			Node tree = t.general_tree();

			for (String word : line){
				for (int i=0; i < word.length()-1; i++){

				char ascii_code = (char)word.charAt(i);

				path += tree.deep_path("", ascii_code, new ArrayList<Node>());
				}
			}
			System.out.println(path);
		}
	}
}