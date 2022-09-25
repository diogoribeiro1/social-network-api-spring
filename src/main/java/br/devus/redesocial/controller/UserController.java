package br.devus.redesocial.controller;

import br.devus.redesocial.dto.CreateUserRoleDTO;
import br.devus.redesocial.model.UserModel;
import br.devus.redesocial.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/user")
@Api(value = "Api Rest Users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    @ResponseBody
    @ApiOperation(value = "Get all users")
    public ResponseEntity<List<UserModel>> getAllUsers() {
        return userService.getAllUsers();
    }
    
    @PostMapping
    @ResponseBody
    @ApiOperation(value = "Create a user without role")
    public ResponseEntity<UserModel> saveUser(@RequestBody UserModel userModel) {
        return userService.saveUser(userModel);
    }

    @PostMapping("/createAdmin")
    @ResponseBody
    @ApiOperation(value = "Create a user with role ADMIN")
    public ResponseEntity<UserModel> saveUserAdmin(@RequestBody UserModel user) {
        return userService.saveUserWithRoleAdmin(user);
    }

    @PostMapping("/createUSER")
    @ResponseBody
    @ApiOperation(value = "Create user with role USER")
    public ResponseEntity<UserModel> saveUserWithRole(@RequestBody UserModel user) {
        return userService.saveUserWithRoleUser(user);
    }

    @PostMapping(value = "/role")
    @ResponseBody
    @ApiOperation(value = "Add Role in user")
    public ResponseEntity<UserModel> role(@RequestBody CreateUserRoleDTO createUserRoleDTO) {
        return userService.saveRoleToExistingUser(createUserRoleDTO);
    }

    @GetMapping("/{id}")
    @ResponseBody
    @ApiOperation(value = "Get a user by id")
    public ResponseEntity<UserModel> getUserById(@PathVariable UUID id) {
        return userService.getUserById(id);
    }

    @PutMapping("/{id}")
    @ResponseBody
    @ApiOperation(value = "Update a user")
    public ResponseEntity<UserModel> updateUser(@PathVariable UUID id, @RequestBody UserModel userModel) {
        return userService.updateUserById(userModel, id);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Delete a user")
    public ResponseEntity<Object> deleteUser(@PathVariable UUID id) {
        return userService.deleteUserById(id);
    }

}
