package com.Mineback.plugin.thread;

import com.Mineback.plugin.Mineback;
import com.Mineback.plugin.data.DataQueue;
import com.Mineback.plugin.data.HTTPRequestBuilder;
import com.Mineback.plugin.data.MinebackData;

public class MinebackThread implements Runnable {

	private Mineback mineback = null;
	
	private boolean run = false;
	
	public MinebackThread(Mineback mineback) {
		
		this.mineback = mineback;
		
	}
	
	@Override
	public void run() {
		
		if(!this.run)
			return;
		
		DataQueue queue = this.mineback.getDataQueue();
		
		for(MinebackData data : queue.getData()) {
			
			if(data.canRetire()) {
				
				HTTPRequestBuilder builder = new HTTPRequestBuilder(data);
				
				if(builder.build())
					if(builder.fire())
						data.setHandled();
					else
						data.getMineback().doNotifyAdmins("Could not fire HTTP data.");
				else
					data.getMineback().doNotifyAdmins("Could not build HTTP data.");
				
			}
			
		}
		
		queue.doUpdateData();
		
	}
	
	public void start() {
		
		this.run = true;
		
	}
	
	public void stop() {
		
		this.run = false;
		
	}

}
