package com.sagarpr.todoapp.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MyTodoSearchDto {
    public String query;
}
