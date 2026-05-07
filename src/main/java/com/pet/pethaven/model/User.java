package com.pet.pethaven.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "user")
public record User(
        @Id
        String id,
        String firstName,
        String lastName,
        String email,
        String password,
        String phoneNumber,
        Address address
) {
        public User setPassword (String password) {
                return new User(this.id, this.firstName, this.lastName, this.email, password, phoneNumber, address);
                }
        }

