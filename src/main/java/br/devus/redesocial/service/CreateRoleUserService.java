package br.devus.redesocial.service;

import br.devus.redesocial.dto.CreateUserRoleDTO;
import br.devus.redesocial.model.Role;
import br.devus.redesocial.model.UserModel;
import br.devus.redesocial.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CreateRoleUserService {

    final UserRepository userRepository;

    final CreateUserService userService;

    public CreateRoleUserService(UserRepository userRepository, CreateUserService userService) {
        this.userRepository = userRepository;
        this.userService = userService;
    }

    public UserModel execute(CreateUserRoleDTO createUserRoleDTO) {

        Optional<UserModel> userExists = userRepository.findById(createUserRoleDTO.getIdUser());

        List<Role> roles = new ArrayList<>();

        if (userExists.isEmpty()) {
            throw new Error("User does not exists!");
        }

        roles = createUserRoleDTO.getIdsRoles().stream().map(role -> {
            return new Role(role);
        }).collect(Collectors.toList());

        UserModel user = userExists.get();

        user.setRoles(roles);

        userRepository.save(user);

        return user;
    }

}
