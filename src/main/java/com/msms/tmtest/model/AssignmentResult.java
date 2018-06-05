package com.msms.tmtest.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "assignment_result")
public class AssignmentResult {
    @Id
    String id;

    String teamId;

    String taskAssigned;

    public void setId() {
        this.id = teamId + taskAssigned;
    }
}
