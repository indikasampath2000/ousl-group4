package ousl.group4.dao.impl;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ousl.group4.dao.RoleDao;
import ousl.group4.model.Role;

@Repository("roleDao")
public class RoleDaoImpl implements RoleDao{

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Role getRoleByName(String roleName) {
        return (Role) sessionFactory.getCurrentSession().createCriteria(Role.class)
                .add(Restrictions.eq("name", roleName))
                .uniqueResult();
    }
}
