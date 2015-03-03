package com.sagi.source;

import com.sagi.source.API.Packages;
import com.sagi.source.generate.Data;
import com.sagi.source.gui.MainInformation;

/**
 * This class starts the program
 *
 * @author SAGI Code
 */

public class PMCore {
	
	/**
	 * This is the main class which starts when the program is running
	 * 
	 * @param args - Command Line Arguments
	 */
	public static void main(String[] args) {
		System.out.println("--------------------------------------------------------");
		System.out.println("setting-up page-maker version Alpha 3.0.0");
		System.out.println("--------------------------------------------------------");
		Data.createDirs();
		Packages.readFiles();
		System.out.println("--------------------------------------------------------");
		System.out.println("Change Log");
		System.out.println("--------------------------------------------------------");
		System.out.println(" * Changed 'include' folder to 'packages");
		System.out.println(" + Added JQuery download");
		System.out.println(" + Added PM-API version 1.0.0");
		System.out.println("--------------------------------------------------------");
		System.out.println("For Info about the development kit and the PM-API Please\nGo to www.SAGICode.com");
		System.out.println("--------------------------------------------------------");
		MainInformation.main();
	}
	
}
