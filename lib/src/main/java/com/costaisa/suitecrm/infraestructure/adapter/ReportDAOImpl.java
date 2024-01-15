package com.costaisa.suitecrm.infraestructure.adapter;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.costaisa.suitecrm.domain.port.ReportDAO;
import com.costaisa.suitecrm.domain.to.AssignedUserInfoTO;
import com.costaisa.suitecrm.domain.to.ListValueTO;

public class ReportDAOImpl implements ReportDAO {

	private String host;
	private String port;
	private String database;
	private String user;
	private String password;
	
	private String urlConnection;
	
	public ReportDAOImpl() {
		init();
	}
	
	private void init() {
		this.host = "noooo.costaisa.org"; // NO NO NO
		this.port = "3306";
		this.database = "suitecrm";
		this.user = "root";
		this.password = "***"; // NO NO NO
		
		this.urlConnection = "jdbc:mysql://" + host + ":" + port + "/" + database + "?useSSL=false&requireSSL=false";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		}
	}
	
	private int getCount(String query) {
		int records = 0;
		try (
			Connection connection = DriverManager.getConnection(urlConnection, user, password);	
			PreparedStatement preparedStatements = connection.prepareStatement(query);
			ResultSet resultSet = preparedStatements.executeQuery();
		) { 
			while (resultSet.next()) {
		        records = resultSet.getInt(1);
		    }
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		System.out.println(query + " -> " + records + " rows");
		return records;		
	}
	
	@Override
	public String getHostName() {
		return this.host;
	}
	
	@Override
	public int getNumberOfRows(String table, String tableCstm) {
	    return getNumberOfRows(table, tableCstm, null);
	}
	
	@Override
	public int getNumberOfRows(String table, String tableCstm, String field) {
		String query;
		if (field != null) {
			// for char, varchar, date fields
			query = "SELECT COUNT(*) FROM " + table + " AS t LEFT JOIN "+ tableCstm + " AS tc ON t.id = tc.id_c WHERE " + field + " IS NOT NULL AND " + field + " <>'' AND t.deleted = 0";
		} else {
			// to get total row count
			query = "SELECT COUNT(*) FROM " + table + " AS t LEFT JOIN "+ tableCstm + " AS tc ON t.id = tc.id_c WHERE t.deleted = 0";
		}
		return getCount(query);		
	}	
	
	@Override
	public int getNumberOfRowsBoolean(String table, String tableCstm, String field) {
		// for tinyint fields, 1 = true, default 0 
		String query= "SELECT COUNT(*) FROM " + table + " AS t LEFT JOIN "+ tableCstm + " AS tc ON t.id = tc.id_c WHERE " + field + " IS NOT NULL AND " + field + " = 1 AND t.deleted = 0";
		return getCount(query);
	}	
	
	@Override
	public int getNumberOfRowsNumeric(String table, String tableCstm, String field) {
		// for numeric fields with default 0
		String query= "SELECT COUNT(*) FROM " + table + " AS t LEFT JOIN "+ tableCstm + " AS tc ON t.id = tc.id_c WHERE " + field + " IS NOT NULL AND " + field + " > 0 AND t.deleted = 0";
		return getCount(query);
	}
	
	@Override
	public ArrayList<ListValueTO> getList(String table, String tableCstm, String field) {
		ArrayList<ListValueTO> listValuesTO = new ArrayList<>();
		String query= "SELECT DISTINCT(" + field + ") AS val, COUNT(" + field + ") AS cont FROM " + table + " AS t LEFT JOIN " + tableCstm + " AS tc ON t.id = tc.id_c WHERE t.deleted = 0 GROUP BY val HAVING cont > 0 ORDER BY cont DESC";
		int total = 0;
		
		try (
				Connection connection = DriverManager.getConnection(urlConnection, user, password);	
				PreparedStatement preparedStatements = connection.prepareStatement(query);
				ResultSet resultSet = preparedStatements.executeQuery();
		) { 
			while (resultSet.next()) {				
		        String value = resultSet.getString(1);
		        Integer count = resultSet.getInt(2);
		        listValuesTO.add(new ListValueTO(value, count));
		        total += count;
		    }
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		listValuesTO.add(new ListValueTO("TOTAL", total));
		
		System.out.println(query + " -> " + total + " total");
		return listValuesTO;	
	}
	
	@Override
	public ArrayList<AssignedUserInfoTO> getAssignedUsers(String table, String tableCstm) {
		ArrayList<AssignedUserInfoTO> listUsersTO = new ArrayList<>();
		String query = "SELECT DISTINCT(assigned_user_id) AS val, u.first_name AS nombre, u.last_name AS apellidos, COUNT(assigned_user_id) AS cont "
		+ "FROM " + table + " AS t LEFT JOIN  " + tableCstm + " AS tc ON t.id = tc.id_c "
		+ "LEFT JOIN users AS u ON t.assigned_user_id = u.id "
		+ "WHERE t.deleted = 0 GROUP BY val HAVING cont > 0 ORDER BY cont DESC";
		int total = 0;
		
		try (
				Connection connection = DriverManager.getConnection(urlConnection, user, password);	
				PreparedStatement preparedStatements = connection.prepareStatement(query);
				ResultSet resultSet = preparedStatements.executeQuery();
		) { 
			while (resultSet.next()) {				
		        String nombre = resultSet.getString(2);
		        String apellidos = resultSet.getString(3);
		        Integer count = resultSet.getInt(4);
		        listUsersTO.add(new AssignedUserInfoTO(nombre, apellidos, count));
		        total += count;
		    }
		} catch (SQLException ex) {
			ex.printStackTrace();
		}	
		listUsersTO.add(new AssignedUserInfoTO("TOTAL", "", total));
		
		System.out.println(query + " -> " + total + " total");
		return listUsersTO;			
	}
}
