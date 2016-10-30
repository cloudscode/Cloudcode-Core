package com.cloudcode.report.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.util.CellRangeAddress;

public class ExcelUtil {
	private static HSSFWorkbook wb;
	private static CellStyle titleStyle;
	private static Font titleFont;
	private static CellStyle dateStyle;
	private static Font dateFont;
	private static CellStyle headStyle;
	private static Font headFont;
	private static CellStyle contentStyle;
	private static Font contentFont;

	public static void main(String[] args) {
		try {
			ResultInfo resultInfo = new ResultInfo();
			ReportLayoutInfo reportLayoutInfo = new ReportLayoutInfo();

			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			resultInfo.setOutStream(baos);
			ExcelUtil.export2Excel(reportLayoutInfo, resultInfo);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void export2Excel(ReportLayoutInfo reportLayoutInfo, ResultInfo resultInfo)
			throws IOException, IllegalArgumentException, IllegalAccessException {
		init();

		Map<String, Object> exCellMap = new HashMap<String, Object>();

		String[] sheetNames = new String[1];
		sheetNames[0] = "导出数据";
		HSSFSheet[] sheets = getSheets(sheetNames.length, sheetNames);
		HSSFSheet sheet = sheets[0];
		List<ReportCellInfo> reportCellList = reportLayoutInfo.getCellList();

		List<List<ReportCellInfo>> reportCellInfoList = new ArrayList();

		List<ReportCellInfo> tempReportCellInfo = new ArrayList();
		int r = 1;
		// for (ReportCellInfo reportCellInfo : reportCellList) {
		// if (reportCellInfo.getRelaId() != -1) {
		// if (reportCellInfo.getR() == r)
		// {
		// tempReportCellInfo.add(reportCellInfo);
		// }
		// else
		// {
		// reportCellInfoList.add(tempReportCellInfo);
		// tempReportCellInfo = new ArrayList();
		// tempReportCellInfo.add(reportCellInfo);
		// r++;
		// }
		// }
		// }
		reportCellInfoList.add(tempReportCellInfo);
		List<List<ReportCellInfo>> reportCellInfoList2 = new ArrayList();
		for (int i = 0; i < reportCellInfoList.size(); i++) {
			List<ReportCellInfo> reportCellInfos = (List) reportCellInfoList.get(i);
			List<ReportCellInfo> reportCellInfos2 = new ArrayList();
			int k = 0;
			String exCell;
			for (ReportCellInfo reportCellInfo : reportCellInfos) {
				exCell = (String) exCellMap.get(i + "_" + k);
				if ((exCell != null) && (!"".equals(exCell))) {
					int exCellint = Integer.valueOf(exCell).intValue();
					for (int j = 0; j < exCellint; j++) {
						reportCellInfos2.add(new ReportCellInfo());
					}
				}
				reportCellInfos2.add(reportCellInfo);
				int rs = reportCellInfo.getRs() == 0 ? 1 : reportCellInfo.getRs();
				int cs = reportCellInfo.getCs() == 0 ? 1 : reportCellInfo.getCs();
				if (cs > 1) {
					for (int j = 1; j < cs; j++) {
						reportCellInfos2.add(new ReportCellInfo());
					}
				}
				if (rs > 1) {
					for (int j = 1; j < rs; j++) {
						exCellMap.put(i + j + "_" + k, cs);
					}
				}
				k++;
			}
			boolean as = false;
			// for (ReportCellInfo reportCellInfo : reportCellInfos2)
			// {
			// String styleInfo = reportCellInfo.getStyleInfo();
			// if (styleInfo == null) {
			// styleInfo = "";
			// }
			// if (styleInfo.indexOf("display:none") == -1)
			// {
			// as = true;
			// break;
			// }
			// }
			if (as) {
				reportCellInfoList2.add(reportCellInfos2);
			}
		}
		reportCellInfoList = reportCellInfoList2;
		for (int i = 0; i < reportCellInfoList.size(); i++) {
			List<ReportCellInfo> reportCellInfos = (List) reportCellInfoList.get(i);
			HSSFRow contentRow = sheet.createRow(i);

			contentRow.setHeight((short) 300);

			HSSFCell[] cells = new HSSFCell[reportCellInfos.size()];
			for (int j = 0; j < cells.length; j++) {
				cells[j] = contentRow.createCell(j);
				cells[j].setCellStyle(contentStyle);
			}
			for (int j = 0; j < reportCellInfos.size(); j++) {
				ReportCellInfo reportCellInfo = (ReportCellInfo) reportCellInfos.get(j);
				HSSFCell hssfCell = cells[j];
				int rs = reportCellInfo.getRs() == 0 ? 1 : reportCellInfo.getRs();
				int cs = reportCellInfo.getCs() == 0 ? 1 : reportCellInfo.getCs();
				if (rs > 1) {
					sheet.addMergedRegion(new CellRangeAddress(i, i + rs - 1, j, j));
				}
				if (cs > 1) {
					sheet.addMergedRegion(new CellRangeAddress(i, i, j, j + cs - 1));
				}
			}
		}
		// String styleInfo = reportCellInfo.getStyleInfo();
		// if (styleInfo == null) {
		// styleInfo = "";
		// }
		// if ((!"hidden".equals(reportCellInfo.getWtype())) &&
		// (styleInfo.indexOf("display:none") == -1)) {
		// if ("asyncFile".equals(reportCellInfo.getWtype())) {
		// Map<String, String> map =
		// reportCellInfo.getCellData().getOperandValue();
		// String childModule = "";
		// String module = "";
		// String parentPid = "";
		// String catalogId = "";
		// if (map.get("childModule") != null) {
		// childModule = (String) map.get("childModule");
		// }
		// if (map.get("module") != null) {
		// module = (String) map.get("module");
		// }
		// if (map.get("parentPid") != null) {
		// parentPid = (String) map.get("parentPid");
		// }
		// if (map.get("catalogId") != null) {
		// catalogId = (String) map.get("catalogId");
		// }
		// // AttachmentCondition condition = new
		// // AttachmentCondition();
		// // condition.setPid(catalogId);
		// // condition.setChildModule(childModule);
		// // condition.setParentPid(parentPid);
		// // condition.setIsActive(1);
		// // List<Attachment> list =
		// // AsyncFileUpload.getAttachmentManager().list(condition);
		// // StringBuffer attrName = new StringBuffer();
		// // for (Attachment attachment : list) {
		// // attrName.append(attachment.getName() + ",");
		// // }
		// // if (StringUtil.isNotNull(attrName.toString())) {
		// // hssfCell.setCellValue(attrName.substring(0,
		// // attrName.length() - 1));
		// // }
		// } else {
		// hssfCell.setCellValue(reportCellInfo.getDisplayValue());
		// }
		// }
		// }
		// }
		wb.write(resultInfo.getOutStream());
	}

	private static void init() {
		wb = new HSSFWorkbook();

		titleFont = wb.createFont();
		titleStyle = wb.createCellStyle();
		dateStyle = wb.createCellStyle();
		dateFont = wb.createFont();
		headStyle = wb.createCellStyle();
		headFont = wb.createFont();
		contentStyle = wb.createCellStyle();
		contentFont = wb.createFont();

		initTitleCellStyle();
		initTitleFont();
		initDateCellStyle();
		initDateFont();
		initHeadCellStyle();
		initHeadFont();
		initContentCellStyle();
		initContentFont();
	}

	private static void adjustColumnSize(HSSFSheet[] sheets, int sheetNum, String[] fieldNames) {
		for (int i = 0; i < fieldNames.length + 1; i++) {
			sheets[sheetNum].autoSizeColumn(i, true);
		}
	}

	private static HSSFSheet[] getSheets(int num, String[] names) {
		HSSFSheet[] sheets = new HSSFSheet[num];
		for (int i = 0; i < num; i++) {
			if (names[i] != null) {
				sheets[i] = wb.createSheet(names[i]);
				sheets[i].setDefaultColumnWidth(20);
			}
		}
		return sheets;
	}

	private static HSSFCell[] getCells(HSSFRow contentRow, int num) {
		HSSFCell[] cells = new HSSFCell[num];
		for (int i = 0; i < cells.length; i++) {
			cells[i] = contentRow.createCell(i);
			cells[i].setCellStyle(contentStyle);
		}
		return cells;
	}

	private static void initTitleCellStyle() {
		titleStyle.setAlignment((short) 2);
		titleStyle.setVerticalAlignment((short) 1);
		titleStyle.setFont(titleFont);
		titleStyle.setFillBackgroundColor(IndexedColors.SKY_BLUE.getIndex());
	}

	private static void initDateCellStyle() {
		dateStyle.setAlignment((short) 6);
		dateStyle.setVerticalAlignment((short) 1);
		dateStyle.setFont(dateFont);
		dateStyle.setFillBackgroundColor(IndexedColors.SKY_BLUE.getIndex());
	}

	private static void initHeadCellStyle() {
		headStyle.setAlignment((short) 2);
		headStyle.setVerticalAlignment((short) 1);
		headStyle.setFont(headFont);
		headStyle.setFillBackgroundColor(IndexedColors.YELLOW.getIndex());
		headStyle.setBorderTop((short) 2);
		headStyle.setBorderBottom((short) 1);
		headStyle.setBorderLeft((short) 1);
		headStyle.setBorderRight((short) 1);
	}

	private static void initContentCellStyle() {
		contentStyle.setAlignment((short) 2);
		contentStyle.setVerticalAlignment((short) 1);
		contentStyle.setFont(contentFont);
		contentStyle.setBorderTop((short) 1);
		contentStyle.setBorderBottom((short) 1);
		contentStyle.setBorderLeft((short) 1);
		contentStyle.setBorderRight((short) 1);

		contentStyle.setWrapText(true);
	}

	private static void initTitleFont() {
		titleFont.setFontName("华文楷体");
		titleFont.setFontHeightInPoints((short) 20);
		titleFont.setBoldweight((short) 700);
		titleFont.setCharSet((byte) 1);
	}

	private static void initDateFont() {
		dateFont.setFontName("隶书");
		dateFont.setFontHeightInPoints((short) 10);
		dateFont.setBoldweight((short) 700);
		dateFont.setCharSet((byte) 1);
	}

	private static void initHeadFont() {
		headFont.setFontName("宋体");
		headFont.setFontHeightInPoints((short) 10);
		headFont.setBoldweight((short) 700);
		headFont.setCharSet((byte) 1);
	}

	private static void initContentFont() {
		contentFont.setFontName("宋体");
		contentFont.setFontHeightInPoints((short) 10);
		contentFont.setBoldweight((short) 400);
		contentFont.setCharSet((byte) 1);
	}
}
