package com.codegym.service.impl;

import com.codegym.model.LopHoc;
import com.codegym.repository.LopHocRepository;
import com.codegym.service.LopHocService;
import org.springframework.beans.factory.annotation.Autowired;

public class LopHocServiceImpl implements LopHocService {
    @Autowired
    private LopHocRepository lopHocRepository;
    @Override
    public Iterable<LopHoc> findAll() {
        return lopHocRepository.findAll();
    }

    @Override
    public void save(LopHoc lopHoc) {
        lopHocRepository.save(lopHoc);
    }

    @Override
    public void remove(Long id) {
        lopHocRepository.delete(id);

    }

    @Override
    public LopHoc findById(Long id) {
        return lopHocRepository.findOne(id);
    }
}
