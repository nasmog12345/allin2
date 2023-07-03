package ch.zhaw.sml.iwi.meng.leantodo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ch.zhaw.sml.iwi.meng.leantodo.entity.Excercise;
import ch.zhaw.sml.iwi.meng.leantodo.entity.ExcerciseRepository;
import ch.zhaw.sml.iwi.meng.leantodo.entity.TrainingSession;
import ch.zhaw.sml.iwi.meng.leantodo.entity.TrainingSessionDTO;
import ch.zhaw.sml.iwi.meng.leantodo.entity.TrainingsSessionRepository;
import ch.zhaw.sml.iwi.meng.leantodo.entity.User;
import ch.zhaw.sml.iwi.meng.leantodo.entity.UserRepository;

@Component
public class TrainingController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ExcerciseRepository excerciseRepository;

    @Autowired
    private TrainingsSessionRepository trainingSessionRepository;

    public List<TrainingSession> getAllSessions(String username) {

        User u = userRepository.getReferenceById(username);

        return u.getSessions();

    }

   public void createTrainingSession(String username, TrainingSessionDTO sessionDTO) {
    User user = userRepository.findById(username).orElseThrow(() -> new RuntimeException("User not found"));
    Excercise excercise = excerciseRepository.findByName(sessionDTO.getExcerciseName()).orElseGet(() -> {
        Excercise newExcercise = new Excercise();
        newExcercise.setName(sessionDTO.getExcerciseName());
        newExcercise.setRep(sessionDTO.getRep());
        return excerciseRepository.save(newExcercise);
    });

    TrainingSession trainingSession = new TrainingSession();
    trainingSession.setTrainingDate(sessionDTO.getTrainingDate());
    trainingSession.setTrainingDurationMinutes(sessionDTO.getDurationMinutes());
    trainingSession.getExcercises().add(excercise);
    trainingSession.setUser(user);

    trainingSessionRepository.save(trainingSession);
}


}
