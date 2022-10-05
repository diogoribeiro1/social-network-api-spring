package br.devus.redesocial.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.devus.redesocial.model.FollowModel;
import br.devus.redesocial.model.ProfileModel;

public interface FollowRepository extends JpaRepository<FollowModel,UUID>{

    Optional<List<FollowModel>> findByFollower(ProfileModel follower);
    
}
