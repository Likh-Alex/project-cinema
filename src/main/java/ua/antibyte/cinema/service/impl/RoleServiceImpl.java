package ua.antibyte.cinema.service.impl;

import org.springframework.stereotype.Service;
import ua.antibyte.cinema.dao.RoleDao;
import ua.antibyte.cinema.model.Role;
import ua.antibyte.cinema.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService {
    private final RoleDao roleDao;

    public RoleServiceImpl(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    @Override
    public void add(Role role) {
        roleDao.add(role);
    }

    @Override
    public Role getRoleByName(String roleName) {
        return roleDao.findByName(roleName);
    }
}
