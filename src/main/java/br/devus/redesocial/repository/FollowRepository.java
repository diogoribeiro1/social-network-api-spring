package br.devus.redesocial.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.devus.redesocial.model.FollowModel;

public interface FollowRepository extends JpaRepository<FollowModel,UUID>{
    
}
