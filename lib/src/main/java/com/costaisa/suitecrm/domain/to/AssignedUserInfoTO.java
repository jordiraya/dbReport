package com.costaisa.suitecrm.domain.to;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class AssignedUserInfoTO {

	private String nombre;
	private String apellidos;
	private Integer cont;
	
	public String toCSV() {
		StringBuilder sb = new StringBuilder();
		sb.append(nombre);
		sb.append(";");
		sb.append(apellidos);
		sb.append(";");
		sb.append(cont);
		sb.append("\n");
		return sb.toString();
	}	
}
