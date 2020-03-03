import java.io.*;
import java.nio.file.Path; 
import java.nio.file.Paths; 
import java.util.*;


public class Main {

	public static void main(String[] args) throws FileNotFoundException{
		Path path = Paths.get(System.getProperty("user.dir")).getParent();

		ReadData data = new ReadData(path + "/data/extraitalice.txt");

		ArrayList<String[]> data_text = data.read();

		Alphabet alphabet = new Alphabet(data_text);

		System.out.println(alphabet.frequence());

	}
}