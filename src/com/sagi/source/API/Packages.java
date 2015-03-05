package com.sagi.source.API;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.JOptionPane;

import com.sagi.source.generate.Edit;
import com.sagi.source.gui.Editor;
import com.sagi.source.io.ReadFile;
import com.sagi.source.io.WriteFile;

/**
 * This class is on charge of the API. It installs and reading and running all the packages.
 *
 * @author SAGI Code
 * @version 1.0.0
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
	 * List of packages
	 */
	public static HashMap<String, File> packages = new HashMap<String, File>();
	/**
	 * List of installed files
	 */
	public static List<File> installed = new ArrayList<File>();
	
	/**
	 * Gets all the packages in the '/resources/packages/ folder'
	 */
	public static void getPackages() {
		File folder = new File(System.getProperty("user.dir") + "/resources/packages");
		File[] listOfFiles = folder.listFiles();
		for(int i = 0 ; i < listOfFiles.length; i++) {
			if(listOfFiles[i].getName().contains(".package")) {
				packages.put(listOfFiles[i].getName(), listOfFiles[i]);
				Editor.addPackage(listOfFiles[i].getName());
			}
		}
	}
	
	/**
	 * This loads all the installed packages to make sure the filles excist and linked
	 */
	public static void loadFiles(){
		String data = ReadFile.readFile(new File(System.getProperty("user.dir") + "/resources/packages.conf"));
		data = data.replace("[PLACEHOLDER]", "");
		String[] files = data.split(";");
		for(int i = 0; i < files.length; i++) {
			String path = System.getProperty("user.dir") + "/resources/packages/" + files[i];
			if(!path.equals(System.getProperty("user.dir") + "/resources/packages/")) {
				load(new File(path));					
			}
		}
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
			System.out.println("Error when trying to load " + file.getName() + " -> " + e.getMessage());
        	e.printStackTrace();
        }
	}
	
	/**
	 * This adds the files to the memory and reads them
	 * 
	 * @param file - The file to load
	 */
	public static void install(File file) {
		System.out.print("Installing" + file.getName());
		String data = ReadFile.readFile(new File(System.getProperty("user.dir") + "/resources/packages.conf"));
		if(data.contains(file.getName())) {
			System.out.print("   ....   Already Installed!");
			JOptionPane.showMessageDialog(null, "Package already installed!", "Editor", JOptionPane.ERROR_MESSAGE);
		}else {
			data = data.replace("[PLACEHOLDER]", file.getName() + ";[PLACEHOLDER]");
			WriteFile.writeFile(System.getProperty("user.dir") + "/resources/packages.conf", "packages.conf", data);
			installed.add(file);
			load(file);
			System.out.println("   ....   Done!");
		}
	}
	
}
