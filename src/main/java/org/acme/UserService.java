package org.acme;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityNotFoundException;

import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class UserService {

    public List<UserEntity> getUsers() {
        return UserEntity.listAll();
    }

    public UserEntity createUser(UserEntity user) {
        UserEntity.persist(user);
        return user;
    }

    public UserEntity getUserById(UUID userId) {
        UserEntity user = UserEntity.findById(userId);
        if (user == null) {
            throw new EntityNotFoundException("User not found (userId: " + userId + ")");
        }
        return UserEntity.findById(userId);
    }
}
