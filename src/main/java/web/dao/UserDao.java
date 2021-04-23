package web.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import web.model.Role;
import web.model.User;

import java.util.List;

public interface UserDao  {

    List<User> getAllUsers();

    void saveUser(User user);

    User getUserId(Long id);

    public void updateUser(User userUpdateInfo);

    void deleteUser(Long id);

    User getByName(String name);
}
