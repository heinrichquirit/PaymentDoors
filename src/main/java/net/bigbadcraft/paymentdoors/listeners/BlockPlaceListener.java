package main.java.net.bigbadcraft.paymentdoors.listeners;

import main.java.net.bigbadcraft.paymentdoors.DoorsPlugin;
import main.java.net.bigbadcraft.paymentdoors.utils.DoorManager;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

public class BlockPlaceListener implements Listener {
	
	private DoorManager manager;
	protected DoorsPlugin plugin;
	
	public BlockPlaceListener(DoorsPlugin plugin) {
		this.plugin = plugin;
		manager = plugin.manager;
	}
	
	@EventHandler
	public void onBlockPlace(BlockPlaceEvent event) {
		final Player player = event.getPlayer();
		if (manager.isToggled(player)) {
			if (event.getBlock().getType() == Material.IRON_DOOR) {
				
			} else {
				manager.message(player, ChatColor.RED, "You must have an iron door in order to use this.");
			}
		}
	}
}
