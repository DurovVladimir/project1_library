package ru.durov.library.model;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

public class Person {
    private int id;
    @NotEmpty(message = "Поле имя не должно быть пустым")
    private String fullName;

    @Min(value = 1920, message = "Год рождения должен быть больше 1920")
    private int birthYear;

    public Person(int id, String fullName, int birthYear) {
        this.id = id;
        this.fullName = fullName;
        this.birthYear = birthYear;
    }

    public Person() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(int birthYear) {
        this.birthYear = birthYear;
    }
}
