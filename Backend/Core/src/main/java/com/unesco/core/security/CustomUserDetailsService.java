package com.unesco.core.security;

import com.unesco.core.entities.account.UserEntity;
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
        UserEntity userEntity =userRepository.findByUsername(username);
        if(userEntity == null){
            throw new UsernameNotFoundException("No userEntity present with username: "+username);
        }else{
            return new CustomUserDetails(userEntity);
        }
    }

    @Override
    public CustomUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity =userRepository.findByUsername(username);

        if(userEntity == null){
            throw new UsernameNotFoundException("No userEntity present with username: "+username);
        }else{
            return new CustomUserDetails(userEntity);
        }
    }
}