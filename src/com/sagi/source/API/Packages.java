package com.sagi.source.API;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;

import com.sagi.source.generate.Edit;
import com.sagi.source.gui.Editor;
import com.sagi.source.io.WriteFile;

/**
 * This class creates the files needed for the program to run
 *
 * @author SAGI Code
 */
public class Packages {
	
	/**
	 * List of the classes the user added
	 */
	public static HashMap<String, String> classes = new HashMap<String, String>();
	/**
	 * List of the types the user added
	 */
	public static HashMap<String, String> types = new HashMap<String, String>(); 
	
	/**
	 * Find all the files in the /include/folder to get all the .include files
	 * 
	 * @return list of all the files in the folder
	 */
	private static File[] readFolder() {
		File folder = new File(System.getProperty("user.dir") + "/resources/packages");
		File[] listOfFiles = folder.listFiles();
		return listOfFiles;
	}
	
	/**
	 * This finds all the files with a 'package' and loads them.
	 */
	public static void readFiles() {
		File[] listOfFiles = readFolder();
		System.out.print("Loading packages");
		for(int i = 0 ; i < listOfFiles.length; i++) {
			if(listOfFiles[i].getName().contains(".package")) {
				load(listOfFiles[i]);
			}
		}
		System.out.println("   ....   Done!");
	}
	
	/**
	 * This reads the files and saves the needed data to the memory
	 * 
	 * @param file - The file to load
	 */
	private static void load(File file) {
		String data = "";
		boolean done = false; 
		boolean info = false;
		boolean css = false;
		FileReader isr = null;
		String main = null;
        try {
            isr = new FileReader(file);
        } catch (FileNotFoundException e) {
            System.err.println("Error when trying to load the file " + file.getName() + " -> " + e.getMessage());
            System.exit(0);
        }
        BufferedReader reader = new BufferedReader(isr);
        String line;
        try{
        	while(!done) {
        		line = reader.readLine();
                if(line.startsWith("<")){
                	if(line.equals("<INFO>")) {
                		info = true;
                	}else if(line.equals("</INFO>")) {
                		info = false;
                	}else if(line.equals("<CSS>")) {
                		css = true;
                	}else if(line.equals("</CSS>")) {
                		System.out.println(data);
                		WriteFile.writeFile(System.getProperty("user.dir") + "/generatedPages/css/" + main, main, data);
                		css = false;
                	}else if(line.equals("<FINISH>")) {
                		done = true;
                	}
                }else if(info && !line.startsWith("#")) {
                	String[] currentLine = line.split("=");
                	if(currentLine[0].equals("main")) {
                		main = currentLine[1];
                		Edit.linkCSS(main);
                	}else if(currentLine[0].equals("class")) {
                		classes.put(currentLine[1] + " (" + main + ")", currentLine[1]);
                		Editor.addClass(currentLine[1] + " (" + main + ")");
                	}else if(currentLine[0].equals("type")) {
                		String[] type = currentLine[1].split(";");
                		Editor.addType(type[0]);
                		types.put(type[0], type[1]);
                		System.out.print(type[0] + " " + type[1]);
                	}
                }else if(css && !line.startsWith("#")){
                	data+=line;
                }else {
                	System.out.println("Unknown input in file: " + file.getName());
                }
        	}
            reader.close();
        }catch(Exception e) {
			System.out.println("Error when trying to load " + file.getName() + " -> " + e.getCause());
        	e.printStackTrace();
        }
	}
	
}
