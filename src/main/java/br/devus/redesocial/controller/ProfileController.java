package br.devus.redesocial.controller;

import br.devus.redesocial.model.ProfileModel;
import br.devus.redesocial.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/profile")
public class ProfileController {

    @Autowired
    ProfileService profileService;

    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<ProfileModel> saveProfile(@RequestBody ProfileModel profileModel) {
        return profileService.saveProfile(profileModel);
    }

    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<List<ProfileModel>> getAllProfile() {
        return profileService.getAllProfiles();
    }

    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<ProfileModel> getProfileById(@PathVariable UUID id) {
        return profileService.getProfileById(id);
    }

}



