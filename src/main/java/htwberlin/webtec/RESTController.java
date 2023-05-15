package htwberlin.webtec;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/exercises")
public class RESTController {

    @Autowired
    private EntityRepository EntityRepository;

    @GetMapping
    public List<ExerciseEntity> getAllExercises() {
        return EntityRepository.findAll();
    }

    @PostMapping
    public ExerciseEntity createExercise(@RequestBody ExerciseEntity exercise) {
        return EntityRepository.save(exercise);
    }
}
