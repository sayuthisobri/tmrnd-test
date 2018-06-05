package com.msms.tmtest.controller;

import com.msms.tmtest.model.AssignmentResult;
import com.msms.tmtest.model.Task;
import com.msms.tmtest.model.Team;
import com.msms.tmtest.model.TeamSkill;
import com.msms.tmtest.repo.AssignmentResultRepository;
import com.msms.tmtest.repo.TaskRepository;
import com.msms.tmtest.repo.TeamRepository;
import com.msms.tmtest.repo.TeamSkillRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@Log4j2
public class WebController {
    @Autowired
    TaskRepository taskRepository;
    @Autowired
    TeamRepository teamRepository;
    @Autowired
    TeamSkillRepository teamSkillRepository;
    @Autowired
    AssignmentResultRepository resultRepository;

    @RequestMapping("/")
    public String index() {
        return "Ok";
    }

    @RequestMapping("/task")
    public List<Task> getTasks() {
        List<Task> tasks = new ArrayList<>();
        taskRepository.findAll().forEach(tasks::add);
        return tasks;
    }

    @RequestMapping("/team")
    public List<Team> getTeams() {
        List<Team> teams = new ArrayList<>();
        teamRepository.findAll().forEach(teams::add);
        return teams;
    }

    @RequestMapping("/team-skill")
    public List<TeamSkill> getTeamSkills() {
        List<TeamSkill> teamSkills = new ArrayList<>();
        teamSkillRepository.findAll().forEach(teamSkills::add);
        return teamSkills;
    }

    @RequestMapping("/assignment_result")
    public Object getResult() {
        List<AssignmentResult> results = new ArrayList<>();
        resultRepository.findAll().forEach(results::add);
        return results;
    }
}

