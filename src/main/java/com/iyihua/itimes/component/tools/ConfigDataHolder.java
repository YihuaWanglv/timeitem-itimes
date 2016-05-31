package com.iyihua.itimes.component.tools;

import java.util.HashSet;
import java.util.Set;

public class ConfigDataHolder {

//	private static Set<String> ignorePathSet = new ConcurrentHashSet<String>();
	private static Set<String> ignorePathSet = new HashSet<String>();

	static {
		ignorePathSet.add("/item[\\s\\S]+");
		ignorePathSet.add("/view[\\s\\S]+");
		ignorePathSet.add("/report[\\s\\S]+");
		ignorePathSet.add("/data/user/confi[\\s\\S]+");
		ignorePathSet.add("/");
		ignorePathSet.add("/login");
	}
	
	public static Set<String> getDigitalSignaturesIgnorePaths() {
		return ignorePathSet;
	}
	
	public static void setDigitalSignaturesIgnorePaths(Set<String> ignoreSet) {
		synchronized (ignorePathSet) {
			ignorePathSet = ignoreSet;			
		}
	}
	
	public static void addDigitalSignaturesIgnorePath(String path) {
		ignorePathSet.add(path);
	}
}
