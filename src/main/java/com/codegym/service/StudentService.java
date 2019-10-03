package com.codegym.service;

import com.codegym.model.LopHoc;
import com.codegym.model.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface StudentService {
    Page<Student> findAll(Pageable pageable);
    Student findById(Long id);
    void save(Student student);
    void remove(Long id);
    Iterable<Student> findAllByLopHoc(LopHoc lopHoc);
}
