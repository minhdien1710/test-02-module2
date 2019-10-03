package com.codegym.controller;

import com.codegym.model.LopHoc;
import com.codegym.model.Student;
import com.codegym.service.LopHocService;
import com.codegym.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LopHocController {

    @Autowired
    private LopHocService lopHocService;
    @Autowired
    private StudentService studentService;
    @GetMapping("/view-lopHoc/{id}")
    public ModelAndView viewLopHoc(@PathVariable("id") Long id){
        LopHoc lopHoc = lopHocService.findById(id);
        if(lopHoc == null){
            return new ModelAndView("/error");
        }

        Iterable<Student> students = studentService.findAllByLopHoc(lopHoc);

        ModelAndView modelAndView = new ModelAndView("/class/view");
        modelAndView.addObject("lopHoc", lopHoc);
        modelAndView.addObject("students", students);
        return modelAndView;
    }

    @GetMapping("/lopHocs")
    public ModelAndView listLopHoc(){
        Iterable<LopHoc> lopHocs = lopHocService.findAll();
        ModelAndView modelAndView = new ModelAndView("/class/list");
        modelAndView.addObject("lopHocs", lopHocs);
        return modelAndView;
    }

    @GetMapping("/create-lopHoc")
    public ModelAndView showCreateForm(){
        ModelAndView modelAndView = new ModelAndView("/class/create");
        modelAndView.addObject("lopHoc", new LopHoc());
        return modelAndView;
    }

    @PostMapping("/create-lopHoc")
    public ModelAndView saveLopHoc(@ModelAttribute("lopHoc") LopHoc lopHoc){
        lopHocService.save(lopHoc);

        ModelAndView modelAndView = new ModelAndView("/class/create");
        modelAndView.addObject("lopHoc", new LopHoc());
        return modelAndView;
    }

    @GetMapping("/edit-lopHoc/{id}")
    public ModelAndView showEditForm(@PathVariable Long id){
        LopHoc lopHoc = lopHocService.findById(id);
        if(lopHoc != null) {
            ModelAndView modelAndView = new ModelAndView("/class/update");
            modelAndView.addObject("lopHoc", lopHoc);
            return modelAndView;

        }else {
            ModelAndView modelAndView = new ModelAndView("/error.404");
            return modelAndView;
        }
    }

    @PostMapping("/edit-lopHoc")
    public ModelAndView updateProvince(@ModelAttribute("lopHoc") LopHoc lopHoc){
        lopHocService.save(lopHoc);
        ModelAndView modelAndView = new ModelAndView("/class/update");
        modelAndView.addObject("lopHoc", lopHoc);
        return modelAndView;
    }

    @GetMapping("/delete-lopHoc/{id}")
    public ModelAndView showDeleteForm(@PathVariable Long id){
        LopHoc lopHoc = lopHocService.findById(id);
        if(lopHoc != null) {
            ModelAndView modelAndView = new ModelAndView("/class/delete");
            modelAndView.addObject("lopHoc", lopHoc);
            return modelAndView;

        }else {
            ModelAndView modelAndView = new ModelAndView("/error");
            return modelAndView;
        }
    }

    @PostMapping("/delete-lopHoc")
    public String deleteLopHoc(@ModelAttribute("lopHoc") LopHoc lopHoc){
        lopHocService.remove(lopHoc.getId());
        return "redirect:lopHocs";
    }
}
