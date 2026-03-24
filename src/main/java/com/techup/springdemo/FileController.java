package com.techup.springdemo;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@RestController
@RequestMapping("/files")
public class FileController {

    private final SupabaseService supabaseService;

    public FileController(SupabaseService supabaseService) {
        this.supabaseService = supabaseService;
    }

    @PostMapping("/upload")
    public Map<String, String> upload(@RequestParam("file") MultipartFile file) {
        String url = supabaseService.uploadFile(file);
        return Map.of("url", url);
    }
}