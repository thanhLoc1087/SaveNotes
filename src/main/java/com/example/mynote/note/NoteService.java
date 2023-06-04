package com.example.mynote.note;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.crypto.Data;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class NoteService {
    private final NoteRepository repository;

    @Autowired
    public NoteService(NoteRepository repository) {
        this.repository = repository;
    }

    public List<Note> getNotes() {
        return repository.findAll();
    }

    public Note getNoteById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new IllegalStateException("Note with id " + id + " does not exist."));
    }

    public void addNewNote(Note note) {
        Optional<Note> noteByName = repository.findNoteByName(note.getName());
        if (noteByName.isPresent()) {
            throw new IllegalStateException("Note name existed.");
        }
        if (note.getName().length() == 0) {
            note.setName("My note");
        }
        if (note.getCreatedAt() == null) {
            note.setCreatedAt(LocalDateTime.now());
        }
        repository.save(note);
    }

    public void deleteNote(Long id) {
        boolean exists = repository.existsById(id);
        if (!exists) {
            throw new IllegalStateException("Note with id " + id + " does not exist.");
        }
        repository.deleteById(id);
    }

    @Transactional
    public void updateNote(Note newNote) {
        Note note = repository.findById(newNote.getId())
                .orElseThrow(() -> new IllegalStateException("Note with id " + newNote.getId() + " does not exist."));
        if (newNote.getName() != null &&
                newNote.getName().length() > 0 &&
                !Objects.equals(note.getName(), newNote.getName())) {
            Optional<Note> noteByName = repository.findNoteByName(note.getName());
            if (noteByName.isPresent()) {
                throw new IllegalStateException("Note name existed.");
            }
            note.setName(newNote.getName());
        }
        if (newNote.getDetail() != null &&
                newNote.getDetail().length() > 0 &&
                !Objects.equals(note.getDetail(), newNote.getDetail())) {
            note.setDetail(newNote.getDetail());
        }
    }
}
