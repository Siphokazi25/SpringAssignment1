package com.example.springassignmentone;
import jakarta.persistence.*;
import lombok.*;
import java.io.Serializable;

@Entity
@Table(name = "courses")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Course implements Serializable {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "code", nullable = false)
    private int code;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "category", nullable = false )
    private String category;

    @Column(name = "fullCourseName", nullable = false, unique = true)
    private String fullCourseName;
}
