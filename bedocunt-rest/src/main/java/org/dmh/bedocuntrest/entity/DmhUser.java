package org.dmh.bedocuntrest.entity;

import org.dmh.bedocuntrest.enums.DMHRoles;

public class DmhUser {

	public DmhUser() {
	}


	private String who;
	private String first_name;
	private String last_name;
	private String location_code;
	private String permit;
	private String pswd;
	private boolean occupancRate;
	private DMHRoles role;
	private String email;
	private String phone;
	private String csu;
	private String cl;
	
	
	
	public String getWho() {
		return who;
	}
	public void setWho(String who) {
		this.who = who;
	}
	public String getFirst_name() {
		return first_name;
	}
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}
	public String getLast_name() {
		return last_name;
	}
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}
	public String getLocation_code() {
		return location_code;
	}
	public void setLocation_code(String location_code) {
		this.location_code = location_code;
	}
	public String getPermit() {
		return permit;
	}
	public void setPermit(String permit) {
		this.permit = permit;
	}
	/*public String getLAST_LOGGED_IN() {
		return LAST_LOGGED_IN;
	}
	public void setLAST_LOGGED_IN(String lAST_LOGGED_IN) {
		LAST_LOGGED_IN = lAST_LOGGED_IN;
	}*/
	public String getPswd() {
		return pswd;
	}
	public void setPswd(String pswd) {
		this.pswd = pswd;
	}
	public DMHRoles getRole() {
		return role;
	}
	public void setRole(DMHRoles role) {
		this.role = role;
	}
	public boolean isOccupancRate() {
		return occupancRate;
	}
	public void setOccupancRate(boolean occupancRate) {
		this.occupancRate = occupancRate;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getCsu() {
		return csu;
	}
	public void setCsu(String csu) {
		this.csu = csu;
	}
	public String getCl() {
		return cl;
	}
	public void setCl(String cl) {
		this.cl = cl;
	}
	
	
	
	
	
	
}
