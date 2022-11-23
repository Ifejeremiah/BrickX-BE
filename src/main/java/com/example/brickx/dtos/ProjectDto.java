package com.example.brickx.dtos;

import com.example.brickx.entities.Application;
import com.example.brickx.entities.Contractor;
import com.example.brickx.entities.Worker;
import com.example.brickx.entities.enums.ProjectStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProjectDto {
    private int id;
    private String title;
    private String duration;
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime startDate;
    private int budget;

    private String jobs;

    public ProjectDto(String title, String duration, LocalDateTime startDate, int budget, String jobs) {
        this.title = title;
        this.duration = duration;
        this.startDate = startDate;
        this.budget = budget;
        this.jobs = jobs;
    }

    public ProjectDto(String title, String duration, LocalDateTime startDate, int budget) {
        this.title = title;
        this.duration = duration;
        this.startDate = startDate;
        this.budget = budget;
    }
}
