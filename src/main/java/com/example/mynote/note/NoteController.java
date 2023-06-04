package com.example.mynote.note;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class NoteController {
    private final NoteService noteService;

    @Autowired
    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    // Main view
    @GetMapping("/")
    public String getNotes(Note note, Model model) {
        model.addAttribute("notes", noteService.getNotes());
        return "index";
    }

    // Read Note
    @GetMapping("{noteId}")
    public String getNote(@PathVariable("noteId") Long noteId, Model model) {
        Note note = noteService.getNoteById(noteId);
        model.addAttribute("note", note);
        return "view_note";
    }

    // Add Notes
    @GetMapping(path = "add")
    public String addNewNote(Note note) {
        return "add_note";
    }

    @PostMapping(path = "add")
    public String registerNewNote(Note note, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "add_note";
        }
        noteService.addNewNote(note);
        return "redirect:/";
    }

    // Delete Note
    @DeleteMapping(path = "delete/{noteId}")
    public void deleteNote(@PathVariable("noteId") Long noteId) {
        noteService.deleteNote(noteId);
    }

    @GetMapping(path = "delete/{noteId}")
    public String delete(@PathVariable("noteId") Long noteId) {
        noteService.deleteNote(noteId);
        return "redirect:/";
    }

    // Update Note
    @GetMapping(path = "edit/{noteId}")
    public String UpdateForm(@PathVariable("noteId") Long noteId, Model model) {
        Note note = noteService.getNoteById(noteId);
        model.addAttribute("note", note);
        return "update_note";
    }

    @PutMapping(path = "edit/{noteId}")
    public void update(
            @PathVariable("noteId") Long noteId,
            Note note
    ) {
        noteService.updateNote(note);
    }

    @PostMapping(value = "edit/{noteId}")
    public String updateNote(
            @PathVariable("noteId") Long noteId,
            Note note,
            BindingResult result,
            Model model
    ) {
        if (result.hasErrors()) {
            return "update-user";
        }
        note.setId(noteId);
        noteService.updateNote(note);
        return "redirect:/";
    }
}
