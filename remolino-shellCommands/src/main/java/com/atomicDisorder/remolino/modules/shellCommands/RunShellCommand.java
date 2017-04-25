package com.atomicDisorder.remolino.modules.shellCommands;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;

import org.apache.log4j.Logger;

import com.atomicDisorder.remolino.commons.modules.ModuleAbstract;
import com.atomicDisorder.remolino.commons.utils.Configurations;

public class RunShellCommand {

	private String commandToRun;
	private Logger logger = Logger.getLogger(Configurations.class.getName());

	public RunShellCommand() {
		// TODO Auto-generated constructor stub
	}

	public String getCommandToRun() {
		return commandToRun;
	}

	public void setCommandToRun(String commandToRun) {
		this.commandToRun = commandToRun;
	}

	public void run() {
		//String[] command = { "CMD", "/C", "dir" };// FOR WINDOWS MARIANITUS
		//Process p = Runtime.getRuntime().exec(new String[]{"bash","-c","ls /home/XXX"});//linux
		//Process p = Runtime.getRuntime().exec(new String[]{"csh","-c","cat /home/narek/pk.txt"});//linux
		String[] command =null;
		String[] commandForWindows = {"cmd","/c", this.getCommandToRun().trim() } ;
		String[] commandForLinux = {"bash","-c", this.getCommandToRun().trim() } ;
		if (System.getProperty("os.name").equalsIgnoreCase("linux"))
		{
			command = commandForLinux ;
		}
		if (System.getProperty("os.name").toLowerCase().contains("windows"))
		{
			command = commandForWindows ;
		}
		if (command==null)
		{
			logger.warn("Unknown operating system. " + System.getProperty("os.name") + " not supported... yet");			
		}

		ProcessBuilder probuilder = new ProcessBuilder(command);
		// You can set up your work directory
		// probuilder.directory(new File("c:\\xyzwsdemo"));

		Process process;
		try {
			process = probuilder.start();

			// Read out dir output
			InputStream is = process.getInputStream();
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isr);
			String line;
			System.out.printf("Output of running %s is:\n", Arrays.toString(command));
			while ((line = br.readLine()) != null) {
				System.out.println(line);
			}

			// Wait to get exit value
			try {
				int exitValue = process.waitFor();
				System.out.println("\n\nExit Value is " + exitValue);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}

	/*
	 * public void run() { try { // Run "netsh" Windows command Process process
	 * = Runtime.getRuntime().exec("cmd /c " + this.getCommandToRun());
	 * 
	 * // Get input streams BufferedReader stdInput = new BufferedReader(new
	 * InputStreamReader(process.getInputStream())); BufferedReader stdError =
	 * new BufferedReader(new InputStreamReader(process.getErrorStream()));
	 * 
	 * // Read command standard output String s;
	 * System.out.println("Standard output: "); while ((s = stdInput.readLine())
	 * != null) { System.out.println(s); }
	 * 
	 * // Read command errors System.out.println("Standard error: "); while ((s
	 * = stdError.readLine()) != null) { System.out.println(s); } } catch
	 * (Exception e) { e.printStackTrace(System.err); } }
	 */
}
