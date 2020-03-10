import java.io.*;

public class Main {

	public static void main(String[] args) throws FileNotFoundException{
		HuffmanManage hf = new HuffmanManage("extraitalice");

		hf.compression();
		hf.decompression();
	}
}
