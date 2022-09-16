package br.devus.redesocial.model;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.context.annotation.Profile;

import javax.persistence.*;
import javax.swing.*;
import java.util.UUID;

@Entity
@Table(name = "publications_tbl")
public class PublicationsModel {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "idPublications", columnDefinition = "BINARY(16)")
    private UUID idPublications;

    private String description;

    @ManyToOne
    private ProfileModel profile;

    public PublicationsModel() {
    }

    public PublicationsModel(UUID idPublications, String description, ProfileModel profile) {
        this.idPublications = idPublications;
        this.description = description;
        this.profile = profile;
    }

    public UUID getIdPublications() {
        return idPublications;
    }

    public void setIdPublications(UUID idPublications) {
        this.idPublications = idPublications;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ProfileModel getProfile() {
        return profile;
    }

    public void setProfile(ProfileModel profile) {
        this.profile = profile;
    }
}
