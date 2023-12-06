package com.sagarpr.todoapp.services;

import com.sagarpr.todoapp.dto.TodoTagDto;

import java.util.List;

public interface TodoTagService {
    void createTodoTag(long myTodoId, TodoTagDto todoTagDto);

    List<TodoTagDto> getAllTodoTags();
}
