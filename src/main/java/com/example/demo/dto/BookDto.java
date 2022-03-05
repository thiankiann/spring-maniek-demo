package com.example.demo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

// DTO -> D-ata T-ransfer O-bject
public final class BookDto {

    private Long id;

    @JsonProperty("author")
    private String author;

    @JsonProperty("title")
    private String title;

    public BookDto(Long id, String author, String title) {
        this.id = id;
        this.author = author;
        this.title = title;
    }

    public Long getId() {
        return id;
    }

    public String getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public String toString() {
        return "BookDto{" +
                "id=" + id +
                ", author='" + author + '\'' +
                ", title='" + title + '\'' +
                '}';
    }
}
