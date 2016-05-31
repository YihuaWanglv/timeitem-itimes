package com.iyihua.itimes.component.tools;

import com.google.gson.Gson;

public class singletonManager {

	private static final Gson gson = new Gson();
	
	public static Gson getSimpleGson() {
		return gson;
	}
}
