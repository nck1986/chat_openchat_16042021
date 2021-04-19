package client;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.List;

public class LocalClientHistory implements RWMessagesToFile {

	private PrintWriter printWriter;


	public LocalClientHistory(File file) {
		try {
			FileOutputStream out = new FileOutputStream(file, true);
			printWriter = new PrintWriter(out, true);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void close() {
		if (printWriter != null) {
			printWriter.close();
		}
	}

	@Override
	public void writeMessageToFile(String msg) {
		printWriter.println(msg);
	}

	@Override
	public List<String> getLastMessages(File file, Integer quantityOfMessages) {
		return null;
	}
}
