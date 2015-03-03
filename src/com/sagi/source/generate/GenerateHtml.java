package com.sagi.source.generate;

import java.io.File;

import com.sagi.source.io.ReadFile;
import com.sagi.source.io.WriteFile;

public class GenerateHtml {
	
	private static String path = System.getProperty("user.dir") + "/resources/index.html";
	private static String copy = System.getProperty("user.dir") + "/generatedPages/index.html";
	
	
	/*
	 * Saves the file in a String 
	 * Replaces what needed with the values given
	 * creates new file in the same dir at /generatedPages/index.html
	 */
	
	public static void generateHtml(String cn, String cp) {
		String htmlFile = ReadFile.readFile(new File(path));
		htmlFile = htmlFile.replace("000000", cn);
		htmlFile = htmlFile.replace("000001", cp);
		WriteFile.writeFile(copy, "index.html", htmlFile);
	}
	
}
