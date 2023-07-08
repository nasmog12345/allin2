package ch.zhaw.sml.iwi.meng.leantodo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import ch.zhaw.sml.iwi.meng.leantodo.entity.Weight;
import ch.zhaw.sml.iwi.meng.leantodo.entity.WeightRepository;

@Controller
public class WeightController {
    private final WeightRepository weightRepository;

    @Autowired
    public WeightController(WeightRepository weightRepository) {
        this.weightRepository = weightRepository;
    }

    public Weight createWeight(Weight weight) {
        return weightRepository.save(weight);
    }

    public List<Weight> getWeights() {
        return weightRepository.findAll();
    }
}