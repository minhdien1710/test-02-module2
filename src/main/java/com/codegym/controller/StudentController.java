package com.codegym.controller;

import com.codegym.model.LopHoc;
import com.codegym.model.Student;
import com.codegym.service.LopHocService;
import com.codegym.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
public class StudentController {
        @Autowired
        StudentService studentService;
        @Autowired
        LopHocService lopHocService;

        @ModelAttribute("lopHocs")
        public Iterable<LopHoc> classes() {
            return lopHocService.findAll();
        }

        @GetMapping("/students")
        public ModelAndView students(@ModelAttribute("s") Optional<LopHoc> s, Pageable pageable){
            Page<Student> students;
            if(s.isPresent()){
                students = studentService.findAllByLopHoc(s.get(),pageable);
            }else {
                students = studentService.findAll(pageable);
            }
            ModelAndView modelAndView = new ModelAndView("/student/list");
            modelAndView.addObject("students", students);
            return modelAndView;
        }

        @GetMapping("/create-student")
        public ModelAndView showCreateForm() {
            ModelAndView modelAndView = new ModelAndView("/student/create");
            modelAndView.addObject("student", new Student());
            return modelAndView;
        }

        @PostMapping("/create-student")
        public ModelAndView saveStudent(@ModelAttribute("student") Student student) {
            studentService.save(student);

            ModelAndView modelAndView = new ModelAndView("/student/create");
            modelAndView.addObject("student", new Student());
            return modelAndView;
        }

        @GetMapping("/delete-student/{id}")
        public ModelAndView showDeleteForm(@PathVariable Long id) {
            Student student = studentService.findById(id);
                ModelAndView modelAndView = new ModelAndView("/student/delete");
                modelAndView.addObject("student", student);
                return modelAndView;
        }

        @PostMapping("/delete-student")
        public String deleteStudent(@ModelAttribute("student") Student student) {
            studentService.remove(student.getId());
            return "redirect:students";
        }

        @GetMapping("/edit-student/{id}")
        public ModelAndView showEditForm(@PathVariable Long id) {
            Student student = studentService.findById(id);
                ModelAndView modelAndView = new ModelAndView("/student/update");
                modelAndView.addObject("student", student);
                return modelAndView;

        }

        @PostMapping("/edit-student")
        public ModelAndView updateStudent(@ModelAttribute("student") Student student) {
            studentService.save(student);
            ModelAndView modelAndView = new ModelAndView("/student/update");
            modelAndView.addObject("student", student);
            return modelAndView;
        }
    }
