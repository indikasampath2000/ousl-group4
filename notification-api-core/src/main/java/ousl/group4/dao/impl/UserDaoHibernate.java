package ousl.group4.dao.impl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ousl.group4.dao.UserDao;
import ousl.group4.model.User;

import java.util.List;

@Repository("userDao")
public class UserDaoHibernate implements UserDao, UserDetailsService {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public User getUser(Long userId) {
        User user = (User) sessionFactory.getCurrentSession().get(User.class, userId);
        return user;
    }

    @Override
    public User getUserByUsername(String username) {
        return (User) sessionFactory.getCurrentSession().createQuery("from User u where u.username= :username").setParameter("username", username).list().get(0);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<User> getUsers() {
        return sessionFactory.getCurrentSession().createQuery("from User u order by upper(u.username)").list();
    }

    @Override
    public User saveUser(User user) {
        sessionFactory.getCurrentSession().saveOrUpdate(user);
        sessionFactory.getCurrentSession().flush();
        return user;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = (User) sessionFactory.getCurrentSession().createQuery("from User u where u.username= :username").setParameter("username", username).list().get(0);

        if (user == null || user.getAuthorities().isEmpty()) {
            throw new UsernameNotFoundException("user '" + username + "' not found...");
        } else {
            return (UserDetails) user;
        }
    }

}
