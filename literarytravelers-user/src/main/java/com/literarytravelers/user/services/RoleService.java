package com.literarytravelers.user.services;

import com.literarytravelers.user.entities.Role;
import java.util.List;
import java.util.Optional;

public interface RoleService {
    Role addRole(Role role);
    Optional<Role> getRoleById(Long id);
    List<Role> getAllRoles();
    Role updateRole(Long id, Role roleDetails);
    void deleteRole(Long id);
}
