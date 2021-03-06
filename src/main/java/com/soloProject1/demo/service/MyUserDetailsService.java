package com.soloProject1.demo.service;

import com.soloProject1.demo.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service("userDetailsService")
public class MyUserDetailsService implements UserDetailsService {
    private RestService restService;

    @Autowired
    public MyUserDetailsService(RestService restService) {
        this.restService = restService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        com.soloProject1.demo.model.User user = restService.findByUsername(username);

        List<GrantedAuthority> authorities = buildUserAuthority(user.getRoles());
        return buildUserForAuthentication(user, authorities);
    }

    private User buildUserForAuthentication(com.soloProject1.demo.model.User user,
                                            List<GrantedAuthority> authorities) {
        return new User(user.getUserName(), user.getPassword(), authorities);
    }

    private List<GrantedAuthority> buildUserAuthority(Set<Role> roles) {
        Set<GrantedAuthority> setAuthorities = new HashSet<>();

        for (Role role : roles) {
            setAuthorities.add(new SimpleGrantedAuthority(role.getName()));
        }

        List<GrantedAuthority> result = new ArrayList<>(setAuthorities);
        return result;
    }
}