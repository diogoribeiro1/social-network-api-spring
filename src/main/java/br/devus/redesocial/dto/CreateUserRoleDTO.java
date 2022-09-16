package br.devus.redesocial.dto;

import java.util.List;
import java.util.UUID;

public class CreateUserRoleDTO {

    private UUID idUser;

    private List<UUID> idsRoles;

    public UUID getIdUser() {
        return idUser;
    }

    public void setIdUser(UUID idUser) {
        this.idUser = idUser;
    }

    public List<UUID> getIdsRoles() {
        return idsRoles;
    }

    public void setIdsRoles(List<UUID> idsRoles) {
        this.idsRoles = idsRoles;
    }
}
