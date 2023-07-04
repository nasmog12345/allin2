package ch.zhaw.sml.iwi.meng.leantodo.entity;

import java.util.Date; // Use java.util.Date instead of java.sql.Date
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Data
public class TrainingSession {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date trainingDate; // This is now a java.util.Date
    private int trainingDurationMinutes;
    private int rep;
    private String exercise;
}