package com.pet.pethaven.service;

import com.pet.pethaven.exceptionHandler.EntityNotFoundException;
import com.pet.pethaven.model.User;
import com.pet.pethaven.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {
    public UserRepository userRepository;

    public User saveUser(User user) {
        validatePhoneNumber(user.getPhoneNumber());
         return userRepository.save(user);
    }

    public Boolean validatePhoneNumber(String phoneNumber) {

        if (phoneNumber.matches("^(?=(?:[8-9]){1})(?=[0-9]{8}).*")) {
            return true;
        }
        else {
            throw new EntityNotFoundException("Not a valid phone number");
        }
    }
}
