package main.java.net.bigbadcraft.paymentdoors.listeners;

import main.java.net.bigbadcraft.paymentdoors.DoorsPlugin;
import main.java.net.bigbadcraft.paymentdoors.utils.DoorManager;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerQuitListener implements Listener {

	private DoorManager manager;
	protected DoorsPlugin plugin;
	
	public PlayerQuitListener(DoorsPlugin plugin) {
		this.plugin = plugin;
		manager = plugin.manager;
	}
	
	@EventHandler
	public void onQuit(PlayerQuitEvent event) {
		final Player player = event.getPlayer();
		if (manager.containsExp(player)) {
			manager.removeExp(player);
		} else if (manager.containsMoney(player)) {
			manager.removeMoney(player);
		}
	}
}
