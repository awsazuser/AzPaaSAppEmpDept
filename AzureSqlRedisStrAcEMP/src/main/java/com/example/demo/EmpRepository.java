package com.example.demo;


import com.example.demo.Emp;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

import org.springframework.*;

public interface EmpRepository extends CrudRepository<Emp, Integer> {
   Emp findByName(String name);
   Iterable<Emp> findByDid(Integer did);
}