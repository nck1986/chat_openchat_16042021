package client;

import java.io.File;
import java.util.List;

public interface RWMessagesToFile {

	void close();

	void writeMessageToFile(String msg);

	List<String> getLastMessages(File file, Integer quantityOfMessages);

}
