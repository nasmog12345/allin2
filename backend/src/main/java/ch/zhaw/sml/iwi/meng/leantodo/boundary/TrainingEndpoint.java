package ch.zhaw.sml.iwi.meng.leantodo.boundary;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import ch.zhaw.sml.iwi.meng.leantodo.controller.TrainingController;
import ch.zhaw.sml.iwi.meng.leantodo.entity.TrainingSession;

import java.util.List;

@RestController
public class TrainingEndpoint {

    private final TrainingController trainingController;

    public TrainingEndpoint(TrainingController trainingController) {
        this.trainingController = trainingController;
    }

    @PostMapping(path = "/api/sessions")
    @PreAuthorize("isAuthenticated()")
    public TrainingSession createTrainingSession(@RequestBody TrainingSession trainingSession) {
        trainingSession.setId(null);
        return trainingController.createTrainingSession(trainingSession);
    }

    @GetMapping(path = "/api/sessions")
    @PreAuthorize("isAuthenticated()")
    public List<TrainingSession> getTrainingSessions() {
        return trainingController.getTrainingSessions();
    }
}
