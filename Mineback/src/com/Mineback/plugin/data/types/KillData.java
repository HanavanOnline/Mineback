package com.Mineback.plugin.data.types;

import com.Mineback.plugin.Mineback;
import com.Mineback.plugin.data.MinebackData;
import com.Mineback.plugin.data.RequestType;

public class KillData extends MinebackData {

	public KillData(Mineback mineback) {
		super(mineback);
		
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
