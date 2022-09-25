package br.devus.redesocial.service;

import br.devus.redesocial.exceptionhandler.publicationExceptions.PublicationNotFoundException;
import br.devus.redesocial.model.ProfileModel;
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
    private PublicationRepository publicationRepository;

    @Autowired
    private ProfileService profileService;

    public ResponseEntity<PublicationsModel> savePublication(PublicationsModel publicationsModel, UUID idProfile) {
        ProfileModel profileModel = profileService.getProfileById(idProfile).getBody();
        publicationsModel.setProfile(profileModel);
        PublicationsModel modelResponse = publicationRepository.save(publicationsModel);
        return ResponseEntity.ok().body(modelResponse);
    }

    public ResponseEntity<List<PublicationsModel>> getAllPublications() {
        List<PublicationsModel> listPublications = publicationRepository.findAll();
        return ResponseEntity.ok().body(listPublications);
    }

    public ResponseEntity<Object> deletePublication(UUID idPublication) {
        return publicationRepository.findById(idPublication).map(publicationsModel -> {
            publicationRepository.delete(publicationsModel);
            return ResponseEntity.noContent().build();
        }).orElseThrow(PublicationNotFoundException::new);
    }

    public ResponseEntity<List<PublicationsModel>> getAllPublicationsByIdProfile(UUID uuid) {
        List<PublicationsModel> listPublications = publicationRepository.findPublicationsByIdProfile(uuid);
        return ResponseEntity.ok().body(listPublications);
    }

}
