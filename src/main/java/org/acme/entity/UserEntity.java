package org.acme.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;
import org.acme.entity.vo.Username;

import java.util.UUID;

@Entity
@Table(name = "users")
public class UserEntity extends PanacheEntityBase {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    public UUID userId;
    @Embedded
    public Username username;
    public String password;
    public String email;

    protected UserEntity() {}

    public UserEntity(Username username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public UUID getUserId() {
        return userId;
    }
}
