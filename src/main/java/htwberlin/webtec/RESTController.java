package htwberlin.webtec;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/api/exercises")
public class RESTController {

    @Autowired
    ExerciseService exerciseService;

    @GetMapping("/api/exercises")
    public ResponseEntity<List<Exercise>> fetchExercise() {
        return ResponseEntity.ok(exerciseService.findAll());
    }

    @GetMapping("/api/exercise/{id}")
    public ResponseEntity<Exercise> fetchExerciseById(@PathVariable Long id) {
        var exercise = exerciseService.findById(id);
        return exercise != null ? ResponseEntity.ok(exercise) : ResponseEntity.notFound().build();
    }

    @PostMapping("/api/exercises")
    public ResponseEntity<Void> createExercise(@RequestBody ExerciseManipulationRequest request) throws URISyntaxException {
        var exercise = exerciseService.create(request);
        URI uri = new URI("/api/exercise/" + exercise.getId());
        return ResponseEntity.created(uri).build();
    }
}
