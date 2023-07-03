package htwberlin.webtec;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ExerciseEntityTests{

    private final ExerciseEntity exerciseEntity = new ExerciseEntity();

    @Test
    @DisplayName("should return the given Values")
    public void testExerciseEntity() {
        exerciseEntity.setId(1L);
        exerciseEntity.setName("Bench Press");
        exerciseEntity.setMuscleGroup("Brust");
        exerciseEntity.setWeight(40.0);
        exerciseEntity.setCategory("Push");

        assertEquals(1L, exerciseEntity.getId());
        assertEquals("Bench Press", exerciseEntity.getName());
        assertEquals("Brust", exerciseEntity.getMuscleGroup());
        assertEquals(40.0, exerciseEntity.getWeight());
        assertEquals("Push", exerciseEntity.getCategory());
    }

}
