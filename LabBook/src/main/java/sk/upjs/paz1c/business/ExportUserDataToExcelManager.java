package sk.upjs.paz1c.business;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import sk.upjs.paz1c.entities.Task;
import sk.upjs.paz1c.entities.User;
import sk.upjs.paz1c.persistent.DAOfactory;
import sk.upjs.paz1c.persistent.UserDAO;

public class ExportUserDataToExcelManager {

	// https://www.callicoder.com/java-write-excel-file-apache-poi/

	private static String[] projectColumns = { "Name", "Active", "Start of the project", "End of the project",
			"Each item is available" };
	private static String[] taskColumns = { "Project", "Name", "Active", "Start of the project", "End of the project",
			"Each item is available", "Laboratory" };
	private static String[] noteColumns = { "Text", "Timestamp", "Project", "Task", "Item" };

	private static UserDAO userDAO = DAOfactory.INSTANCE.getUserDAO();

	public static void ExportUserData(User user) throws IOException {
		// Create a Workbook
		Workbook workbook = new XSSFWorkbook(); // new HSSFWorkbook() for generating `.xls` file

		/*
		 * CreationHelper helps us create instances of various things like DataFormat,
		 * Hyperlink, RichTextString etc, in a format (HSSF, XSSF) independent way
		 */
		CreationHelper createHelper = workbook.getCreationHelper();

		// Create a Sheet
		Sheet sheet = workbook.createSheet("Projects");
		// Create a Font for styling header cells
		Font headerFont = workbook.createFont();
		headerFont.setBold(true);
		headerFont.setFontHeightInPoints((short) 14);
		headerFont.setColor(IndexedColors.RED.getIndex());
		// Create a CellStyle with the font
		CellStyle headerCellStyle = workbook.createCellStyle();
		headerCellStyle.setFont(headerFont);
		// Create a Row
		Row headerRow = sheet.createRow(0);
		// Create cells
		for (int i = 0; i < projectColumns.length; i++) {
			Cell cell = headerRow.createCell(i);
			cell.setCellValue(projectColumns[i]);
			cell.setCellStyle(headerCellStyle);
		}
		// Create Cell Style for formatting Date
		CellStyle dateCellStyle = workbook.createCellStyle();
		dateCellStyle.setDataFormat(createHelper.createDataFormat().getFormat("dd-MM-yyyy"));

		sheet = workbook.createSheet("Tasks");

		headerFont.setColor(IndexedColors.BLUE.getIndex());

		headerRow = sheet.createRow(0);

		for (int i = 0; i < taskColumns.length; i++) {
			Cell cell = headerRow.createCell(i);
			cell.setCellValue(taskColumns[i]);
			cell.setCellStyle(headerCellStyle);
		}

		List<Task> tasks = userDAO.getTasks(user);
		int rowNum = 1;
		for (Task task : tasks) {
			Row row = sheet.createRow(rowNum++);

			row.createCell(0).setCellValue(task.getProject().getName());
			row.createCell(1).setCellValue(task.getName());
			row.createCell(2).setCellValue(task.isActive());
			Cell dateTimeFrom = row.createCell(3);
			if (task.getDateTimeFrom() != null) {
				dateTimeFrom.setCellValue(
						// https://stackoverflow.com/questions/22929237/convert-java-time-localdate-into-java-util-date-type
						Date.from(task.getDateTimeFrom().atStartOfDay(ZoneId.systemDefault()).toInstant()));
				dateTimeFrom.setCellStyle(dateCellStyle);
			} else
				row.createCell(3).setCellValue("Not defined");
			Cell dateTimeUntil = row.createCell(4);
			if (task.getDateTimeUntil() != null) {
				dateTimeUntil.setCellValue(
						Date.from(task.getDateTimeUntil().atStartOfDay(ZoneId.systemDefault()).toInstant()));
				dateTimeUntil.setCellStyle(dateCellStyle);
			} else
				row.createCell(4).setCellValue("Not defined");
			row.createCell(5).setCellValue(task.isEachItemAvailable());
			if (task.getLaboratory() != null)
				row.createCell(6).setCellValue(task.getLaboratory().getName());
			else
				row.createCell(6).setCellValue("Not defined");
		}

		sheet = workbook.createSheet("Notes");

		headerFont.setColor(IndexedColors.PINK.getIndex());

		headerRow = sheet.createRow(0);

		for (int i = 0; i < noteColumns.length; i++) {
			Cell cell = headerRow.createCell(i);
			cell.setCellValue(noteColumns[i]);
			cell.setCellStyle(headerCellStyle);
		}

		// Write the output to a file
		FileOutputStream fileOut = new FileOutputStream("userData.xlsx");
		workbook.write(fileOut);
		fileOut.close();

		// Closing the workbook
		workbook.close();

	}
	
	public static void main(String[] args) {
		
	}

}
