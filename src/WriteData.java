import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class WriteData{
	public void write_binary(String file, String data){
		Path path_file = Paths.get(System.getProperty("user.dir")).getParent();
		Path path = Paths.get(path_file + "/data/" + file + ".bin");

		byte[] bytes = data.getBytes(StandardCharsets.UTF_8);

		try {
			Files.write(path, bytes);

			this.compression_rate(file);
		}
		catch (IOException e){
			e.printStackTrace();
		}
	}

	public void compression_rate(String file){
		Path path_file = Paths.get(System.getProperty("user.dir")).getParent();

		File file_txt = new File(path_file + "/data/" + file + ".txt");
		File file_bin = new File(path_file + "/data/" + file + ".bin");

		long size_txt = file_txt.length();
		long size_bin = file_bin.length();

		float rate = 1 - (size_bin/size_txt);

		System.out.println("Compression rate : " + rate);
	}
}