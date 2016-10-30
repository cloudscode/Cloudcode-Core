package com.cloudcode.report.util;

import java.util.ArrayList;
import java.util.List;

public class ReportLayoutInfo {
	private List<ReportCellInfo> cellList = new ArrayList<ReportCellInfo>();

	public List<ReportCellInfo> getCellList() {
		return cellList;
	}

	public void setCellList(List<ReportCellInfo> cellList) {
		this.cellList = cellList;
	}
	
}
