package ch.zhaw.sml.iwi.meng.leantodo.entity;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class TrainingSessionDTO {
    private String excerciseName;
    private Date trainingDate;
    private int durationMinutes;
    private int rep;
    
   
}

    

