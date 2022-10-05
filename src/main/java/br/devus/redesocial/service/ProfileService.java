package br.devus.redesocial.service;

import br.devus.redesocial.exceptionhandler.profileException.ProfileAlreadyExistsException;
import br.devus.redesocial.exceptionhandler.profileException.ProfileNotFoundException;
import br.devus.redesocial.model.FollowModel;
import br.devus.redesocial.model.ProfileModel;
import br.devus.redesocial.model.UserModel;
import br.devus.redesocial.repository.FollowRepository;
import br.devus.redesocial.repository.ProfileRepository;
import br.devus.redesocial.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.transaction.Transactional;

@Service
@Transactional
public class ProfileService {

    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FollowRepository followRepository;

    public ResponseEntity<ProfileModel> saveProfile(ProfileModel profileModel) {

        UserModel userModel = userService.getUserById(profileModel.getIdUser()).getBody();

        // Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        // String nome;

        // if (principal instanceof UserDetails) {
        //     nome = ((UserDetails) principal).getUsername();
        // } else {
        //     nome = principal.toString();
        // }
        // System.out.println(nome);

        if (userModel.getProfile() == null) {

            userModel.setProfile(profileModel);
            userModel = userRepository.save(userModel);

            return ResponseEntity.status(HttpStatus.CREATED).body(userModel.getProfile());
        
        } else {
            throw new ProfileAlreadyExistsException();
        }
    }

    public ResponseEntity<List<ProfileModel>> getAllProfiles() {
        return ResponseEntity.ok().body(profileRepository.findAll());
    }

    public ResponseEntity<ProfileModel> getProfileById(UUID idProfile) {
        ProfileModel profileModel = profileRepository.findById(idProfile).orElseThrow(ProfileNotFoundException::new);
        return ResponseEntity.ok().body(profileModel);
    }

    public ResponseEntity<Object> deleteProfileById(UUID id){
       ProfileModel profileModel =  this.getProfileById(id).getBody();
       profileRepository.delete(profileModel);
       return ResponseEntity.noContent().build();
    }

    public ResponseEntity<FollowModel> followProfile(UUID id, UUID idProfileToFollow){

        ProfileModel profile = this.getProfileById(id).getBody();
        ProfileModel profileToFollow = this.getProfileById(idProfileToFollow).getBody();

        FollowModel followModel = new FollowModel(profileToFollow.getIdProfile(), profile);

        followModel = followRepository.save(followModel);

        return ResponseEntity.ok().body(followModel);
     }

     public ResponseEntity<List<FollowModel>> getAllFollowers(UUID id) {
        
        ProfileModel profile = this.getProfileById(id).getBody();
       
        List<FollowModel> followers = new ArrayList<>();
        
        followers = followRepository.findByFollower(profile).orElseThrow();

        return ResponseEntity.ok().body(followers);
    }


}
