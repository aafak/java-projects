package test;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;

public class FindWordInFile {

	public static void main(String[] args) {
		
		System.out.println("Enter some string");
		BufferedReader reader =
			    new BufferedReader(new InputStreamReader( System.in ));
		if(reader != null){
			System.out.println(reader);
		}
		
		String Str = new String("Welcome to Tutorialspoint.com");

	      System.out.print("Return Value :" );
	      System.out.println(Str.matches("(.*)Tutorials(.*)"));

	      System.out.print("Return Value :" );
	      System.out.println(Str.matches("Tutorials"));

	      System.out.print("Return Value :" );
	      System.out.println(Str.matches("Welcome(.*)"));
		
		
		try{
			BufferedReader buffer = new BufferedReader(new InputStreamReader( new 
				FileInputStream("resource/findword.txt")));
					
			System.out.println("Hello..........");
			while(true){
				String line = buffer.readLine();
				if(line == null){
					break;
				}
				//System.out.println(line);

				//System.out.println(line.toString().matches(".*aafak.*"));

				String [] words = line.split(" ");
				for(String word : words) {
					if(word.equals("SE"))
						System.out.println(word);

				}
				//System.out.println(line);
			}

		} catch(FileNotFoundException ex) {
			ex.printStackTrace();
		} 
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}
