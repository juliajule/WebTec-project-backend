package htwberlin.webtec;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ExerciseService {

    @Autowired
    EntityRepository exerciseRepository;

    //gibt Liste von allen Übungen zurück
    public List<Exercise> findAll() {
        List<ExerciseEntity> exercise = exerciseRepository.findAll();
        return exercise.stream()
                .map(this::transformEntity)
                .collect(Collectors.toList());
    }

    //findById() gibt optional ÜbungEntity zurück -> Wrapper Klasse um die Entity, wenn die Rückgabe ein konkretes Objekt oder null sein könnte
    public Exercise findById(Long id) {
        var exerciseEntity = exerciseRepository.findById(id);
        return exerciseEntity.map(this::transformEntity).orElse(null);
    }

    //Create, um einen neuen Datensatz zu erstellen
    public Exercise create(ExerciseManipulationRequest request) {
        var exerciseEntity = new ExerciseEntity(request.getName(), request.getMuscleGroup(), request.getWeight(), request.getCategory());
        exerciseEntity = exerciseRepository.save(exerciseEntity);
        return transformEntity(exerciseEntity);
    }

    //Methode, damit eine Entity in eine Übung gemappt wird
    private Exercise transformEntity(ExerciseEntity exerciseEntity) {
        return new Exercise(
                exerciseEntity.getId(),
                exerciseEntity.getName(),
                exerciseEntity.getMuscleGroup(),
                exerciseEntity.getWeight(),
                exerciseEntity.getCategory()
        );
    }
}
