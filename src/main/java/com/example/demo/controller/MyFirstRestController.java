package com.example.demo.controller;

import com.example.demo.service.MyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyFirstRestController {

    private static final Logger logger = LoggerFactory.getLogger(MyFirstRestController.class);

    private final MyService myService;

    public MyFirstRestController(MyService myService) {
        this.myService = myService;
    }

    @GetMapping("say-something")
    public String saySomething(){

        logger.info("/say-something");
        logger.debug("is it visible???");
        return "Hello RestController";
    }
/**
 * Exc. 1
 *  → create endpoint which get from a user his name (as @RequestParam )and will give it back but converted to upper case
 * => zrobimy to w taki sposob, ze jesli uzytkownik w adresie podal parametr myName to żeby to imie bylo automatycznie wstawiane do argumentu w naszej metodce
 * (tzn. Wpisujac http://localhost:8080/convert-name?myName=Mariusz → otrzymamy MARIUSZ )
 * Exc.1'
 * -> ad logger which print in-put parameter (name) and result
 */
@GetMapping("/convert-name")
    public String convertNameForUpperCase(@RequestParam("myName") String name) {

        String result = name.toUpperCase();

        logger.info(" in-put: [{}] , result [{}]" , name, result);

        return result;
    }
}
