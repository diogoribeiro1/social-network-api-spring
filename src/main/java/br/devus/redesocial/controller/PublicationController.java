package br.devus.redesocial.controller;

import br.devus.redesocial.model.ProfileModel;
import br.devus.redesocial.model.PublicationsModel;
import br.devus.redesocial.service.ProfileService;
import br.devus.redesocial.service.PublicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
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
    public ResponseEntity<PublicationsModel> savePublication (@PathVariable UUID idProfile, @RequestBody PublicationsModel publicationsModel)
    {
       ProfileModel profileModel =  profileService.getProfileById(idProfile).getBody();

       publicationsModel.setProfile(profileModel);

       return publicationService.savePublication(publicationsModel);
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping
    public ResponseEntity<List<PublicationsModel>> getAllPublications ()
    {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String nome;

        if (principal instanceof UserDetails) {
            nome = ((UserDetails)principal).getUsername();
        } else {
            nome = principal.toString();
        }
        System.out.println(nome);

        return publicationService.getAllPublications();
    }

    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<List<PublicationsModel>> getAllPublicationsByIdProfile (@PathVariable UUID id)
    {
        return publicationService.getAllPublicationsByIdProfile(id);
    }

    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @DeleteMapping("/{idPublication}")
    public ResponseEntity<Object> deletePublication (@PathVariable UUID idPublication)
    {
        publicationService.deletePublication(idPublication);
        return ResponseEntity.noContent().build();
    }

}
