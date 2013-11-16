package main.java.net.bigbadcraft.paymentdoors;

import java.io.File;

import main.java.net.bigbadcraft.paymentdoors.commands.DoorCommand;
import main.java.net.bigbadcraft.paymentdoors.listeners.BlockPlaceListener;
import main.java.net.bigbadcraft.paymentdoors.listeners.PlayerInteractListener;
import main.java.net.bigbadcraft.paymentdoors.utils.DoorManager;
import main.java.net.bigbadcraft.paymentdoors.utils.Utilities;

import org.bukkit.plugin.java.JavaPlugin;

public class DoorsPlugin extends JavaPlugin {
	
	public Utilities utils;
	public DoorManager manager;
	
	private final File config = new File(getDataFolder(), "config.yml");
	
	@Override
	public void onEnable() {
		utils = new Utilities();
		manager = new DoorManager();
		saveDefaultConfig();
		utils.loadFile(config);
		getCommand("doors").setExecutor(new DoorCommand(this));
		getServer().getPluginManager().registerEvents(new BlockPlaceListener(this), this);
		getServer().getPluginManager().registerEvents(new PlayerInteractListener(), this);
	}
	
	@Override
	public void onDisable() {
		manager.clearMaps();
	}
}
