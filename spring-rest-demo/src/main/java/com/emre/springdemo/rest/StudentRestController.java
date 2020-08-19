package com.emre.springdemo.rest;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.emre.springdemo.entity.Student;

@RestController
@RequestMapping("/api")
public class StudentRestController {
	
	private List<Student> theStudents;
	
	
	@PostConstruct
	public void loadData() {
		theStudents = new ArrayList<>();
		theStudents.add(new Student("Mario","Rossi"));
		theStudents.add(new Student("Mary","Ransom"));
		theStudents.add(new Student("Jay","Public"));
	}
	
	//endpoint for "/students"
	@GetMapping("/students")
	public List<Student> getStudents(){
		
		return theStudents;
	}
	
	@GetMapping("/students/{studentid}")
	public Student getStudent(@PathVariable int studentid){
	
		//check if student exists
		if(studentid >= theStudents.size() || studentid <0) {
			throw new StudentNotFoundException("Student id not found - "+studentid);
		}
		
		return theStudents.get(studentid);
	}


	
}
