package br.devus.redesocial.dto;

import java.util.UUID;

public class FollowProfileDTO {
    
    private UUID IdProfile;

    private UUID idProfileToFollow;

    public FollowProfileDTO() {
    }

    public FollowProfileDTO(UUID idProfile, UUID idProfileToFollow) {
        IdProfile = idProfile;
        this.idProfileToFollow = idProfileToFollow;
    }

    public UUID getIdProfile() {
        return IdProfile;
    }

    public void setIdProfile(UUID idProfile) {
        IdProfile = idProfile;
    }

    public UUID getIdProfileToFollow() {
        return idProfileToFollow;
    }

    public void setIdProfileToFollow(UUID idProfileToFollow) {
        this.idProfileToFollow = idProfileToFollow;
    }

    
}
