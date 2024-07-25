package com.literarytravelers.user.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import com.literarytravelers.user.entities.User;
import com.literarytravelers.user.exceptions.ApplicationException;
import com.literarytravelers.user.security.TestSecurityConfig;
import com.literarytravelers.user.services.UserService;

@WebMvcTest(UserController.class)
@ActiveProfiles("test")
@Import(TestSecurityConfig.class) // Importar a configuração de segurança para testes
public class UserControllerTest {

    @InjectMocks
    private UserController userController;

    @MockBean
    private UserService userService;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private User user;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        startUser();
    }

    @Test
    void startUser() {
        user = new User();
        user.setId(1L);
        user.setFullname("Fernanda Avellar");
        user.setloginName("FERNANDA");
        user.setPassword("Aa*456");
        user.setEmail("fernanda.avellar@example.com");
        user.setCpf("11111111111");
        user.setBirthDate(null);

    }

    @Test
    @DisplayName("Listar todos os usuários")
    void testeListarTodosOsUsuáriosComSucesso() {
        // Mockando o serviço para retornar uma lista com o objeto user
        when(userService.getAllUsers()).thenReturn(List.of(user));

        // Chamando o método do controlador
        ResponseEntity<List<User>> response = userController.getAllUsers();

        // Verificando se a resposta não é nula
        assertNotNull(response);
        assertNotNull(response.getBody());

        // Check if getBody() is not null before accessing its properties
        if (response.getBody() != null) {
            // Verificando o status da resposta
            assertEquals(HttpStatus.OK, response.getStatusCode());

            // Verificando o tipo da resposta e da lista no corpo da resposta
            assertEquals(ResponseEntity.class, response.getClass());
            assertTrue(List.class.isAssignableFrom(response.getBody().getClass())); // Verifica se a classe implementa a
                                                                                    // interface List

            // Verificando o tipo do objeto dentro da lista
            assertEquals(User.class, response.getBody().get(0).getClass());

            // Verificando os atributos do objeto user dentro da lista
            assertEquals(user.getId(), response.getBody().get(0).getId());
            assertEquals(user.getFullname(), response.getBody().get(0).getFullname());
            assertEquals(user.getLoginName(), response.getBody().get(0).getLoginName());
            assertEquals(user.getBirthDate(), response.getBody().get(0).getBirthDate());
            assertEquals(user.getEmail(), response.getBody().get(0).getEmail());
        }
    }

    @Test
    @DisplayName("Listar por ID")
    void ListarPorIdRetornarSucesso() {
        // Configurando o usuário para os testes
        User user = new User();
        user.setId(1L);
        user.setFullname("John Doe");
        user.setloginName("johndoe");
        user.setEmail("johndoe@example.com");

        // Mockando o serviço para retornar um Optional com o objeto user
        when(userService.getUserById(anyLong())).thenReturn(Optional.of(user));

        // Chamando o método do controlador
        ResponseEntity<User> response = userController.getUserById(user.getId());

        // Verificando se a resposta não é nula
        assertNotNull(response);
        assertNotNull(response.getBody());

        // Verificando o tipo da resposta e do corpo da resposta
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(User.class, response.getBody().getClass());

        // Verificando os atributos do objeto User retornado
        assertEquals(user.getId(), response.getBody().getId());
        assertEquals(user.getFullname(), response.getBody().getFullname());
        assertEquals(user.getLoginName(), response.getBody().getLoginName());
        assertEquals(user.getBirthDate(), response.getBody().getBirthDate());
        assertEquals(user.getEmail(), response.getBody().getEmail());
    }

    @Test
    @DisplayName("Cadastrar Usuário")
    @WithMockUser(username = "user", roles = { "USER" })
    void testSaveUser() throws Exception {
        when(userService.saveUser(any())).thenReturn(user);

        ResponseEntity<User> response = userController.saveUser(user);

        assertNotNull(response);
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(user, response.getBody());

        verify(userService, times(1)).saveUser(any());
    }

    @Test
    @DisplayName("Atualizar Usuário com sucesso")
    @WithMockUser(username = "user", roles = { "USER" })
    void testUpdateUser() {
        when(userService.updateUser(anyLong(), any())).thenReturn(user);

        ResponseEntity<User> response = userController.updateUser(user.getId(), user);

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(User.class, response.getBody().getClass());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(user, response.getBody());

        assertEquals(user.getId(), response.getBody().getId());
        assertEquals(user.getFullname(), response.getBody().getFullname());
        assertEquals(user.getLoginName(), response.getBody().getLoginName());
        assertEquals(user.getBirthDate(), response.getBody().getBirthDate());
        assertEquals(user.getEmail(), response.getBody().getEmail());
        assertEquals(user.getPassword(), response.getBody().getPassword());

    }

    @Test
    @DisplayName("Atualizar Usuário - Requisição inválida")
    @WithMockUser(username = "user", roles = { "USER" })
    void testUpdateUserBadRequest() {
        // Mockando o serviço para lançar uma exceção de validação
        when(userService.updateUser(anyLong(), any())).thenThrow(new IllegalArgumentException("Invalid user data"));

        // Chamando o método do controlador
        ResponseEntity<User> response = userController.updateUser(1L, user);

        // Verificando se a resposta não é nula
        assertNotNull(response);

        // Verificando o status da resposta
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    @DisplayName("Atualizar Usuário - Usuário não encontrado")
    @WithMockUser(username = "user", roles = { "USER" })
    void testUpdateUserNotFound() {
        // Configurando o retorno do serviço para quando o usuário não for encontrado
        when(userService.updateUser(anyLong(), any())).thenThrow(new ApplicationException("User Not Found", null));

        // Chamando o método do controlador
        ResponseEntity<User> response = userController.updateUser(1L, user);

        // Verificando se a resposta não é nula
        assertNotNull(response);

        // Verificando o status da resposta
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }


    @Test
    @DisplayName("Deletar Usuário com sucesso")
    @WithMockUser(username = "user", roles = { "USER" })
    void whenDelete_ThenReturnSuccess() {
        doNothing().when(userService).deleteUser(anyLong());

        ResponseEntity<Void> response = userController.deleteUser(user.getId());

        assertNotNull(response);
        assertEquals(ResponseEntity.class, response.getClass());

        verify(userService, times(1)).deleteUser(anyLong());
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    @DisplayName("Encontrar Usuários por id - Não encontra o id informado")
    @WithMockUser(username = "user", roles = { "USER" })
    void testGetUserByIdNotFound() {
        // Configurando o retorno do serviço para quando o usuário não for encontrado
        when(userService.getUserById(anyLong())).thenReturn(Optional.empty());

        // Chamando o método do controlador
        ResponseEntity<User> response = userController.getUserById(1L);

        // Verificando se a resposta não é nula
        assertNotNull(response);

        // Verificando o status da resposta
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    
    @Test
    @DisplayName("Cadastrar Usuário - Requisição inválida")
    @WithMockUser(username = "user", roles = { "USER" })
    void testSaveUserBadRequest() {
        // Mockando o serviço para lançar uma exceção de validação
        when(userService.saveUser(any())).thenThrow(new IllegalArgumentException("Invalid user data"));

        // Chamando o método do controlador
        ResponseEntity<User> response = userController.saveUser(user);

        // Verificando se a resposta não é nula
        assertNotNull(response);

        // Verificando o status da resposta
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    @DisplayName("Listar todos os usuários - Nenhum usuário encontrado")
    @WithMockUser(username = "user", roles = { "USER" })
    void testGetAllUsersNotFound() {
        // Mockando o serviço para retornar uma lista vazia
        when(userService.getAllUsers()).thenReturn(List.of());

        // Chamando o método do controlador
        ResponseEntity<List<User>> response = userController.getAllUsers();

        // Verificando se a resposta não é nula
        assertNotNull(response);

        // Verificando o status da resposta
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    @DisplayName("Deletar Usuário - Usuário não encontrado")
    @WithMockUser(username = "user", roles = { "USER" })
    void testDeleteUserNotFound() {
        // Configurando o serviço para lançar ApplicationException quando o usuário não for encontrado
        doThrow(new ApplicationException("User not Found", null)).when(userService).deleteUser(anyLong());
    
        // Chamando o método do controlador
        ResponseEntity<Void> response = userController.deleteUser(1L);
    
        // Verificando se a resposta não é nula
        assertNotNull(response);
    
        // Verificando o status da resposta
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

}

