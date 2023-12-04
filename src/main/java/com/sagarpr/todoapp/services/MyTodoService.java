package com.sagarpr.todoapp.services;

import com.sagarpr.todoapp.dto.MyTodoDto;

import java.util.List;

public interface MyTodoService {
    List<MyTodoDto> findAllTodo();
}
