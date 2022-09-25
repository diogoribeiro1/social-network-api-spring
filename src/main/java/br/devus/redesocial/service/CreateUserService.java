package br.devus.redesocial.service;

import br.devus.redesocial.exceptionhandler.userexception.*;
import br.devus.redesocial.model.UserModel;
import br.devus.redesocial.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CreateUserService {

    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    final UserRepository userRepository;

    public CreateUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserModel execute(UserModel user) {

        UserModel existsUser = userRepository.findByEmailFetchRoles(user.getEmail());
        if (existsUser != null) {
            throw new UserAlreadyExistsException();
        }
        user.setPassword(passwordEncoder().encode(user.getPassword()));
        UserModel createdUser = userRepository.save(user);

        return createdUser;
    }
}
