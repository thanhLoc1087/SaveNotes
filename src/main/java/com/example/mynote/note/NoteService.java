package com.example.mynote.note;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public void addNewNote(Note note) {
        Optional<Note> noteByName = repository.findNoteByName(note.getName());
        if (noteByName.isPresent()) {
            throw new IllegalStateException("Note name existed.");
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
    public void updateNote(Long noteId, String name, String detail) {
        Note note = repository.findById(noteId)
                .orElseThrow(() -> new IllegalStateException("Note with id \" + id + \" does not exist."));
        if (name != null && name.length() > 0 && !Objects.equals(note.getName(), name)){
            Optional<Note> noteByName = repository.findNoteByName(note.getName());
            if (noteByName.isPresent()) {
                throw new IllegalStateException("Note name existed.");
            }
            note.setName(name);
        }
        if (detail != null && detail.length() > 0 && !Objects.equals(note.getDetail(), detail)){
            note.setDetail(detail);
        }
    }
}
