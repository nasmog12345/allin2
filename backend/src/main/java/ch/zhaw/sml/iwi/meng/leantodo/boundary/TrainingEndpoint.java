package ch.zhaw.sml.iwi.meng.leantodo.boundary;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import ch.zhaw.sml.iwi.meng.leantodo.controller.TrainingController;
import ch.zhaw.sml.iwi.meng.leantodo.entity.TrainingSession;

import java.util.List;

@RestController
@RequestMapping("/api/sessions")
public class TrainingEndpoint {

    private final TrainingController trainingController;

    @Autowired
    public TrainingEndpoint(TrainingController trainingController) {
        this.trainingController = trainingController;
    }

    @PostMapping
    public TrainingSession createTrainingSession(@RequestBody TrainingSession trainingSession) {
        return trainingController.createTrainingSession(trainingSession);
    }

    @GetMapping
    public List<TrainingSession> getTrainingSessions() {
        return trainingController.getTrainingSessions();
    }
}
