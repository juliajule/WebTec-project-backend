package htwberlin.webtec;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.contains;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@WebMvcTest(RESTController.class)
public class RESTControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EntityRepository entityRepository;

    @Test
    public void testGetAllExercises() throws Exception {

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
}
