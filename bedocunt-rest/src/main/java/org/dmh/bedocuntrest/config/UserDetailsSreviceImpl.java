package org.dmh.bedocuntrest.config;

import static java.util.Collections.emptyList;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import org.dmh.bedocuntrest.dao.UserDetailsDao;
import org.dmh.bedocuntrest.entity.DmhUser;

@Service
public class UserDetailsSreviceImpl implements UserDetailsService{
	
	@Autowired
	UserDetailsDao userdetailsDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        DmhUser applicationUser = userdetailsDao.getUserById(username);
        if (applicationUser == null) {
            throw new UsernameNotFoundException(username);
        }
        return new User(applicationUser.getWho(), applicationUser.getPermit(), emptyList());
    }


}
