package com.codegym.repository;

import com.codegym.model.LopHoc;
import com.codegym.model.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface StudentRepository extends PagingAndSortingRepository<Student,Long> {
    Page<Student> findAllByLopHoc(LopHoc lopHoc,Pageable pageable);
}
