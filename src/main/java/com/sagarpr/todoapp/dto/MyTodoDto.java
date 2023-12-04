package com.sagarpr.todoapp.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MyTodoDto {
    private long id;
    private String title;
    private String description;
}
