package com.costaisa.suitecrm.infraestructure.batch;

import com.costaisa.suitecrm.domain.api.ReportAccounts;
import com.costaisa.suitecrm.domain.api.ReportContacts;
import com.costaisa.suitecrm.domain.api.ReportOpportunities;
import com.costaisa.suitecrm.domain.core.ReportAccountsImpl;
import com.costaisa.suitecrm.domain.core.ReportContactsImpl;
import com.costaisa.suitecrm.domain.core.ReportOpportunitiesImpl;

public class Application {

	private static final String pathReportAccounts = "C:\\test\\crm\\reportAccounts.csv";
	private static final String pathReportContacts = "C:\\test\\crm\\reportContacts.csv";
	private static final String pathReporOpportunities = "C:\\test\\crm\\reportOpportunities.csv";
	
	ReportAccounts reportAccounts = new ReportAccountsImpl();
	ReportContacts reportContacts = new ReportContactsImpl();
	ReportOpportunities reportOpportunities = new ReportOpportunitiesImpl();
		
	public Application() {
		
	}

	public void run() {
		reportAccounts.printCSV(pathReportAccounts);
		reportContacts.printCSV(pathReportContacts);
		reportOpportunities.printCSV(pathReporOpportunities);
	}
	
	public static void main(String[] args) {		
		Application application = new Application();
		application.run();
	}

}
