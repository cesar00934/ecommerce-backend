package com.ecommerce.backend.service;


import com.ecommerce.backend.repository.UserRepository;
import com.ecommerce.backend.model.User;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.stream.Collectors;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

@Service
public class CustomUserDetailsService implements UserDetailsService {

  @Autowired
  UserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User u = userRepository.findByUsername(username)
        .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    return new org.springframework.security.core.userdetails.User(
      u.getUsername(),
      u.getPassword(),
      u.getRoles().stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList())
    );
  }
}
