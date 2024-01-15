package com.costaisa.suitecrm.domain.api;

import java.util.List;

import com.costaisa.suitecrm.domain.to.TableFieldInfoTO;

public interface ReportContacts {

	public List<TableFieldInfoTO> getTableInfo();
	
	public void printCSV(String pathFile);
}
