package web.Dao;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import web.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional
public class UserDaoImpl implements UserDao{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<User> getAllUsers() {
        return entityManager.createQuery("select u from  User u", User.class).getResultList();
    }

    @Override
    @Transactional
    public void saveUser(User user) {
        entityManager.persist(user);
    }



    @Override
    public User getUserId(Long id) {
        return entityManager.find(User.class, id);

    }

    @Override
    public void updateUser(Long id, User userUpdateInfo) {
        User userToUpdate = getUserId(id);
        userToUpdate.setFirstName(userUpdateInfo.getFirstName());
        userToUpdate.setLastName(userUpdateInfo.getLastName());
        userToUpdate.setEmail(userUpdateInfo.getEmail());

    }

    @Override
    public void deleteUser(Long id) {
        entityManager.remove(getUserId(id));
    }


}
