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
    	int file_len;
   		byte data[] = new byte[16];
   		int hex_charac;
   		String binary_path = "";

    	try{
			FileInputStream file = new FileInputStream(filename);
			file_len = file.read(data);

			while (file_len != -1){
	  			for (int j = 0; j < file_len; j++){
	    			hex_charac = data[j];
	    			binary_path += Integer.toBinaryString(hex_charac);
	    			System.out.println(Integer.toBinaryString(hex_charac));
	  			}
				file_len = file.read(data);
	   	 	}
	   	 }
	   	 catch (IOException e){
	   	 	;
	   	 }

		return binary_path;
	}

}

