import java.io.*;
import java.util.*;

import java.nio.file.Path; 
import java.nio.file.Paths; 


public class Main {

	public static void main(String[] args) throws FileNotFoundException{
		Path path = Paths.get(System.getProperty("user.dir")).getParent();

		ReadData data = new ReadData(path + "/data/textesimple.txt");

		ArrayList<String[]> data_text = data.read();

		Alphabet alphabet = new Alphabet(data_text);

		LinkedHashMap frequence = alphabet.sorted_frequence();

		System.out.println(frequence);
	}
}