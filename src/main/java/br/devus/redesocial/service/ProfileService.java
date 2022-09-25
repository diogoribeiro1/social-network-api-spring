package br.devus.redesocial.service;

import br.devus.redesocial.exceptionhandler.profileException.ProfileAlreadyExistsException;
import br.devus.redesocial.exceptionhandler.profileException.ProfileNotFoundException;
import br.devus.redesocial.model.ProfileModel;
import br.devus.redesocial.model.UserModel;
import br.devus.redesocial.repository.ProfileRepository;
import net.bytebuddy.implementation.bytecode.Throw;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ProfileService {

    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private UserService userService;

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
            userService.saveUser(userModel);
            return ResponseEntity.status(HttpStatus.CREATED).body(profileModel);
        
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
       ProfileModel profileModel =  getProfileById(id).getBody();
       profileRepository.delete(profileModel);
       return ResponseEntity.noContent().build();
    }

}
