package com.minutemedia.pi;

public class PiData {

	private int id;
	private String venueName;
	private String startDateTime;
	private String stopDateTime;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return venueName;
	}

	public void setName(String venueName) {
		this.venueName = venueName;
	}

	public String getStart() {
		return startDateTime;
	}

	public void setStart(String start) {
		this.startDateTime = start;
	}

	public String getStop() {
		return stopDateTime;
	}

	public void setStop(String stop) {
		this.stopDateTime = stop;
	}

}
