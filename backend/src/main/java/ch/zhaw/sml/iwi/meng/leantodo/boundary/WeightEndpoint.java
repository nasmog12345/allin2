package ch.zhaw.sml.iwi.meng.leantodo.boundary;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import ch.zhaw.sml.iwi.meng.leantodo.controller.WeightController;
import ch.zhaw.sml.iwi.meng.leantodo.entity.Weight;

import java.util.List;

@RestController
public class WeightEndpoint {

    private final WeightController weightController;

    public WeightEndpoint(WeightController weightController) {
        this.weightController = weightController;
    }

    @PostMapping(path = "/api/weight")
    @PreAuthorize("isAuthenticated()")
    public Weight createWeight(@RequestBody Weight weight) {
        weight.setId(null);
        return weightController.createWeight(weight);
    }

    @GetMapping(path = "/api/weight")
    @PreAuthorize("isAuthenticated()")
    public List<Weight> getWeights() {
        return weightController.getWeights();
    }
}
