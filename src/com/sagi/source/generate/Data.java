package com.sagi.source.generate;

import java.io.File;

import com.sagi.source.io.WriteFile;

/**
 * This class creates all directories and file needed for the Page Maker to work
 *
 * @author SAGI Code
 */

public class Data {
	
	//This is all the dirs as a 'File' object
	private static String dir = System.getProperty("user.dir");
	private static File generatedPages = new File(dir + "/generatedPages/");
	private static File css = new File(generatedPages.getPath() + "/css/");
	private static File js = new File(generatedPages.getPath() + "/js/");
	private static File resources = new File(dir + "/resources/");
	private static File packages = new File(resources.getPath() + "/packages/");
	
	/**
	 * This calls all the create method
	 */
	public static void createDirs() {
		createPagesDir();
		createCssDir();
		createJsDir();
		createResourcesDir();
		createPackagesDir();
	}
	
	/**
	 * This creates the generated pages directory (/genertedPages/)
	 */
	private static void createPagesDir() {
		if(!generatedPages.exists()) {
			System.out.println("generated Pages directory was not found!");
			System.out.print("Creating generated Pages directory");
			try {
				generatedPages.mkdir();
				System.out.println("   ....   Created!");
				//createPages();
			} catch (SecurityException e){
				System.out.println("Error when trying to create the generated pages directory -> " + e.getMessage());
				e.printStackTrace();
			}
		}else {
			System.out.println("Generated Pages directory was found at " + generatedPages.toPath());
		}
	}
	
	/**
	 * This creates the CSS files directory (/generatedPages/css/)
	 */
	private static void createCssDir() {
		if(!css.exists()) {
			System.out.println("css files directory was not found!");
			System.out.print("Creating css files directory");
			try {
				css.mkdir();
				System.out.println("   ....   Created!");
				//createPages();
			} catch (SecurityException e){
				System.out.println("Error when trying to create the css files directory -> " + e.getMessage());
				e.printStackTrace();
			}
		}else {
			System.out.println("css files directory was found at " + css.toPath());
		}
	}
	
	/**
	 * This creates the JS directory (/generatedPages/js/)
	 */
	private static void createJsDir() {
		if(!js.exists()) {
			System.out.println("javaScripts files directory was not found!");
			System.out.print("Creating javaScripts files directory");
			try {
				js.mkdir();
				System.out.println("   ....   Created!");
			} catch (SecurityException e){
				System.out.println("Error when trying to create the javaScripts files directory -> " + e.getMessage());
				e.printStackTrace();
			}
		}else {
			System.out.println("javaScripts files directory was found at " + js.toPath());
		}
	}
	
	/**
	 * This creates the resources directory (/resources/)
	 */
	private static void createResourcesDir() {
		if(!resources.exists()) {
			System.out.println("resources files directory was not found!");
			System.out.print("Creating resources files directory");
			try {
				resources.mkdir();
				System.out.println("   ....   Created!");
				WriteFile.writeFile(resources.toPath() + "/index.html", "index.html", "<!DOCTYPE html><html lang=\"en\"><head><meta charset=\"UTF-8\"><link rel=\"stylesheet\" href=\"./css/style.css\"><!-- CSS/JS HERE --><title>000000</title> <!-- Company name --></head><body><header class=\"strip\"><h1 class=\"largeText\">000000</h1> <!-- Company name --></header><section><br><br><!-- CODE IN HERE --><br><br></section><footer class=\"strip\"><p><small><em>&copy; Copy Right 2015 000001</em></small></p> <!-- Copy Right name --></footer></body></html>");;
				WriteFile.writeFile(resources.toPath() + "/style.css", "style.css", "body {background-color: 000000; /* background color (main) */color: 000001; /* text color (main) */font-family: arial; /* text font (main) */}.strip {background-color: 000002; /* background color (header and footer) */color: 000003; /* text color (header and footer) */padding: 10px;border-radius: 5px;}.largeText {	font-size: 36px; /* font size of 36(pixels) */}");;
				WriteFile.writeFile(resources.getPath() + "/packages.conf", "packages.conf", "[PLACEHOLDER]");
			} catch (SecurityException e){
				System.out.println("Error when trying to create the resources files directory -> " + e.getMessage());
				e.printStackTrace();
			}
		}else {
			System.out.println("resources files directory was found at " + resources.toPath());
		}
	}
	
	/**
	 * This creates the packages directory (/resources/packages/)
	 */
	private static void createPackagesDir() {
		if(!packages.exists()) {
			System.out.println("packages directory was not found!");
			System.out.print("Creating packages directory");
			try {
				packages.mkdir();
				System.out.println("   ....   Created!");
			} catch (SecurityException e){
				System.out.println("Error when trying to create the packages directory -> " + e.getMessage());
				e.printStackTrace();
			}
		}else {
			System.out.println("packages directory was found at " + packages.toPath());
		}
	}
	
}
