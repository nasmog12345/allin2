package ch.zhaw.sml.iwi.meng.leantodo.boundary;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ch.zhaw.sml.iwi.meng.leantodo.controller.TrainingController;
import ch.zhaw.sml.iwi.meng.leantodo.entity.TrainingSession;
import ch.zhaw.sml.iwi.meng.leantodo.entity.TrainingSessionDTO;

@RestController
public class TrainingEndpoint {

    @Autowired
    private TrainingController trainingController;

    @RequestMapping(path = "/api/sessions", method = RequestMethod.GET)
    @PreAuthorize("isAuthenticated()")
    public List<TrainingSession> getAllTrainingSessions(Principal principal) {
        return trainingController.getAllSessions(principal.getName());
    }

    @RequestMapping(path = "/api/sessions", method = RequestMethod.POST)
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Void> createTrainingSession(Principal principal, @RequestBody TrainingSessionDTO sessionDTO) {
        trainingController.createTrainingSession(principal.getName(), sessionDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
