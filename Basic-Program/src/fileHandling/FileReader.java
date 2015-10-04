package fileHandling;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class FileReader {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try{
			
			BufferedReader br = new BufferedReader(new InputStreamReader(new
					FileInputStream("resources/findWord")));
			while(true){
				String str = br.readLine();
				if(str != null){
					String []words = str.split(" ");
					for(String word : words){
						System.out.println(word);
					}
					//System.out.println(str);
				} else {
					break;
				}
			}
			
			//Reading char by char
			/*FileInputStream file = new FileInputStream("resources/findWord");
			System.out.println(file.read());
			int ch;
			while((ch=file.read())!=-1){
				System.out.print((char)ch);
			}*/
		} catch(FileNotFoundException ex){
			ex.printStackTrace();
		} catch(IOException ex){
			ex.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
