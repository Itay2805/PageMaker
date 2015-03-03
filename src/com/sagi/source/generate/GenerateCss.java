package com.sagi.source.generate;

import java.io.File;

import com.sagi.source.io.ReadFile;
import com.sagi.source.io.WriteFile;

public class GenerateCss {
	
	private static String path = System.getProperty("user.dir") + "/resources/style.css";
	private static String copy = System.getProperty("user.dir") + "/generatedPages/css/style.css";
	
	/*
	 * Saves the file in a String 
	 * Replaces what needed with the values given
	 * creates new file in the same dir at /generatedPages/css/style.css
	 */
	
	public static void generateCss(String bgmain, String tcmain, String bgstrip, String tcstrip) {
		String cssFile = ReadFile.readFile(new File(path));
		cssFile = cssFile.replace("000000", bgmain);
		cssFile = cssFile.replace("000001", tcmain);
		cssFile = cssFile.replace("000002", bgstrip);
		cssFile = cssFile.replace("000003", tcstrip);
		WriteFile.writeFile(copy, "style.css", cssFile);
	}
	
}
