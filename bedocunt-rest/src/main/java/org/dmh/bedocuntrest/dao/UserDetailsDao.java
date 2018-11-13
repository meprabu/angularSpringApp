package org.dmh.bedocuntrest.dao;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.dmh.bedocuntrest.entity.DmhUser;
import org.dmh.bedocuntrest.enums.DMHRoles;

@Repository
public class UserDetailsDao {
	
	private JdbcTemplate jdbcTemplate;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	
	public DmhUser getUserById(String email) {
		String sql = "select * from DMH.dbo.USER_TABLE where who =?";
		@SuppressWarnings("unchecked")
		DmhUser user = (DmhUser) jdbcTemplate.queryForObject(sql, new Object[] { email }, new BeanPropertyRowMapper(DmhUser.class));
		//setUserRoles(user);
		return user;
	}
	
	/*private DmhUser setUserRoles(DmhUser user){
		if(user.getPermit().equals("999")){
			user.setRole(DMHRoles.ADMIN);
		}else {
			user.setRole(DMHRoles.PROVIDER);
		}
		return user;
	}*/
	
}
