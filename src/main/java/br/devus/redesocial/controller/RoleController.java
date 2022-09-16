package br.devus.redesocial.controller;

import br.devus.redesocial.model.Role;
import br.devus.redesocial.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    RoleService roleService;

    @PostMapping
    public ResponseEntity<Role> createRole(@RequestBody Role role)
    {
        return roleService.createRole(role);
    }

    @GetMapping
    public ResponseEntity<List<Role>> getAllRoles()
    {
        return roleService.getAllRoles();
    }
}
