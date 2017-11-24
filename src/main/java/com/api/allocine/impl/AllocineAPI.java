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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.api.allocine.IAllocineAPI;
import com.api.allocine.decod.IDecoder;
import com.api.allocine.model.IChapter;
import com.api.allocine.model.IChapterResponse;
import com.api.allocine.model.IJsonResponse;
import com.api.allocine.model.IMovie;
import com.api.allocine.model.IMovieResponse;
import com.api.allocine.model.ISearchResponse;
import com.api.allocine.model.ISeason;
import com.api.allocine.model.ISeasonResponse;
import com.api.allocine.model.ISerie;
import com.api.allocine.model.ISerieResponse;

public class AllocineAPI implements IAllocineAPI {

	Logger logger = LoggerFactory.getLogger( AllocineAPI.class );
	
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
	public IJsonResponse httpQuery( ALLO_CINE_METHOD method, Map<ALLO_CINE_PARAMS, String> query ) throws UnsupportedEncodingException{
		
		String sed = sdf.format( new Date() );
		
		Map<ALLO_CINE_PARAMS, String> inputParams = new HashMap<ALLO_CINE_PARAMS, String>();
		
		inputParams.put( ALLO_CINE_PARAMS.PARTNER , PARTNER_KEY );
		inputParams.put( ALLO_CINE_PARAMS.SED , sed);
		
		for(ALLO_CINE_PARAMS param : query.keySet()){
			inputParams.put( param , query.get(param) );
		}
		
		String parsedParams = formatParametersToHTTP( inputParams );
		String signature;
		
		try {
			signature = URLEncoder.encode( new String(Base64.encodeBase64( DigestUtils.sha( PRIVATE_KEY + parsedParams ) ) ) , "UTF-8" );
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return null;
		}
		
		String urlQuery = API_URL + "/" + new String( method.toString() ).toLowerCase() + "?" + parsedParams + "&sig=" + signature ;
		
		logger.debug( "Query ALLOCINE : " + urlQuery );
		
		try {
			String response = query( urlQuery );
			logger.debug( "Allocine response : " + response );
			if( response != null && !"".equals(response)){
				return generateResponse(method, response);
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public ISearchResponse<IMovie> searchMovies( String search ) throws UnsupportedEncodingException{
		
		logger.debug( "Search movie " + search );
		
		Map<AllocineAPI.ALLO_CINE_PARAMS, String> params = new HashMap<AllocineAPI.ALLO_CINE_PARAMS, String>();
		
		params.put(ALLO_CINE_PARAMS.SEARCH, search );
		params.put(ALLO_CINE_PARAMS.FORMAT, String.valueOf( responseFormat ) );
		params.put(ALLO_CINE_PARAMS.FILTER, String.valueOf( FILTER.MOVIE ) );
		
		return (ISearchResponse<IMovie>) httpQuery( ALLO_CINE_METHOD.SEARCH , params );
	}

	@SuppressWarnings("unchecked")
	public ISearchResponse<ISerie> searchSeries( String search ) throws UnsupportedEncodingException {
logger.debug( "Search movie " + search );
		
		Map<AllocineAPI.ALLO_CINE_PARAMS, String> params = new HashMap<AllocineAPI.ALLO_CINE_PARAMS, String>();
		
		params.put(ALLO_CINE_PARAMS.SEARCH, search );
		params.put(ALLO_CINE_PARAMS.FORMAT, String.valueOf( responseFormat ) );
		params.put(ALLO_CINE_PARAMS.FILTER, String.valueOf( FILTER.SERIE ) );
		
		return (ISearchResponse<ISerie>) httpQuery( ALLO_CINE_METHOD.SEARCH , params );
	}
	
	@Override
	public IMovieResponse getMovieDetails(IMovie movie) throws UnsupportedEncodingException {
		
		logger.debug( "Search movie details of " + movie );
		
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
		String profile = params.get( ALLO_CINE_PARAMS.PROFILE );
		
		if( partner != null && !"".equals(partner)){
			httpQuery += "partner=" + partner;
		}
		
		if( profile != null && !"".equals(profile)){
			httpQuery += "&profile=" + profile;
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
		
		IJsonResponse response = null;
		
		if( method == ALLO_CINE_METHOD.SEARCH ){
			logger.debug( " Decode search response " );
			response = decoder.decodeSearchResponse( URLDecoder.decode( json , "UTF-8" ) );
		}
		else if( method == ALLO_CINE_METHOD.MOVIE ){
			logger.debug( " Decode movie response " + json);
			response = decoder.decodeMovieResponse( json );
		}
		else if( method == ALLO_CINE_METHOD.TVSERIES ){
			logger.debug( " Decode serie response " );
			response = decoder.decodeSerieResponse( json );
		}
		else if( method == ALLO_CINE_METHOD.SEASON ){
			logger.debug( "Decode season response" );
			response = decoder.decodeSeasonResponse(json);
		}
		else if( method == ALLO_CINE_METHOD.EPISODE ){
			logger.debug( "Decode episode response" );
			response = decoder.decodeChapterResponse(json);
		}else{
			logger.error("Unknown decoder for method {} " , method);
		}
		return response;
	}

	@Override
	public ISerieResponse getSerieDetails(ISerie serie) throws UnsupportedEncodingException {
		logger.debug( "Search serie details of " + serie );
		
		Map<AllocineAPI.ALLO_CINE_PARAMS, String> params = new HashMap<AllocineAPI.ALLO_CINE_PARAMS, String>();
		
		params.put(ALLO_CINE_PARAMS.CODE, String.valueOf(serie.getCode()) );
		params.put(ALLO_CINE_PARAMS.FORMAT, "json" );
		params.put(ALLO_CINE_PARAMS.PROFILE, "large" );
		
		return (ISerieResponse) httpQuery( ALLO_CINE_METHOD.TVSERIES , params );
		
	}

	@Override
	public ISeasonResponse getSeasonDetails(ISeason season) throws UnsupportedEncodingException {
		
		logger.debug( "Search season details of " + season );
		
		Map<AllocineAPI.ALLO_CINE_PARAMS, String> params = new HashMap<AllocineAPI.ALLO_CINE_PARAMS, String>();
		
		params.put(ALLO_CINE_PARAMS.CODE, String.valueOf(season.getCode()) );
		params.put(ALLO_CINE_PARAMS.FORMAT, "json" );
		params.put(ALLO_CINE_PARAMS.PROFILE, "large" );
		
		return (ISeasonResponse) httpQuery( ALLO_CINE_METHOD.SEASON , params );
		
	}

	@Override
	public IChapterResponse getChapterDetails(IChapter chapter) throws UnsupportedEncodingException {
		logger.debug( "Search chapter details of " + chapter );
		
		Map<AllocineAPI.ALLO_CINE_PARAMS, String> params = new HashMap<AllocineAPI.ALLO_CINE_PARAMS, String>();
		
		params.put(ALLO_CINE_PARAMS.CODE, String.valueOf(chapter.getCode()) );
		params.put(ALLO_CINE_PARAMS.FORMAT, "json" );
		params.put(ALLO_CINE_PARAMS.PROFILE, "medium" );
		
		return (IChapterResponse) httpQuery( ALLO_CINE_METHOD.EPISODE , params );
	}
	
}
