package com.example.demo.service;

import com.example.demo.repository.MyRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class MyService {
    private static final Logger logger = LoggerFactory.getLogger(MyService.class);

    private final MyRepository myRepository;

    public MyService() {
        myRepository = new MyRepository();
    }
}
