package br.devus.redesocial.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "users_tbl")
public class UserModel implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "idUser", columnDefinition = "BINARY(16)")
    private UUID idUser;

    @NotNull
    @Column(nullable = false, unique = true)
    private String fullName;

    @NotNull
    @Column(nullable = false, unique = false)
    private String birthDate;

    @NotNull
    @Column(nullable = false, unique = false)
    private String password;

    @NotNull
    @Column(nullable = false, unique = true)
    private String email;

    @OneToOne(cascade = CascadeType.ALL)
//    @JoinTable(name = "user_profile",
//            joinColumns =
//                    { @JoinColumn(name = "user_id", referencedColumnName = "idUser") },
//            inverseJoinColumns =
//                    { @JoinColumn(name = "profile_id", referencedColumnName = "idProfile") })
    private ProfileModel profile;

    @ManyToMany
    private List<Role> roles;


    public UserModel() {
    }

    public UserModel(UUID idUser, String fullName, String birthDate, String password, String email, ProfileModel profile, List<Role> roles) {
        this.idUser = idUser;
        this.fullName = fullName;
        this.birthDate = birthDate;
        this.password = password;
        this.email = email;
        this.profile = profile;
        this.roles = roles;
    }

    public UserModel(UUID idUser, String fullName, String birthDate, String password, String email, ProfileModel profile) {
        this.idUser = idUser;
        this.fullName = fullName;
        this.birthDate = birthDate;
        this.password = password;
        this.email = email;
        this.profile = profile;
    }

    public UUID getIdUser() {
        return idUser;
    }

    public void setIdUser(UUID idUser) {
        this.idUser = idUser;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    @JsonIgnore
    public String getPassword() {
        return password;
    }

    @JsonProperty
    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public ProfileModel getProfile() {
        return profile;
    }

    public void setProfile(ProfileModel profile) {
        this.profile = profile;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
}
