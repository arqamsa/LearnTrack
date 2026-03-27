package com.airtribe.learntrack.entity;

public class Trainer extends Person {

    private String specialization;

    public Trainer() {
        super();
    }

    public Trainer(int id, String firstName, String lastName, String email, String specialization) {
        super(id, firstName, lastName, email);
        this.specialization = specialization;
    }

    @Override
    public String getDisplayName() {
        return super.getDisplayName() + " [Trainer | " + specialization + "]";
    }

    public String getSpecialization() { return specialization; }
    public void setSpecialization(String specialization) { this.specialization = specialization; }
}
