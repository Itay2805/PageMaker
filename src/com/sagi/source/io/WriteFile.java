package com.sagi.source.io;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

/**
 * This class reads files
 *
 * @author SAGI Code
 */
public class WriteFile {
	
	/**
	 * This writes(creates) a file
	 * 
	 * @param path - the path of the directory
	 * @param name - the name of the file to create
	 * @param data - the data to write in the file
	 */
	public static void writeFile(String path, String name, String data) {
		System.out.print("Generating " + name);
		PrintWriter writer;
		try {
			writer = new PrintWriter(path, "UTF-8");
			writer.println(data);
			writer.close();
		} catch (FileNotFoundException e) {
			System.out.println("Error when trying to create file at " + path + name + " -> " + e.getMessage());
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			System.out.println("Error when trying to load the config file -> " + e.getMessage());
			e.printStackTrace();
		}
		System.out.println("   ....   Created!");

	}
	
}
