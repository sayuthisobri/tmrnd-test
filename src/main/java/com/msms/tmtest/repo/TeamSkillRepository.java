package com.msms.tmtest.repo;

import com.msms.tmtest.model.Team;
import com.msms.tmtest.model.TeamSkill;
import org.springframework.data.repository.CrudRepository;

import java.util.Set;

public interface TeamSkillRepository extends CrudRepository<TeamSkill, String> {
    Set<TeamSkill> findAllByTeam(Team team);
}
