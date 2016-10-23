package com.Mineback.plugin;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class MinebackCommands implements CommandExecutor {
	
	private Mineback mineback = null;
	
	public MinebackCommands(Mineback mineback) {
		
		this.mineback = mineback;
	
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if(!label.equalsIgnoreCase("mineback"))
			return true;
		
		if(args.length == 1) {
			
			if(args[0].equalsIgnoreCase("reload")) {
				
				this.mineback.doConfig();
				
				sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6[&2Mineback&6]&f Mineback reloaded."));
				
			}
			
		}
		
		return true;
	}

}
