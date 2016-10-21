package com.Mineback.plugin;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import com.Mineback.plugin.data.types.KillData;

public class PlayerListener implements Listener {

	private Mineback mineback = null;

	public PlayerListener(Mineback mineback) {
		
		this.mineback = mineback;
		
	}
	
	@EventHandler
	public void onPlayerKillPlayer(PlayerDeathEvent event) {
		
		Player victim = event.getEntity();
		
		if(victim == null)
			return;
		
		if(victim.getKiller() != null) {
			
			Player killer = victim.getKiller();
			
			KillData data = new KillData(this.getMineback());
			
			data.setKiller(killer).setVictim(victim);
			
			this.mineback.getDataQueue().addData(data);
			
		}
		
	}
	
	public Mineback getMineback() {
		
		return this.mineback;
		
	}
	
}
