package com.costaisa.suitecrm.domain.core;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.costaisa.suitecrm.domain.api.ReportContacts;
import com.costaisa.suitecrm.domain.to.TableFieldInfoTO;

public class ReportContactsImpl extends ReportAbstract implements ReportContacts {

	private static final String REPORT_NAME = "Contactos";
	private static final String TABLE = "contacts";
	private static final String TABLE_CSTM = "contacts_cstm";
	
	private static final Map<String, String> FIELDS;
	private static final Map<String, String> FIELDS_LIST;
	
	static {
		FIELDS = new LinkedHashMap<>(); // preserve insertion order
		FIELDS.put("descripcion", "description");
		FIELDS.put("usuario asignado", "assigned_user_id");
		FIELDS.put("saludo", "salutation");
		FIELDS.put("nombre", "first_name");
		FIELDS.put("apellidos", "last_name");
		FIELDS.put("título", "title");
		FIELDS.put("foto", "photo");
		FIELDS.put("departamento", "department");
		FIELDS.put("teléfono particular", "phone_home");
		FIELDS.put("teléfono movil", "phone_mobile");
		FIELDS.put("teléfono trabajo", "phone_work");
		FIELDS.put("otro teléfono", "phone_other");
		FIELDS.put("fax", "phone_fax");
		FIELDS.put("calle", "primary_address_street");
		FIELDS.put("ciudad", "primary_address_city");
		FIELDS.put("provincia", "primary_address_state");
		FIELDS.put("código postal", "primary_address_postalcode");
		FIELDS.put("país", "primary_address_country");
		FIELDS.put("calle alternativa", "alt_address_street");
		FIELDS.put("ciudad alternativa", "alt_address_city");
		FIELDS.put("provincia alternativa", "alt_address_state");
		FIELDS.put("código postal alternativo", "alt_address_postalcode");
		FIELDS.put("país alternativo", "alt_address_country");
		FIELDS.put("asistente", "assistant");
		FIELDS.put("teléfono asistente", "assistant_phone");
		FIELDS.put("lead", "lead_source");
		FIELDS.put("reporta a", "reports_to_id");
		FIELDS.put("fecha de nacimiento", "birthdate");
		FIELDS.put("campaña", "campaign_id");
		FIELDS.put("cuenta joomla", "joomla_account_id");
		FIELDS.put("tipo de usuario portal", "portal_user_type");
		FIELDS.put("cargo", "cargo_c");
		FIELDS.put("departamento", "departamento_c");
		FIELDS.put("id skype", "idskype_c");
		FIELDS.put("idioma", "idioma_c");
		FIELDS.put("área funcional", "area_funcional_c");
		FIELDS.put("cargo costaisa", "cargo_costaisa_c");
		FIELDS.put("departamento costaisa", "departamento_costaisa_c");
		FIELDS.put("cuenta de portal deshabilitada (S/N)", "portal_account_disabled");
		FIELDS.put("target contacto (S/N)", "contactotarget_c");
		FIELDS.put("enviar mail (S/N)", "send_mail_c");
		FIELDS.put("no llamar (S/N)", "do_not_call");
		
		FIELDS_LIST = new LinkedHashMap<>(); // preserve insertion order
		FIELDS_LIST.put("área funcional", "area_funcional_c");
	}	
	
	public ReportContactsImpl() {
		super(REPORT_NAME, TABLE, TABLE_CSTM, FIELDS, FIELDS_LIST);
	}

	public static void main (String[] args) {
		ReportContactsImpl report = new ReportContactsImpl();
		String pathFile = "C:\\test\\crm\\report.csv";
		report.printCSV(pathFile);
	}
		
	
}
