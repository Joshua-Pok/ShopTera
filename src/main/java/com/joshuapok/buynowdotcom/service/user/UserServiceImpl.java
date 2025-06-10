package com.joshuapok.buynowdotcom.service.user;

import com.joshuapok.buynowdotcom.Dto.UserDto;
import com.joshuapok.buynowdotcom.Repository.UserRepository;
import com.joshuapok.buynowdotcom.Requests.CreateUserRequest;
import com.joshuapok.buynowdotcom.Requests.UpdateUserRequest;
import com.joshuapok.buynowdotcom.model.User;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;


import java.util.Optional;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Override
    public User createUser(CreateUserRequest request) {
        //check if user exists before trying to create
        return Optional.of(request)
                .filter(user -> !userRepository.existsByEmail(request.getEmail()))
                .map(req -> {
                    User user = new User();
                    user.setFirstName(request.getFirstName());
                    user.setLastName(request.getLastName());
                    user.setEmail(request.getEmail());
                    user.setPassword(request.getPassword());
                    return userRepository.save(user);
                }).orElseThrow(() -> new EntityExistsException("User with this email already exists"));
    }

    @Override
    public User updateUser(UpdateUserRequest request, Long userId) {
        return userRepository.findById(userId).map(existingUser -> {
            existingUser.setFirstName(request.getFirstName());
            existingUser.setLastName(request.getLastName());
            return userRepository.save(existingUser);
        }).orElseThrow(() -> new EntityNotFoundException("User not found"));
    }

    @Override
    public User findUserById(Long userId) {
        return userRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException("User not found"));
    }

    @Override
    public void deleteUser(Long userId) {
        userRepository.findById(userId).ifPresentOrElse(userRepository::delete, () -> {throw new EntityNotFoundException("User not Found");});
    }

    public UserDto convertToDto(User user){
        UserDto userDto = modelMapper.map(user, UserDto.class);
        return userDto;
    }
}
