package com.msms.tmtest.repo;

import com.msms.tmtest.model.AssignmentResult;
import com.msms.tmtest.model.Task;
import org.springframework.data.repository.CrudRepository;

import java.util.Set;

public interface AssignmentResultRepository extends CrudRepository<AssignmentResult, String> {
}
