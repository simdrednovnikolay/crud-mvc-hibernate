package web.Dao;

import web.model.User;

import java.util.List;

public interface UserDao {

    List<User> getAllUsers();

    void saveUser(User user);

    User getUserId(Long id);

    void updateUser(Long id, User userUpdateInfo);

    void deleteUser(Long id);
}
