package org.acme.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "courses")
public class CourseEntity extends PanacheEntityBase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    public String name;
    public String description;
    public String instructor;
    public String platform;
    public String category;
    public String url;

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    public List<ReviewEntity> reviewEntity;
}
