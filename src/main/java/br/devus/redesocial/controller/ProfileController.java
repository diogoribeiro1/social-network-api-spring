package br.devus.redesocial.controller;

import br.devus.redesocial.model.ProfileModel;
import br.devus.redesocial.model.UserModel;
import br.devus.redesocial.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/profile")
public class ProfileController {

    @Autowired
    UserService userService;

    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<UserModel> saveProfile(@RequestBody ProfileModel profileModel) {

        UserModel userModel = userService.getUserById(profileModel.getIdUser()).getBody();

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        String nome;

        if (principal instanceof UserDetails) {
            nome = ((UserDetails)principal).getUsername();
        } else {
            nome = principal.toString();
        }
        System.out.println(nome);

        if (userModel.getProfile() == null)
        {
            userModel.setProfile(profileModel);
            userService.saveUser(userModel);
            return ResponseEntity.ok().body(userModel);
        }else{
            return ResponseEntity.badRequest().build();
        }
    }



}
