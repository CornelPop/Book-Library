package org.example.repository.security;

import org.example.model.Right;
import org.example.model.Role;
import org.example.model.User;

import java.util.List;

public interface RightsRolesRepository {
    void addRole(String role);

    void addRight(String right);

    Role findRoleByTitle(String role);

    Role findRoleById(Long roleId);

    Right findRightByTitle(String right);

    void addRolesToUser(User user, List<Role> roles);

    List<Role> findRolesForUser(Long userId);

    void addRoleRight(Long roleId, Long rightId);
}
