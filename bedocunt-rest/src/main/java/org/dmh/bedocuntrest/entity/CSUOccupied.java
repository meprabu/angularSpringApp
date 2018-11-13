package org.dmh.bedocuntrest.entity;

import java.sql.Timestamp;

public class CSUOccupied {
	
	
	private String orgCode;
	private String locationCode;
	private String bedtype;
	private String orgName;
	private String locationName;
	private String occupied_male;
	private String occupied_female;
	private String occupied_coed;
	private String pending_adm;
	private String pending_dis;
	private String modifyUser;
	private Timestamp modifydate;
	
	//community living fields
	
	private String admitted_male;
	private String admitted_female;
	private String discharged_male;
	private String discharged_female;
	private String pass;
	
	
	public String getOrgCode() {
		return orgCode;
	}
	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}
	public String getLocationCode() {
		return locationCode;
	}
	public void setLocationCode(String locationCode) {
		this.locationCode = locationCode;
	}
	public String getBedtype() {
		return bedtype;
	}
	public void setBedtype(String bedtype) {
		this.bedtype = bedtype;
	}
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	public String getLocationName() {
		return locationName;
	}
	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}
	public String getOccupied_male() {
		return occupied_male;
	}
	public void setOccupied_male(String occupied_male) {
		this.occupied_male = occupied_male;
	}
	public String getOccupied_female() {
		return occupied_female;
	}
	public void setOccupied_female(String occupied_female) {
		this.occupied_female = occupied_female;
	}
	public String getOccupied_coed() {
		return occupied_coed;
	}
	public void setOccupied_coed(String occupied_coed) {
		this.occupied_coed = occupied_coed;
	}
	public String getPending_adm() {
		return pending_adm;
	}
	public void setPending_adm(String pending_adm) {
		this.pending_adm = pending_adm;
	}
	public String getPending_dis() {
		return pending_dis;
	}
	public void setPending_dis(String pending_dis) {
		this.pending_dis = pending_dis;
	}
	public String getModifyUser() {
		return modifyUser;
	}
	public void setModifyUser(String modifyUser) {
		this.modifyUser = modifyUser;
	}
	public Timestamp getModifydate() {
		return modifydate;
	}
	public void setModifydate(Timestamp modifydate) {
		this.modifydate = modifydate;
	}
	public String getAdmitted_male() {
		return admitted_male;
	}
	public void setAdmitted_male(String admitted_male) {
		this.admitted_male = admitted_male;
	}
	public String getAdmitted_female() {
		return admitted_female;
	}
	public void setAdmitted_female(String admitted_female) {
		this.admitted_female = admitted_female;
	}
	public String getDischarged_male() {
		return discharged_male;
	}
	public void setDischarged_male(String discharged_male) {
		this.discharged_male = discharged_male;
	}
	public String getDischarged_female() {
		return discharged_female;
	}
	public void setDischarged_female(String discharged_female) {
		this.discharged_female = discharged_female;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	
	
	
	
	

}
