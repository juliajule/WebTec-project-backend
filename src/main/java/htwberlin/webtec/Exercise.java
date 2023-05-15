package htwberlin.webtec;

public class Exercise {
    private long id;
    private String name;
    private String muscleGroup;
    private double weight;
    private String category;

    public Exercise(long id, String name, String muscleGroup, double weight, String category) {
        this.id = id;
        this.name = name;
        this.muscleGroup = muscleGroup;
        this.weight = weight;
        this.category = category;
    }

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
