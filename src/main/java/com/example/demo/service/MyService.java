package com.example.demo.service;

import com.example.demo.repository.MyRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class MyService {
    private static final Logger logger = LoggerFactory.getLogger(MyService.class);

    private final MyRepository myRepository;

    public MyService() {
        myRepository = new MyRepository();
    }
/**
 * Exc. 2
 * create our own method convertToUpperCase() in service layer and use it in controller layer
 * add logger
 */

    public static String convertToUpperCase(String string) {

        String result = Objects.nonNull(string) ? string.toUpperCase() : null ;
        logger.info("String before Conversion [{}] and after conversion : [{}]", string, result);

        return result;
    }
}
