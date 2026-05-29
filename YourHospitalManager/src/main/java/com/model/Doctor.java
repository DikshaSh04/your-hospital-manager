package com.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalTime;

@Entity
@Table(name = "doctors")
@Data
public class Doctor {

    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String specialization;


    @Schema(type = "string", example = "09:00:00")
    private LocalTime availableFrom;

    @Schema(type = "string", example = "17:00:00")
    private LocalTime availableTill;

}
