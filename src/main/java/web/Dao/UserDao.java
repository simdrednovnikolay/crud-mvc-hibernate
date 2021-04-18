package web.Dao;

import web.model.User;

import java.util.List;

public interface UserDao {

    public List<User> getAllUsers();

    public void saveUser(User user);

    public User getUserId(Long id);

    public void updateInfo(Long id, User userUpdateInfo);

    public void deleteUser(Long id);
}
