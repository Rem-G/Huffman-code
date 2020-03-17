import java.io.*;

public class Main {

	public static void main(String[] args) throws FileNotFoundException{
		HuffmanManage hf = new HuffmanManage("textesimple");

		long startTime = System.nanoTime();

		hf.compression();

		long endTime = System.nanoTime();

		System.out.println((endTime - startTime)/1000000000);

		hf.decompression();
	}
}
