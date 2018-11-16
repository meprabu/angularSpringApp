package org.dmh.bedocuntrest.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.dmh.bedocuntrest.entity.CSULicensed;
import org.dmh.bedocuntrest.entity.DmhUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;

@Repository
public class CSULicensedDao {
	
	
	private JdbcTemplate jdbcTemplate;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	

	public List<String> getCSUProviders(){
		String user_id = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String permit = (String) SecurityContextHolder.getContext().getAuthentication().getCredentials();
		List<String> distinctProviders = new ArrayList<String>();
		String selectQuery  =  "select distinct ORG_CODE, PROVIDER_NAME from DMH.dbo.bc_providers where ORG_CODE in (select Provider_number from DMH.dbo.bc_locations where Location_code not like 'CL%')";
			List<Map<String, Object>> rows = jdbcTemplate.queryForList(selectQuery);
			rows.stream().forEach((row) -> {
				distinctProviders.add(row.get("org_code") + " - " + row.get("PROVIDER_NAME"));
			});
		return distinctProviders;
	}
	
	
	public List<CSULicensed> getCSULocations(String locId){
		String permit = (String) SecurityContextHolder.getContext().getAuthentication().getCredentials();
		List<String> distinctProviders = new ArrayList<String>();
		String selectQuery  =  "select  distinct P.org_code, P.PROVIDER_NAME, L.LOCATION_CODE, L.LOCATION_NAME, L.BED_TYPE, D.bed_type_desc from DMH.dbo.bc_providers P "
				+ "inner join DMH.dbo.bc_locations L on P.org_code = L.PROVIDER_NUMBER inner join DMH.dbo.bc_bed_type D on L.BED_TYPE = D.bed_type where P.ORG_CODE = ? and L.LOCATION_CODE not like 'CL%'";
			List<Map<String, Object>> rows = jdbcTemplate.queryForList(selectQuery, new Object[] { locId });
			rows.stream().forEach((row) -> {
				distinctProviders.add(row.get("org_code") + " - "   + row.get("location_code") + " - " + row.get("bed_type") +  " - " + row.get("PROVIDER_NAME") + " - "   + row.get("LOCATION_NAME") + " - " + row.get("bed_type_desc"));
			});
		List<CSULicensed> licenseBeds = getLicenseBedRecords(distinctProviders, permit);
		return licenseBeds;
	}
	
	
	
	
	private List<CSULicensed> getLicenseBedRecords(
			List<String> distinctProviders, String orgCode) {
		List<CSULicensed> licenseBeds =  new ArrayList<CSULicensed>();
		
		for(String provider : distinctProviders){
			if("995".equals(orgCode.trim())){
					String[] providers =   provider.split("-");
					String selectQuery = "SELECT * FROM DMH.dbo.bc_license WHERE modify_date= (SELECT MAX(modify_date) FROM DMH.dbo.bc_license where org_code = ? and location_code = ? and bed_type = ?) and org_code = ? and location_code = ? and bed_type = ?";
					licenseBeds  = (List<CSULicensed>) jdbcTemplate.query(selectQuery, new Object[] { providers[0].trim(), providers[1].trim(), providers[2].trim(),providers[0].trim(), providers[1].trim(), providers[2].trim() }, 
							new CountryWithStateExtractor(providers));
			}else if("999".equals(orgCode.trim())){
				String[] providers =   provider.split("-");
				CSULicensed bcLic = new CSULicensed();
				bcLic.setOrg_code(providers[0].trim());
				bcLic.setLocation_code(providers[1].trim());
				bcLic.setBed_type( providers[2].trim());
				bcLic.setLic_bed_male("");
				bcLic.setLic_bed_female("");
				bcLic.setLic_bed_coed( "");
				bcLic.setAct_bed_female("");
				bcLic.setAct_bed_male("");
				bcLic.setAct_bed_coed("");
				bcLic.setOrgName(providers[3]);
				bcLic.setLocationName(providers[4]);
				bcLic.setBedTypeDesc(providers[5]);
				licenseBeds.add(bcLic);
			}
		}
		return licenseBeds;
	}
	
	class CountryWithStateExtractor implements ResultSetExtractor<List<CSULicensed>> {
		String[] providers = null;
		List<CSULicensed> licenseBeds =  new ArrayList<CSULicensed>();
		CountryWithStateExtractor(String[] providers){
			this.providers =  providers;
		}
	    public List<CSULicensed> extractData(ResultSet rs) throws SQLException {
		while(rs.next()) {
			CSULicensed bcLic = new CSULicensed();
			bcLic.setOrg_code(rs.getString("org_code"));
			bcLic.setLocation_code(rs.getString("location_code"));
			bcLic.setBed_type( rs.getString("bed_type"));
			bcLic.setLic_bed_male(rs.getString("lic_bed_male"));
			bcLic.setLic_bed_female(rs.getString("lic_bed_female"));
			bcLic.setLic_bed_coed( rs.getString("lic_bed_coed"));
			bcLic.setAct_bed_female(rs.getString("act_bed_female"));
			bcLic.setAct_bed_male(rs.getString("act_bed_male"));
			bcLic.setAct_bed_coed(rs.getString("act_bed_coed"));
			bcLic.setOrgName(providers[3]);
			bcLic.setLocationName(providers[4]);
			bcLic.setBedTypeDesc(providers[5]);
			licenseBeds.add(bcLic);
		}
		return licenseBeds;
	  }
	}
	
}
