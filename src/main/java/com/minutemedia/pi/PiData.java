package com.minutemedia.pi;


//{"54:35:30:89:6e:75":
//{"distance":"-59",
//"mac_short":"54-35-30",
//"mac":"54-35-30-89-6e-75",
//"venueid":"ac:86:74:0b:c7:08",
//"samples":1,
//"captime":1455700162.940852},

public class PiData {

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDistance() {
		return distance;
	}
	public void setDistance(String distance) {
		this.distance = distance;
	}
	public String getMac_short() {
		return mac_short;
	}
	public void setMac_short(String mac_short) {
		this.mac_short = mac_short;
	}
	public String getMac() {
		return mac;
	}
	public void setMac(String mac) {
		this.mac = mac;
	}
	public String getVenueid() {
		return venueid;
	}
	public void setVenueid(String venueid) {
		this.venueid = venueid;
	}
	public String getSamples() {
		return samples;
	}
	public void setSamples(String samples) {
		this.samples = samples;
	}
	public String getCaptime() {
		return captime;
	}
	public void setCaptime(String captime) {
		this.captime = captime;
	}
	private int id;
	private String distance;
	private String mac_short;
	private String mac;
	private String venueid;
	private String samples;
	private String captime;

}
