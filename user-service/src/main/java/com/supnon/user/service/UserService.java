package com.supnon.user.service;

import com.supnon.user.dto.AuthResponse;
import com.supnon.user.dto.LoginRequest;
import com.supnon.user.dto.RegisterRequest;
import com.supnon.user.dto.UserDTO;

public interface UserService {
    AuthResponse register(RegisterRequest request);
    AuthResponse login(LoginRequest request);
    UserDTO getCurrentUser(String username);
    UserDTO updateUser(String username, UserDTO userDTO);
    void deleteUser(String username);
} 