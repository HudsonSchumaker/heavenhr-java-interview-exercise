package br.com.schumaker.heavenhr.model;

/**
*
* @author Hudson Schumaker
* @version 1.0.0
*/
public enum EnumApplicationStatus {
	APPLIED, INVITED, REJECTED, HIRED;
	
	public static EnumApplicationStatus getApplicationStatusByName(String name) {
		for (EnumApplicationStatus e : EnumApplicationStatus.values()) {
			if (e.name().equals(name)) {
				return e;
			}
		}
		return null;
	}
}