package com.bexperr.app.util;

import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class ResourceGenericBundle {
	
	private static final Logger log = LogManager.getLogger(ResourceGenericBundle.class);
	
	public String getProperties(String sproperties) {
		ResourceBundle bundle = null;
		try {
			bundle = ResourceBundle.getBundle(Constant.PROPERTIES.phrase(),Locale.getDefault());
		} catch (MissingResourceException e) {
			bundle = null;
		}
		
		String cadena = "";
		if(bundle != null) {
			try {
				cadena = bundle.getString(sproperties);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return cadena;
		}
		return null;
	}

}
