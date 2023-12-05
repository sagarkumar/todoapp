package com.sagarpr.todoapp.controllers;

import com.sagarpr.todoapp.dto.MyTodoDto;
import com.sagarpr.todoapp.models.MyTodo;
import com.sagarpr.todoapp.services.MyTodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class MyTodoController {

    private MyTodoService myTodoService;

    @Autowired
    public MyTodoController(MyTodoService myTodoService) {
        this.myTodoService = myTodoService;
    }

    @GetMapping("/mytodo")
    public String listMyTodos(Model model){
        List<MyTodoDto> myTodoDtos = this.myTodoService.findAllTodo();
        model.addAttribute("myTodos",myTodoDtos);
        return "mytodo-list";
    }

    @GetMapping("/createmytodo")
    public String createMyTodoForm(Model model){
        return "create-mytodo";
    }

    @PostMapping("/createmytodo")
    public String saveMyTodo(@RequestParam("title") String title,@RequestParam("description") String description){
        this.myTodoService.save(MyTodo.builder()
                .title(title)
                .description(description).build());
        return "redirect:/mytodo";
    }

    @GetMapping("/mytodo/{myTodoId}/edit")
    public String editMyTodoForm(@PathVariable("myTodoId") long myTodoId,Model model){
        MyTodoDto myTodoDto = this.myTodoService.findMyTodoById(myTodoId);
        model.addAttribute("myTodoDto",myTodoDto);
        return "edit-mytodo";
    }

    @PostMapping("/mytodo/{myTodoId}/edit")
    public String editMyTodo(@PathVariable("myTodoId") long myTodoId, @ModelAttribute("myTodoDto") MyTodoDto myTodoDto){
        myTodoDto.setId(myTodoId);
        this.myTodoService.updateMyTodo(myTodoDto);
        return "redirect:/mytodo";
    }
}
