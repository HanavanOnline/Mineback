package com.Mineback.plugin.data;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.List;
import java.util.logging.Logger;

import com.Mineback.plugin.HTTP;

public class HTTPRequestBuilder {

	private MinebackData data = null;
	
	private String json = null;
	
	public HTTPRequestBuilder(MinebackData data) {
		
		this.data = data;
		
	}
	
	public boolean build() {
		
		String s = "{";
		
		int size = data.getData().size();
		
		for(String key : data.getData().keySet()) {
			
			--size;
			
			s = s + "\"" + this.serialize(data.getData().get(key)) + "\"";
			
			if(size != 0) {
				
				s = s + ",";
				
			}
			
		}
		
		s += "}";
		
		this.json = s;
		
		return true;
		
	}
	
	private String serialize(Object o) { 
		
		if(o instanceof List) {
			
			String s = "[";
			
			List list = (List) o;
			
			int size = list.size();
			
			for(Object value : list) {
				
				--size;
				
				s = s + "\"" + this.serialize(value) + "\"";
				
				if(size != 0) {
					
					s = s + ",";
					
				}
				
			}
			
			s += "]";
			
			return s;
			
		}
		
		if(o instanceof Integer) {
			
			return ((int) o) + "";
			
		}
		
		if(o instanceof Long) {
			
			return ((long) o) + "";
			
		}
		
		if(o instanceof Double) {
			
			return ((double) o) + "";
			
		}
		
		if(o instanceof Float) {
			
			return ((float) o) + "";
			
		}
		
		return o.toString();
		
	}
	
	public boolean fire() {
		
		if(this.json == null) {
			
			Logger.getLogger("Mineback").info("A plugin using Mineback tried to send data without building its HTTP request...");
			
			this.data.getMineback();
			
			return false;
			
		}
		
        URL url = null;
		try {
			url = new URL(HTTP.ADDRESS + this.data.getRequestURI());
		} catch (MalformedURLException e) {
			e.printStackTrace();
			return false;
		}
        HttpURLConnection connection = null;
		try {
			connection = (HttpURLConnection) url.openConnection();
		} catch (IOException e2) {
			e2.printStackTrace();
			return false;
		}
        try {
			connection.setRequestMethod(this.data.getRequestType().toString());
		} catch (ProtocolException e) {
			e.printStackTrace();
			return false;
		}
        connection.setDoOutput(true);
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setRequestProperty("Accept", "application/json");
        OutputStreamWriter osw = null;
		try {
			osw = new OutputStreamWriter(connection.getOutputStream());
		} catch (IOException e1) {
			e1.printStackTrace();
			return false;
		}
        try {
			osw.write(this.json);
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
        try {
			osw.flush();
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
        try {
			osw.close();
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
        
        return true;
        /*try {

			BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String line;
			while((line = reader.readLine()) != null) {
			    Core.debug(line);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
	}
	
}
