package com.example.mynote.note;

import jakarta.persistence.*;
import org.hibernate.id.factory.internal.SequenceGenerationTypeStrategy;

import java.time.LocalDateTime;

@Entity
@Table
public class Note {
    @Id
    @SequenceGenerator(
            name = "note_sequence",
            sequenceName = "note_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "note_sequence"
    )
    private Long id;
    private String name;
    private String detail;
    private LocalDateTime createdAt;

    public Note() {}

    public Note(Long id, String name, String detail, LocalDateTime createdAt) {
        this.id = id;
        this.name = name;
        this.detail = detail;
        this.createdAt = createdAt;
    }

    public Note(String name, String detail, LocalDateTime createdAt) {
        this.name = name;
        this.detail = detail;
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "Note{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", detail='" + detail + '\'' +
                ", createdAt=" + createdAt +
                '}';
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

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
