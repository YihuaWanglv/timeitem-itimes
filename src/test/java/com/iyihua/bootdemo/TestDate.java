//package com.iyihua.bootdemo;
//
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//
//import com.google.gson.Gson;
//import com.iyihua.itimes.model.user.UserConfig;
//import com.iyihua.model.base.report.ReportConfig;
//import com.iyihua.model.base.user.UserConfigObject;
//
//public class TestDate {
//
//	public static void main(String[] args) {
//		// Date date = new Date(2016, 5, 5);
//		// System.out.println(date.getTime());
//
////		UserConfigObject config = new UserConfigObject();
////
////		List<ReportConfig> reportConfigs = new ArrayList<ReportConfig>();
////		reportConfigs.add(new ReportConfig.Builder("category").enabled(1).type("bar").title("每个分类所用时间").build());
////		reportConfigs.add(
////				new ReportConfig.Builder("categoryTime").enabled(1).type("line").muti(1).title("各个分类在每月份的时间").build());
////		reportConfigs.add(new ReportConfig.Builder("project").enabled(1).type("bar").title("各个项目所用时间").build());
////		reportConfigs.add(
////				new ReportConfig.Builder("projectTime").enabled(1).type("line").muti(1).title("各个项目在每个月份所用时间").build());
////		reportConfigs.add(new ReportConfig.Builder("location").enabled(1).type("bar").title("各个地点所用时间").build());
////		reportConfigs.add(new ReportConfig.Builder("locationTime").enabled(1).type("line").muti(1).title("各个地点在每个月份所用时间").build());
////		config.setReportConfigs(reportConfigs);
////		
////		Gson gson = new Gson();
////		String json = gson.toJson(config);
////		System.err.println(json);
////		
//////		String jsonString = "{'reportConfigs':[{'key':'category','enabled':1,'type':'bar','muti':0,'title':'每个分类所用时间'},{'key':'categoryTime','enabled':1,'type':'line','muti':1,'title':'各个分类在每月份的时间'},{'key':'project','enabled':1,'type':'bar','muti':0,'title':'各个项目所用时间'},{'key':'projectTime','enabled':1,'type':'line','muti':1,'title':'各个项目在每个月份所用时间'},{'key':'location','enabled':1,'type':'bar','muti':0,'title':'各个地点所用时间'},{'key':'locationTime','enabled':0,'type':'bar','muti':0}]}";
////		UserConfigObject newObj = gson.fromJson(json, UserConfigObject.class);
////		System.out.println("---------");
////		System.out.println(gson.toJson(newObj));
//		
////		String rex = "*/item/*";
//		String rex = "[\\s\\S]+/item/[\\s\\S]+";
//		System.out.println("http://timeitem.com:8080/item/index.html".matches(rex));
//
//	}
//}
