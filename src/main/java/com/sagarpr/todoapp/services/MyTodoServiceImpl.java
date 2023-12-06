package com.sagarpr.todoapp.services;

import com.sagarpr.todoapp.dto.MyTodoDto;
import com.sagarpr.todoapp.models.MyTodo;
import com.sagarpr.todoapp.repository.MyTodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MyTodoServiceImpl implements MyTodoService{

    private MyTodoRepository myTodoRepository;

    @Autowired
    public MyTodoServiceImpl(MyTodoRepository myTodoRepository) {
        this.myTodoRepository = myTodoRepository;
    }

    @Override
    public List<MyTodoDto> findAllTodo() {
        List<MyTodo> myTodos = this.myTodoRepository.findAll();
        return myTodos.stream()
                .map(this::mapTodoToDto)
                .collect(Collectors.toList());
    }

    @Override
    public void save(MyTodo myTodo) {
        this.myTodoRepository.save(myTodo);
    }

    @Override
    public MyTodoDto findMyTodoById(long myTodoId) {
        MyTodo myTodo = this.myTodoRepository.findById(myTodoId).get();
        return mapTodoToDto(myTodo);
    }

    @Override
    public void updateMyTodo(MyTodoDto myTodoDto) {
        MyTodo myTodo = MyTodo.builder()
                .id(myTodoDto.getId())
                .title(myTodoDto.getTitle())
                .description(myTodoDto.getDescription())
                .build();
        this.myTodoRepository.save(myTodo);
    }

    @Override
    public void deleteMyTodo(long myTodoId) {
        this.myTodoRepository.deleteById(myTodoId);
    }

    private MyTodoDto mapTodoToDto(MyTodo x) {
        return MyTodoDto
                .builder()
                .id(x.getId())
                .title(x.getTitle())
                .description(x.getDescription())
                .build();
    }
}
