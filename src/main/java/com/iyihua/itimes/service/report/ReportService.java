package com.iyihua.itimes.service.report;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iyihua.itimes.mapper.report.ReportMapper;
import com.iyihua.model.base.report.ReportData;
import com.iyihua.model.base.report.ReportQuery;
import com.iyihua.remote.base.report.ReportRemote;

@Service
public class ReportService implements ReportRemote {

	@Autowired private ReportMapper reportMapper;

//	@Override
//	public List<CategoryReport> reportCategory() {
//		return reportMapper.reportCategory();
//	}
//
//	@Override
//	public List<CategoryTimeReport> reportCategoryTime() {
//		return reportMapper.reportCategoryTime();
//	}

	@Override
	public List<ReportData> reportSimple(ReportQuery query) {
		return reportMapper.reportSimple(query);
	}

	@Override
	public List<ReportData> reportWithTime(ReportQuery query) {
		return reportMapper.reportWithTime(query);
	}

}
