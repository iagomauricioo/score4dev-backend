package org.acme.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityNotFoundException;
import org.acme.entity.UserEntity;

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

    public UserEntity editUser(UUID userId, UserEntity user) {
        UserEntity userToUpdate = UserEntity.findById(userId);
        if (userToUpdate == null) {
            throw new EntityNotFoundException("User not found (userId: " + userId + ")");
        }
        if (user.username != null) {
            userToUpdate.username = user.username;
        }
        if (user.password != null) {
            userToUpdate.password = user.password;
        }
        if (user.email != null) {
            userToUpdate.email = user.email;
        }
        return userToUpdate;
    }

    public void deleteUser(UUID userId) {
        UserEntity user = UserEntity.findById(userId);
        if (user == null) {
            throw new EntityNotFoundException("User not found (userId: " + userId + ")");
        }
        UserEntity.deleteById(userId);
    }

    public UserEntity getUserById(UUID userId) {
        UserEntity user = UserEntity.findById(userId);
        if (user == null) {
            throw new EntityNotFoundException("User not found (userId: " + userId + ")");
        }
        return UserEntity.findById(userId);
    }
}
