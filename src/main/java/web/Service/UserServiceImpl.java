package web.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import web.Dao.UserDao;
import web.model.User;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserDao userDao;

    @Override
    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    @Override
    public void saveUser(User user) {
        userDao.saveUser(user);
    }

    @Override
    public User getUserId(Long id) {
        return userDao.getUserId(id);
    }

    @Override
    public void updateUser(Long id, User userUpdateInfo) {
        userDao.updateUser(id,userUpdateInfo);
    }

    @Override
    public void deleteUser(Long id) {
        userDao.deleteUser(id);
    }
}
