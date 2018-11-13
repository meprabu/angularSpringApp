package org.dmh.bedocuntrest.controller.reports;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.management.Query;
import javax.sql.DataSource;

import org.dmh.bedocuntrest.entity.CSULicensed;
import org.dmh.bedocuntrest.entity.CSUOccupied;
import org.dmh.bedocuntrest.entity.CSUReports;
import org.dmh.bedocuntrest.entity.DmhUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.apache.commons.lang3.StringUtils;

@RestController
public class CLReports {

	Logger logger = LoggerFactory.getLogger(CLReports.class);

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping(value = { "/api/csureports" })
	public List<CSUReports> getReports() {

		logger.debug("-------------------------------------------------------inside the getReports");
		String userName = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String permit = (String) SecurityContextHolder.getContext().getAuthentication().getCredentials();
		List<CSUReports> reports = new ArrayList<CSUReports>();
		List<CSUReports> lstReprts = new ArrayList<CSUReports>();
		for (int i = 0; i < 3; i++) {
			CSUReports csur = new CSUReports();
			csur.setBedType("AP1");
			csur.setCoed(null);
			csur.setFemale("5");
			csur.setLocationCode("113");
			csur.setLocationName("Jackson Community center");
			csur.setMale("5");
			csur.setOrgCode("999");
			csur.setOrgName(null);
			csur.setPendAdm("3");
			csur.setPendDis("2");
			lstReprts.add(csur);
		}

		if (StringUtils.isNotEmpty(userName) && StringUtils.isNotEmpty(permit)) {
			String sql = "select distinct P.ORG_CODE, L.LOCATION_CODE , L.LOCATION_NAME , L.BED_TYPE from DMH.dbo.bc_providers P inner join DMH.dbo.bc_locations L on P.org_code = L.PROVIDER_NUMBER and L.LOCATION_CODE not like 'CL%'";
			// @SuppressWarnings("unchecked")
			// DmhUser user = (DmhUser) jdbcTemplate.queryForObject(sql, new
			// Object[] {}, new BeanPropertyRowMapper(DmhUser.class));
			List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);
			List<CSULicensed> listLincesed = getLicensedbeds();
			List<CSUOccupied> listOccupied = getOccupiedBeds();
			List<String> providerDeials = new ArrayList<String>();
			rows.stream().forEach((row) -> {
				providerDeials.add(row.get("ORG_CODE") + " - " + row.get("LOCATION_CODE") + " - " + row.get("BED_TYPE")
						+ " - " + row.get("LOCATION_NAME"));
			});
			providerDeials.stream().forEach(Provlocation -> {
				String[] providerLoc = Provlocation.split("-");
				String provider = providerLoc[0].trim();
				String location = providerLoc[1].trim();
				String locationName = providerLoc[3].trim();
				CSUReports report = new CSUReports();
				int totalActive = 0;
				int totalVacancy = 0;
				int coed = 0;
				String activeMale = "";
				String activeFemale = "";
				String activeCoed = "";
				String occupiedMale = "";
				String occupiedFemale = "";
				String occupiedCoed = "";
				String pendAdm = "";
				String pendDisc = "";
				String modifiedDate = "";
				for (CSULicensed licenseBed : listLincesed) {
					if (licenseBed.getOrgCode().equals(provider) && licenseBed.getLocationCode().equals(location)) {
						activeMale = licenseBed.getActBedMale();
						activeFemale = licenseBed.getActBedFeMale();
						activeCoed = licenseBed.getActBedCoed();
					}
				}
				for (CSUOccupied occupiedBed : listOccupied) {
					if (occupiedBed.getOrgCode().equals(provider) && occupiedBed.getLocationCode().equals(location)) {
						occupiedMale = occupiedBed.getOccupied_male();
						occupiedFemale = occupiedBed.getOccupied_female();
						occupiedCoed = occupiedBed.getOccupied_coed();
						pendAdm = occupiedBed.getPending_adm();
						pendDisc = occupiedBed.getPending_dis();

						Date dateObj = toDate(occupiedBed.getModifydate());
						DateFormat df = new SimpleDateFormat("MM/dd/yyy hh:mm:ss a");
						modifiedDate = df.format(dateObj);
						report.setModifyDate(modifiedDate);
					}
				}
				totalActive = Integer.parseInt(nullZero(activeMale)) + Integer.parseInt(nullZero(activeFemale));
				totalVacancy = totalActive
						- (Integer.parseInt(nullZero(occupiedMale)) + Integer.parseInt(nullZero(occupiedFemale)));

				report.setMale(occupiedMale);
				report.setFemale(String.valueOf(occupiedFemale));
				report.setCoed(String.valueOf(totalVacancy));
				report.setPendAdm(nullZero(pendAdm));
				report.setPendDis(nullZero(pendDisc));
				report.setOrgCode(nullZero(provider));
				report.setLocationCode(nullZero(location));
				report.setLocationName(locationName);
				reports.add(report);
			});
		}
		return reports;
	}

	private List<CSULicensed> getLicensedbeds() {
		String selectLicSql = "select t1.* from DMH.dbo.bc_license t1 JOIN (SELECT  org_code, location_code, MAX(modify_date) modify_date  FROM DMH.dbo.bc_license GROUP BY org_code , location_code) t2 ON t1.org_code = t2.org_code and t1.location_code = t2.location_code and t1.modify_date = t2.modify_date";
		return jdbcTemplate.query(selectLicSql, new BeanPropertyRowMapper<CSULicensed>(CSULicensed.class));
	}

	private List<CSUOccupied> getOccupiedBeds() {
		String selectLicSql = "select t1.* from DMH.dbo.bc_occupied t1 JOIN (SELECT  org_code, location_code, MAX(modify_date) modify_date  FROM DMH.dbo.bc_occupied GROUP BY org_code , location_code) t2 ON t1.org_code = t2.org_code and t1.location_code = t2.location_code and t1.modify_date = t2.modify_date";
		return jdbcTemplate.query(selectLicSql, new BeanPropertyRowMapper<CSUOccupied>(CSUOccupied.class));
	}

	public static java.util.Date toDate(java.sql.Timestamp timestamp) {
		if(null == timestamp){
			return new Date();
		}
		long millisec = timestamp.getTime() + (timestamp.getNanos() / 1000000);
		return new Date(millisec);
	}

	private String nullZero(String num) {
		if (null == num || "".equals(num.trim())) {
			return "0";
		} else {
			return num.trim();
		}
	}
}
