package com.costaisa.suitecrm.domain.core;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.costaisa.suitecrm.domain.api.ReportAccounts;
import com.costaisa.suitecrm.domain.to.TableFieldInfoTO;

public class ReportAccountsImpl extends ReportAbstract implements ReportAccounts {

	private static final String REPORT_NAME = "Cuentas";
	private static final String TABLE = "accounts";
	private static final String TABLE_CSTM = "accounts_cstm";
	
	private static final Map<String, String> FIELDS;
	private static final Map<String, String> FIELDS_LIST;
	
	static {
		FIELDS = new LinkedHashMap<>(); // preserve insertion order
		FIELDS.put("nombre", "name");
		FIELDS.put("descripción", "description");
		FIELDS.put("usuario asignado", "assigned_user_id");
		FIELDS.put("tipo de cuenta", "account_type");
		FIELDS.put("industria", "industry");
		FIELDS.put("facturación", "annual_revenue");
		FIELDS.put("tel/fax", "phone_fax");
		FIELDS.put("calle", "billing_address_street");
		FIELDS.put("ciudad", "billing_address_city");
		FIELDS.put("provincia", "billing_address_state");
		FIELDS.put("código postal", "billing_address_postalcode");
		FIELDS.put("país", "billing_address_country");
		FIELDS.put("ratio", "rating");
		FIELDS.put("tel oficina", "phone_office");
		FIELDS.put("tel alternativo", "phone_alternate");
		FIELDS.put("sitio web", "website");
		FIELDS.put("propietario", "ownership");
		FIELDS.put("empleados", "employees");
		FIELDS.put("ticker", "ticker_symbol");
		FIELDS.put("calle envío", "shipping_address_street");
		FIELDS.put("ciudad envío", "shipping_address_city");
		FIELDS.put("provincia envío", "shipping_address_state");
		FIELDS.put("código postal envío", "shipping_address_postalcode");
		FIELDS.put("país envío", "shipping_address_country");
		FIELDS.put("campaña", "campaign_id");
		FIELDS.put("his programa actual", "his_c");
		FIELDS.put("subactividad", "subactivity_c");
		FIELDS.put("código cuenta", "codigo_cuenta_c");
		FIELDS.put("razón social", "razon_social_c");
		FIELDS.put("grupo empresa", "grupo_c");
		FIELDS.put("nif", "cif_nif_c");
		FIELDS.put("erp actual", "erp_c");
		FIELDS.put("tipo cuenta fihoca", "account_type_fihoca_c");
		FIELDS.put("tipo cuenta sisemed", "account_type_sisemed_c");
		FIELDS.put("tipo cuenta sapas", "account_type_sapas_c");
		FIELDS.put("actividad", "actividad_c");
		FIELDS.put("tipo empresa", "tipo_empresa_c");
		FIELDS.put("titularidad", "titularidad_c");
		FIELDS.put("fecha alta", "fecha_alta_c");
		FIELDS.put("año facturación", "ano_facturacion_c");
		FIELDS.put("comunidad autónoma", "comunidadautonoma_c");		
		FIELDS.put("enviar mail (S/N)", "send_mail_c");
		FIELDS.put("cuenta objetivo (S/N)", "cuentaobjetivo_c");
		
		FIELDS_LIST = new LinkedHashMap<>(); // preserve insertion order
		FIELDS_LIST.put("tipo de cuenta", "account_type");
		FIELDS_LIST.put("industria", "industry");		
	}	
	
	public ReportAccountsImpl() {
		super(REPORT_NAME, TABLE, TABLE_CSTM, FIELDS, FIELDS_LIST);
	}

	
	public static void main (String[] args) {
		ReportAccountsImpl report = new ReportAccountsImpl();
		String pathFile = "C:\\test\\crm\\report.csv";
		report.printCSV(pathFile);
	}
	
}
