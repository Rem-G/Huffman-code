import java.io.*;

public class Main {

	public static void main(String[] args) throws FileNotFoundException{
		HuffmanManage hf = new HuffmanManage("textesimple");

		hf.compression();
		hf.decompression();
	}
}
