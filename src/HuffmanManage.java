import java.util.*;
import java.io.*;

import java.nio.file.Path;
import java.nio.file.Paths;

public class HuffmanManage{
	private String original_text;

	public HuffmanManage(String original_text){
		this.original_text = original_text;
	}

	public void compression(){
		Path path_file = Paths.get(System.getProperty("user.dir")).getParent();
		ReadData data = new ReadData();
		ArrayList<String> data_text = data.read(path_file + "/Huffman-code/data/" + this.original_text + ".txt");

		String binary_path = "";

		Alphabet a = new Alphabet(data_text, true);

		LinkedHashMap alphabet = a.sorted_frequence();

		Tree t = new Tree(alphabet);
		Node tree = t.general_tree();

		HashMap<Character, String> encoded_char = new HashMap<Character, String>();

		ArrayList<Character> keys = new ArrayList<Character>(encoded_char.keySet());

		for (String word : data_text){
			for (int i=0; i < word.length(); i++){

				char c = (char)word.charAt(i);

				if (keys.contains(c)){
					binary_path += encoded_char.get(c);
				}
				else{
					String char_path = tree.deep_path("", c, new ArrayList<Node>());
					encoded_char.put(c, char_path);
					keys.add(c);
					binary_path += char_path;
				}
			}
			binary_path += tree.deep_path("", (System.lineSeparator()).charAt(0), new ArrayList<Node>());
		} 
		

		WriteData wf = new WriteData();
		wf.write_binary(this.original_text, binary_path);
		wf.write_alphabet(this.original_text, alphabet);
	}

	public void decompression(){
		Path path_file = Paths.get(System.getProperty("user.dir")).getParent();
		ReadData data = new ReadData();

		ArrayList<String> data_compressed = data.convert_array_to_string(path_file + "/Huffman-code/data/" + this.original_text + "_alphabet.txt");

		Alphabet a = new Alphabet(data_compressed, false);
		LinkedHashMap alphabet = a.sorted_frequence();

		Tree t = new Tree(alphabet);
		Node tree = t.general_tree();

		String binary_path = data.read_path(path_file + "/Huffman-code/data/" + this.original_text + ".bin");

		HashMap<Character, String> encoded_char = new HashMap<Character, String>();
		ArrayList<Character> alphabet_keys = new ArrayList<Character>(alphabet.keySet());

		for (char c : alphabet_keys){
			encoded_char.put(c, tree.deep_path("", c, new ArrayList<Node>()));
		}

		ArrayList<String> encoded_char_values = new ArrayList<String>(encoded_char.values());
		ArrayList<Character> encoded_char_keys = new ArrayList<Character>(encoded_char.keySet());

		String converted_path = "";
		String charac = "";
		int i = 0;

		try{
			while (binary_path.length() > 0){

				charac += Character.toString(binary_path.charAt(i));

				if (encoded_char_values.contains(charac)){
					int index = encoded_char_values.indexOf(charac);
					converted_path += encoded_char_keys.get(index);
					charac = "";
					i = 0;
				}

				binary_path = binary_path.substring(i, binary_path.length());
				i++;
			}
		}
		catch (Exception e){
			System.out.println(converted_path);
			System.out.println(binary_path);
		}

		/*
		String[] new_path = path.split(" ");

		String decompressed_ch = "";

		for (String search_path : new_path){
			String c = tree.deep_path_decompression("", search_path, new ArrayList<Node>());

			if (c == System.getProperty("line.separator")){
				decompressed_ch += "\n";
			}
			else{
				decompressed_ch += c;
			}
		}
		System.out.println(decompressed_ch);*/
	}
}