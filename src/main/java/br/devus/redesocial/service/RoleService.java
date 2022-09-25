package br.devus.redesocial.service;

import br.devus.redesocial.model.Role;
import br.devus.redesocial.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {

    @Autowired
    RoleRepository roleRepository;

    public ResponseEntity<Role> createRole(Role role) {
        Role roleModel = roleRepository.save(role);
        return ResponseEntity.status(HttpStatus.CREATED).body(roleModel);
    }

    public ResponseEntity<List<Role>> getAllRoles() {
        List<Role> roles = roleRepository.findAll();
        return ResponseEntity.ok().body(roles);
    }

    public ResponseEntity<Role> getRoleByName(String name) {
        Role role = roleRepository.findRoleByRoleName(name);
        return ResponseEntity.ok().body(role);
    }

}
