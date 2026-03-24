package com.techup.springdemo;

import lombok.Data;

@Data
public class NoteRequest {
    private String title;
    private String content;
}