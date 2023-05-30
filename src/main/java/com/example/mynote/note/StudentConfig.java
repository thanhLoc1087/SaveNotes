package com.example.mynote.note;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;
import java.util.List;

@Configuration
public class StudentConfig {
    @Bean
    CommandLineRunner commandLineRunner(NoteRepository repository) {
        return ars -> {
            Note note1 = new Note("Note 1", "first note of the day", LocalDateTime.now());
            Note note2 = new Note("Note 2", "second note of the day", LocalDateTime.now());
            repository.saveAll(List.of(note1, note2));
        };
    }
}
