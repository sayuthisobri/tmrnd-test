package com.msms.tmtest.repo;

import com.msms.tmtest.model.Task;
import com.msms.tmtest.model.Team;
import org.springframework.data.repository.CrudRepository;

public interface TeamRepository extends CrudRepository<Team, String> {
}
