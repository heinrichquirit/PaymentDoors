package main.java.net.bigbadcraft.paymentdoors.utils;

import java.util.HashMap;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;

public class DoorManager {
	
	private final ChatColor GOLD = ChatColor.GOLD;
	private final ChatColor WHITE = ChatColor.WHITE;
	
	private final HashMap<String, Integer> storedExp = new HashMap<String, Integer>();
	private final HashMap<String, Integer> storedMoney = new HashMap<String, Integer>();
	
	// Map handling
	
	public void saveExp(Player player, int amount) {
		storedExp.put(player.getName(), amount);
	}
	
	public void removeExp(Player player) {
		storedExp.remove(player.getName());
	}
	
	public boolean containsExp(Player player) {
		return storedExp.containsKey(player.getName());
	}
	
	public void saveMoney(Player player, int amount) {
		storedMoney.put(player.getName(), amount);
	}
	
	public void removeMoney(Player player) {
		storedMoney.remove(player.getName());
	}
	
	public boolean containsMoney(Player player) {
		return storedMoney.containsKey(player.getName());
	}
	
	public boolean isToggled(Player player) {
		return containsExp(player) || containsMoney(player);
	}
	
	public void clearMaps() {
		storedExp.clear();
		storedMoney.clear();
	}
	
	// Message handling
	
	public String helpMenu() {
		return "----- [" + GOLD + "PaymentDoors" + WHITE +"] -----\n" +
			GOLD + "-/doors xp <amount>" + WHITE + " - The amount of xp your door should charge.\n" +
			GOLD + "-/doors cost <amount>" + WHITE + " - The cost amount your door should charge.\n" +
			"--------------------------";
	}
	
	public void message(Player player, ChatColor color, String message) {
		player.sendMessage("[" + ChatColor.GOLD + "PaymentDoors" + ChatColor.WHITE + "] " + color + message);
	}
	
	// Door management
	
	public void attachSign(Block block) {
		@SuppressWarnings("deprecation")
		int face = block.getData();
		switch(face) {
		case 3: 
			Block n = block.getRelative(BlockFace.NORTH); 
			n.setType(Material.SIGN);
			Sign sign = (Sign) n.getState();
			sign.setLine(0, "[PaymentDoors]");
		case 4: block.getRelative(BlockFace.EAST).setType(Material.SIGN);
		case 5: block.getRelative(BlockFace.WEST).setType(Material.SIGN);
		case 2: block.getRelative(BlockFace.SOUTH).setType(Material.SIGN);
		}
	}
	
}
