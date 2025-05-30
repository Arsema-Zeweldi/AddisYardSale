package org.web2.addisyardsale.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.web2.addisyardsale.model.Condition;
import org.web2.addisyardsale.repository.ConditionRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/conditions")
public class ConditionController {

    @Autowired
    private ConditionRepository conditionRepository;

    @GetMapping
    public List<Condition> getAllConditions() {
        return conditionRepository.findAll();
    }

    @GetMapping("/{conditionId}")
    public Optional<Condition> getContiton(@PathVariable Long conditionId) {
        return conditionRepository.findById(conditionId);
    }

    @PostMapping
    public Condition createCondition(@RequestBody Condition condition) {
        return conditionRepository.save(condition);
    }
}
