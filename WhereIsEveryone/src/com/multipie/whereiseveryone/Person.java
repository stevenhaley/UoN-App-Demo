package com.multipie.whereiseveryone;

public class Person {
	@SuppressWarnings("unused")
	private long id;
	private String name;
	private String latitude;
	private String longitude;


	private Person() {
		// Keep GSON happy
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getName() {
		return name;
	}


	public double getLatitude() {
		return Double.parseDouble(latitude);
	}


	public double getLongitude() {
		return Double.parseDouble(longitude);
	}
}
