package com.techup.springdemo;

import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class NoteService {

    private final NoteRepository noteRepository;

    public NoteService(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    //  GET ALL
    public List<NoteResponse> getAll() {
        return noteRepository.findAll()
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    // 🔹 CREATE
    public NoteResponse create(NoteRequest request) {
        Note note = Note.builder()
                .title(request.getTitle())
                .content(request.getContent())
                .build();

        return toResponse(noteRepository.save(note));
    }

    // 🔹 UPDATE
    public NoteResponse update(Long id, NoteRequest request) {
        Note existing = noteRepository.findById(id).orElseThrow();

        existing.setTitle(request.getTitle());
        existing.setContent(request.getContent());

        return toResponse(noteRepository.save(existing));
    }

    //  DELETE
    public void delete(Long id) {
        noteRepository.deleteById(id);
    }

    //  helper แปลง Entity → Response
    private NoteResponse toResponse(Note note) {
        return NoteResponse.builder()
                .id(note.getId())
                .title(note.getTitle())
                .content(note.getContent())
                .build();
    }
}