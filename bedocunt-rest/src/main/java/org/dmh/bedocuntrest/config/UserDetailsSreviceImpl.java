package org.dmh.bedocuntrest.config;

import static java.util.Collections.emptyList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import org.dmh.bedocuntrest.dao.UserDetailsDao;
import org.dmh.bedocuntrest.entity.DmhUser;
import org.dmh.bedocuntrest.enums.DMHRoles;

@Service
public class UserDetailsSreviceImpl implements UserDetailsService {

	@Autowired
	UserDetailsDao userdetailsDao;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		DmhUser applicationUser = userdetailsDao.getUserById(username);
		if (applicationUser == null) {
			throw new UsernameNotFoundException(username);
		}
		return new User(applicationUser.getWho(), applicationUser.getPermit(), getUserAuthorities(applicationUser));
	}

	private List<GrantedAuthority> getUserAuthorities(DmhUser applicationUser) {

		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		if (applicationUser.getPermit().equals("999")) {
			authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
		} else if (applicationUser.getPermit().equals("995")) {
			authorities.add(new SimpleGrantedAuthority("ROLE_PROVIDERADMIN"));
		} else {
			authorities.add(new SimpleGrantedAuthority("ROLE_PROVIDER"));
		}
		return authorities;
	}

}
