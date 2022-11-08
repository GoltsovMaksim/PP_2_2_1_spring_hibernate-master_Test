package hiber.dao;

import hiber.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void add(User user) {
        sessionFactory.getCurrentSession().save(user);
    }

    @Override
    public List<User> listUsers() {
        CriteriaBuilder criteriaBuilder = sessionFactory.getCurrentSession().getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
        Root<User> root = criteriaQuery.from(User.class);
        criteriaQuery.select(root);
        Query<User> query = sessionFactory.getCurrentSession().createQuery(criteriaQuery);
        return query.getResultList();
    }

    @Override
    public User getUserByCar(String model, int series) {
        String HQL = "FROM User where car.model =: carModelParam and car.series =: carSeriesParam";
        return (User) sessionFactory.getCurrentSession().createQuery(HQL)
                .setParameter("carModelParam", model)
                .setParameter("carSeriesParam", series)
                .getSingleResult();
    }
}
