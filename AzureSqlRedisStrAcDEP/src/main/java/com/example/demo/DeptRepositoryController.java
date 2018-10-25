package com.example.demo;

import com.example.demo.Dept;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.client.support.*;

@Configuration
@EnableCaching
@RestController

public class DeptRepositoryController {
    @Autowired
    private DeptRepository deptRepository;
    
    @RequestMapping(method=RequestMethod.GET, value="/dept")
    @Cacheable("d1")
    public Iterable<Dept> dept() {
        return deptRepository.findAll();
    }
    
    @RequestMapping(method=RequestMethod.GET, value="/dept/{id}")
    @Cacheable("d2")
    public String show(@PathVariable Integer id) {
    	RestTemplate restTemplate = new RestTemplate();
    	String result = restTemplate.getForObject("https://webappemp.azurewebsites.net/deps/" + id, String.class);
    	return "Listing Employees by department" + result;
    }
    
    @RequestMapping(method=RequestMethod.POST, value="/dept")
    @Cacheable("d3")
    public String save(@RequestBody Dept dept) {
        deptRepository.save(dept);
        return dept.getName();
    }
}
