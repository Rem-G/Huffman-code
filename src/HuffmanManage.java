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
		//*******PATH*******
		Path path_file = Paths.get(System.getProperty("user.dir")).getParent();

		//*******GET ALPHABET*******
		ReadData data = new ReadData();
		ArrayList<String> data_text = data.read(path_file + "/Huffman-code/data/" + this.original_text + ".txt");
		Alphabet a = new Alphabet(data_text, true);
		LinkedHashMap alphabet = a.sorted_frequence();

		//*******BUILD THE HUFFMAN TREE*******
		Tree t = new Tree(alphabet);
		Node tree = t.general_tree();

		//*******GET COMPRESSED VALUE OF CHARACTERS*******
		HashMap<Character, String> encoded_char = new HashMap<Character, String>();
		ArrayList<Character> alphabet_keys = new ArrayList<Character>(alphabet.keySet());

		for (char c : alphabet_keys){
			encoded_char.put(c, tree.deep_path("", c, new ArrayList<Node>()));
		}

		//*******COMPRESS DATA*******
		int nb_charac = 0;
		String binary_path = "";

		for (String word : data_text){
			for (int i=0; i < word.length(); i++){
				char c = (char)word.charAt(i);
				binary_path += encoded_char.get(c);

				nb_charac++;
			}
			binary_path += tree.deep_path("", (System.lineSeparator()).charAt(0), new ArrayList<Node>());
			nb_charac++;
		}

		//*******WRITE COMPRESSED DATA*******
		WriteData wf = new WriteData();
		wf.write_binary(this.original_text, binary_path);
		wf.write_alphabet(this.original_text, alphabet, nb_charac);
		wf.compression_rate(this.original_text);
		System.out.println("Avegare number of bits per character : " + (float)binary_path.length()/nb_charac);
	}

	public void decompression(){
		//*******PATH*******
		Path path_file = Paths.get(System.getProperty("user.dir")).getParent();

		//*******GET ALPHABET*******
		ReadData data = new ReadData();
		ArrayList<String> alphabet_compressed = data.get_alphabet(path_file + "/Huffman-code/data/" + this.original_text + "_freq.txt");
		Alphabet a = new Alphabet(alphabet_compressed, false);
		LinkedHashMap alphabet = a.sorted_frequence();

		//*******BUILD THE HUFFMAN TREE*******
		Tree t = new Tree(alphabet);
		Node tree = t.general_tree();

		//*******GET THE COMPRESSED DATA*******
		String binary_path = data.read_path(path_file + "/Huffman-code/data/" + this.original_text + "_comp.bin");

		//*******BUILD A DICT OF CHARACTERS AND THEIR PATH*******
		HashMap<Character, String> encoded_char = new HashMap<Character, String>();
		ArrayList<Character> alphabet_keys = new ArrayList<Character>(alphabet.keySet());

		for (char c : alphabet_keys){
			encoded_char.put(c, tree.deep_path("", c, new ArrayList<Node>()));
		}

		ArrayList<String> encoded_char_values = new ArrayList<String>(encoded_char.values());
		ArrayList<Character> encoded_char_keys = new ArrayList<Character>(encoded_char.keySet());

		String converted_path = "";
		String charac = "";

		//*******DECODE COMPRESSED DATA*******
		while (binary_path.length() > 0){
			charac += Character.toString(binary_path.charAt(0));

			if (encoded_char_values.contains(charac)){
				int index = encoded_char_values.indexOf(charac);
				converted_path += encoded_char_keys.get(index);
				charac = "";
			}
			binary_path = binary_path.substring(1, binary_path.length());
		}

		System.out.println(converted_path);
	}
}