import java.util.*;
import java.io.*;

public class ReadData{
	public ArrayList read(String filename){
		ArrayList<String[]> text = new ArrayList<String[]>();

		try{
			BufferedReader br = new BufferedReader(new FileReader(filename));
			String line;

			while ((line = br.readLine()) != null){
				//Check if the line is not empty
				if (line.length() > 1){
					String[] newLine = line.split("\n");
					text.add(newLine);
				}
			}
			br.close();
		}

		catch(IOException e) {
			System.out.println(e);
		}

		return text;
	}

	public ArrayList<String[]> convert_array_to_string(ArrayList<String[]> text){
		String ch = "";
		String bf = "";

		ArrayList<String[]> ch_array = new ArrayList<String[]>();
		String[] temp_char = new String[1];


		for (String[] line : text){
			for (String word : line){
				for (int i=0; i < word.length(); i++){
					if ((char)word.charAt(i) == ":".charAt(0)){
						try{
							bf = String.valueOf(word.charAt(i-1));
						}
						catch(Exception e){
							bf = String.valueOf((char)13);
						}

							int af = (int) (word.charAt(i+1) - '0');

							for (int x = 0; x <= af-1; x++){
								temp_char[0] = bf;
								ch_array.add(temp_char);
								temp_char = new String[1];
							}
					}
				}
			}
		}
		return ch_array;
	}

	public String read_path(String filename){
		ArrayList<String[]> text = this.read(filename);

		String ch = "";

		for (String[] line : text){
			for (String word : line){
				ch += word;
			}
		}

		return ch;
	}

}

