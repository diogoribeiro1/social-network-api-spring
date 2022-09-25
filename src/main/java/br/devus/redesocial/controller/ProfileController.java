package br.devus.redesocial.controller;

import br.devus.redesocial.model.ProfileModel;
import br.devus.redesocial.service.ProfileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/profile")
@Api(value = "Api Rest profiles")
public class ProfileController {

    @Autowired
    ProfileService profileService;

    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @PostMapping
    @ApiOperation(value = "Create a profile")
    public ResponseEntity<ProfileModel> saveProfile(@RequestBody ProfileModel profileModel) {
        return profileService.saveProfile(profileModel);
    }

    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @GetMapping
    @ApiOperation(value = "Get all profiles")
    public ResponseEntity<List<ProfileModel>> getAllProfile() {
        return profileService.getAllProfiles();
    }

    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @GetMapping("/{id}")
    @ApiOperation(value = "Get a profile by id")
    public ResponseEntity<ProfileModel> getProfileById(@PathVariable UUID id) {
        return profileService.getProfileById(id);
    }

}



