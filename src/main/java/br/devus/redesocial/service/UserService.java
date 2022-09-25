package br.devus.redesocial.service;

import br.devus.redesocial.dto.CreateUserRoleDTO;
import br.devus.redesocial.exceptionhandler.userexception.*;
import br.devus.redesocial.model.Role;
import br.devus.redesocial.model.UserModel;
import br.devus.redesocial.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CreateUserService createUserService;

    @Autowired
    private CreateRoleUserService createRoleUserService;

    @Autowired
    private RoleService roleService;

    public ResponseEntity<UserModel> saveUser(UserModel userModel) {
        UserModel existsUser = userRepository.findByEmailFetchRoles(userModel.getEmail());

        if (existsUser != null) {
            throw new UserAlreadyExistsException();
        }

        UserModel user = userRepository.save(userModel);

        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    public ResponseEntity<UserModel> saveUserWithRoleAdmin(UserModel user) {

        UserModel userModel = createUserService.execute(user);
        Role role = roleService.getRoleByName("ADMIN").getBody();

        CreateUserRoleDTO userRoleDTO = new CreateUserRoleDTO();
        List<UUID> lista = new ArrayList<>();

        lista.add(role.getRoleId());
        userRoleDTO.setIdsRoles(lista);
        userRoleDTO.setIdUser(userModel.getIdUser());
        createRoleUserService.execute(userRoleDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(userModel);
    }

    public ResponseEntity<UserModel> saveUserWithRoleUser(UserModel user) {

        UserModel userModel = createUserService.execute(user);
        Role role = roleService.getRoleByName("USER").getBody();

        CreateUserRoleDTO userRoleDTO = new CreateUserRoleDTO();
        List<UUID> lista = new ArrayList<>();

        lista.add(role.getRoleId());
        userRoleDTO.setIdsRoles(lista);
        userRoleDTO.setIdUser(userModel.getIdUser());

        createRoleUserService.execute(userRoleDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(userModel);
    }

    public ResponseEntity<UserModel> saveRoleToExistingUser(CreateUserRoleDTO createUserRoleDTO) {
        UserModel userModel = createRoleUserService.execute(createUserRoleDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(userModel);
    }

    public ResponseEntity<List<UserModel>> getAllUsers() {
        List<UserModel> listUsers = userRepository.findAll();
        return ResponseEntity.ok().body(listUsers);
    }

    public ResponseEntity<UserModel> getUserById(UUID id) {
        return userRepository.findById(id).map(user -> ResponseEntity.ok().body(user))
                .orElseThrow(UserNotFoundException::new);
    }

    public ResponseEntity<Object> deleteUserById(UUID id) {
        return userRepository.findById(id).map(userToDelete -> {
            userRepository.delete(userToDelete);
            return ResponseEntity.noContent().build();
        }).orElseThrow(UserNotFoundException::new);
    }

    public ResponseEntity<UserModel> updateUserById(UserModel userModel, UUID id) {
        return userRepository.findById(id).map(userToUpdate -> {
            userToUpdate.setFullName(userModel.getFullName());
            userToUpdate.setBirthDate(userModel.getBirthDate());
            userToUpdate.setEmail(userModel.getEmail());
            userToUpdate.setPassword(userModel.getPassword());
            UserModel updated = userRepository.save(userToUpdate);
            return ResponseEntity.ok().body(updated);
        }).orElseThrow(UserNotFoundException::new);
    }

}
