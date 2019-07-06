package com.jds.epathshala.persistenceservice.db.model;

public enum Role {
	ADMIN("ADMIN", 0), STUDENT("STUDENT", 1), TEACHER("TEACHER", 2);
	private String name;
	private int value;

	private Role(String name, int value) {
		this.name = name;
		this.value = value;
	}

	public String getName() {
		return name;
	}

	public int getValue() {
		return value;
	}

}
