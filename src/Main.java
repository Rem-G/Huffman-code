import java.io.*;

public class Main {

	public static void main(String[] args) throws FileNotFoundException{
		HuffmanManage hf = new HuffmanManage("textesimple");

		long startTime = System.nanoTime();
		System.out.println("*******COMPRESSION*******");
		hf.compression();
		long endTime = System.nanoTime();
		System.out.println((endTime - startTime)/1000000000 + " seconds to compress");

		startTime = System.nanoTime();
		System.out.println("\n*******DECOMPRESSION*******");
		hf.decompression();
		endTime = System.nanoTime();
		System.out.println((endTime - startTime)/1000000000 + " seconds to uncompress");
	}
}