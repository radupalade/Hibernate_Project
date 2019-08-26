package com.sda.entities;

import com.sun.jmx.snmp.SnmpUnknownAccContrModelException;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.concurrent.locks.Lock;

@NamedQueries({
        @NamedQuery(
                name = "get_student_by_name",
                query = "select s from Students s where first_name = : first_name"
        )
})
@Entity
@Table(name = "students")
public class Students implements Serializable {
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
    @OneToOne
    @JoinColumn(name = "locker_id")
    private Locker locker;
    @ManyToOne
    @JoinTable(name = "students_teachers", joinColumns = {@JoinColumn(name = "student_id")}, inverseJoinColumns =
            {@JoinColumn(name = "group_id")})
    private Classes classes;
    @ManyToMany
    private List<Teachers> teachers;


    public Students() {
    }

    public Students(String firstName, String lastName, int age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
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

    public boolean equals(Students studentToCompare) {
        if (this.firstName.equals(studentToCompare.getFirstName()) &&
                this.lastName.equals(studentToCompare.getLastName()) &&
                this.age == studentToCompare.getAge() &&
                this.id == studentToCompare.getId()) {
            return true;
        }

        return false;
    }

    public int hashCode() {
        return this.firstName.length() + this.lastName.length() + this.age;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                '}';
    }
}
