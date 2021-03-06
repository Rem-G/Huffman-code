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
		catch(Exception e) {
			System.out.println(e);
		}
		return text;
	}

	public ArrayList<String> get_alphabet(String filename){
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
		catch(Exception e) {
			System.out.println(e);
		}

		String bf = "";
		int af = 0;

		ArrayList<String> ch_array = new ArrayList<>();

		for (String[] line : text){
			for (String word : line){
				if (word.contains(" ")){
					if (word.substring(0, 2).equals("  ")){//" " character
						bf = " ";
						af = Integer.parseInt(word.substring(2, word.length()));
					}
					else if (word.substring(0,1).equals(" ")){//\n character
						bf = System.lineSeparator();
						af = Integer.parseInt(word.substring(1, word.length()));
					}
					else{
						String word_split[] = word.split(" ");
						bf = word_split[0];
						af = Integer.parseInt(word_split[1]);
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
   		StringBuilder binary_path = new StringBuilder();

		try{
			byte[] data = Files.readAllBytes(path);

			for (byte hex : data){
				String str = Integer.toBinaryString(hex & 0xFF);

				while (str.length() < 8){//000011 is written as 11
					str = "0" + str;
				}
				//System.out.println(hex & 0xFF);
				//System.out.println(str + "\n");
	    		binary_path.append(str);
			}
		}
		catch (IOException e){
			System.out.println(e);
		}
		return binary_path.toString();
	}
}