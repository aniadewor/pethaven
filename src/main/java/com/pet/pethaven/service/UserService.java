package com.pet.pethaven.service;

import com.pet.pethaven.dto.UserDTO;
import com.pet.pethaven.exceptionHandler.EntityNotFoundException;
import com.pet.pethaven.model.Address;
import com.pet.pethaven.model.User;
import com.pet.pethaven.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.jspecify.annotations.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {
    public UserRepository userRepository;
    public PasswordEncoder passwordEncoder;

    public User saveUser(User user) {
        validatePhoneNumber(user.getPhoneNumber());
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
         return userRepository.save(user);
    }

    public Boolean validatePhoneNumber(String phoneNumber) {

        if (phoneNumber.matches("^[0-9]{9}$")) {
            return true;
        }
        else {
            throw new EntityNotFoundException("Not a valid phone number");
        }
    }
    public UserDTO getUserByEmail(String email) {
        if(email == null){
            throw new EntityNotFoundException("User email not found");
        }
        User user = userRepository.findByEmail(email);
        UserDTO userDTO = getUserDTO(email, user);
        return userDTO;
    }

    private static @NonNull UserDTO getUserDTO(String email, User user) {
        UserDTO userDTO = new UserDTO();
        Address address = new Address();
        userDTO.setEmail(email);
        userDTO.setFirstName(user.getFirstName());
        userDTO.setLastName(user.getLastName());
        userDTO.setPhoneNumber(email);
        userDTO.setAddress(address);
        address.setCity(user.getAddress().getCity());
        address.setCountry(user.getAddress().getCountry());
        address.setStreet(user.getAddress().getStreet());
        address.setZipCode(user.getAddress().getZipCode());
        address.setBuildingNumber(user.getAddress().getBuildingNumber());
        address.setApartmentNumber(user.getAddress().getApartmentNumber());
        userDTO.setAddress(address);
        return userDTO;
    }
}
