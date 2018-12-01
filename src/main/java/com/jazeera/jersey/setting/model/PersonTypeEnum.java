package com.jazeera.jersey.setting.model;

/**
 * Lookup Enum defines the possible Lookup values for Person Types
 * @author navas
 */
public enum PersonTypeEnum {
	
	STUDENT("Student", "STUDENT", "person_type"),
	STAFF("Staff", "STAFF", "person_type");
	
	private final String name;
	private final String code;
	private final String lookupReference;

    /**
     * @param text
     */
    private PersonTypeEnum(String name, String code, String lookupReference) {
        this.name = name;
        this.code = code;
        this.lookupReference = lookupReference;
    }
    
    public String getName() {
		return name;
	}

	public String getCode() {
		return code;
	}

	public String getLookupReference() {
		return lookupReference;
	}
}
