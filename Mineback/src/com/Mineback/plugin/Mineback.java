package com.Mineback.plugin;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import com.Mineback.plugin.data.DataQueue;
import com.Mineback.plugin.thread.MinebackThread;

public class Mineback extends JavaPlugin {
	
	private MinebackThread thread = null;
	
	private DataQueue dataQueue = null;
	
	private String publicKey = null;
	private String privateKey = null;
	
	private int serverId = -1;

	@Override
	public void onEnable() {
		
		this.thread = new MinebackThread(this);
		
		this.doConfig();
		
		this.getCommand("mineback").setExecutor(new MinebackCommands(this));
		
	}
	
	@Override
	public void onDisable() {
		
		this.thread.stop();
		
	}
	
	public void doConfig() {
		
		this.saveDefaultConfig();
		
		this.getConfig().options().copyDefaults(true);
		
		this.serverId = this.getConfig().getInt("server-id");
		
		this.publicKey = this.getConfig().getString("public-key");
		this.privateKey = this.getConfig().getString("private-key");
		
	}
	
	public int getServerId() {
		
		return this.serverId;
		
	}
	
	public String getPublicKey() {
		
		return this.publicKey;
		
	}
	
	public String getPrivateKey() {
		
		return this.privateKey;
		
	}
	
	public DataQueue getDataQueue() {
		
		return this.dataQueue;
		
	}
	
	public void doNotifyAdmins(String message) {
		
		for(Player player : Bukkit.getOnlinePlayers())
			if(player.isOp() || player.hasPermission("mineback.admin") || player.hasPermission("mineback.*"))
				player.sendMessage(ChatColor.translateAlternateColorCodes('&',"&6[&2Mineback&6] &f]!!A plugin using Mineback tried to send data without building its HTTP request...!!"));
		
	}
	
}
