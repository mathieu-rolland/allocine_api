package com.mediatheque.launcher;

import java.util.HashMap;
import java.util.Map;

import com.api.allocine.IAllocineAPI;
import com.api.allocine.IAllocineAPI.ALLO_CINE_METHOD;
import com.api.allocine.IAllocineAPI.ALLO_CINE_PARAMS;
import com.api.allocine.factory.IFactory;
import com.api.allocine.factory.impl.AllocineFactory;
import com.api.allocine.impl.AllocineAPI;

public class Launcher {

	public static void main(String[] args) {
		
		IFactory factory = new AllocineFactory();
		IAllocineAPI api = factory.createSimpleAllocineAPI();
		
		Map<AllocineAPI.ALLO_CINE_PARAMS, String> params = new HashMap<AllocineAPI.ALLO_CINE_PARAMS, String>();
		
		params.put(ALLO_CINE_PARAMS.SEARCH, "potter");
		params.put(ALLO_CINE_PARAMS.FORMAT, "json" );
		params.put(ALLO_CINE_PARAMS.FILTER, "movie");
		
		
		api.httpQuery( ALLO_CINE_METHOD.SEARCH , params );
		
		params = new HashMap<AllocineAPI.ALLO_CINE_PARAMS, String>();
		
		params.put(ALLO_CINE_PARAMS.CODE, "58608");
		params.put(ALLO_CINE_PARAMS.FORMAT, "json" );
		params.put(ALLO_CINE_PARAMS.FILTER, "movie");
		
		api.httpQuery( ALLO_CINE_METHOD.MOVIE , params );
		
	}
	
}
