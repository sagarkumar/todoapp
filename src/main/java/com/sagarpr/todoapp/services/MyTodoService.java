package com.sagarpr.todoapp.services;

import com.sagarpr.todoapp.dto.MyTodoDto;
import com.sagarpr.todoapp.models.MyTodo;

import java.util.List;

public interface MyTodoService {
    List<MyTodoDto> findAllTodo();

    void save(MyTodo myTodoDto);

    MyTodoDto findMyTodoById(long myTodoId);

    void updateMyTodo(MyTodoDto myTodoDto);

    void deleteMyTodo(long myTodoId);
}
