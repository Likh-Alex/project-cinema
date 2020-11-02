package ua.antibyte.cinema.dao;

import ua.antibyte.cinema.model.Role;

public interface RoleDao {
    void add(Role role);

    Role findByName(String roleName);
}
