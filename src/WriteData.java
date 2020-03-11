import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

import java.io.*;


public class WriteData{
	public void write_alphabet(String file, LinkedHashMap alphabet){
		Path path_file = Paths.get(System.getProperty("user.dir")).getParent();
		Path path = Paths.get(path_file + "/data/" + file + "_alphabet.txt");

		String alpha = "";

		ArrayList<Character> keys = new ArrayList<Character>(alphabet.keySet());

		for (char key : keys){
			alpha += key + ":" + alphabet.get(key) + "\n";
		}

		byte[] bytes = alpha.getBytes(StandardCharsets.UTF_8);

		try {
			Files.write(path, bytes);
		}
		catch (IOException e){
			e.printStackTrace();
		}

	}

	public void write_binary(String file, String data){
		Path path_file = Paths.get(System.getProperty("user.dir")).getParent();

		String byteString = "";

		try{
			FileOutputStream fos = new FileOutputStream(path_file + "/data/" + file + ".bin");
        	BufferedOutputStream out = new BufferedOutputStream(fos);

      		while (data.length() > 1){
	  			if (data.length() >= 7){
	  				byteString  = data.substring(0, 7);
	  			}
	  			else{
	  				while (data.length() < 7){
	  					data += "0";
	  				}
	  				byteString = data;
	  			}
	  			data = data.substring(7, data.length());

	  			int parsedByte = Integer.parseInt(byteString, 2);

	  			//System.out.println(byteString);

	       		fos.write(parsedByte);
      		}
      		out.flush();
       	 	fos.close();
       	 	this.compression_rate(file);
		}
		catch (IOException e){
			System.out.println(e);
		}
	}

	public void compression_rate(String file){
		Path path_file = Paths.get(System.getProperty("user.dir")).getParent();

		File file_txt = new File(path_file + "/data/" + file + ".txt");
		File file_bin = new File(path_file + "/data/" + file + ".bin");

		float size_txt = file_txt.length();
		float size_bin = file_bin.length();

		float rate = (1 - (size_bin/size_txt))*100;

		System.out.println("Compression rate : " + rate + "%");
	}
}