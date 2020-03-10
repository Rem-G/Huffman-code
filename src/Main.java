import java.io.*;
import java.util.*;

import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {

	public static void main(String[] args) throws FileNotFoundException{
		String original_text = "extraitalice";

		Path path_file = Paths.get(System.getProperty("user.dir")).getParent();
		ReadData data = new ReadData();
		ArrayList<String[]> data_text = data.read(path_file + "/data/" + original_text + ".txt");

		String binary_path = "";

		Alphabet a = new Alphabet(data_text);

		LinkedHashMap alphabet = a.sorted_frequence();

		Tree t = new Tree(alphabet);
		Node tree = t.general_tree();

		for(String[] line : data_text){
			for (String word : line){
				for (int i=0; i < word.length(); i++){

					char c = (char)word.charAt(i);

					binary_path += " " + tree.deep_path("", c, new ArrayList<Node>());
				}
			}
			//binary_path += " " + tree.deep_path("", (char)13, new ArrayList<Node>());
			//System.out.println(binary_path);
		}
		WriteData wf = new WriteData();
		wf.write_binary(original_text, binary_path);
		wf.write_alphabet(original_text, alphabet);


		//Decompression
		ArrayList<String[]> data_alphabet = data.read(path_file + "/data/" + original_text + "_alphabet.txt");
		ArrayList<String[]> data_compressed = data.convert_array_to_string(data_alphabet);


		Alphabet a2 = new Alphabet(data_compressed);
		LinkedHashMap alphabet2 = a2.sorted_frequence();

		Tree t2 = new Tree(alphabet);
		Node tree2 = t2.general_tree();

		String path = "1000 11011 0100 0111 111 0100 0111 111 000 0110 111 001 11010 000 0101 10101 10011 001 111 10100 1100 111 000 111 11011 10111 1100 1100 0101 000 0110 111 1000 10110 001 001";
		String[] new_path = path.split(" ");

		String decompressed_ch = "";

		for (String search_path : new_path){
			String c = tree2.deep_path_decompression("", search_path, new ArrayList<Node>());

			decompressed_ch += c;
		}

		System.out.println(decompressed_ch);

	}
}
