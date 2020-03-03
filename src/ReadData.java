import java.util.ArrayList;
import java.io.*;

public class ReadData{
	private String filename;

	public ReadData(String filename){
		this.filename = filename;
	};

	public ArrayList read(){

		ArrayList<String[]> text = new ArrayList<String[]>();

		try{
				BufferedReader br = new BufferedReader(new FileReader(this.filename));
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

		return text;
	}
}

