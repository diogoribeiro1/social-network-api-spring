package br.devus.redesocial.controller;

import br.devus.redesocial.model.UserModel;
import br.devus.redesocial.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    UserRepository userRepository;

    //prepare
    @BeforeEach
    public void setup()
    {
        UserModel userModel = new UserModel();
        userModel.setIdUser(UUID.fromString("b751db30-7150-447b-818d-7ab9a2e2cd64"));
        userModel.setFullName("Marcos");
        userModel.setEmail("diogoribeiro698@gmail.com");
        userModel.setBirthDate("16/06/2004");
        userModel.setPassword("123");

        Mockito.when(userRepository.save(Mockito.any()))
                .thenReturn(userModel);

//        Mockito.when(eventRepository.findAll())
//                .thenReturn(List.of(eventModel));
//
//        Mockito.when(eventRepository.findById(Mockito.any()))
//                .thenReturn(Optional.of(eventModel));
//
//        Mockito.when(eventRepository.save(Mockito.any()))
//                .thenReturn(eventModel);

    }


    @Test
    void testCanSaveAUser () throws Exception
    {
        //act
        this.mockMvc.perform(post("/user")
//                        .with(user("diogo").password("123").roles("ADMIN"))
                        .content("{\"fullName\": \"Diogo Ribeiro Ramos\",\"email\": \"diogoribeiro698@gmail.com\",\"password\": \"123\",\"birthDate\": \"16/06/2004\"}")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                //assert
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.idUser").value("b751db30-7150-447b-818d-7ab9a2e2cd64"))
                .andExpect(jsonPath("$.fullName").value("Marcos"))
                .andExpect(jsonPath("$.email").value("diogoribeiro698@gmail.com"))
                .andExpect(jsonPath("$.password").value("123"))
                .andExpect(jsonPath("$.birthDate").value("16/06/2004"));
    }

}
