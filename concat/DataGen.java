import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.nio.file.*;
import java.io.*;

public class DataGen extends IOException {
	
	public DataGen(int testCases) {
	//	this.testCases(testCases);
		this.catinate();
	}
	
	/*private void testCases(int testCases) {
	
		FileSystem fs = FileSystem.getDefault(); // Works for both windows and Linux
		Path file = fs.getPath() + fs.getSeperator(); +"dataGen"l 
		
		for(int y = 0; y < testCases; y++) { // Each time this loop runs, a new .txt will be created in "\dataGen"
			File text = new File(file);
			BufferedWriter() writer = new BufferedWriter(new FileWriter(text)); 
			for(int x = 0; x < 26) { // x must be less than 26 because there are 26 letters in the alphabet
			 
			}
		}
	}*/

	private void catinate() {
		
		try {
			System.out.println("Entered catinate method");	
			File concat = new File(".//texts");
			File[] allFiles = concat.listFiles();
				
			BufferedWriter writer = new BufferedWriter(new FileWriter(".//concat"));
			BufferedReader reader;
			
			
			for(int x = 0; x < allFiles.length; x++) {
				reader = new BufferedReader(new FileReader(allFiles[x]));
				String test = reader.readLine();
				while(!(test == null)) {
					System.out.println("Inside while loop");
					writer.write(test,0,test.length()); //writing to concat
					writer.flush();
					System.out.println("Time for new reader");
					test = reader.readLine();
					System.out.println(test);
				}
				reader.close();
			}
		} catch (IOException e) {
			System.out.println("Ya done fucked up Gilmore.");			
		}
		
	}
	
	public static void main(String[] args) {
		DataGen gen = new DataGen(1);
	}
}
