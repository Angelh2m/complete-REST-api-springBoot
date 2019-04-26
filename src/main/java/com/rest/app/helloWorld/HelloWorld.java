package com.rest.app.helloWorld;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorld {

    @RequestMapping(method = RequestMethod.GET, path ="hello-world")
    public String helloWorld(){
     return "HelloWorld";
    }

    @RequestMapping(method = RequestMethod.GET, path ="hello-world-bean")
    public HelloWorldBean helloWorldBean(){
        return new HelloWorldBean("Hello Wold");
    }

    @RequestMapping(method = RequestMethod.GET, path ="hello-world/path-variable/{name}")
    public HelloWorldBean helloWorldPathVariable(@PathVariable String name){
        return new HelloWorldBean(String.format("Hello Wold, %s", name));
    }
}
