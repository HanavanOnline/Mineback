package com.Mineback.plugin.data.types;

import org.bukkit.entity.Player;

import com.Mineback.plugin.Mineback;
import com.Mineback.plugin.data.MinebackData;
import com.Mineback.plugin.data.RequestType;

public class KillData extends MinebackData {
	
	private String killerName = null;
	private String victimName = null;

	public KillData(Mineback mineback) {
		super(mineback);
		
	}
	
	public KillData setKiller(Player killer) {
		
		this.getData().put("killer", killer.getName());
		
		this.killerName = killer.getName();
		
		return this;
		
	}
	
	public String getKillerName() {
		
		return this.killerName;
		
	}
	
	public KillData setVictim(Player victim) {
		
		this.getData().put("victim", victim.getName());
		
		this.victimName = victim.getName();
		
		return this;
		
	}
	
	public String getVictimName() {
		
		return this.victimName;
		
	}

	@Override
	public RequestType getRequestType() {
		
		return RequestType.PUT;
	}

	@Override
	public String getRequestURI() {
		
		return "api/put/kill";
	}
	
}
