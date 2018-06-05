package com.msms.tmtest.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "task")
public class Task {
    @Id
    @Column(name = "task_id")
    String taskId;

    @Column(name = "skill")
    String skill;
}
