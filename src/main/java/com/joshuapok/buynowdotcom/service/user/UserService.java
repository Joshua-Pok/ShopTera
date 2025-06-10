package com.joshuapok.buynowdotcom.service.user;

import com.joshuapok.buynowdotcom.Requests.CreateUserRequest;
import com.joshuapok.buynowdotcom.Requests.UpdateUserRequest;
import com.joshuapok.buynowdotcom.model.User;

public interface UserService {
    User createUser(CreateUserRequest request);
    User updateUser(UpdateUserRequest request, Long userId);
    User findUserById(Long userId);
    void deleteUser(Long userId);

}
