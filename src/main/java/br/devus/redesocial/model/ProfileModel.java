package br.devus.redesocial.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "profiles_tbl")
public class ProfileModel {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "idProfile", columnDefinition = "BINARY(16)")
    private UUID idProfile;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = true)
    private String urlImage;

    @Column(nullable = true)
    private String description;

    @OneToMany
    @Column(nullable = true)
    private List<FollowModel> followers;

    @OneToMany
    @Column(nullable = true)
    private List<PublicationsModel> publications;

    @Column(nullable = false)
    private UUID idUser;

    public ProfileModel() {
    }

    public ProfileModel(UUID idProfile, String username, String urlImage, String description, List<FollowModel> followers, List<PublicationsModel> publications, UUID idUser) {
        this.idProfile = idProfile;
        this.username = username;
        this.urlImage = urlImage;
        this.description = description;
        this.followers = followers;
        this.publications = publications;
        this.idUser = idUser;
    }

    public UUID getIdProfile() {
        return idProfile;
    }

    public void setIdProfile(UUID idProfile) {
        this.idProfile = idProfile;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<FollowModel> getFollowers() {
        return followers;
    }

    public void setFollowers(List<FollowModel> followers) {
        this.followers = followers;
    }

    public List<PublicationsModel> getPublications() {
        return publications;
    }

    public void setPublications(List<PublicationsModel> publications) {
        this.publications = publications;
    }

    public UUID getIdUser() {
        return idUser;
    }

    public void setIdUser(UUID idUser) {
        this.idUser = idUser;
    }
}
