package com.sabbir.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.boot.autoconfigure.web.WebProperties;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "questions")
public class Tasks {
    @Id
    @Column(name = "questions_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String title;
    private String optionA;
    private String optionB;
    private String optionC;
    private int ans;
    private int chosen;
}
