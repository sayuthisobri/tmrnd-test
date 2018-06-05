package com.msms.tmtest.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "team_skill")
public class TeamSkill {

    @Id
    String id;

    @Column(name = "skill")
    String skill;

    @Column(name = "team_id")
    String teamId;

    public void setId() {
        this.id = skill + teamId;
    }
}
