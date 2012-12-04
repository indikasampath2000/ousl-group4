package ousl.group4.dao.impl;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ousl.group4.dao.CountryDao;
import ousl.group4.model.Country;

import java.util.List;

@Repository("countryDao")
public class CountryDaoHibernate implements CountryDao {

    @Autowired
    private SessionFactory sessionFactory;

    @SuppressWarnings("unchecked")
    public List<Country> getAllCountries() {
        return sessionFactory.getCurrentSession().createQuery("from Country c").list();
    }

    public Country countryByCountryCode(String countryCode) {
        return (Country) sessionFactory.getCurrentSession().createCriteria(Country.class).add(Restrictions.eq("countryCode", countryCode)).uniqueResult();
    }

}
