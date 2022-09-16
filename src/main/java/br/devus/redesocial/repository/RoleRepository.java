package br.devus.redesocial.repository;

import br.devus.redesocial.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RoleRepository extends JpaRepository<Role, UUID> {

    Role findRoleByRoleName(String roleName);
}
