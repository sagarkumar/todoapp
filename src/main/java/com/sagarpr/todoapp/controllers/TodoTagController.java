package com.sagarpr.todoapp.controllers;

import com.sagarpr.todoapp.dto.TodoTagDto;
import com.sagarpr.todoapp.services.TodoTagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class TodoTagController  {

    private TodoTagService todoTagService;

    @Autowired
    public TodoTagController(TodoTagService todoTagService) {
        this.todoTagService = todoTagService;
    }

    @GetMapping("/todotag/{myTodoId}/create")
    public String createTodoTagForm(@PathVariable("myTodoId") long myTodoId, Model model){
        TodoTagDto todoTagDto = TodoTagDto.builder().build();
        model.addAttribute("myTodoId",myTodoId);
        model.addAttribute("todoTagDto",todoTagDto);
        return "todotag-create";
    }

    @PostMapping("/todotag/{myTodoId}")
    public String saveTodoTag(@PathVariable("myTodoId") long myTodoId,@ModelAttribute("todoTagDto") TodoTagDto todoTagDto, Model model){
        this.todoTagService.createTodoTag(myTodoId,todoTagDto);
        return "redirect:/mytodo/"+ myTodoId;
    }

    @GetMapping("/alltodotags")
    public String getAllEvents(Model model){
        List<TodoTagDto> todoTagDtoList = this.todoTagService.getAllTodoTags();
        model.addAttribute("todoTagDtoList",todoTagDtoList);
        return "alltodotag-details";
    }
}
