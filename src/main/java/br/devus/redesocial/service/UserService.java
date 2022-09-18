package br.devus.redesocial.service;

import br.devus.redesocial.model.UserModel;
import br.devus.redesocial.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public ResponseEntity<UserModel> saveUser(UserModel userModel)
    {
        UserModel user = userRepository.save(userModel);
        return ResponseEntity.status(201).body(user);
    }

    public ResponseEntity<List<UserModel>> getAllUsers()
    {
        List<UserModel> listUsers = userRepository.findAll();
        return ResponseEntity.ok().body(listUsers);
    }

    public ResponseEntity<UserModel> getUserById(UUID id)
    {
        return userRepository.findById(id).map(user -> ResponseEntity.ok().body(user))
                .orElse(ResponseEntity.notFound().build());
    }

    public ResponseEntity<Object> deleteUserById(UUID id)
    {
           return userRepository.findById(id).map(userToDelete -> {
            userRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }).orElse(ResponseEntity.notFound().build());
    }

    public ResponseEntity<UserModel> updateUserById(UserModel userModel, UUID id) {
        return userRepository.findById(id).map(userToUpdate -> {
            userToUpdate.setFullName(userModel.getFullName());
            userToUpdate.setBirthDate(userModel.getBirthDate());
            userToUpdate.setEmail(userModel.getEmail());
            userToUpdate.setPassword(userModel.getPassword());
            UserModel updated = userRepository.save(userToUpdate);
            return ResponseEntity.ok().body(updated);
        }).orElse(ResponseEntity.notFound().build());
    }

}
