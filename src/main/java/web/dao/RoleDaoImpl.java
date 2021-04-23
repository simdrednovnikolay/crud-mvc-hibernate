package web.dao;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import web.model.Role;
import web.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@Repository
public class RoleDaoImpl implements RoleDao{

    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public List<Role> getAllRoles() {
        return entityManager.createQuery("select r from Role r", Role.class).getResultList();
    }

    @Override
    public Role getByName(String name) {
        TypedQuery<Role> query = entityManager.createQuery("select r from Role r where r.name=:name", Role.class);
        query.setParameter("name", name);
        return query.getResultList().get(0);
    }
}
