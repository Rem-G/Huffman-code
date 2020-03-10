import java.util.*;
import java.io.*;

public class ReadData{
	public ArrayList<String> read(String filename){
		ArrayList<String> text = new ArrayList<>();

		try{
			BufferedReader br = new BufferedReader(new FileReader(filename));
			while (br.ready()){
					text.add(br.readLine());
				}
			br.close();
		}
		catch(IOException e) {
			System.out.println(e);
		}
		return text;
	}

	public ArrayList<String> convert_array_to_string(String filename){
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

		String ch = "";
		String bf = "";
		int af = 0;

		ArrayList<String> ch_array = new ArrayList<String>();

		for (String[] line : text){
			for (String word : line){
				if (word.contains(":")){
					String[] word_split = word.split(":");
					try{
						bf = String.valueOf(word_split[0]);
						af = Integer.parseInt(word_split[1]);

					}
					catch(Exception e){
						if (String.valueOf(word.charAt(0)).equals(":")){
							System.out.println("ok");
							bf = String.valueOf((char)13);
							af = Integer.parseInt(word.substring(2, word.length()));
						}
						else{
							bf = String.valueOf(word.charAt(0));
							af = Integer.parseInt(word.substring(2, word.length()));
						}
					}

					for (int x = 0; x <= af-1; x++){
						ch_array.add(bf);
					}
				}
			}
		}
		return ch_array;
	}

	public String read_path(String filename){
		ArrayList<String> text = this.read(filename);

		String ch = "";

		for (String word : text){
			ch += word;
		}

		return ch;
	}

}

