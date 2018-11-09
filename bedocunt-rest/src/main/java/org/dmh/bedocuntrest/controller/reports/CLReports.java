package org.dmh.bedocuntrest.controller.reports;

import java.util.ArrayList;
import java.util.List;

import org.dmh.bedocuntrest.entity.CSUReports;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CLReports {
	
	Logger logger = LoggerFactory.getLogger(CLReports.class);
	
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping(value= {"/api/csureports"})
	public List<CSUReports> getReports(){
		
		logger.debug("-------------------------------------------------------inside the getReports");
		System.out.println("--------------------inside the setWelcomeToken-------------------------");
		String userName = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		List<CSUReports> lstReprts = new ArrayList<CSUReports>();
		for(int i=0;i<3;i++){
			CSUReports csur = new CSUReports();
			csur.setBedType("AP1");
			csur.setCoed(null);csur.setFemale("5");
			csur.setLocationCode("113");
			csur.setLocationName("Jackson Community center");
			csur.setMale("5");
			csur.setOrgCode("999");
			csur.setOrgName(null);
			csur.setPendAdm("3");
			csur.setPendDis("2");
			lstReprts.add(csur);
		}
		
			
		return lstReprts;
	}
	
	
	

}
