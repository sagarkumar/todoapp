package com.sagarpr.todoapp.controllers;

import com.sagarpr.todoapp.dto.MyTodoDto;
import com.sagarpr.todoapp.services.MyTodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

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
        MyTodoDto myTodoDto = MyTodoDto.builder().build();
        model.addAttribute("myTodoDto", myTodoDto);
        return "create-mytodo";
    }

    @PostMapping("/createmytodo")
    public String saveMyTodo(@ModelAttribute("MyTodoDto") MyTodoDto myTodoDto ){
        this.myTodoService.save(myTodoDto);
        return "redirect:/mytodo";
    }
}
