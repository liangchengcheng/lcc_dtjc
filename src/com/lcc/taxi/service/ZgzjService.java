package com.lcc.taxi.service;

import com.lcc.taxi.dao.ZgzjDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ZgzjService {
    @Autowired
    private ZgzjDao zgzjDao;
}