package com.example.demo;

import com.example.demo.*;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name = "emp")
public class Emp implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer eid;
	private String name;
	private Integer did;
    private String job;
    private Integer sal;
    private Integer comm;
    
    public Emp() {
    }

    public Emp(String name, Integer did, String job, Integer sal, Integer comm ) {
        this.name = name;
        this.did = did;
        this.job = job;
        this.sal = sal;
        this.comm = comm;
    }

	public Integer getEid() {
		return eid;
	}
	public void setEid(Integer eid) {
		this.eid = eid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getDid() {
		return did;
	}
	public void setDid(Integer did) {
		this.did = did;
	}
	public String getJob() {
		return job;
	}
	public void setJob(String job) {
		this.job = job;
	}
	public void setSal(Integer sal) {
		this.sal = sal;
	}
	public void setCommDid(Integer comm) {
		this.comm = comm;
	}
    @Override
    public String toString() {
        return "Emp{" +
                ", Name='" + name + '\'' +
                ", Dept='" + did + '\'' +
                ", Job='" + job + '\'' +
                ", Sal='" + sal + '\'' +
                ", Comm=" + comm +
                '}';
    }
}