package br.devus.redesocial.controller;

import br.devus.redesocial.dto.CreateUserRoleDTO;
import br.devus.redesocial.model.Role;
import br.devus.redesocial.model.UserModel;
import br.devus.redesocial.service.CreateRoleUserService;
import br.devus.redesocial.service.CreateUserService;
import br.devus.redesocial.service.RoleService;
import br.devus.redesocial.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    RoleService roleService;

    @Autowired
    CreateUserService createUserService;

    @Autowired
    CreateRoleUserService createRoleUserService;

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

    //     CRIANDO USER COM ROLE('ADMIN')
    @PostMapping("/createAdmin")
    public UserModel saveUserAdmin(@RequestBody UserModel user)
    {
        UserModel userModel = createUserService.execute(user);
        Role role = roleService.getRoleByName("ADMIN").getBody();

        CreateUserRoleDTO userRoleDTO = new CreateUserRoleDTO();
        List<UUID> lista = new ArrayList<>();

        lista.add(role.getRoleId());
        userRoleDTO.setIdsRoles(lista);
        userRoleDTO.setIdUser(userModel.getIdUser());

        createRoleUserService.execute(userRoleDTO);
        return userModel;
    }

    //     CRIANDO USER COM ROLE('USER')
    @PostMapping("/createUSER")
    public UserModel saveUserWithRole(@RequestBody UserModel user)
    {
        UserModel userModel = createUserService.execute(user);
        Role role = roleService.getRoleByName("USER").getBody();

        CreateUserRoleDTO userRoleDTO = new CreateUserRoleDTO();
        List<UUID> lista = new ArrayList<>();

        lista.add(role.getRoleId());
        userRoleDTO.setIdsRoles(lista);
        userRoleDTO.setIdUser(userModel.getIdUser());

        createRoleUserService.execute(userRoleDTO);
        return userModel;
    }

    // ADCIONA ROLE EM USER
    @PostMapping(value = "/role")
    public UserModel role(@RequestBody CreateUserRoleDTO createUserRoleDTO)
    {
        return createRoleUserService.execute(createUserRoleDTO);
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
