package com.ecommerce.backend.service;


import com.ecommerce.backend.dto.*;
import com.ecommerce.backend.model.User;
import com.ecommerce.backend.repository.UserRepository;
import com.ecommerce.backend.security.JwtUtils;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import java.util.*;
import jakarta.transaction.Transactional;

@Service
public class AuthService {

  @Autowired UserRepository userRepository;
  @Autowired BCryptPasswordEncoder passwordEncoder;
  @Autowired JwtUtils jwtUtils;

  @Transactional
  public AuthResponse register(RegisterRequest req){
    if(userRepository.existsByUsername(req.getUsername())) {
      throw new RuntimeException("Usuario ya existe");
    }
    Set<String> roles = new HashSet<>();
    if(req.getRoles()!=null && !req.getRoles().isEmpty()){
      req.getRoles().forEach(r -> {
        if(r.startsWith("ROLE_")) roles.add(r);
        else roles.add("ROLE_" + r);
      });
    } else {
      roles.add("ROLE_CLIENTE");
    }
    User u = new User(req.getUsername(), passwordEncoder.encode(req.getPassword()), roles);
    userRepository.save(u);
    String token = jwtUtils.generateToken(u.getUsername(), roles);
    return new AuthResponse(token, new ArrayList<>(roles));
  }

  public AuthResponse login(LoginRequest req){
    User u = userRepository.findByUsername(req.getUsername()).orElseThrow(() -> new RuntimeException("Usuario o contraseña inválidos"));
    if(!passwordEncoder.matches(req.getPassword(), u.getPassword())){
      throw new RuntimeException("Usuario o contraseña inválidos");
    }
    String token = jwtUtils.generateToken(u.getUsername(), u.getRoles());
    return new AuthResponse(token, new ArrayList<>(u.getRoles()));
  }
}
