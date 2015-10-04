package fileHandling;

import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader;
		
public class FilePrint {

	public static void print(String path)
			throws IOException {

		FileReader fr = new FileReader(path);
		BufferedReader br = new BufferedReader(fr);
		String textRead = br.readLine();
		
		System.out.println("File contents: ");
		
		while (textRead != null) {
		
			System.out.println("    " + textRead);
			textRead = br.readLine();
		}
		
		fr.close();
		br.close();
	}
}