package com.airtribe.learntrack.entity;

public class Student extends Person {

    private String batch;
    private boolean active;

    public Student() {
        super();
        this.active = true;
    }

    public Student(int id, String firstName, String lastName, String email, String batch) {
        super(id, firstName, lastName, email);
        this.batch = batch;
        this.active = true;
    }

    public Student(int id, String firstName, String lastName, String batch) {
        super(id, firstName, lastName);
        this.batch = batch;
        this.active = true;
    }

    @Override
    public String getDisplayName() {
        return super.getDisplayName() + " [Batch: " + batch + "]";
    }

    public String getBatch() { return batch; }
    public void setBatch(String batch) { this.batch = batch; }

    public boolean isActive() { return active; }
    public void setActive(boolean active) { this.active = active; }

    @Override
    public String toString() {
        return String.format("Student{id=%d, name=%s, email=%s, batch=%s, active=%s}",
                getId(), getFirstName() + " " + getLastName(),
                getEmail().isEmpty() ? "-" : getEmail(), batch, active);
    }
}
