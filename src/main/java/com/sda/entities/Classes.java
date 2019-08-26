package com.sda.entities;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "classes")
public class Classes {
    @Id
    @GenericGenerator(name = "gen", strategy = "increment")
    @GeneratedValue(generator = "gen")
    private int id;
    @Column(name = "name")
    private String name;
    @OneToMany(mappedBy = "classes")
    @ElementCollection(targetClass = Students.class)
    private List<Students> students;

    public Classes() {

    }

    public Classes(List<Students> students) {
        this.students = students;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean equals(Classes classesToCompare) {
        if (this.name.equals(classesToCompare.getName()) &&
                this.id == classesToCompare.id) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return this.name.length() + this.id;
    }
}
