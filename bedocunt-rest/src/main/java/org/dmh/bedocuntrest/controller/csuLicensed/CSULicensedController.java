package org.dmh.bedocuntrest.controller.csuLicensed;

import java.util.List;

import org.dmh.bedocuntrest.controller.reports.CLReports;
import org.dmh.bedocuntrest.dao.CSULicensedDao;
import org.dmh.bedocuntrest.entity.CSULicensed;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class CSULicensedController {
	
	
	@Autowired
	CSULicensedDao csuLicensedao; 
	
	Logger logger = LoggerFactory.getLogger(CLReports.class);
	
	@GetMapping(value = { "/api/csuLicProv" })
	public ResponseEntity<List<String>> getProviders(){
		logger.debug(String.format("In the Method %s with getMapiing %s", "getProviders", "/api/csuLicProv"));
		return ResponseEntity.ok().body(csuLicensedao.getCSUProviders());
	}
	
	@GetMapping(value = { "/api/csuLicLocations" })
	public ResponseEntity<List<CSULicensed>> getLocations(@RequestParam("locid") String locId){
		logger.debug(String.format("In the Method %s with getMapiing %s", "getProviders", "/api/csuLicLocations"));
		return ResponseEntity.ok().body(csuLicensedao.getCSULocations(locId));
	}

}
