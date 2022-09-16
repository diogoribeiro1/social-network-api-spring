package br.devus.redesocial.model;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "follows_tbl")
public class FollowModel {

    @Id
    @GeneratedValue
    private UUID idFollow;

    @OneToOne
    private UserModel followers;

    @OneToOne
    private UserModel follow;

    @ManyToOne
    private ProfileModel profile;

    public FollowModel() {
    }

    public FollowModel(UUID idFollow, UserModel followers, UserModel follow, ProfileModel profile) {
        this.idFollow = idFollow;
        this.followers = followers;
        this.follow = follow;
        this.profile = profile;
    }

    public UUID getIdFollow() {
        return idFollow;
    }

    public void setIdFollow(UUID idFollow) {
        this.idFollow = idFollow;
    }

    public UserModel getFollowers() {
        return followers;
    }

    public void setFollowers(UserModel followers) {
        this.followers = followers;
    }

    public UserModel getFollow() {
        return follow;
    }

    public void setFollow(UserModel follow) {
        this.follow = follow;
    }

    public ProfileModel getProfile() {
        return profile;
    }

    public void setProfile(ProfileModel profile) {
        this.profile = profile;
    }
}
