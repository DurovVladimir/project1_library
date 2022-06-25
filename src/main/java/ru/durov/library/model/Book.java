package ru.durov.library.model;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

public class Book {
    private int id;
    @NotEmpty(message = "Поле 'Имя' не должно быть пустым")
    private String title;
    @NotEmpty(message = "Поле 'Автор' не должно быть пустым")
    private String author;
    @Min(value = 0, message = "Год выпуска должен быть больше 0")
    private int year;
    private String readerName;

    public Book() {
    }

    public Book(int id, String title, String author, int year, String readerName) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.year = year;
        this.readerName = readerName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getReaderName() {
        return readerName;
    }

    public void setReaderName(String readerName) {
        this.readerName = readerName;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
