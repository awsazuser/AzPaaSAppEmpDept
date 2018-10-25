package com.example.demo;

import com.example.demo.*;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name = "dept")
public class Dept implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String name;
    private String loc;
    
    public Dept() {
    }

    public Dept(String name, String loc) {
        this.name = name;
        this.loc = loc;
    }

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getLoc() {
        return loc;
    }
    public void setLoc(String loc) {
        this.loc = loc;
    }
    @Override
    public String toString() {
        return "Dept{" +
                ", Name='" + name + '\'' +
                ", Loc=" + loc +
                '}';
    }
}