package org.acme;

import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public class UserService {

    public List<UserEntity> getUsers() {
        return UserEntity.listAll();
    }

    public UserEntity createUser(UserEntity user) {
        UserEntity.persist(user);
        return user;
    }
}
