package br.devus.redesocial.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.devus.redesocial.model.FollowModel;
import br.devus.redesocial.model.ProfileModel;
import br.devus.redesocial.repository.FollowRepository;

@Service
public class FollowService {

    @Autowired
    private FollowRepository followRepository;

    @Autowired
    private ProfileService profileService;


    // public ResponseEntity<FollowModel> followProfile(UUID id, UUID idProfileToFollow){

    //     ProfileModel profile = profileService.getProfileById(id).getBody();
    //     ProfileModel profileToFollow = profileService.getProfileById(idProfileToFollow).getBody();

    //     profile.getFollowing().add(profileToFollow.getIdProfile());
    //     profileToFollow.getFollowers().add(profile.getIdProfile());

    //     profile = profileRepository.save(profile);
    //     profileRepository.save(profileToFollow);

    //     return ResponseEntity.ok().body(profile);
    //  }

    //  public ResponseEntity<List<ProfileModel>> getAllFollowers(UUID id) {
    //     ProfileModel profile = this.getProfileById(id).getBody();
    //     System.out.println(profile.getFollowers());
    //     return ResponseEntity.ok().body(profile.getFollowers());
    // }
       
}
