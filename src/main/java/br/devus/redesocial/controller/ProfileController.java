package br.devus.redesocial.controller;

import br.devus.redesocial.model.ProfileModel;
import br.devus.redesocial.model.UserModel;
import br.devus.redesocial.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/profile")
public class ProfileController {

    @Autowired
    UserService userService;

    @PostMapping
    public ResponseEntity<UserModel> saveProfile(@RequestBody ProfileModel profileModel) {
        UserModel userModel = userService.getUserById(profileModel.getIdUser()).getBody();
        if (userModel.getProfile().equals(null))
        {
            userModel.setProfile(profileModel);
            userService.saveUser(userModel);
            return ResponseEntity.ok().body(userModel);
        }else{
            return ResponseEntity.badRequest().build();
        }
    }



}
