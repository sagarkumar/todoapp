package com.sagarpr.todoapp.repository;

import com.sagarpr.todoapp.models.MyTodo;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.swing.text.html.Option;
import java.util.Optional;

public interface MyTodoRepository extends JpaRepository<MyTodo,Long> {
    Optional<MyTodo> findByTitle(String title);
}
