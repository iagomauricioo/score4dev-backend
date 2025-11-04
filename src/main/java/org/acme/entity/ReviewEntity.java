package org.acme.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;

@Entity
@Table(name = "reviews")
public class ReviewEntity extends PanacheEntityBase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    public String review;
    public int rating;

    @ManyToOne
    @JoinColumn(name = "course_id", nullable = false)
    public CourseEntity course;
}
