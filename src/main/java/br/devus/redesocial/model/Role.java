package br.devus.redesocial.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "role_tbl")
public class Role implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "roleId", columnDefinition = "BINARY(16)")
    private UUID roleId;

    //@Enumerated(EnumType.STRING)
    //@Column(nullable = false, unique = true)
    private String roleName;

    public Role() {
    }

    public Role(UUID roleId, String roleName) {
        this.roleId = roleId;
        this.roleName = roleName;
    }

    public Role(UUID roleId) {
        this.roleId = roleId;
    }

    public UUID getRoleId() {
        return roleId;
    }

    public String getRoleName() {
        return roleName;
    }
}