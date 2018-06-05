package com.msms.tmtest.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "team")
public class Team {
    @Id
    String teamId;
}
