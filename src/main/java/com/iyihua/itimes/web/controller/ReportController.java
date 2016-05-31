package com.iyihua.itimes.web.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.iyihua.itimes.component.security.LoginSessionManager;
import com.iyihua.model.base.report.ReportData;
import com.iyihua.model.base.report.ReportQuery;
import com.iyihua.model.component.JsonObject;
import com.iyihua.model.component.report.ReportDataSet;
import com.iyihua.model.enums.GroupType;
import com.iyihua.remote.base.report.ReportRemote;

@RestController
@RequestMapping("/report")
public class ReportController {

	@Autowired
	private ReportRemote reportService;

	@Autowired
	private LoginSessionManager loginSessionManager;

	@RequestMapping(value = "/{type}", method = RequestMethod.GET)
	public JsonObject report(@PathVariable String type) {
		JsonObject json = new JsonObject();
		Long userId = loginSessionManager.getSessionUserId();
		List<ReportData> datas = reportService.reportSimple(new ReportQuery.Builder(GroupType.fromString(type).getId()).userId(userId).build());
		ReportDataSet dataset = new ReportDataSet();
		Set<String> labels = new LinkedHashSet<String>();
		Map<String, List<BigDecimal>> report = new HashMap<String, List<BigDecimal>>();
		List<BigDecimal> list = new ArrayList<BigDecimal>();
		for (ReportData reportData : datas) {
			labels.add(reportData.getTypeName());
			list.add(reportData.getDurations());
		}
		report.put(type, list);
		dataset.setLabels(labels);
		dataset.setDataset(report);
		json.setData(dataset);
		return json;
	}
	
	@RequestMapping(value = "/time/{type}", method = RequestMethod.GET)
	public JsonObject reportWithTime(@PathVariable String type) {
		JsonObject json = new JsonObject();
		Long userId = loginSessionManager.getSessionUserId();
		List<ReportData> datas = reportService.reportWithTime(new ReportQuery.Builder(GroupType.fromString(type).getId()).userId(userId).build());
		ReportDataSet dataset = new ReportDataSet();
		Set<String> labels = new LinkedHashSet<String>();
		Map<String, List<BigDecimal>> report = new HashMap<String, List<BigDecimal>>();
		for (ReportData rd : datas) {
			String date = rd.getReportdate();
			String name = rd.getTypeName();
			BigDecimal durations = rd.getDurations();
			labels.add(date);
			List<BigDecimal> list = null;
			if (report.get(name) == null) {
				list = new ArrayList<BigDecimal>();
			} else {
				list = report.get(name);
			}
			list.add(durations);
			report.put(name, list);
		}
		dataset.setLabels(labels);
		dataset.setDataset(report);
		json.setData(dataset);
		return json;
	}
	
}
