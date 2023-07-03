package htwberlin.webtec;

import jakarta.persistence.*;
import org.springframework.web.bind.annotation.*;
@Entity
public class ExerciseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private Long id;
    private String name;
    private String muscleGroup;
    private double weight;
    private String category;

    public ExerciseEntity(){
    }

    public ExerciseEntity(String name, String muscleGroup, double weight, String category) {
        this.name = name;
        this.muscleGroup = muscleGroup;
        this.weight = weight;
        this.category = category;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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