package com.jcepeda.lean.demo.controllers;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.jcepeda.lean.demo.models.Position;
import com.jcepeda.lean.demo.repositories.PositionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class PositionRestController {

    @Autowired
    private final PositionRepository positionRepository;

    public PositionRestController(PositionRepository positionRepository ) {
        this.positionRepository =  positionRepository;
    }

    @GetMapping("/positions")
    public List<Position> getAllPositions()
    {
        return positionRepository.findAll();
    }

    @GetMapping("/positions/{id}")
    @JsonIgnoreProperties("employees")
    public ResponseEntity<Position> getPersonById(@PathVariable(value = "id") Long positionId)
    throws Exception {
        Position position = positionRepository.findById(positionId)
            .orElseThrow(() -> new Exception("Person not found for this id :: " + positionId));
        return ResponseEntity.ok().body(position);
    }

    @PostMapping("/positions")
    public Position createPosition(@RequestBody Position newPosition)
    {
    return positionRepository.save(newPosition);
    }
}
