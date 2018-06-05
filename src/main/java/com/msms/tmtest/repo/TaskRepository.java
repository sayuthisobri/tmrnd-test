package com.msms.tmtest.repo;

import com.msms.tmtest.model.Task;
import org.springframework.data.repository.CrudRepository;

import java.util.Set;

public interface TaskRepository extends CrudRepository<Task, String> {
    Set<Task> findAllBySkill(String skill);
}
