package main.java.net.bigbadcraft.paymentdoors;

import java.io.File;

import main.java.net.bigbadcraft.paymentdoors.commands.DoorCommand;
import main.java.net.bigbadcraft.paymentdoors.listeners.BlockPlaceListener;
import main.java.net.bigbadcraft.paymentdoors.listeners.PlayerInteractListener;
import main.java.net.bigbadcraft.paymentdoors.listeners.PlayerQuitListener;
import main.java.net.bigbadcraft.paymentdoors.listeners.SignEditListener;
import main.java.net.bigbadcraft.paymentdoors.utils.DoorManager;
import main.java.net.bigbadcraft.paymentdoors.utils.Utilities;
import net.milkbowl.vault.economy.Economy;

import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

public class DoorsPlugin extends JavaPlugin {
	
	public static Economy economy = null;
	
	public Utilities utils;
	public DoorManager manager;
	
	private final File config = new File(getDataFolder(), "config.yml");
	
	@Override
	public void onEnable() {
		utils = new Utilities();
		manager = new DoorManager();
		saveDefaultConfig();
		utils.loadFile(config);
		initVault();
		getCommand("doors").setExecutor(new DoorCommand(this));
		registerEvents();
	}
	
	@Override
	public void onDisable() {
		manager.clearMaps();
	}
	
	private boolean initVault() {
		if (getConfig().getBoolean("vault.enable")) {
			if (getServer().getPluginManager().getPlugin("Vault") != null) {
				setupEconomy();
			}
		}
		return false;
	}
	
	private boolean setupEconomy() {
        RegisteredServiceProvider<Economy> economyProvider = getServer().getServicesManager().getRegistration(net.milkbowl.vault.economy.Economy.class);
        if (economyProvider != null) {
            economy = economyProvider.getProvider();
        }
        return (economy != null);
    }
	
	private void registerEvents() {
		PluginManager pm = getServer().getPluginManager();
		pm.registerEvents(new BlockPlaceListener(this), this);
		pm.registerEvents(new PlayerInteractListener(), this);
		pm.registerEvents(new PlayerQuitListener(this), this);
		pm.registerEvents(new SignEditListener(), this);
	}
	
}
