package main.java.net.bigbadcraft.paymentdoors.commands;

import main.java.net.bigbadcraft.paymentdoors.DoorsPlugin;
import main.java.net.bigbadcraft.paymentdoors.utils.DoorManager;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class DoorCommand implements CommandExecutor {

	private DoorManager manager;
	protected DoorsPlugin plugin;
	
	public DoorCommand(DoorsPlugin plugin) {
		this.plugin = plugin;
		manager = plugin.manager;
	}
	
	@Override
	public boolean onCommand(CommandSender cs, Command cmdObj, String lbl, String[] strings) {
		if (cs instanceof Player) {
			Player player = (Player) cs;
			if (cmdObj.getName().equalsIgnoreCase("doors")) {
				return mainCommand(player, strings);
			}
		}
		return true;
	}
	
	public boolean mainCommand(Player player, String[] strings) {
		if (strings.length == 0) {
			player.sendMessage(manager.helpMenu());
		} else if (strings.length == 1) {
			if (strings[0].equalsIgnoreCase("xp")) {
				manager.message(player, ChatColor.RED, "Incorrect syntax, usage: /doors xp <amount>");
			} else if (strings[0].equalsIgnoreCase("cost")) {
				manager.message(player, ChatColor.RED, "Incorrect syntax, usage: /doors cost <amount>");
			}
		} else if (strings.length == 2) {
			if (strings[0].equalsIgnoreCase("xp")) {
				try {
					int xpAmount = Integer.parseInt(strings[1]);
					manager.saveExp(player, xpAmount);
					manager.message(player, ChatColor.GREEN, "Your door will cost " + xpAmount + " exp to use, please place your door now.");
				} catch (NumberFormatException e) {
					manager.message(player, ChatColor.RED, "Invalid argument, please enter a number.");
				}
			} else if (plugin.getConfig().getBoolean("vault.enable")) {
				if (strings[0].equalsIgnoreCase("cost")) {
					try {
						int money = Integer.parseInt(strings[1]);
						manager.saveMoney(player, money);
						manager.message(player, ChatColor.GREEN, "Your door will cost $" + money + " to use, please place your door now.");
					} catch (NumberFormatException e) {
						manager.message(player, ChatColor.RED, "Invalid argument, please enter a number.");
					}
				}
			} else {
				manager.message(player, ChatColor.RED, "This command is disabled, since Vault is not found. Contact your Administrator for assistance.");
			}
		}
		return true;
	}
	
}
