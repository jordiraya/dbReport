package com.costaisa.suitecrm.domain.to;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class ListValueTO {

	private String value;
	private Integer cont;
	
	public String toCSV() {
		StringBuilder sb = new StringBuilder();
		sb.append(value);
		sb.append(";");
		sb.append(cont);
		sb.append("\n");
		return sb.toString();
	}
}
