package com.costaisa.suitecrm.domain.core;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.costaisa.suitecrm.domain.api.ReportContacts;
import com.costaisa.suitecrm.domain.api.ReportOpportunities;
import com.costaisa.suitecrm.domain.to.TableFieldInfoTO;

public class ReportOpportunitiesImpl extends ReportAbstract implements ReportOpportunities {

	private static final String REPORT_NAME = "Oportunidades";
	private static final String TABLE = "opportunities";
	private static final String TABLE_CSTM = "opportunities_cstm";
	
	private static final Map<String, String> FIELDS;
	private static final Map<String, String> FIELDS_LIST;
	
	static {
		FIELDS = new LinkedHashMap<>(); // preserve insertion order
		FIELDS.put("nombre", "name");
		FIELDS.put("descripcion", "description");
		FIELDS.put("usuario asignado", "assigned_user_id");
		FIELDS.put("tipo", "opportunity_type");
		FIELDS.put("campaña", "campaign_id");
		FIELDS.put("lead", "lead_source");
		FIELDS.put("importe (num)", "amount");
		FIELDS.put("importe $ (num)", "amount_usdollar");
		FIELDS.put("moneda", "currency_id");
		FIELDS.put("fecha de cierre", "date_closed");
		FIELDS.put("siguiente paso", "next_step");
		FIELDS.put("fase de venta", "sales_stage");
		FIELDS.put("probabilidad (num)", "probability");
		FIELDS.put("fecha de oferta", "date_offer_c");
		FIELDS.put("codigo oferta", "cod_oferta_c");
		FIELDS.put("grupo de empresas", "empresa_grupo_c");
		FIELDS.put("origen", "origen_c");
		FIELDS.put("tipo de proyecto", "tipo_proyecto_cis_c");
		FIELDS.put("fecha de entrega", "date_entrega_c");
		FIELDS.put("fecha inicio contrato", "date_start_contract_c");
		FIELDS.put("tecnología fih", "tecnologia_fih_c");
		FIELDS.put("motivo de pérdida", "motivo_perdida_c");
		FIELDS.put("fecha fin contrato", "date_end_contrato_c");
		FIELDS.put("probabilidad", "probabilidad_c");
		FIELDS.put("competencia", "competencia_c");
		FIELDS.put("gestión licitación", "gest_licitacion_c");
		FIELDS.put("color", "color_c");		
		FIELDS.put("promotor", "promotor_c");
		FIELDS.put("linea_negocio", "linea_negocio_c");
		FIELDS.put("línea de negocio 2", "lineadenegocio_2_c");		
		FIELDS.put("linea negocio CiS", "linea_negocio_cis_c");
		FIELDS.put("renovable (S/N)", "renovable_c");
		
		FIELDS_LIST = new LinkedHashMap<>(); // preserve insertion order
		FIELDS_LIST.put("linea negocio CiS", "linea_negocio_cis_c");
		FIELDS_LIST.put("fase de venta", "sales_stage");
		FIELDS_LIST.put("grupo de empresas", "empresa_grupo_c");
		FIELDS_LIST.put("origen", "origen_c");
		FIELDS_LIST.put("probabilidad", "probabilidad_c");
		FIELDS_LIST.put("tipo de proyecto", "tipo_proyecto_cis_c");
		FIELDS_LIST.put("gestión licitación", "gest_licitacion_c");
	}	
	
	public ReportOpportunitiesImpl() {
		super(REPORT_NAME, TABLE, TABLE_CSTM, FIELDS, FIELDS_LIST);
	}

	public static void main (String[] args) {
		ReportOpportunitiesImpl report = new ReportOpportunitiesImpl();
		String pathFile = "C:\\test\\crm\\report.csv";
		report.printCSV(pathFile);
	}
		
	
}
