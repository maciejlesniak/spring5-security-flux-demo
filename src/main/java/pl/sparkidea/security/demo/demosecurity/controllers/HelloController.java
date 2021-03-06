package pl.sparkidea.security.demo.demosecurity.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Maciej Lesniak
 */
@Slf4j
@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello() {
        log.debug("hello");
        return "hello";
    }

    @GetMapping("/")
    public String home() {
        log.debug("home");
        return "home";
    }

}
