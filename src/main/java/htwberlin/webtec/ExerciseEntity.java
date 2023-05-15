package htwberlin.webtec;

import jakarta.persistence.*;
import org.springframework.web.bind.annotation.*;
@Entity(name = "exercises")
public class ExerciseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;
    private String muscleGroup;
    private double weight;
    private String category;


    public ExerciseEntity(String name, String muscleGroup, double weight, String category) {
        this.name = name;
        this.muscleGroup = muscleGroup;
        this.weight = weight;
        this.category = category;
    }

    protected ExerciseEntity() {}

    public long getId() {
        return id;
    }


    public String getName() {

        return name;
    }

    public void setName(String name) {

        this.name = name;
    }

    public String getMuscleGroup() {

        return muscleGroup;
    }

    public void setMuscleGroup(String muscleGroup) {

        this.muscleGroup = muscleGroup;
    }

    public double getWeight() {

        return weight;
    }

    public void setWeight(double weight) {

        this.weight = weight;
    }

    public String getCategory() {

        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}


