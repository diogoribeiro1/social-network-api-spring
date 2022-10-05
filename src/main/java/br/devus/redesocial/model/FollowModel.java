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

    private UUID followed;

    @ManyToOne
    private ProfileModel follower;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    public FollowModel() {
    }

    public FollowModel(UUID id, UUID Followed, ProfileModel Follower, LocalDateTime createdAt) {
        this.id = id;
        followed = Followed;
        follower = Follower;
        this.createdAt = createdAt;
    }

    public FollowModel(UUID Followed, ProfileModel Follower) {
        followed = Followed;
        follower = Follower;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getFollowed() {
        return followed;
    }

    public void setFollowed(UUID Followed) {
        followed = Followed;
    }

    public ProfileModel getFollower() {
        return follower;
    }

    public void setFollower(ProfileModel Follower) {
        follower = Follower;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
    
}
