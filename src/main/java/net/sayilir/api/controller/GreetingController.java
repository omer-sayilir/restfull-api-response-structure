package net.sayilir.api.controller;

import net.sayilir.api.common.ApiResponse;
import net.sayilir.api.common.ResponseUtil;
import net.sayilir.api.model.Greeting;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author omersayilir
 */
@RestController
@RequestMapping("/api")
public class GreetingController {
   private static final String  template="Hello World, %s!";
   private final AtomicInteger counter=new AtomicInteger(0);
    @GetMapping("/greeting")
    public ResponseEntity<ApiResponse<Greeting>> getGreeting(@RequestParam(value="name", defaultValue="World") String name) {
        Greeting greeting=new Greeting(counter.incrementAndGet(),String.format(template,name));
        ApiResponse<Greeting> response= ResponseUtil.success(greeting,"Greeting created successfully","/api/greeting");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @PostMapping("/greeting/add")
    public ResponseEntity<ApiResponse<Greeting>> addGreeting(@RequestBody Greeting greeting) {
        System.out.println(greeting);
        ApiResponse<Greeting> response= ResponseUtil.success(greeting,"Greeting added successfully","/api/greeting/add");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
