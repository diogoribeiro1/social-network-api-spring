package br.devus.redesocial.controller;

import br.devus.redesocial.model.Role;
import br.devus.redesocial.service.RoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/role")
@Api(value = "Api Rest Roles")
public class RoleController {

    @Autowired
    RoleService roleService;

    @PostMapping
    @ApiOperation(value = "Create a role")
    public ResponseEntity<Role> createRole(@RequestBody Role role)
    {
        return roleService.createRole(role);
    }

    @GetMapping
    @ApiOperation(value = "Get all roles")
    public ResponseEntity<List<Role>> getAllRoles()
    {
        return roleService.getAllRoles();
    }
}
