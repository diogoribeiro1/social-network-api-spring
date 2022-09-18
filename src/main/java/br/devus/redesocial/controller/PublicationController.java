package br.devus.redesocial.controller;

import br.devus.redesocial.model.ProfileModel;
import br.devus.redesocial.model.PublicationsModel;
import br.devus.redesocial.service.ProfileService;
import br.devus.redesocial.service.PublicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/publication")
public class PublicationController {

    @Autowired
    PublicationService publicationService;

    @Autowired
    ProfileService profileService;

    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @PostMapping("/{idProfile}")
    public PublicationsModel savePublication (@PathVariable UUID idProfile, @RequestBody PublicationsModel publicationsModel)
    {
       ProfileModel profileModel =  profileService.getProfileById(idProfile);

       publicationsModel.setProfile(profileModel);

       return publicationService.savePublication(publicationsModel);
    }

    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @GetMapping
    public List<PublicationsModel> getAllPublications ()
    {
        return publicationService.getAllPublications();
    }

    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @GetMapping("/{id}")
    public List<PublicationsModel> getAllPublicationsByIdProfile (@PathVariable UUID id)
    {
        return publicationService.getPublicationsByIdProfile(id);
    }

    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @DeleteMapping("/{idPublication}")
    public ResponseEntity<Object> deletePublication (@PathVariable UUID idPublication)
    {
        publicationService.deletePublication(idPublication);
        return ResponseEntity.noContent().build();
    }

}
