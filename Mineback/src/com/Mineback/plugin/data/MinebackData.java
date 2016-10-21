package com.Mineback.plugin.data;

import java.util.Date;
import java.util.HashMap;

import com.Mineback.plugin.Mineback;

public abstract class MinebackData {
	
	private Mineback mineback = null;
	
	private boolean handled = false;
	
	private long birth = -1L;
	private long retirement = -1L;
	
	private long ttl = -1L;
	
	private HashMap<String, Object> data = null;

	public MinebackData(Mineback mineback) {
		
		this.mineback = mineback;
		
		this.data = new HashMap<String, Object>();
		
		this.birth = new Date().getTime();
		
	}
	
	public MinebackData setData(String key, Object value) {
		
		this.data.put(key, value);
		
		return this;
		
	}
	
	public Object getData(String key) {
		
		return this.data.get(key);
		
	}
	
	public HashMap<String, Object> getData() {
		
		return this.data;
		
	}
	
	public long getBirth() {
		
		return this.birth;
		
	}
	
	public MinebackData setBirth(long birth) {
		
		this.birth = birth;
		
		return this;
		
	}
	
	public long getRetirement() {
		
		return this.retirement;
		
	}
	
	public MinebackData setTimeToLive(long ttl) {
		
		this.ttl = ttl;
		
		return this;
		
	}
	
	public long getTimeToLive() {
		
		return this.ttl;
		
	}
	
	public boolean canRetire() {
		
		return (new Date().getTime() > this.getRetirement() && this.getRetirement() != -1L) || (this.getRetirement() - this.getBirth() <= this.getTimeToLive() && this.getTimeToLive() != -1L);
		
	}
	
	public MinebackData setHandled() {
		
		this.handled = true;
		
		return this;
		
	}
	
	public boolean isHandled() {
		
		return this.handled;
		
	}
	
	public Mineback getMineback() {
		
		return this.mineback;
		
	}
	
	/**
	 * The type of HTTP request to be made to Mineback servers.
	 * @return The RequestType
	 */
	public abstract RequestType getRequestType();
	
	/**
	 * The URI of the request to be made to the Mineback servers.
	 * @return The URI as a String
	 */
	public abstract String getRequestURI();
	
}
