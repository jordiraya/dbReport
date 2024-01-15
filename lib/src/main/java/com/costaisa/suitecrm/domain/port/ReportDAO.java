package com.costaisa.suitecrm.domain.port;

import java.util.ArrayList;
import java.util.List;

import com.costaisa.suitecrm.domain.to.AssignedUserInfoTO;
import com.costaisa.suitecrm.domain.to.ListValueTO;

public interface ReportDAO {

	public String getHostName();
	
	public int getNumberOfRows(String table, String tableCstm);
	
	public int getNumberOfRows(String table, String tableCstm, String field);
	
	public int getNumberOfRowsBoolean(String table, String tableCstm, String field);
	
	public int getNumberOfRowsNumeric(String table, String tableCstm, String field);
	
	public List<ListValueTO> getList(String table, String tableCstm, String field); 
	
	public ArrayList<AssignedUserInfoTO> getAssignedUsers(String table, String tableCstm);
}
