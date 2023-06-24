package htwberlin.webtec;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@RequestMapping("/api/exercises")
public class RESTController {

    @Autowired
    private EntityRepository EntityRepository;

    @GetMapping("/api/exercises")
    public List<ExerciseEntity> getAllExercises() {
        return EntityRepository.findAll();
    }

    @PostMapping("/api/exercises")
    public ExerciseEntity createExercise(@RequestBody ExerciseEntity exercise) {
        return EntityRepository.save(exercise);
    }

    @PutMapping("/api/exercises/{id}")
    public ExerciseEntity updateExercise(@PathVariable long id, @RequestBody ExerciseEntity exercise){
        if (EntityRepository.findById(id).isEmpty())
            return null;
        var exerciseEntity = EntityRepository.findById(id).get();
        exerciseEntity.setWeight(exercise.getWeight());
        return EntityRepository.save(exerciseEntity);
    }

    @DeleteMapping("/api/exercises/{id}")
    public void deleteExercise(@PathVariable long id){
        if (!EntityRepository.existsById(id))
            throw new RuntimeException();
        EntityRepository.deleteById(id);
    }
}
