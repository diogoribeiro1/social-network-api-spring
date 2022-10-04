package br.devus.redesocial.controller;

import br.devus.redesocial.dto.FollowProfileDTO;
import br.devus.redesocial.model.FollowModel;
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
    private ProfileService profileService;

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

    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    @ApiOperation(value = "Delete a profile")
    public ResponseEntity<Object> deleteProfile(@PathVariable UUID id) {
        return profileService.deleteProfileById(id);
    }

    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @PostMapping("/follow")
    @ApiOperation(value = "Follow a profile")
    public ResponseEntity<FollowModel> followProfile(@RequestBody FollowProfileDTO followProfileDTO) {
        return profileService.followProfile(followProfileDTO.getIdProfile(), followProfileDTO.getIdProfileToFollow());
    }

    // @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    // @GetMapping("/{id}/followers")
    // @ApiOperation(value = "Get all followers")
    // public ResponseEntity<List<ProfileModel>> getFollowers(@PathVariable UUID id) {
    //     return profileService.getAllFollowers(id);
    // }

}



