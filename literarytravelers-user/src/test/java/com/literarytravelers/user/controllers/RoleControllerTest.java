package com.literarytravelers.user.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertTrue;


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

import com.literarytravelers.user.entities.Role;
import com.literarytravelers.user.exceptions.ApplicationException;
import com.literarytravelers.user.security.TestSecurityConfig;
import com.literarytravelers.user.services.RoleService;

@WebMvcTest(RoleController.class)
@ActiveProfiles("test")
@Import(TestSecurityConfig.class)
public class RoleControllerTest {

    @InjectMocks
    private RoleController roleController;

    @MockBean
    private RoleService roleService;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private Role role;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        startRole();
    }

    @Test
    void startRole() {
        role = new Role();
        role.setId(1L);
        role.setRoleName("ROLE_USER");
    }

    @Test
    @DisplayName("Adicionar Permissão")
    @WithMockUser(username = "admin", roles = { "ADMIN" })
    void testAddRole() {
        when(roleService.addRole(any())).thenReturn(role);

        ResponseEntity<Role> response = roleController.addRole(role);

        assertNotNull(response);
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(role, response.getBody());

        verify(roleService, times(1)).addRole(any());
    }

    @Test
    @DisplayName("Deletar Permissão")
    @WithMockUser(username = "admin", roles = { "ADMIN" })
    void testDeleteRole() {
        doNothing().when(roleService).deleteRole(anyLong());

        ResponseEntity<Void> response = roleController.deleteRole(role.getId());

        assertNotNull(response);
        assertEquals(ResponseEntity.class, response.getClass());

        verify(roleService, times(1)).deleteRole(anyLong());
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    @DisplayName("Listar todas as permissões")
    @WithMockUser(username = "admin", roles = { "ADMIN" })
    public void testGetAllRoles() throws Exception {
        when(roleService.getAllRoles()).thenReturn(List.of(role));

        ResponseEntity<List<Role>> response = ResponseEntity.ok(roleService.getAllRoles());

        assertNotNull(response);
        assertNotNull(response.getBody());

        if (response.getBody() != null) {
            assertEquals(HttpStatus.OK, response.getStatusCode());
            assertEquals(ResponseEntity.class, response.getClass());
            assertTrue(List.class.isAssignableFrom(response.getBody().getClass()));
            assertEquals(Role.class, response.getBody().get(0).getClass());

            assertEquals(role.getId(), response.getBody().get(0).getId());
            assertEquals(role.getRoleName(), response.getBody().get(0).getRoleName());
        }
    }

    @Test
    @DisplayName("Encontrar Permissão por id")
    @WithMockUser(username = "admin", roles = { "ADMIN" })
    void testGetRoleById() {
        when(roleService.getRoleById(anyLong())).thenReturn(Optional.of(role));

        ResponseEntity<Role> response = roleController.getRoleById(role.getId());

        assertNotNull(response);
        assertNotNull(response.getBody());

        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(Role.class, response.getBody().getClass());

        assertEquals(role.getId(), response.getBody().getId());
        assertEquals(role.getRoleName(), response.getBody().getRoleName());
    }

    @Test
    @DisplayName("Atualizar Permissão")
    @WithMockUser(username = "admin", roles = { "ADMIN" })
    void testUpdateRole() {
        when(roleService.updateRole(anyLong(), any())).thenReturn(role);

        ResponseEntity<Role> response = roleController.updateRole(role.getId(), role);

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(Role.class, response.getBody().getClass());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(role, response.getBody());

        assertEquals(role.getId(), response.getBody().getId());
        assertEquals(role.getRoleName(), response.getBody().getRoleName());
    }

    @Test
    @DisplayName("Adicionar Permissão - Requisição inválida")
    @WithMockUser(username = "admin", roles = { "ADMIN" })
    void testAddRoleBadRequest() {
        when(roleService.addRole(any())).thenThrow(new IllegalArgumentException("Invalid role data"));

        ResponseEntity<Role> response = roleController.addRole(role);

        assertNotNull(response);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    @DisplayName("Atualizar Permissão - Requisição inválida")
    @WithMockUser(username = "admin", roles = { "ADMIN" })
    void testUpdateRoleBadRequest() {
        when(roleService.updateRole(anyLong(), any())).thenThrow(new IllegalArgumentException("Invalid role data"));

        ResponseEntity<Role> response = roleController.updateRole(1L, role);
    
        assertNotNull(response);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    @DisplayName("Atualizar Permissão - Permissão não encontrada")
    @WithMockUser(username = "admin", roles = { "ADMIN" })
    void testUpdateRoleNotFound() {
        when(roleService.updateRole(anyLong(), any())).thenThrow(new ApplicationException("Role Not Found", null));

        ResponseEntity<Role> response = roleController.updateRole(1L, role);

        assertNotNull(response);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    @DisplayName("Encontrar Permissão por id - Não encontra o id informado")
    @WithMockUser(username = "admin", roles = { "ADMIN" })
    void testGetRoleByIdNotFound() {
        when(roleService.getRoleById(anyLong())).thenReturn(Optional.empty());

        ResponseEntity<Role> response = roleController.getRoleById(1L);

        assertNotNull(response);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    @DisplayName("Listar todas as permissões - Nenhuma permissão encontrada")
    @WithMockUser(username = "admin", roles = { "ADMIN" })
    void testGetAllRolesNotFound() {
        when(roleService.getAllRoles()).thenReturn(List.of());

    ResponseEntity<List<Role>> response = new ResponseEntity<>(roleService.getAllRoles(), HttpStatus.NOT_FOUND);

        assertNotNull(response);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    @DisplayName("Deletar Permissão - Permissão não encontrada")
    @WithMockUser(username = "admin", roles = { "ADMIN" })
    void testDeleteRoleNotFound() {
        doThrow(new ApplicationException("Role not Found", null)).when(roleService).deleteRole(anyLong());

        ResponseEntity<Void> response = roleController.deleteRole(1L);

        assertNotNull(response);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
}