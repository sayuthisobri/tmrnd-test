package com.msms.tmtest.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "team_skill")
public class TeamSkill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    String id;

    @Column(name = "skill")
    String skill;

    @ManyToOne
    @JoinColumn(name = "team_id")
    Team team;
}
