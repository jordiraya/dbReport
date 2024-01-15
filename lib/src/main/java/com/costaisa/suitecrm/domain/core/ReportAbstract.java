package com.costaisa.suitecrm.domain.core;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.costaisa.suitecrm.domain.port.ReportDAO;
import com.costaisa.suitecrm.domain.to.AssignedUserInfoTO;
import com.costaisa.suitecrm.domain.to.ListValueTO;
import com.costaisa.suitecrm.domain.to.TableFieldInfoTO;
import com.costaisa.suitecrm.infraestructure.adapter.ReportDAOImpl;

public abstract class ReportAbstract {

	private String reportName;	
	private String tableName;	
	private String tableCustomName;
	private Map<String, String> fields;	
	private Map<String, String> fieldsList;	
	
	private static final DecimalFormat decimalFormatInt = new DecimalFormat("#,###");
	
	private ReportDAO reportDAO = new ReportDAOImpl();
	
	public ReportAbstract(String reportName, String tableName, String tableCustomName, Map<String, String> fields, Map<String, String> fieldsList) {
		this.reportName = reportName;
		this.tableName = tableName;
		this.tableCustomName = tableCustomName;
		this.fields = fields;
		this.fieldsList = fieldsList;
	}
	
	public List<TableFieldInfoTO> getTableInfo() {
		List<TableFieldInfoTO> tableFieldInfos = new ArrayList<>();
		
		Set<String> fieldNames = fields.keySet();
		
		int totalNumberOfRows = reportDAO.getNumberOfRows(tableName, tableCustomName);
		// System.out.println("totalNumberOfRows: " + totalNumberOfRows);
		
		fieldNames.forEach(fieldName -> {			
			String fieldNameInRow = fields.get(fieldName);
			Integer rows;
			if (fieldName.endsWith("(S/N)")) {
				rows = reportDAO.getNumberOfRowsBoolean(tableName, tableCustomName, fieldNameInRow);
			} else if (fieldName.endsWith("(num)")) {
				rows = reportDAO.getNumberOfRowsNumeric(tableName, tableCustomName, fieldNameInRow);
			} else {
				rows = reportDAO.getNumberOfRows(tableName, tableCustomName, fieldNameInRow);
			}
			Integer rowsEmpty = totalNumberOfRows - rows;
			Double percentageRows = Math.round( (rows * 100.0 / totalNumberOfRows) * 100.0) / 100.0;
			Double percentageRowsEmpty = Math.round( (100.0 - percentageRows) * 100.00) / 100.00 ;
			
			TableFieldInfoTO to = new TableFieldInfoTO();
			to.setField(fieldName);
			to.setFieldNameInTable(fieldNameInRow);
			to.setRows(rows);
			to.setRowsEmpty(rowsEmpty);
			to.setPercentageRows(percentageRows);
			to.setPercentageRowsEmpty(percentageRowsEmpty);
			tableFieldInfos.add(to);
		});
		
		return tableFieldInfos;
	}
	
	public List<List<ListValueTO>> getListsInfo() {		
		List<List<ListValueTO>> listsListValue = new ArrayList<>();
		Set<String> fieldNames = fieldsList.keySet();
		
		fieldNames.forEach(fieldName -> {
			List<ListValueTO> listValue = reportDAO.getList(tableName, tableCustomName, fieldsList.get(fieldName));
			// pw.append("nombre del campo;nº total de filas\n");
			listsListValue.add(listValue);
		});
		return listsListValue;
	}
	
	public void printCSV(String pathFile) {
		File csvOutputFile = new File(pathFile);
		System.out.println("printing to " + pathFile);
		
		// LocalDate dateObj = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String dateStr = LocalDate.now().format(formatter);
		
		try (PrintWriter pw = new PrintWriter(csvOutputFile)) {
			pw.append("Auditoría de " + reportName + "\n");
			pw.append("Fecha de extracción;" + dateStr + "\n");
			pw.append("Host;" + reportDAO.getHostName() + "\n");
			pw.append("\n");
			
			pw.append("Concepto;Nº de filas;% filas llenas;Nº filas vacias; % filas vacias\n");				
			int totalNumberOfRows = reportDAO.getNumberOfRows(tableName, tableCustomName);			
			pw.append("Nº de filas totales;" + decimalFormatInt.format(totalNumberOfRows) + ";100,00 %;\n");				
			List<TableFieldInfoTO> tableFieldsInfo = getTableInfo();
			for(TableFieldInfoTO tableFieldInfo : tableFieldsInfo) {
				pw.append(tableFieldInfo.toCSV());
			}
			
			pw.append("\n");
			pw.append("\n");
						
			List<AssignedUserInfoTO> assignedUsersInfo = reportDAO.getAssignedUsers(tableName, tableCustomName);
			pw.append("Nombre usuario;Apellidos Usuario;Nº total de filas\n");
			for (AssignedUserInfoTO assignedUserInfo : assignedUsersInfo) {
				pw.append(assignedUserInfo.toCSV());
			}
			
			pw.append("\n");
			pw.append("\n");
			
			List<List<ListValueTO>> listsListValue = getListsInfo();
			int contLists = 0;
			Set<String> fieldNames = fieldsList.keySet();
			List<String> listNames = new ArrayList<>();
			listNames.addAll(fieldNames);

			for (List<ListValueTO> listValues : listsListValue) {
				String listName = listNames.get(contLists);
				pw.append(listName + ";Nº total de filas\n");
				for (ListValueTO listValue : listValues) {
					pw.append(listValue.toCSV());
				}
				pw.append("\n");
				contLists ++;
			}
			
		} catch (FileNotFoundException ex) {
			ex.printStackTrace();
		}
		
		System.out.println("end printing");
	}
}
