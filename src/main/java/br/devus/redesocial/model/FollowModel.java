package br.devus.redesocial.model;

import javax.persistence.*;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "follows_tbl")
public class FollowModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "id", columnDefinition = "BINARY(16)")
    private UUID id;

    private UUID Followed;

    @ManyToOne
    private ProfileModel Follower;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    public FollowModel() {
    }

    public FollowModel(UUID id, UUID followed, ProfileModel follower, LocalDateTime createdAt) {
        this.id = id;
        Followed = followed;
        Follower = follower;
        this.createdAt = createdAt;
    }

    public FollowModel(UUID followed, ProfileModel follower) {
        Followed = followed;
        Follower = follower;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getFollowed() {
        return Followed;
    }

    public void setFollowed(UUID followed) {
        Followed = followed;
    }

    public ProfileModel getFollower() {
        return Follower;
    }

    public void setFollower(ProfileModel follower) {
        Follower = follower;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
    
}
