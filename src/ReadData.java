import java.util.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


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

		String bf = "";
		int af = 0;

		ArrayList<String> ch_array = new ArrayList<String>();

		for (String[] line : text){
			for (String word : line){
				if (word.contains(":")){
					if ( String.valueOf(word.charAt(0)).equals(":") ){
						if (word.substring(0, 2).equals("::")){
							bf = ":";
							af = Integer.parseInt(word.substring(2, word.length()));
						}

						else if ( !(word.substring(0, 2).equals("::")) ){
							bf = System.getProperty("line.separator");
							af = Integer.parseInt(word.substring(1, word.length()));
						}
					}

					else {
						bf = String.valueOf(word.charAt(0));
						af = Integer.parseInt(word.substring(2, word.length()));
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
		Path path = Paths.get(filename);
   		String binary_path = "";

		try{
			byte[] data = Files.readAllBytes(path);
			for (int hex : data){
	    		binary_path += Integer.toBinaryString(hex);
			}
		}
		catch (IOException e){;}

		return binary_path;
	}

}

