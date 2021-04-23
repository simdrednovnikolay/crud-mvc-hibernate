package web.dao;

import web.model.Role;
import web.model.User;

import java.util.List;

public interface RoleDao  {

    List<Role> getAllRoles();

    public Role getByName(String name);

}
