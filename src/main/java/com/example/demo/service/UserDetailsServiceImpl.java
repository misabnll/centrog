package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Authority;
import com.example.demo.repository.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	@Autowired
	UserRepository repository1;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		com.example.demo.entity.User user = repository1.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("No existe usuario"));

	    List<GrantedAuthority> grantedList = new ArrayList<GrantedAuthority>();

    	for (Authority authority:user.getAuthority()) {
	    	GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(authority.getAuthority());
	    	grantedList.add(grantedAuthority);
	    }

	    UserDetails userDetails = (UserDetails) new User(user.getUsername(), user.getPassword(), user.isEnabled(), true, true, !user.isLocked(), grantedList);
        return userDetails;
	}

}
