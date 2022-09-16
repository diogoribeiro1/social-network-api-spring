package br.devus.redesocial.controller;

import br.devus.redesocial.model.UserModel;
import br.devus.redesocial.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping
    @ResponseBody
    public ResponseEntity<UserModel> saveUser(@RequestBody UserModel userModel)
    {
        return userService.saveUser(userModel);
    }

    @GetMapping
    @ResponseBody
    public ResponseEntity<List<UserModel>> getAllUsers()
    {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<UserModel> getUserById(@PathVariable UUID id)
    {
        return userService.getUserById(id);
    }

    @PutMapping("/{id}")
    @ResponseBody
    public ResponseEntity<UserModel> updateUser(@PathVariable UUID id, @RequestBody UserModel userModel)
    {
        return userService.updateUserById(userModel, id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteUser(@PathVariable UUID id)
    {
        return userService.deleteUserById(id);
    }

}
