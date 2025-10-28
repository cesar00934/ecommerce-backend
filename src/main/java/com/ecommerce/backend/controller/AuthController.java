package com.ecommerce.backend.controller;
import java.util.Map;

import com.ecommerce.backend.dto.*;
import com.ecommerce.backend.service.AuthService;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

  @Autowired AuthService authService;

  @PostMapping("/register")
  public ResponseEntity<?> register(@RequestBody RegisterRequest req){
    try {
      AuthResponse res = authService.register(req);
      return ResponseEntity.ok(res);
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("error", e.getMessage()));
    }
  }

  @PostMapping("/login")
  public ResponseEntity<?> login(@RequestBody LoginRequest req){
    try {
      AuthResponse res = authService.login(req);
      return ResponseEntity.ok(res);
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("error", e.getMessage()));
    }
  }
}
