package com.joshuapok.buynowdotcom.Controller;

import com.joshuapok.buynowdotcom.Requests.CreateUserRequest;
import com.joshuapok.buynowdotcom.Requests.UpdateUserRequest;
import com.joshuapok.buynowdotcom.Response.ApiResponse;
import com.joshuapok.buynowdotcom.model.User;
import com.joshuapok.buynowdotcom.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("${api.prefix}/users")
public class UserController {

    private final UserService userService;

    @GetMapping("/user/{userId}/user")
    public ResponseEntity<ApiResponse> getUserById(@PathVariable Long userId){
        User user = userService.findUserById(userId);
        return ResponseEntity.ok(new ApiResponse("Found!", user));
    }

    @PostMapping("/user/add")
    public ResponseEntity<ApiResponse> createUser(@RequestBody CreateUserRequest createUserRequest){
        User user = userService.createUser(createUserRequest);
        return ResponseEntity.ok(new ApiResponse("Created!", user));
    }

    @PutMapping("/user/{userId}/update")
    public ResponseEntity<ApiResponse> updateUser(@RequestBody UpdateUserRequest request, @PathVariable Long userId){
        User user = userService.updateUser(request, userId);
        return ResponseEntity.ok(new ApiResponse("Updated!", user));
    }

    @DeleteMapping("/{userId}/delete")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable Long userId){
        userService.deleteUser(userId);
        return ResponseEntity.ok(new ApiResponse("User Deleted!", userId));
    }
}
