package web.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import web.model.Role;
import web.model.User;

import java.util.List;

public interface UserService extends UserDetailsService {

     List<User> getAllUsers();

     void saveUser(User user);

     User getUserId(Long id);

     public void updateUser(User userUpdateInfo);

     void deleteUser(Long id);

     User getByName(String name);

     List<Role> getAllRoles();

     void updateUserAndRoles(User user, String[] roleList);


}
