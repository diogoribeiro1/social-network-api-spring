package br.devus.redesocial.service;

import br.devus.redesocial.model.PublicationsModel;
import br.devus.redesocial.repository.PublicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class PublicationService {

    @Autowired
    PublicationRepository publicationRepository;

    public ResponseEntity<PublicationsModel> savePublication(PublicationsModel publicationsModel) {
        PublicationsModel modelResponse = publicationRepository.save(publicationsModel);
        return ResponseEntity.ok().body(modelResponse);
    }

    public ResponseEntity<List<PublicationsModel>> getAllPublications() {
        List<PublicationsModel> listPublications = publicationRepository.findAll();
        return ResponseEntity.ok().body(listPublications);
    }

    public void deletePublication(UUID idPublication) {
        publicationRepository.findById(idPublication).map(publicationsModel -> {
            publicationRepository.delete(publicationsModel);
            return null;
        });
    }

    public ResponseEntity<List<PublicationsModel>> getAllPublicationsByIdProfile(UUID uuid) {
        List<PublicationsModel> listPublications = publicationRepository.findPublicationsByIdProfile(uuid);
        return ResponseEntity.ok().body(listPublications);
    }

}
