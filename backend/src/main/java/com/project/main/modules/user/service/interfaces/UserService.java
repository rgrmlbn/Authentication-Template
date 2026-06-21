package com.project.main.modules.user.service.interfaces;

import com.project.main.modules.user.dto.request.ChangePasswordRequest;
import com.project.main.modules.user.dto.request.UpdateUserRequest;
import com.project.main.modules.user.dto.response.UserResponse;

import java.util.List;

public interface UserService {

    List<UserResponse> getAllUsers();

    UserResponse getUserById(Long id);

    UserResponse getMe();

    UserResponse updateUserById(Long id, UpdateUserRequest update);

    void changePasswordById(Long id, ChangePasswordRequest request);

    void deleteUserById(Long id);
}
