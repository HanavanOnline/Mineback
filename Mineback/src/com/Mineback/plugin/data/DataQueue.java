package com.Mineback.plugin.data;

import java.util.ArrayList;
import java.util.List;

import com.Mineback.plugin.Mineback;

public class DataQueue {
	
	/** 
	 * Data that is queued to enter the queue for data to be processed... Apologies for the redundancy; will refactor in the future.
	 * 
	 * Implemented to prevent ConcurrentModificationException.
	 */
	private List<MinebackData> dataQueue = null;
	
	/**
	 * Data that is in the queue to be processed by the MinebackThread.
	 */
	private List<MinebackData> dataProcessQueue = null;
	
	public DataQueue(Mineback mineback) {
		
		this.dataQueue = new ArrayList<MinebackData>();
		
	}
	
	/**
	 * Added data to the queue to join the data process queue.
	 * @param data The data to add to the former queue.
	 */
	public void addData(MinebackData data) {
		
		this.dataQueue.add(data);
		
	}
	
	/**
	 * Called by MinebackThread frequently. Used to remove data that has already been processed from the data process queue.
	 */
	public void doUpdateData() {
		
		List<MinebackData> tmp = new ArrayList<MinebackData>();
		
		for(MinebackData data : this.dataProcessQueue) {
			
			if(data.isHandled())
				tmp.add(data);
			
		}
		
		for(MinebackData data : tmp) {
			
			this.dataProcessQueue.remove(data);
			
		}
		
		for(MinebackData data : this.dataQueue) {
			
			this.dataProcessQueue.add(data);
			
		}
		
		tmp = null;
		
	}
	
	/**
	 * Return a live reference to the data in the process queue. This method should not, but can be, accessed by third party plugins.
	 * @return The data process queue.
	 */
	public List<MinebackData> getData() {
		
		return this.dataProcessQueue;
		
	}

}
