package com.techup.springdemo;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class NoteResponse {
    private Long id;
    private String title;
    private String content;
}