package main.java.net.bigbadcraft.paymentdoors.utils;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;

import org.bukkit.Bukkit;

public class Utilities {
	
	public void loadFile(File file) {
		if (!file.exists()) {
			try {
				log(Level.INFO, file.getName() + " doesn't exist, creating..");
				file.createNewFile();
				log(Level.INFO, file.getName() + " successfully created.");
			} catch (IOException ioe) {
				log(Level.SEVERE, file.getName() + " could not be created.");
				ioe.printStackTrace();
			}
		}
	}
	
	public void log(Level lvl, String message) {
		Bukkit.getLogger().log(lvl, message);
	}
}
