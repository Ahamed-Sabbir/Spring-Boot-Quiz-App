package com.sabbir.model;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "Results")
public class Result {
    @Id
    @Column(name = "Student_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String userName;
    private int totalCorrect = 0;
}
