package com.rest.app.todo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

//@CrossOrigin(origins ="" )
@RestController
public class TodoResource {

    @Autowired
    private TodoHardcodedService todoService;


    @PutMapping("/users/{username}/todos/{id}")
    public ResponseEntity<Todo> updateTodo(
            @PathVariable String username, @PathVariable Long id, @RequestBody Todo todo) {
           Todo todoUpdated =  todoService.save(todo);
           return new ResponseEntity<Todo>(todo, HttpStatus.OK );
    }

    @PostMapping("/users/{username}/todos")
    public ResponseEntity<Void> updateTodo(
            @PathVariable String username, @RequestBody Todo todo) {
        Todo createdTodo =  todoService.save(todo);
//        Get current resource url /users/{username}/todos
        URI  uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(createdTodo.getId()).toUri();

        return ResponseEntity.created(uri).build();
    }


    @GetMapping("/users/{username}/todos")
    public List<Todo> getAlltodos(@PathVariable String username){
        return todoService.findAll();
    }

    @GetMapping("/users/{username}/todos/{id}")
    public Todo getTodo(@PathVariable String username, @PathVariable Long id){
        return todoService.findById(id);
    }

    // Delete mapping
    @DeleteMapping("/users/{username}/todos/{id}")
    public ResponseEntity<Void> deleteTodo(@PathVariable String username, @PathVariable Long id){

        Todo todo = todoService.deleteById(id);

        if (todo != null){
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.notFound().build();
    }
}
