package com.prodmaster.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "work_centers")
public class WorkCenter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ColumnDefault("nextval('work_centers_id_seq'::regclass)")
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "status", length = 50)
    private String status;

    @Column(name = "equipment")
    private String equipment;

    @Column(name = "operator")
    private String operator;

    @OneToMany(mappedBy = "workCenter")
    private Set<TechnologyRoute> technologyRoutes = new LinkedHashSet<>();
}