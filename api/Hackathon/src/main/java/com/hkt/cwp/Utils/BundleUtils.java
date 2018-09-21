/*
 * Copyright (C) 2018 Co-Well Asia. All rights reserved.
 * CO-WELL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.hkt.cwp.Utils;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * @author CaoTT
 */
public class BundleUtils {
	/* Define RSbundle and locate and set default*/
	private static Locale defaultLocale = Locale.JAPAN;
	private static ResourceBundle rsConfig;

	/*
	 * Get string language from properties files with 2 params
	 * 
	 * @params: key is key have define on properties files
	 * 
	 * @params: locate is current locate
	 */
	public static String getString(String key, Locale... locale) {
		try {
			if (locale != null) {
				if (locale.length == 0) {
					if (defaultLocale != null) {
						rsConfig = ResourceBundle.getBundle(Constants.LANGUAGE, defaultLocale);
					} else {
						rsConfig = ResourceBundle.getBundle(Constants.LANGUAGE,
								new Locale(getResourceDefault("defaultLanguage")));
					}
				} else {
					rsConfig = ResourceBundle.getBundle(Constants.LANGUAGE, locale[0]);
				}
			} else {
				rsConfig = ResourceBundle.getBundle(Constants.LANGUAGE,
						new Locale(getResourceDefault("defaultLanguage")));
			}

			return rsConfig.getString(key);
		} catch (Exception e) {
			return key;
		}
	}

	/*
	 * Get resource and set bundle locate default
	 */
	public static String getResourceDefault(String key) {
		try {
			rsConfig = ResourceBundle.getBundle("cas");
			String str = rsConfig.getString(key);
			if (DataUtils.isStringNullOrEmpty(str)) {
				return key;
			} else {
				return str;
			}
		} catch (Exception e) {
			return key;
		}
	}
	/* 
	 * Get string constant configurations
	*/
	public static String getConfigString(String key) {
		rsConfig = ResourceBundle.getBundle(Constants.BUSINESS_CONFIG);
		return rsConfig.getString(key);
	}
}