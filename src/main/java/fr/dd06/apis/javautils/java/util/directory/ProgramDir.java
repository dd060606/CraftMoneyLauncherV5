package fr.dd06.apis.javautils.java.util.directory;

import java.io.File;

public class ProgramDir {
	public static File createProgramDir(String dirName) {
		String os = System.getProperty("os.name").toLowerCase();
		if (os.contains("win"))
			return new File(System.getProperty("user.home") + "\\AppData\\Local\\Programs\\" + dirName);
		if (os.contains("mac")) {
			return new File(System.getProperty("user.home") + "/Library/Application Support/" + dirName);
		}
		return new File(System.getProperty("user.home") + "/" + dirName);
	}
}
