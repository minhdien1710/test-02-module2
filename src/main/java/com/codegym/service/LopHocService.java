package com.codegym.service;

import com.codegym.model.LopHoc;

public interface LopHocService {
    Iterable<LopHoc> findAll();
    void save(LopHoc lopHoc);
    void remove(Long id);
    LopHoc findById(Long id);
}
