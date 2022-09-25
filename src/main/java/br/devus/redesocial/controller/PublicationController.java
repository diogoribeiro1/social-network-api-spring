package br.devus.redesocial.controller;

import br.devus.redesocial.model.PublicationsModel;
import br.devus.redesocial.service.PublicationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/publication")
@Api(value = "Api Rest Publications")
public class PublicationController {

    @Autowired
    private PublicationService publicationService;

    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @PostMapping("/{idProfile}")
    @ApiOperation(value = "Create a publication")
    public ResponseEntity<PublicationsModel> savePublication(@PathVariable UUID idProfile, @RequestBody PublicationsModel publicationsModel) {
        return publicationService.savePublication(publicationsModel, idProfile);
    }

    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @GetMapping
    @ApiOperation(value = "Get all publications")
    public ResponseEntity<List<PublicationsModel>> getAllPublications() {
        return publicationService.getAllPublications();
    }

    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @GetMapping("/{id}")
    @ApiOperation(value = "Create a publication by id profile")
    public ResponseEntity<List<PublicationsModel>> getAllPublicationsByIdProfile(@PathVariable UUID id) {
        return publicationService.getAllPublicationsByIdProfile(id);
    }

    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @DeleteMapping("/{idPublication}")
    @ApiOperation(value = "Delete a publication")
    public ResponseEntity<Object> deletePublication(@PathVariable UUID idPublication) {
        return publicationService.deletePublication(idPublication);
    }

}
