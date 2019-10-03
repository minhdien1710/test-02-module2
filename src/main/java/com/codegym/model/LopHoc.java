package com.codegym.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "classes")
public class LopHoc {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    @OneToMany(mappedBy = "lopHoc")
    private Set<Student> students;

    public LopHoc() {
    }

    public LopHoc(String name, Set<Student> students) {
        this.name = name;
        this.students = students;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Student> getStudents() {
        return students;
    }

    public void setStudents(Set<Student> students) {
        this.students = students;
    }
}
