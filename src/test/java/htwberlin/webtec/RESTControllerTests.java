package htwberlin.webtec;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


import java.util.List;
import java.util.Optional;

@WebMvcTest(RESTController.class)
public class RESTControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EntityRepository entityRepository;

    @Test
    @DisplayName("should return all Exercises from entityRepository")
    public void getAllExercisesTest() throws Exception {
        ExerciseEntity e1 = new ExerciseEntity("Bench Press", "Brust", 40.0, "Push");
        e1.setId(1L);
        ExerciseEntity e2 = new ExerciseEntity("Curl", "Arm", 20.0, "Pull");
        e2.setId(2L);
        var exerciseList = List.of(e1, e2);

        doReturn(exerciseList).when(entityRepository).findAll();

        mockMvc.perform(get("/api/exercises"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1L))
                .andExpect(jsonPath("$[0].name").value("Bench Press"))
                .andExpect(jsonPath("$[0].muscleGroup").value("Brust"))
                .andExpect(jsonPath("$[0].weight").value(40.0))
                .andExpect(jsonPath("$[0].category").value("Push"))
                .andExpect(jsonPath("$[1].id").value(2L))
                .andExpect(jsonPath("$[1].name").value("Curl"))
                .andExpect(jsonPath("$[1].muscleGroup").value("Arm"))
                .andExpect(jsonPath("$[1].weight").value(20.0))
                .andExpect(jsonPath("$[1].category").value("Pull"));
    }

    @Test
    @DisplayName("should return the right name of the new Exercise")
    void createExerciseTest() throws Exception {
        ExerciseEntity e1 = new ExerciseEntity("Bench Press", "Brust", 40.0, "Push");
        e1.setId(1L);

        doReturn(e1).when(entityRepository).save(any(ExerciseEntity.class));

        mockMvc.perform(post("/api/exercises")
                        .contentType("application/json")
                        .content("{\"name\": \"Bench Press\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.name").value("Bench Press"));

        ArgumentCaptor<ExerciseEntity> exerciseCaptor = ArgumentCaptor.forClass(ExerciseEntity.class);
        verify(entityRepository).save(exerciseCaptor.capture());
        assertEquals("Bench Press", exerciseCaptor.getValue().getName());
    }

    @Test
    @DisplayName("should return the updated weight of the exercise")
    public void updateExerciseTest() throws Exception {
        ExerciseEntity existingExercise = new ExerciseEntity("Bench Press", "Brust", 40.0, "Push");
        existingExercise.setId(1L);
        ExerciseEntity updatedExercise = new ExerciseEntity("Bench Press", "Brust", 80.0, "Push");
        updatedExercise.setId(1L);

        doReturn(Optional.of(existingExercise)).when(entityRepository).findById(1L);
        doReturn(updatedExercise).when(entityRepository).save(any(ExerciseEntity.class));

        mockMvc.perform(MockMvcRequestBuilders.put("/api/exercises/1")
                        .contentType("application/json")
                        .content("{\"weight\": 80.0}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.name").value("Bench Press"))
                .andExpect(jsonPath("$.weight").value(80.0));
    }
    @Test
    public void deleteExerciseTest1() throws Exception {
        long l = 1L;

        doReturn(true).when(entityRepository).existsById(l);

        mockMvc.perform(delete("/api/exercises/{id}", l))
                .andExpect(MockMvcResultMatchers.status().isOk());

        verify(entityRepository).deleteById(l);
    }


}