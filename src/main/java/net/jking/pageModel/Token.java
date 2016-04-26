package net.jking.pageModel;

import javax.servlet.http.HttpServletRequest;

import net.jking.util.GenericUtil;



public class Token {
	private String signature;
	private String timestamp;
	private String nonce;
	private String echostr;
	private static String Token="jkingweschool";
	
	
	
	public String getSignature() {
		return signature;
	}



	public String getTimestamp() {
		return timestamp;
	}



	public String getNonce() {
		return nonce;
	}



	public String getEchostr() {
		return echostr;
	}

	

	public static String getToken() {
		return Token;
	}



	public Token getToken(HttpServletRequest request){
		
		signature = request.getParameter("signature");
		timestamp = request.getParameter("timestamp");
		nonce = request.getParameter("nonce");
		echostr = request.getParameter("echostr");
		if(GenericUtil.isEmpty(signature)||GenericUtil.isEmpty(timestamp)||GenericUtil.isEmpty(nonce)||GenericUtil.isEmpty(echostr)){
			return null;
		}
		return this;
		
	}
	
	
	
}
