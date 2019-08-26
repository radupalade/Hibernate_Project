package com.sda.entities;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "teachers")
public class Teachers {
    @Id
    @GenericGenerator(name = "gen", strategy = "increment")
    @GeneratedValue(generator = "gen")
    private int id;
    @Column(name = "first_Name")
    private String firstName;
    @Column(name = "last_Name")
    private String lastName;
    @Column(name = "age")
    private int age;
    @Column(name = "type")
    private String type;
    @ManyToMany
    @JoinTable(name = "students_teachers", joinColumns = {@JoinColumn(name = "students_id")}, //aici e declarat tabelul de legatura
            inverseJoinColumns = {@JoinColumn(name = "teachers_id")})
    private List<Students> students;

    public Teachers() {

    }

    public Teachers(String firstName, String lastName, int age, String type) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean equals(Teachers teacherToCompare) {
        if (this.firstName.equals(teacherToCompare.getFirstName()) &&
                this.lastName.equals(teacherToCompare.getLastName()) &&
                this.type.equals(teacherToCompare.getType()) &&
                this.age == teacherToCompare.getAge() &&
                this.id == teacherToCompare.getId()) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return this.firstName.length() + this.lastName.length() + this.age + this.type.length();
    }
}
