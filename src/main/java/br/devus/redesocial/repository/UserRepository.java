package br.devus.redesocial.repository;

import br.devus.redesocial.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<UserModel, UUID> {

    Optional<UserModel> findByEmail(String email);

    @Query("SELECT u from UserModel u JOIN FETCH u.roles where u.email = :email")
    UserModel findByEmailFetchRoles(@Param("email") String email);

}
