package com.example.assinment2;

import java.util.List;

public class Book {
    private int id;
    private String title;
    private String author;
    private int publicationYear;
    private List<String> genre;
    private String description;
    private String coverImage;

    public Book(int id, String title, String author, int publicationYear, List<String> genre, String description, String coverImage) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.publicationYear = publicationYear;
        this.genre = genre;
        this.description = description;
        this.coverImage = coverImage;
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

    public int getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(int publicationYear) {
        this.publicationYear = publicationYear;
    }

    public List<String> getGenre() {
        return genre;
    }

    public void setGenre(List<String> genre) {
        this.genre = genre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCoverImage() {
        return coverImage;
    }

    public void setCoverImage(String coverImage) {
        this.coverImage = coverImage;
    }

    @Override
    public String toString() {
        return
                "\n\tid=" + id +
                ",\n\ttitle='" + title + '\'' +
                ",\n\tauthor='" + author + '\'' +
                ",\n\tpublicationYear=" + publicationYear +
                ",\n\tgenre=" + genre +
                ",\n\tdescription='" + description + '\'' +
                ",\n\tcoverImage='" + coverImage + '\'' +
                "\n";
    }

}
