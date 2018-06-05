package com.msms.tmtest.services.csv;

import com.msms.tmtest.model.AssignmentResult;
import com.msms.tmtest.model.Task;
import com.msms.tmtest.model.Team;
import com.msms.tmtest.model.TeamSkill;
import com.msms.tmtest.repo.AssignmentResultRepository;
import com.msms.tmtest.repo.TaskRepository;
import com.msms.tmtest.repo.TeamRepository;
import com.msms.tmtest.repo.TeamSkillRepository;
import com.opencsv.CSVReader;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Optional;

@Service
@Log4j2
public class CsvReader {
    @Autowired
    TaskRepository taskRepository;
    @Autowired
    TeamRepository teamRepository;
    @Autowired
    TeamSkillRepository teamSkillRepository;
    @Autowired
    AssignmentResultRepository assignmentResultRepository;

    private Optional<CSVReader> read(String file) {
        CSVReader reader = null;
        try {
            reader = new CSVReader(new FileReader(file));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Optional.ofNullable(reader);
    }

    private void closeReader(CSVReader reader) {
        if (reader != null) {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Async
    public void processCsv(File file) {
        log.debug(String.format("Processing file [%s]: %s", Thread.currentThread().getName(), file.getAbsolutePath()));
        log.debug("file valid: " + isValidCsv(file));
        read(file.getAbsolutePath()).ifPresent(reader -> {
            try {
                String[] line;
                reader.skip(1);
                while ((line = reader.readNext()) != null) {
                    if (file.getName().equalsIgnoreCase("team.csv") && line.length > 0) {
                        Team team = new Team();
                        team.setTeamId(line[0]);
                        teamRepository.save(team);
                    } else if (file.getName().equalsIgnoreCase("team_skill.csv") && line.length > 1) {
                        TeamSkill teamSkill = new TeamSkill();
                        teamSkill.setSkill(line[1]);
                        teamSkill.setTeamId(line[0]);
                        teamSkill.setId();
                        teamSkillRepository.save(teamSkill);
                    } else if (file.getName().equalsIgnoreCase("task.csv") && line.length > 1) {
                        Task task = new Task();
                        task.setTaskId(line[0]);
                        task.setSkill(line[1]);
                        taskRepository.save(task);
                    }
                }
            } catch (Exception e) {

            } finally {
                closeReader(reader);

                teamRepository.findAll().forEach(team -> {
                    teamSkillRepository.findAllByTeamId(team.getTeamId()).forEach(
                            teamSkill -> {
                                taskRepository.findAllBySkill(teamSkill.getSkill()).forEach(task -> {
                                    AssignmentResult assignmentResult = new AssignmentResult();
                                    assignmentResult.setTeamId(team.getTeamId());
                                    assignmentResult.setTaskAssigned(task.getTaskId());
                                    assignmentResult.setId();
                                    assignmentResultRepository.save(assignmentResult);
                                });
                            }
                    );
                });
            }
        });
        file.delete();
    }

    public boolean isValidCsv(File file) {
        if (file == null || !file.exists() || file.isDirectory()) return false;
        String fileName = file.getName();
        return fileName.matches(".+\\.csv$");
    }
}
