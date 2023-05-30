package com.example.mynote.note;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1/notes")
public class NoteController {
    private final NoteService noteService;

    @Autowired
    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    @GetMapping
    public List<Note> getNotes() {
        return noteService.getNotes();
    }
    @PostMapping
    public void registerNewNote(@RequestBody Note note) {
        noteService.addNewNote(note);
    }
    @DeleteMapping(path="{noteId}")
    public void deleteNote(@PathVariable("noteId") Long noteId) {
        noteService.deleteNote(noteId);
    }
    @PutMapping(path="{noteId}")
    public void updateNote(
            @PathVariable("noteId") Long noteId,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String detail
    ) {
        noteService.updateNote(noteId, name, detail);
    }
}
