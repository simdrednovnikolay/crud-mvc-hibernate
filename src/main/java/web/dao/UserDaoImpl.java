package web.dao;

import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import web.model.Role;
import web.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@Repository
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
        TypedQuery<User> query = entityManager.createQuery("select u from User u where u.id=:id",User.class);
        query.setParameter("id", id);
        return query.getResultList().stream().findAny().orElse(null);

    }

    @Override
    public void updateUser(User userUpdateInfo) {
        entityManager.merge(userUpdateInfo);


    }

    @Override
    public void deleteUser(Long id) {
        entityManager.remove(getUserId(id));
    }

    @Override
    public User getByName(String name) {
        TypedQuery<User> query = entityManager.createQuery("select u from User u where u.name=:name" ,User.class);
        query.setParameter("name", name);
        return query.getResultList().stream().findAny().orElse(null);
    }



}
