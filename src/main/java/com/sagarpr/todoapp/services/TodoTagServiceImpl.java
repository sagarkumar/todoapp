package com.sagarpr.todoapp.services;

import com.sagarpr.todoapp.dto.TodoTagDto;
import com.sagarpr.todoapp.models.MyTodo;
import com.sagarpr.todoapp.models.TodoTag;
import com.sagarpr.todoapp.repository.MyTodoRepository;
import com.sagarpr.todoapp.repository.TodoTagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TodoTagServiceImpl implements TodoTagService{

    private TodoTagRepository todoTagRepository;
    private MyTodoRepository myTodoRepository;

    @Autowired
    public TodoTagServiceImpl(TodoTagRepository todoTagRepository, MyTodoRepository myTodoRepository) {
        this.todoTagRepository = todoTagRepository;
        this.myTodoRepository = myTodoRepository;
    }

    @Override
    public void createTodoTag(long myTodoId, TodoTagDto todoTagDto) {
        MyTodo myTodo = this.myTodoRepository.findById(myTodoId).get();
        TodoTag todoTag = mapDtoToTodoTag(todoTagDto);
        todoTag.setMyTodo(myTodo);
        this.todoTagRepository.save(todoTag);

    }

    @Override
    public List<TodoTagDto> getAllTodoTags() {
        List<TodoTag> todoTagList = this.todoTagRepository.findAll();
        return todoTagList.stream().map(this::mapToEventDto).collect(Collectors.toList());
    }

    @Override
    public TodoTagDto getTodoTagDetails(long todoTagId) {
        TodoTag todoTag = this.todoTagRepository.findById(todoTagId).get();
        return mapToEventDto(todoTag);
    }

    private TodoTagDto mapToEventDto(TodoTag todoTag) {
        return TodoTagDto.builder()
                .id(todoTag.getId())
                .tagName(todoTag.getTagName())
                .tagValue(todoTag.getTagValue()).build();
    }

    private TodoTag mapDtoToTodoTag(TodoTagDto todoTagDto) {
        return  TodoTag.builder()
                .id(todoTagDto.getId())
                .tagName(todoTagDto.getTagName())
                .tagValue(todoTagDto.getTagValue()).build();
    }
}
