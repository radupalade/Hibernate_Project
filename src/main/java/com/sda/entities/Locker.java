package com.sda.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "locker")
public class Locker {
    @Id
    private Integer id;
    @Column(name = "number")
    private Integer number;
    @OneToOne(mappedBy = "locker")
    private Students students;


    public Locker() {
    }

    public Locker(Integer number) {
        this.number = number;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }


    public boolean equals(Locker lockerToCompare) {
        if (this.number.equals(lockerToCompare.getNumber())) {
            return true;
        }
        return false;
    }


    public int hashCode() {
        return this.number;
    }


    @Override
    public String toString() {
        return "Locker{" +
                "id=" + id +
                ", number=" + number +
                '}';
    }
}
