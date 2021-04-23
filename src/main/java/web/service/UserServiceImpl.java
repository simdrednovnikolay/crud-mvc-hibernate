package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.dao.RoleDao;
import web.dao.UserDao;
import web.model.Role;
import web.model.User;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class UserServiceImpl implements UserService{

    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;

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
    public void updateUser(User userUpdateInfo) {
        userDao.updateUser(userUpdateInfo);
    }

    @Override
    public void deleteUser(Long id) {
        userDao.deleteUser(id);
    }

    @Override
    public User getByName(String name) {
        return userDao.getByName(name);
    }

    @Override
    public List<Role> getAllRoles() {
        return roleDao.getAllRoles();
    }

    @Override
    public void updateUserAndRoles(User user, String[] roleList) {
        List<Role> update = new ArrayList<>();
        if (roleList != null){
            for (int i =0; i<roleList.length; i++) {
                if (!user.getRoles().contains(roleList[i])){
                    update.add(roleDao.getByName(roleList[i]));
                }
            }
            user.setRoles(update);
        }
        userDao.updateUser(user);
    }



    @Override
    @Transactional
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        User user = userDao.getByName(name);
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        for(Role role: user.getRoles()) {
            grantedAuthorities.add(new SimpleGrantedAuthority(role.getName()));
        }

        return new org.springframework.security.core.userdetails.
                User(user.getUsername(), user.getPassword(), grantedAuthorities);
    }
}
