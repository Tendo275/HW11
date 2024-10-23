package ru.dmalomoshin.tasks.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "tasks")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String description;

    @Column
    @Enumerated(EnumType.STRING)
    private TaskStatus status;

    @Column(name = "date_of_creation")
    private LocalDateTime creationDate = LocalDateTime.now().withNano(0);

}
