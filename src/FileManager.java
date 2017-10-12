import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileManager {
	

	
	
	public static String ReadFile(File file) {
		
		Scanner sc = null;
		StringBuilder sb = new StringBuilder();
		//String text = "";
		try {
			sc = new Scanner(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(sc!=null) {
			while(sc.hasNextLine()) {
				sb.append(sc.nextLine());
			}
			sc.close();
		}
			
		return sb.toString();
	}
	
	public static boolean deleteFile(String fileName) {
		File file = new File(fileName);

		if (file.exists() && file.isFile()) {
			if (file.delete()) {
				return true;

			} else {
				return false;
			}

		} else {

			return false;
		}
	}

}
