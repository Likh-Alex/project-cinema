package ua.antibyte.cinema.service;

import ua.antibyte.cinema.model.Role;

public interface RoleService {
    void add(Role role);

    Role getRoleByName(String roleName);
}
