
package com.trendyol.conf;

import java.io.File;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class LogHelper {
	Logger logger = Logger.getLogger("MyLog");
	FileHandler fh;

	/**
	 * <b>Method Name:</b>Write Log<br>
	 * <b>Description:</b>The log.txt file is kept under the project directory<br>
	 * <p>
	 * 
	 * @param message "Message" Message to be printed on the log.txt
	 */
	public void writeLog(String message) {
		try {
			fh = new FileHandler(new File("log.txt").getAbsolutePath());
			logger.addHandler(fh);
			SimpleFormatter formatter = new SimpleFormatter();
			fh.setFormatter(formatter);
			logger.info(message);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
