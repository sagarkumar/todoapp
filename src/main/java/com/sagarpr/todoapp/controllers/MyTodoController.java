package com.sagarpr.todoapp.controllers;

import com.sagarpr.todoapp.dto.MyTodoDto;
import com.sagarpr.todoapp.models.MyTodo;
import com.sagarpr.todoapp.services.MyTodoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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

    @GetMapping("/mytodo/{myTodoId}")
    public String getDetailedMyTodo(@PathVariable("myTodoId") long myTodoId, Model model){
        MyTodoDto myTodoDto = this.myTodoService.findMyTodoById(myTodoId);
        model.addAttribute("myTodoDto",myTodoDto);
        return "mytodo-detail";
    }

    @GetMapping("/mytodo/{myTodoId}/delete")
    public String deleteMyTodo(@PathVariable("myTodoId") long myTodoId){
        this.myTodoService.deleteMyTodo(myTodoId);
        return "redirect:/mytodo";
    }

    @GetMapping("/createmytodo")
    public String createMyTodoForm(Model model){
        model.addAttribute("myTodoDto",MyTodoDto.builder().build());
        return "create-mytodo";
    }

    @PostMapping("/createmytodo")
    public String saveMyTodo(@Valid @ModelAttribute("myTodoDto") MyTodoDto myTodoDto, BindingResult result,Model model ){

        if(result.hasErrors()){
            model.addAttribute("myTodoDto",myTodoDto);
            return "create-mytodo";
        }

        this.myTodoService.save(MyTodo.builder()
                .title(myTodoDto.getTitle())
                .description(myTodoDto.getDescription()).build());
        return "redirect:/mytodo";
    }

    @GetMapping("/mytodo/{myTodoId}/edit")
    public String editMyTodoForm(@PathVariable("myTodoId") long myTodoId,Model model){
        MyTodoDto myTodoDto = this.myTodoService.findMyTodoById(myTodoId);
        model.addAttribute("myTodoDto",myTodoDto);
        return "edit-mytodo";
    }

    @PostMapping("/mytodo/{myTodoId}/edit")
    public String editMyTodo(@PathVariable("myTodoId") long myTodoId, @Valid @ModelAttribute("myTodoDto") MyTodoDto myTodoDto, BindingResult result, Model model){

        if(result.hasErrors()){
            model.addAttribute("myTodoDto",myTodoDto);
            return "edit-mytodo";
        }

        myTodoDto.setId(myTodoId);
        this.myTodoService.updateMyTodo(myTodoDto);
        return "redirect:/mytodo";
    }
}
