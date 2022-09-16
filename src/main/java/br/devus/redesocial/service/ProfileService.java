package br.devus.redesocial.service;

import br.devus.redesocial.model.ProfileModel;
import br.devus.redesocial.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ProfileService {

    @Autowired
    ProfileRepository profileRepository;

    public ProfileModel getProfileById(UUID idProfile)
    {
       return profileRepository.findById(idProfile).orElseThrow();
    }

}
