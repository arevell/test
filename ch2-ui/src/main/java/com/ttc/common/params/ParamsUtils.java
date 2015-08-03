package com.ttc.common.params;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.NoSuchPaddingException;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.UnhandledException;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

import com.google.common.base.Preconditions;
import com.google.common.collect.Maps;



public class ParamsUtils 
{
	private ParamCoder coder;
	private Map<String,Object> params=Maps.newHashMap();
	
	private ParamsUtils() {
		//coder=new BlowfishCoder();
		coder=new BlowfishCoderOriginal();
	}
	
	private ParamsUtils(HttpServletRequest request) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException {
		//coder=new BlowfishCoder(request);
		coder=new BlowfishCoderOriginal();
	}
	
	public ParamsUtils addParam(String key,Object value)
	{
		params.put(key, value);
		return this;
	}
	
	public String encodeAllParams()
	{
		return encodeAllParams(params);
	}
	
	public static ParamsUtils getInstance(HttpServletRequest request) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException
	{
		return new ParamsUtils(request);
	}
	
	public static ParamsUtils getInstance()
	{
		return new ParamsUtils();
	}
	
	public static String encodeOneParam(String key,Object value)
	{
		return ParamsUtils.getInstance().addParam(key, value).encodeAllParams();
	}
	
	
	public Map<String,String> decodeAllParam(String encrypted)
	{				
		Preconditions.checkArgument(StringUtils.isNotBlank(encrypted),"Encrypted param is empty");
		
		Map<String,String> params=null;		
		String decodeParam=coder.decode(encrypted);
		ObjectMapper mapper = new ObjectMapper();
		try {
			params = mapper.readValue(decodeParam, new TypeReference<HashMap<String, String>>() {
			});

		} catch (Exception e) {
			throw new UnhandledException(e);
		}	
		
		return params;		
	}
	
	public String encodeAllParams(Map<String,Object> params)
	{		
		ObjectMapper mapper = new ObjectMapper();
		String json = ""; 
		//convert map to JSON string
		try {
			json = mapper.writeValueAsString(params);
		} catch (JsonGenerationException e) {
			throw new UnhandledException(e);
		} catch (JsonMappingException e) {
			throw new UnhandledException(e);
		} catch (IOException e) {
			throw new UnhandledException(e);
		}
		return coder.encode(json);		
	}
}
