package com.api.allocine.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;

import com.api.allocine.IAllocineAPI;
import com.api.allocine.decod.IDecoder;
import com.api.allocine.model.IJsonResponse;
import com.api.allocine.model.IMovie;
import com.api.allocine.model.IMovieResponse;
import com.api.allocine.model.ISearchResponse;

public class AllocineAPI implements IAllocineAPI {

	private static final String PARTNER_KEY = "100043982026";
	private static final String PRIVATE_KEY = "29d185d98c984a359e6e6f26a0474269";
	
	private static final String API_URL = "http://api.allocine.fr/rest/v3";
	
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
	
	private IDecoder decoder;
	private RESPONSE_FORMAT responseFormat;
	
	public AllocineAPI( IDecoder decoder, RESPONSE_FORMAT format ){
		this.decoder = decoder;
		this.responseFormat = format;
	}
	
	@Override
	public IJsonResponse httpQuery( ALLO_CINE_METHOD method, Map<ALLO_CINE_PARAMS, String> params ) throws UnsupportedEncodingException{
		
		String sed = sdf.format( new Date() );
		
		params.put( ALLO_CINE_PARAMS.PARTNER , PARTNER_KEY );
		params.put( ALLO_CINE_PARAMS.SED , sed);
		
		String parsedParams = formatParametersToHTTP(params);
		String signature;
		
		try {
			signature = URLEncoder.encode( new String(Base64.encodeBase64( DigestUtils.sha( PRIVATE_KEY + parsedParams ) ) ) , "UTF-8" );
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return null;
		}
		
		String query = API_URL + "/" + new String( method.toString() ).toLowerCase() + "?" + parsedParams + "&sig=" + signature ;
		
		System.out.println(query);
		
		try {
			String response = query( query );
			
			if( response != null && !"".equals(response)){
				return generateResponse(method, response);
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	@Override
	public ISearchResponse searchMovies( String search ) throws UnsupportedEncodingException{
		
		Map<AllocineAPI.ALLO_CINE_PARAMS, String> params = new HashMap<AllocineAPI.ALLO_CINE_PARAMS, String>();
		
		params.put(ALLO_CINE_PARAMS.SEARCH, search );
		params.put(ALLO_CINE_PARAMS.FORMAT, String.valueOf( responseFormat ) );
		params.put(ALLO_CINE_PARAMS.FILTER, String.valueOf( FILTER.MOVIE ) );
		
		return (ISearchResponse) httpQuery( ALLO_CINE_METHOD.SEARCH , params );
	}

	@Override
	public IMovieResponse getMovieDetails(IMovie movie) throws UnsupportedEncodingException {
		
		Map<AllocineAPI.ALLO_CINE_PARAMS, String> params = new HashMap<AllocineAPI.ALLO_CINE_PARAMS, String>();
		
		params.put(ALLO_CINE_PARAMS.CODE, String.valueOf(movie.getCode()) );
		params.put(ALLO_CINE_PARAMS.FORMAT, "json" );
		params.put(ALLO_CINE_PARAMS.FILTER, "movie");
		
		return (IMovieResponse) httpQuery( ALLO_CINE_METHOD.MOVIE , params );
	}
	
	private String formatParametersToHTTP ( Map<ALLO_CINE_PARAMS, String> params ) throws UnsupportedEncodingException{
		String httpQuery = "";
		
		String q = params.get(ALLO_CINE_PARAMS.SEARCH);
		String filter = params.get(ALLO_CINE_PARAMS.FILTER);
		String format = params.get(ALLO_CINE_PARAMS.FORMAT);
		String partner = params.get(ALLO_CINE_PARAMS.PARTNER);
		String sed = params.get(ALLO_CINE_PARAMS.SED);
		String code = params.get(ALLO_CINE_PARAMS.CODE);
		
		if( partner != null && !"".equals(partner)){
			httpQuery += "partner=" + partner;
		}
		
		if( q != null && !"".equals(q) ){
			httpQuery += "&q=" + URLEncoder.encode( q , "UTF-8");
		}
		
		if( code != null && !"".equals(code)){
			httpQuery += "&code=" + code;
		}
		
		if( format != null && !"".equals(format)){
			httpQuery += "&format=" + format; 
		}
		
		if( filter != null && !"".equals(filter)){
			httpQuery += "&filter=" + filter;
		}
		
		
		if( sed != null && !"".equals(sed)){
			httpQuery += "&sed=" + sed;
		}
		
		return httpQuery;
	}
	
	private String query(String query) throws MalformedURLException, IOException{
		
		URL url = new URL( query );
		InputStream in = url.openStream();
		
		BufferedReader br = new BufferedReader( new InputStreamReader(in) );
		String response = "";
		
		while( br.ready() ){
			response += br.readLine();
		}
		
		br.close();
		
		return response;
	}
	
	private IJsonResponse generateResponse( ALLO_CINE_METHOD method , String json ) throws UnsupportedEncodingException{
		if( method == ALLO_CINE_METHOD.SEARCH )	return decoder.decodeSearchResponse( URLDecoder.decode( json , "UTF-8" ) );
		if( method == ALLO_CINE_METHOD.MOVIE )  return decoder.decodeMovieResponse( json );
		return null;
	}
	
}
