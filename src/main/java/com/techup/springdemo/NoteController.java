package com.techup.springdemo;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notes")
public class NoteController {

    private final NoteService noteService;

    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    // 🔹 GET ALL
    @GetMapping
    public ResponseEntity<List<NoteResponse>> getAll() {
        return ResponseEntity.ok(noteService.getAll());
    }

    // 🔹 CREATE
    @PostMapping
    public ResponseEntity<NoteResponse> create(@RequestBody NoteRequest request) {
        return ResponseEntity.ok(noteService.create(request));
    }

    // 🔹 UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<NoteResponse> update(
            @PathVariable Long id,
            @RequestBody NoteRequest request
    ) {
        return ResponseEntity.ok(noteService.update(id, request));
    }

    // 🔹 DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        noteService.delete(id);
        return ResponseEntity.noContent().build();
    }
}