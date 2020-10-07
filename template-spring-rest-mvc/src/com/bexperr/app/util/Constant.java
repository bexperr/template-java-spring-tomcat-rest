package com.bexperr.app.util;

public enum Constant {
	
//	PROPERTIES(1, "com.bexperr.app.util.prod"),
	PROPERTIES(1, "com.bexperr.app.util.qa"),
	
	/**
	 * Credentials
	 */
	MD_USER(2,getProperties("ws.md.login.usuario")),
	MD_IP(3,getProperties("ws.md.login.ip")),
	MD_PASS(4,getProperties("ws.md.login.pass")),

	/**
	 * WS Services    
	 */ 
	REGISTERED_CARDS(5, getProperties("ws.endpoint.registeredcards")),
	GET_PRODUCT_DETAIL(6, getProperties("ws.endpoint.getproductdetail")),
	GET_DEFAULT_SHIPPING_ADDRESS(7, getProperties("ws.endpoint.getdefaultshippingadress"));
	
	
	private final int key;
	private final String phrase;
	
	Constant(int key, String phrase) {
		this.key = key;
		this.phrase = phrase;
	}
	
	public int key() {
		return this.key;
	}
	
	public String phrase() {
		return this.phrase;
	}
	
	public static String getProperties(String properti) {
		return new ResourceGenericBundle().getProperties(properti);
	}
  
}
