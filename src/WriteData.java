import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.io.*;

public class WriteData{
	public void write_alphabet(String file, LinkedHashMap alphabet, int nb_charac){
		Path path_file = Paths.get(System.getProperty("user.dir")).getParent();
		Path path = Paths.get(path_file + "/Huffman-code/data/" + file + "_freq.txt");

		StringBuilder alpha = new StringBuilder(nb_charac + "\n");
		ArrayList<Character> keys = new ArrayList<Character>(alphabet.keySet());

		for (char key : keys){
			alpha.append(key).append(" ").append(alphabet.get(key)).append("\n");
		}
		byte[] bytes = alpha.toString().getBytes(StandardCharsets.UTF_8);

		try {
			Files.write(path, bytes);
		}
		catch (IOException e){
			e.printStackTrace();
		}
	}

	public void write_binary(String file, String data) {
		Path path_file = Paths.get(System.getProperty("user.dir")).getParent();
		StringBuilder byteString = new StringBuilder();
		char[] list_bits = data.toCharArray();

		try{
			FileOutputStream fos = new FileOutputStream(path_file + "/Huffman-code/data/" + file + "_comp.bin");
        	BufferedOutputStream out = new BufferedOutputStream(fos);

        	for (char bit : list_bits){
				byteString.append(bit);
				if (byteString.length() == 8){
					int parsedByte = Byte.toUnsignedInt((byte)(Integer.parseInt(byteString.toString(), 2) & 0xFF));//Binary conversion from byteString to byte
					//System.out.println(parsedByte);
					//System.out.println(byteString + "\n");
					out.write(parsedByte);
					byteString = new StringBuilder();
				}
			}

        	if (byteString.length() > 0){//Last bits wrere not sufficient to build a byte
        		while (byteString.length() < 8){
					byteString.append("0");
        		}
				int parsedByte = Byte.toUnsignedInt((byte)(Integer.parseInt(byteString.toString(), 2) & 0xFF));
				out.write(parsedByte);
			}

			out.flush();
       	 	fos.close();
		}
		catch (Exception e){
			System.out.println(e);
		}
	}

	public void compression_rate(String file){
		Path path_file = Paths.get(System.getProperty("user.dir")).getParent();

		File file_txt = new File(path_file + "/Huffman-code/data/" + file + ".txt");
		File file_bin = new File(path_file + "/Huffman-code/data/" + file + "_comp.bin");

		float size_txt = file_txt.length();
		float size_bin = file_bin.length();

		float rate = (1 - (size_bin/size_txt))*100;

		System.out.println("Compression rate : " + rate + "%");
	}
}