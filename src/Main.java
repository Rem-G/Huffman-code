import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

	public static void main(String[] args) throws FileNotFoundException{
		String path_file = Paths.get(System.getProperty("user.dir")).getParent().toString() + "/Huffman-code/data/";

		List<String> result = null;
		ArrayList<String> files = new ArrayList<String>();
		String s = null;

		while (s == null) {
			//Code from https://mkyong.com/java/java-how-to-list-all-files-in-a-directory/
			try (Stream<Path> walk = Files.walk(Paths.get(path_file))) {

				result = walk.map(x -> x.toString())
						.filter(f -> f.endsWith(".txt")).collect(Collectors.toList());

			} catch (IOException e) {
				e.printStackTrace();
			}

			System.out.println("Existing files in /data/ : ");
			for (String r : result) {
				String[] list_values = r.split("/");
				String value = list_values[list_values.length - 1];

				if (!value.contains("_freq")) {
					System.out.println(value);
					String[] value_split = value.split(".txt");
					files.add(value_split[0]);
				}
			}

			System.out.println("\nPlease enter file to compress/decompress : ");
			Scanner in = new Scanner(System.in);
			s = in.nextLine();
			String s_split = null;
			try{s_split = s.split(".txt")[0];} catch (Exception e){;}

			if (files.contains(s) || files.contains(s_split)) {
				System.out.println("ok");
				if (s.contains(".txt")) {
					s = s_split;
				}
			} else {
				s = null;
				System.out.println("Wrong file name\n");
			}
		}

		HuffmanManage hf = new HuffmanManage(s);

		long startTime = System.nanoTime();
		System.out.println("*******COMPRESSION*******");
		hf.compression();
		long endTime = System.nanoTime();
		System.out.println((endTime - startTime)/1000000 + " ms to compress");

		startTime = System.nanoTime();
		System.out.println("\n*******DECOMPRESSION*******");
		hf.decompression();
		endTime = System.nanoTime();
		System.out.println((endTime - startTime)/1000000 + " ms to decompress");
	}
}