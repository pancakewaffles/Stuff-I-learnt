import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/*
 * Writing files is similar to reading files
 */
public class App {

	public App() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		File file = new File("write.txt");

		try (BufferedWriter br = new BufferedWriter(new FileWriter(file))) {
			br.write("This is line one");
			br.newLine();
			br.write("This is line two");
			br.newLine();
			br.write("Last line.");

		} catch (IOException e) {
			System.out.println("Unable to read file :" + file.toString());
		}

	}

}
