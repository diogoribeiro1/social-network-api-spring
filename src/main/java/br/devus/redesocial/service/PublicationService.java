package br.devus.redesocial.service;

import br.devus.redesocial.model.PublicationsModel;
import br.devus.redesocial.repository.PublicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class PublicationService {

    @Autowired
    PublicationRepository publicationRepository;

    public PublicationsModel savePublication (PublicationsModel publicationsModel)
    {
       return publicationRepository.save(publicationsModel);
    }

    public List<PublicationsModel> getAllPublications()
    {
       return publicationRepository.findAll();
    }

    public void deletePublication(UUID idPublication) {
        publicationRepository.findById(idPublication).map(publicationsModel -> {
            publicationRepository.delete(publicationsModel);
            return null;
        });
    }

    public List<PublicationsModel> getPublicationsByIdProfile(UUID idProfile)
    {
        return publicationRepository.findPublicationsByIdProfile(idProfile);
    }

}
