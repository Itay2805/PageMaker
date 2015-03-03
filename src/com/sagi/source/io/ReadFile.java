package com.sagi.source.io;

import java.io.File;
import java.io.FileInputStream;

/**
 * This class reads files
 *
 * @author SAGI Code
 */
public class ReadFile {
	
	/**
	 * This reads a file
	 * 
	 * @param file - File Object
	 * @return returns a String of the file's content
	 */
	public static String readFile(File file) {
		String data = "";
		try {
			FileInputStream fis = new FileInputStream(file);
			int s;
			while ((s = fis.read()) != -1) {
				data += (char) s;
			}
			fis.close();
		} catch (Exception e) {
			System.out.println("Error when trying to read the file at" + file.getPath() + " -> " + e.getMessage());
		}
		return data;
	}
	
}
