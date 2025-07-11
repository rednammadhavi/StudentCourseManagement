package models;

public class Course {
    private String id;
    private String title;
    private String description;
    private int duration;
    private double fee;

    public Course(String id, String title, String description, int duration, double fee) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.duration = duration;
        this.fee = fee;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public String toString() {
        return id + " | " + title + " | " + duration + " weeks | ₹" + fee;
    }
}
