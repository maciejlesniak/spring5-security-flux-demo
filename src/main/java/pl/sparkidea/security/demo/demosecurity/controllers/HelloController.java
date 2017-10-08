package pl.sparkidea.security.demo.demosecurity.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;


/**
 * @author Maciej Lesniak
 */
@Slf4j
@RestController
public class HelloController {

    @GetMapping("/hello")
    public Flux<String> hello() {
        log.debug("hello");
        return Flux.just("hello");
    }

    @GetMapping("/")
    public Flux<String> home() {
        log.debug("home");
        return Flux.just("home");
    }

}
