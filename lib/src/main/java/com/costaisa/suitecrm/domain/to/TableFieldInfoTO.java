package com.costaisa.suitecrm.domain.to;

import java.text.DecimalFormat;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class TableFieldInfoTO {
	
	private String field;
	private String fieldNameInTable;
	private Integer rows;
	private Integer rowsEmpty;
	private Double percentageRows;
	private Double percentageRowsEmpty;
	
	@Getter(AccessLevel.NONE)
	@Setter(AccessLevel.NONE)
	private static final DecimalFormat decimalFormatDouble = new DecimalFormat("0.00");
	
	@Getter(AccessLevel.NONE)
	@Setter(AccessLevel.NONE)
	private static final DecimalFormat decimalFormatInt = new DecimalFormat("#,###");
	
	public String toCSV() {
		StringBuilder sb = new StringBuilder();
		sb.append("Nº de filas con ");
		sb.append(field);
		sb.append(";");
		sb.append(decimalFormatInt.format(rows));
		sb.append(";");
		sb.append(decimalFormatDouble.format(percentageRows));
		sb.append(" %");
		sb.append(";");
		sb.append(decimalFormatInt.format(rowsEmpty));
		sb.append(";");
		sb.append(decimalFormatDouble.format(percentageRowsEmpty));
		sb.append(" %");
		sb.append("\n");
		return sb.toString();
	}
}
