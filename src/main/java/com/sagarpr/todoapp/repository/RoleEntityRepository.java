package com.sagarpr.todoapp.repository;

import com.sagarpr.todoapp.models.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleEntityRepository extends JpaRepository<RoleEntity,Long> {

    RoleEntity findByName(String name);
}
