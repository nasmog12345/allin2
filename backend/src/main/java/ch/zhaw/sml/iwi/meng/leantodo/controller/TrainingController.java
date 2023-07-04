package ch.zhaw.sml.iwi.meng.leantodo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import ch.zhaw.sml.iwi.meng.leantodo.entity.TrainingSession;
import ch.zhaw.sml.iwi.meng.leantodo.entity.TrainingSessionRepository;

import java.util.List;

@Controller
public class TrainingController {

    private final TrainingSessionRepository trainingSessionRepository;

    @Autowired
    public TrainingController(TrainingSessionRepository trainingSessionRepository) {
        this.trainingSessionRepository = trainingSessionRepository;
    }

    public TrainingSession createTrainingSession(TrainingSession trainingSession) {
        return trainingSessionRepository.save(trainingSession);
    }

    public List<TrainingSession> getTrainingSessions() {
        return trainingSessionRepository.findAll();
    }
}
