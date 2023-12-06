package com.sagarpr.todoapp.repository;

import com.sagarpr.todoapp.models.MyTodo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface MyTodoRepository extends JpaRepository<MyTodo,Long> {
    Optional<MyTodo> findByTitle(String title);

    @Query("select m from MyTodo m where m.title LIKE CONCAT('%',:query,'%')")
    List<MyTodo> searchMyToDos(String query);
}
