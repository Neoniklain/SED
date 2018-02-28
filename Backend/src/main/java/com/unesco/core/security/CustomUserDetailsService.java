package com.unesco.core.security;

import com.unesco.core.entities.User;
import com.unesco.core.repositories.account.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service("customUserDetailsService")
public class CustomUserDetailsService implements UserDetailsService{
    @Autowired
    private final UserRepository userRepository;
    @Autowired
    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    public CustomUserDetails getUserDetails() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user=userRepository.findByUsername(username);

        if(user == null){
            throw new UsernameNotFoundException("No user present with username: "+username);
        }else{
            return new CustomUserDetails(user);
        }
    }

    @Override
    public CustomUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user=userRepository.findByUsername(username);

        if(user == null){
            throw new UsernameNotFoundException("No user present with username: "+username);
        }else{
            return new CustomUserDetails(user);
        }
    }
}