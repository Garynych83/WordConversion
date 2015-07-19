package WordConversion;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ReadTextFile {
	public static List<String> ReadFileLines(String filename) throws IOException {

		// This will reference one line at a time
		String line = null;
		List<String> result = new ArrayList<String>();
		BufferedReader bufferedReader = null;

		try {
			// Always wrap FileReader in BufferedReader.
			bufferedReader = new BufferedReader(new FileReader(filename));

			while ((line = bufferedReader.readLine()) != null) {
				result.add(line);
			}
			// Always close files.
			bufferedReader.close();
		} finally {
			if (bufferedReader != null) {
				bufferedReader.close();
			}
		}
		return result;
	}
}