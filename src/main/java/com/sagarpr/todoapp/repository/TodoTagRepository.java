package com.sagarpr.todoapp.repository;

import com.sagarpr.todoapp.models.TodoTag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoTagRepository extends JpaRepository<TodoTag,Long> {
}
