package com.multipie.whereiseveryone;

public class Person {
	private long id;
	private String name;
	private String bio;
	private String latitude;
	private String longitude;


	private Person() {
		// Keep GSON happy
	}


	public long getId() {
		return id;
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


	public String getBio() {
		return bio;
	}


	public void setBio(String bio) {
		this.bio = bio;
	}
}
