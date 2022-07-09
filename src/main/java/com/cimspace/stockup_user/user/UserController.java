package com.cimspace.stockup_user.user;

import java.util.List;
import javax.validation.Valid;

import com.cimspace.stockup_user.UUIDgenerator.UUIDgenerator;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/api/user", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

    private UUIDgenerator uuiDgenerator;
    private final UserService userService;

    public UserController(final UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        return ResponseEntity.ok(userService.findAll());
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<UserDTO> getUser(@PathVariable final String id) {
        return ResponseEntity.ok(userService.get(id));
    }

    @GetMapping("/username/{username}")
    public ResponseEntity<List<UserDTO>> getusername(@PathVariable final String username) {
        return ResponseEntity.ok(userService.getUsername(username));
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<List<UserDTO>> getemail(@PathVariable final String email) {
        return ResponseEntity.ok(userService.getEmail(email));
    }

    @PostMapping
    public ResponseEntity<String> createUser(@RequestBody @Valid final UserDTO userDTO) {
        return new ResponseEntity<>(userService.create(userDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateUser(@PathVariable final String id,
            @RequestBody @Valid final UserDTO userDTO) {
        userService.update(id, userDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable final String id) {
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
