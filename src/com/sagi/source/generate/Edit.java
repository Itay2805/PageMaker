package com.sagi.source.generate;

import java.io.File;

import com.sagi.source.io.ReadFile;
import com.sagi.source.io.WriteFile;

/**
 * This class edits the html
 *
 * @author SAGI Code
 */
public class Edit {
	
	private static String copy = System.getProperty("user.dir") + "/generatedPages/index.html";
	private static File css = new File(System.getProperty("user.dir") + "/generatedPages/css/style.css");
	
	/**
	 * 
	 * Gets the tet from the files and adds some HTML code
	 * 
	 * @param text - the HTML to add to the file
	 * @return 
	 */
	public static boolean editHtml(String text) {
		File f = new File(copy);
		if(css.exists() || f.exists()) {
			String htmlFile = ReadFile.readFile(new File(copy));
			htmlFile = htmlFile.replace("<!-- CODE IN HERE -->", text + "<!-- CODE IN HERE -->");
			WriteFile.writeFile(copy, "index.html", htmlFile);
			return true;
		}else {
			return false;
		}
	}
	
	/**
	 * This links a CSS file to the html
	 * 
	 * @param name - the name of the CSS file
	 */
	public static boolean linkCSS(String name) {
		File f = new File(copy);
		String link = "<link rel=\"stylesheet\" href=\"./css/" + name + "\">";
		if(css.exists() && f.exists()) {
			String htmlFile = ReadFile.readFile(new File(copy));
			if(!htmlFile.contains(link)) {
				htmlFile = htmlFile.replace("<!-- CSS/JS HERE -->", link + "<!-- CSS/JS HERE -->");
				WriteFile.writeFile(copy, "index.html", htmlFile);
				return true;
			}else {
				System.out.println("File ./css/" + name + " already linked to HTML page!");
				return false;
			}
		}else {
			return false;
		}
	}
	
}
