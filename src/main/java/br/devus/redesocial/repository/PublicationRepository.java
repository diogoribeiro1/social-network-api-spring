package br.devus.redesocial.repository;

import br.devus.redesocial.model.PublicationsModel;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface PublicationRepository extends JpaRepository<PublicationsModel, UUID> {

    @Query(value = "SELECT u FROM PublicationsModel u WHERE u.profile.idProfile = :idProfile")
    List<PublicationsModel> findPublicationsByIdProfile (@Param("idProfile") UUID idProfile);
}
